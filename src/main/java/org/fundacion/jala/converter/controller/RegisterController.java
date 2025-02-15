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

import org.fundacion.jala.converter.controller.response.ErrorResponse;
import org.fundacion.jala.converter.controller.response.PaoPaoResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.fundacion.jala.converter.controller.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static org.fundacion.jala.converter.models.UserSQL.usernameExists;

/**
 * This class registers a user in the database.
 */
@RestController
public class RegisterController {
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    /**
     * Inserts users to the database.
     *
     * @param username is a String with the username.
     * @param password is a String with the password.
     * @return an entity response with the user.
     */
    @PostMapping("/register")
    @ApiOperation(value = "Inserts users to the database", notes = "Provide username and password to register")
    public ResponseEntity<PaoPaoResponse> insertUser(final @ApiParam(value = "Introduce the username", required = true)
                                            @RequestParam("username") String username,
                                        final @ApiParam(value = "Introduce the password", required = true)
                                        @RequestParam("password") String password) {
        if (usernameExists(username) || username.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(new ErrorResponse("400", "Invalid username"));
        }
        return ResponseEntity.ok(myUserDetailsService.save(username, password));
    }
}
