package com.jm.springboottemplate.system.controller;

import com.jm.springboottemplate.system.constant.UniversalStatus;
import com.jm.springboottemplate.system.domain.JwtResponse;
import com.jm.springboottemplate.system.domain.payload.Login;
import com.jm.springboottemplate.system.domain.payload.Register;
import com.jm.springboottemplate.system.domain.persistence.User;
import com.jm.springboottemplate.system.exception.SecurityException;
import com.jm.springboottemplate.system.mapper.UserMapper;
import com.jm.springboottemplate.system.response.ResponseBodyBean;
import com.jm.springboottemplate.system.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/register")
    public ResponseBodyBean register(@Valid @RequestBody Register register) {
        User user = new User();
        user.setUsername(register.getUsername());
        user.setPassword(encoder.encode(register.getPassword()));
        userMapper.register(user);
        if (user.getId() > 0) {
            return ResponseBodyBean.ofSuccess("Registered successfully.");
        }
        return ResponseBodyBean.ofFailure("Registered failure.");
    }

    @PostMapping("/login")
    public ResponseBodyBean login(@Valid @RequestBody Login login) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsernameOrEmailOrPhone(),
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
