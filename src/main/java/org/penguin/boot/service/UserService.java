package org.penguin.boot.service;

import org.penguin.boot.model.User;

public interface UserService {

    User selectUserAndRoleById1(Long userId);

    User selectUserAndRoleById2(Long userId);

    User selectUserAndRoleById3(Long userId);

    User selectUserAndRoleById4(Long userId);

    User selectUserAndRoleById5(Long userId);

    User selectUserAndRoleById6(Long userId);

    User createUser(User user);

}
