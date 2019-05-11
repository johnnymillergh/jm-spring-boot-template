package com.jmframework.boot.jmspringbootstarter.controller;

import com.jmframework.boot.jmspringbootstarter.constant.UniversalStatus;
import com.jmframework.boot.jmspringbootstarter.domain.payload.LoginPLO;
import com.jmframework.boot.jmspringbootstarter.domain.payload.RegisterPLO;
import com.jmframework.boot.jmspringbootstarter.domain.persistence.UserPO;
import com.jmframework.boot.jmspringbootstarter.domain.response.JwtRO;
import com.jmframework.boot.jmspringbootstarter.exception.SecurityException;
import com.jmframework.boot.jmspringbootstarter.response.ResponseBodyBean;
import com.jmframework.boot.jmspringbootstarter.service.AuthService;
import com.jmframework.boot.jmspringbootstarter.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Description: Authentication and authorization controller for user signing up, login and logout.
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-23 14:54
 **/
@Slf4j
@RestController
@RequestMapping("/auth")
@Api(value = "Auth Controller", tags = {"auth"})
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder encoder;
    private final AuthService authService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil,
                          BCryptPasswordEncoder encoder,
                          AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.encoder = encoder;
        this.authService = authService;
    }

    @GetMapping("/checkUsernameUniqueness")
    @ApiOperation(value = "Check username uniqueness", notes = "Check username uniqueness")
    public ResponseBodyBean checkUsernameUniqueness(String username) {
        if (StringUtils.isBlank(username)) {
            return ResponseBodyBean.setResponse(UniversalStatus.PARAM_INVALID.getCode(),
                                                UniversalStatus.PARAM_INVALID.getMessage(),
                                                null);
        }
        if (authService.checkUsernameUniqueness(username)) {
            return ResponseBodyBean.ofSuccess("username available");
        }
        return ResponseBodyBean.ofFailure("username not available");
    }

    @GetMapping("/checkEmailUniqueness")
    @ApiOperation(value = "Check email uniqueness", notes = "Check email uniqueness")
    public ResponseBodyBean checkEmailUniqueness(String email) {
        if (StringUtils.isBlank(email)) {
            return ResponseBodyBean.setResponse(UniversalStatus.PARAM_INVALID.getCode(),
                                                UniversalStatus.PARAM_INVALID.getMessage(),
                                                null);
        }
        if (authService.checkEmailUniqueness(email)) {
            return ResponseBodyBean.ofSuccess("email available");
        }
        return ResponseBodyBean.ofFailure("email not available");
    }

    @PostMapping("/register")
    @ApiOperation(value = "Register", notes = "Register (create an account)")
    public ResponseBodyBean register(@Valid @RequestBody RegisterPLO registerPLO) {
        UserPO userPO = new UserPO();
        userPO.setUsername(registerPLO.getUsername());
        userPO.setEmail(registerPLO.getEmail());
        userPO.setPassword(encoder.encode(registerPLO.getPassword()));
        authService.register(userPO);
        if (userPO.getId() > 0) {
            return ResponseBodyBean.ofSuccess("Registered successfully.");
        }
        return ResponseBodyBean.ofFailure("Registered failure.");
    }

    @GetMapping("/validateUsername/{username}")
    @ApiOperation(value = "Validate username", notes = "Validate username for user login. " +
            "If the username do not exist, response failure")
    public ResponseBodyBean validateUsername(@PathVariable String username) {
        boolean validateResult = authService.validateUsername(username);
        return validateResult ? ResponseBodyBean.ofSuccess("Valid username.") :
                ResponseBodyBean.ofFailure("Invalid username.");
    }

    @PostMapping("/login")
    @ApiOperation(value = "Login", notes = "Login (Sign in)")
    public ResponseBodyBean login(@Valid @RequestBody LoginPLO loginPLO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginPLO.getUsernameOrEmailOrPhone(), loginPLO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtil.createJWT(authentication, loginPLO.getRememberMe());
        return ResponseBodyBean.ofSuccess(new JwtRO(jwt));
    }

    @PostMapping("/logout")
    @ApiOperation(value = "Logout", notes = "Logout (Sign out)")
    public ResponseBodyBean logout(HttpServletRequest request) {
        try {
            jwtUtil.invalidateJWT(request);
        } catch (SecurityException e) {
            throw new SecurityException(UniversalStatus.UNAUTHORIZED);
        }
        return ResponseBodyBean.ofSuccess("Logout success.");
    }
}
