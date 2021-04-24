package org.penguin.boot.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.penguin.boot.mapper.UserMapper;
import org.penguin.boot.model.Organization;
import org.penguin.boot.model.User;
import org.penguin.boot.service.OrganizationService;
import org.penguin.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    OrganizationService organizationService;

    @Override
    public User selectUserAndOrganizationUsingResultTypeById(Long userId) {
        return userMapper.selectUserAndOrganizationUsingResultTypeById(userId);
    }

    @Override
    public User selectUserAndRoleUsingResultMapById(Long userId) {
        return userMapper.selectUserAndOrganizationUsingResultMapById(userId);
    }

    @Override
    public User selectUserAndRoleById3(Long userId) {
        return userMapper.selectUserAndRoleById3(userId);
    }

    @Override
    public User selectUserAndRoleById4(Long userId) {
        return userMapper.selectUserAndRoleById4(userId);
    }

    @Override
    public User selectUserAndRoleById5(Long userId) {
        return userMapper.selectUserAndRoleById5(userId);
    }

    @Override
    public User selectUserAndRoleById6(Long userId) {
        return userMapper.selectUserAndRoleById6(userId);
    }

    @Override
    public User createUser(User user) {
        Organization organization = user.getOrganization() == null || user.getOrganization().getId() == null ? null : organizationService.getOrganizationsById(user.getOrganization().getId());
        user.setOrganization(organization);
        userMapper.createUser(user);
        return user;
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }
}
