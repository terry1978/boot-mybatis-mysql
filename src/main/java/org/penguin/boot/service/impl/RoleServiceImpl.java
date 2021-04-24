package org.penguin.boot.service.impl;

import org.penguin.boot.mapper.RoleMapper;
import org.penguin.boot.model.Role;
import org.penguin.boot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public Role selectRoleAndUsersById(Long roleId) {
        return roleMapper.selectRoleAndUsersById(roleId);
    }

    @Override
    public Role selectRoleAndLazyUsersById(Long roleId) {
        return roleMapper.selectRoleAndLazyUsersById(roleId);
    }

    @Override
    public Role selectRoleAndLazyUsersChooseRoleEnabledById(Long roleId) {
        return roleMapper.selectRoleAndLazyUsersChooseRoleEnabledById(roleId);
    }
}
