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
    public User selectUserAndOrganizationUsingResultTypeByUserId(Long userId) {
        return userMapper.selectUserAndOrganizationUsingResultTypeByUserId(userId);
    }

    @Override
    public User selectUserAndRoleUsingResultMapByUserId(Long userId) {
        return userMapper.selectUserAndRoleUsingResultMapByUserId(userId);
    }

    @Override
    public User selectUserAndLazyOrganizationByUserId(Long userId) {
        return userMapper.selectUserAndLazyOrganizationByUserId(userId);
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
