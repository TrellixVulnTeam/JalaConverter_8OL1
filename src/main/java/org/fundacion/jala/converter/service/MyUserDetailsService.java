/**
 * Copyright (c) 2021 Fundacion Jala.
 *
 * This software is the confidential and proprietary information of Fundacion Jala
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with Fundacion Jala
 */
package org.fundacion.jala.converter.service;

import org.fundacion.jala.converter.models.AuthenticationRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.fundacion.jala.converter.models.UserSQL.*;

@Service
public class MyUserDetailsService implements UserDetailsService {
    /**
     * Gets user details with the username
     * @param username to locate the user
     * @return User Details
     * @throws UsernameNotFoundException when user not found or has not granted authority
     */
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        List<org.fundacion.jala.converter.models.User> list = listUser();
        Boolean usernameExists = false;
        int userId = 0;
        for (org.fundacion.jala.converter.models.User user : list) {
             if (user.getName().equals(username)) {
                 usernameExists = true;
                 userId = user.getId();
                 break;
             }
        }
        if (usernameExists) {
            return new User(findUserById(userId).getName(),
                    findUserById(userId).getPassword(),
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    /**
     *
     * @param username
     * @param password
     * @return
     */
    public AuthenticationRequest save(final String username, final String password) {
        insertUserData(username, password, "");
        return new AuthenticationRequest(username, password);
    }
}
