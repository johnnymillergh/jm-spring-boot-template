package com.jmframework.boot.jmspringbootstarter.controller;

import com.jmframework.boot.jmspringbootstarter.exception.SecurityException;
import com.jmframework.boot.jmspringbootstarter.response.ResponseBodyBean;
import com.jmframework.boot.jmspringbootstarter.service.AuthService;
import com.jmframework.boot.jmspringbootstarter.util.JwtUtil;
import com.jmframework.boot.jmspringbootstarterdomain.auth.payload.LoginPLO;
import com.jmframework.boot.jmspringbootstarterdomain.auth.payload.RegisterPLO;
import com.jmframework.boot.jmspringbootstarterdomain.auth.response.JwtRO;
import com.jmframework.boot.jmspringbootstarterdomain.common.constant.HttpStatus;
import com.jmframework.boot.jmspringbootstarterdomain.user.UserPrincipal;
import com.jmframework.boot.jmspringbootstarterdomain.user.persistence.UserPO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.plexus.util.StringUtils;
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
 * <h1>AuthController</h1>
 * <p>Authentication and authorization controller for user signing up, login and logout.</p>
 *
 * @author Johnny Miller (鍾俊), email: johnnysviva@outlook.com
 * @date 2019-03-23 14:54
 **/
@Slf4j
@RestController
@RequestMapping("/auth")
@Api(tags = {"/auth"})
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

    @GetMapping("/check-username-uniqueness")
    @ApiOperation(value = "/check-username-uniqueness", notes = "Check username uniqueness")
    public ResponseBodyBean checkUsernameUniqueness(String username) {
        if (StringUtils.isBlank(username)) {
            return ResponseBodyBean.setResponse(HttpStatus.INVALID_PARAM.getCode(),
                                                HttpStatus.INVALID_PARAM.getMessage(),
                                                null);
        }
        if (authService.checkUsernameUniqueness(username)) {
            return ResponseBodyBean.ofSuccess("username available");
        }
        return ResponseBodyBean.ofFailure("username not available");
    }

    @GetMapping("/check-email-uniqueness")
    @ApiOperation(value = "/check-email-uniqueness", notes = "Check email uniqueness")
    public ResponseBodyBean checkEmailUniqueness(String email) {
        if (StringUtils.isBlank(email)) {
            return ResponseBodyBean.setResponse(HttpStatus.INVALID_PARAM.getCode(),
                                                HttpStatus.INVALID_PARAM.getMessage(),
                                                null);
        }
        if (authService.checkEmailUniqueness(email)) {
            return ResponseBodyBean.ofSuccess("email available");
        }
        return ResponseBodyBean.ofFailure("email not available");
    }

    @PostMapping("/register")
    @ApiOperation(value = "/register", notes = "Register (create an account)")
    public ResponseBodyBean register(@Valid @RequestBody RegisterPLO plo) {
        UserPO po = new UserPO();
        po.setUsername(plo.getUsername());
        po.setEmail(plo.getEmail());
        po.setPassword(encoder.encode(plo.getPassword()));
        authService.register(po);
        if (po.getId() > 0) {
            return ResponseBodyBean.ofSuccess("Registered successfully.");
        }
        return ResponseBodyBean.ofFailure("Registered failure.");
    }

    @GetMapping("/validate-username/{username}")
    @ApiOperation(value = "/validate-username/:username", notes = "Validate username for user login. " +
            "If the username do not exist, response failure")
    public ResponseBodyBean validateUsername(@PathVariable String username) {
        boolean validateResult = authService.validateUsername(username);
        return validateResult ? ResponseBodyBean.ofSuccess("Valid username.") :
                ResponseBodyBean.ofFailure("Invalid username.");
    }

    @PostMapping("/login")
    @ApiOperation(value = "/login", notes = "Login (Sign in)")
    public ResponseBodyBean<JwtRO> login(@Valid @RequestBody LoginPLO plo) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(plo.getUsernameOrEmailOrPhone(), plo.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtil.createJwt(authentication, plo.getRememberMe());
        JwtRO ro = new JwtRO(jwt);
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        ro.setFullName(userPrincipal.getFullName());
        ro.setUsername(userPrincipal.getUsername());
        return ResponseBodyBean.ofSuccess(ro);
    }

    @PostMapping("/logout")
    @ApiOperation(value = "/logout", notes = "Logout (Sign out)")
    public ResponseBodyBean logout(HttpServletRequest request) {
        try {
            jwtUtil.invalidateJwt(request);
        } catch (SecurityException e) {
            throw new SecurityException(HttpStatus.UNAUTHORIZED);
        }
        return ResponseBodyBean.ofSuccess("Logout success.");
    }
}
