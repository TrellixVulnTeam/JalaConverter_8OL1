/**
 * Copyright (c) 2021 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 *
 * @author Raymundo Guaraguara Sansusty
 */
package org.fundacion.jala.converter.controller;

import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fundacion.jala.converter.controller.response.ErrorResponse;
import org.fundacion.jala.converter.controller.response.PaoPaoResponse;
import org.fundacion.jala.converter.controller.response.SuccessAuthenticationResponse;
import org.fundacion.jala.converter.models.AuthenticationRequest;
import org.fundacion.jala.converter.controller.security.util.JwtUtil;
import org.fundacion.jala.converter.controller.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import static org.fundacion.jala.converter.models.UserSQL.editToken;

/**
 * This class makes the user authentication.
 */
@RestController
public class AuthController {
    private Logger authLogger = LogManager.getLogger();

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    /**
     * Creates an authentication response with the token.
     *
     * @param username is a String with the username.
     * @param password is a String with the password.
     * @return response entity with the token.
     */
    @PostMapping("/authenticate")
    @ApiOperation(value = "Authenticates a user into the API", notes = "Provide a username and a password to login")
    public ResponseEntity<PaoPaoResponse> createAuthenticationToken(final @RequestParam String username,
                                                       final @RequestParam String password) {
        final UserDetails userDetails;
        final String jwt;
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(username, password);
        try {
            authLogger.info("Start.");
            UsernamePasswordAuthenticationToken userPassAuthTok;
            Object getUsernameAuth = authenticationRequest.getUsername();
            Object getPassAuth = authenticationRequest.getPassword();
            userPassAuthTok = new UsernamePasswordAuthenticationToken(getUsernameAuth, getPassAuth);
            authenticationManager.authenticate(userPassAuthTok);
        } catch (BadCredentialsException e) {
            authLogger.error("Error. " + e.getLocalizedMessage());
            return ResponseEntity.badRequest().body(new ErrorResponse("400", e.getMessage()));
        }
        userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        jwt = jwtTokenUtil.generateToken(userDetails);
        editToken(username, jwt);
        return ResponseEntity.ok(new SuccessAuthenticationResponse("200", jwt));
    }
}
