package org.penguin.boot.service;

import org.penguin.boot.model.Role;

public interface RoleService {

    Role selectRoleAndUsersById(Long roleId);

    Role selectRoleAndLazyUsersById(Long roleId);
    Role selectRoleAndLazyUsersChooseRoleEnabledById(Long roleId);
}
