package com.course.practice.controller;

import com.course.practice.security.JwtRequest;
import com.course.practice.security.JwtResponse;
import com.course.practice.security.config.JwtUtil;
import com.course.practice.serviceImpl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            autenticar(jwtRequest.getUsername(), jwtRequest.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Usuario no encontrado here");
        }

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void autenticar(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new  UsernamePasswordAuthenticationToken(username, password));
        }catch (DisabledException e){
            throw new Exception("Usuario deshabilitado " + e.getMessage());
        }catch (BadCredentialsException e){
            throw new Exception("Credenciales inválidas " + e.getMessage());
        }
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Map<String, Object>> Exceptio(Exception ex){
        Map<String, Object> ms = new HashMap<>();
        ms.put("time", new Date());
        ms.put("cause", HttpStatus.INTERNAL_SERVER_ERROR);
        ms.put("msg", "error");
        ms.put("status", 500);
        return new ResponseEntity<>(ms, HttpStatus.NOT_FOUND);
    }
}
