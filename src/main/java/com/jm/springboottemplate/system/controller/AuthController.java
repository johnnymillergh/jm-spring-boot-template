package com.jm.springboottemplate.system.controller;

import com.jm.springboottemplate.system.constant.UniversalStatus;
import com.jm.springboottemplate.system.domain.JwtResponse;
import com.jm.springboottemplate.system.domain.payload.Login;
import com.jm.springboottemplate.system.domain.payload.Register;
import com.jm.springboottemplate.system.domain.persistence.User;
import com.jm.springboottemplate.system.exception.SecurityException;
import com.jm.springboottemplate.system.response.ResponseBodyBean;
import com.jm.springboottemplate.system.service.AuthService;
import com.jm.springboottemplate.system.util.JwtUtil;
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
 * @author: Johnny Miller (鍾俊)
 * @email: johnnysviva@outlook.com
 * @date: 2019-03-23
 * @time: 14:54
 **/
@Slf4j
@RestController
@RequestMapping("/auth")
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
    public ResponseBodyBean checkUsernameUniqueness(String username) {
        if (StringUtils.isBlank(username)) {
            return ResponseBodyBean.ofStatus(UniversalStatus.PARAM_INVALID);
        }
        if (authService.checkUsernameUniqueness(username)) {
            return ResponseBodyBean.ofSuccess("username available");
        }
        return ResponseBodyBean.ofFailure("username not available");
    }

    @GetMapping("/checkEmailUniqueness")
    public ResponseBodyBean checkEmailUniqueness(String email) {
        if (StringUtils.isBlank(email)) {
            return ResponseBodyBean.ofStatus(UniversalStatus.PARAM_INVALID);
        }
        if (authService.checkEmailUniqueness(email)) {
            return ResponseBodyBean.ofSuccess("email available");
        }
        return ResponseBodyBean.ofFailure("email not available");
    }

    @PostMapping("/register")
    public ResponseBodyBean register(@Valid @RequestBody Register register) {
        User user = new User();
        user.setUsername(register.getUsername());
        user.setEmail(register.getEmail());
        user.setPassword(encoder.encode(register.getPassword()));
        authService.register(user);
        if (user.getId() > 0) {
            return ResponseBodyBean.ofSuccess("Registered successfully.");
        }
        return ResponseBodyBean.ofFailure("Registered failure.");
    }

    @PostMapping("/login")
    public ResponseBodyBean login(@Valid @RequestBody Login login) {
        Authentication authentication =
                authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(login.getUsernameOrEmailOrPhone(),
                                                                              login.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtil.createJWT(authentication, login.getRememberMe());
        return ResponseBodyBean.ofSuccess(new JwtResponse(jwt));
    }

    @PostMapping("/logout")
    public ResponseBodyBean logout(HttpServletRequest request) {
        try {
            jwtUtil.invalidateJWT(request);
        } catch (SecurityException e) {
            throw new SecurityException(UniversalStatus.UNAUTHORIZED);
        }
        return ResponseBodyBean.setResponse(null, "Logout success.", UniversalStatus.LOGOUT.getCode());
    }
}
