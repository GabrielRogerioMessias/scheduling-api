package com.messias.schedulingapi.services;

import com.messias.schedulingapi.repositories.UserRepository;
import com.messias.schedulingapi.security.jwt.JwtTokenProvider;
import com.messias.schedulingapi.vo.security.AccountCredentialsVO;
import com.messias.schedulingapi.vo.security.TokenVO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthServices {
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public AuthServices(JwtTokenProvider tokenProvider, AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    public ResponseEntity signin(AccountCredentialsVO data) {
        try {
            //extrai usuario e senha da AccountCredentialsVo
            var username = data.getUsername();
            var password = data.getPassword();
            //invocamos o authentication manager e tenta realizar o login passando username e password
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            //após, buscamos o usuário pelo username
            var user = userRepository.findByUsername(username);
            var tokenResponse = new TokenVO();
            //se a busca retornar um usuário, um acess token é criado, passando username e roles
            if (user != null) {
                tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
            } else {
                throw new UsernameNotFoundException("Username :" + username + " not found!");
            }
            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }


}