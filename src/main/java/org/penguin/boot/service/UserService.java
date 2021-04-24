package org.penguin.boot.service;

import org.penguin.boot.model.User;

public interface UserService {

    User selectUserAndOrganizationUsingResultTypeByUserId(Long userId);

    User selectUserAndRoleUsingResultMapByUserId(Long userId);

    User selectUserAndLazyOrganizationByUserId(Long userId);

    User createUser(User user);

    int updateUser(User user);
}
