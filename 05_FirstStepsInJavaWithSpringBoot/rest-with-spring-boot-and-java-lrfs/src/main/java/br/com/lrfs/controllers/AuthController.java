package br.com.lrfs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lrfs.data.vo.v1.security.AccountCredentialsVO;
import br.com.lrfs.services.AuthServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Authentication endpoint")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthServices authServices;

    @SuppressWarnings("rawtypes")
    @Operation(summary = "Authenticate a user and return a token")
    @PostMapping(value = "/signin")
    public ResponseEntity signin(@RequestBody AccountCredentialsVO data){
        

        if(isDataInvalid(data)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client response");
        }

        var token = authServices.signin(data);
        if(token == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client response");
        }
        else{
            return ResponseEntity.ok(token);
        }        
    }
    
    @SuppressWarnings("rawtypes")
    @Operation(summary = "Refresh token for authenticated user and returns a token")
    @PutMapping(value = "/refresh/{username}")
    public ResponseEntity refreshToken(@PathVariable(value="username") String username, @RequestHeader("Authorization") String refreshToken){
        
        if(isDataInvalid(refreshToken, username)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client response");
        }

        var token = authServices.refreshToken(username, refreshToken);
        if(token == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client response");
        }
        else{
            return ResponseEntity.ok(token);
        }
        
    }

    public boolean isDataInvalid(AccountCredentialsVO data) {
        if (data == null || data.getUserName() == null || data.getPassword() == null) {
            return true;
        }
        return false;
    }

    public boolean isDataInvalid(String refreshToken, String username) {
        if (refreshToken == null || refreshToken.isEmpty() || refreshToken.isBlank() || username == null || username.isEmpty() || username.isBlank()) {
            return true;
        }
        return false;
    }
    
}
