package ru.anatomica.cookstarter.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.anatomica.cookstarter.beans.JwtTokenUtil;
import ru.anatomica.cookstarter.entities.dtos.JwtRequest;
import ru.anatomica.cookstarter.entities.dtos.JwtResponse;
import ru.anatomica.cookstarter.exceptions.MarketError;
import ru.anatomica.cookstarter.services.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Api("Set of endpoints for CRUD operations for Users")
public class AuthController {
    private final UsersService usersService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    public AuthController(UsersService usersService,
                          JwtTokenUtil jwtTokenUtil,
                          AuthenticationManager authenticationManager) {
        this.usersService = usersService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    @ApiOperation("Operation with Users")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            return new ResponseEntity<>(new MarketError(HttpStatus.UNAUTHORIZED.value(), "Incorrect username or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = usersService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
