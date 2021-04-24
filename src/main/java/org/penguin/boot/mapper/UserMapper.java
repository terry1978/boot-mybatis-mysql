package org.penguin.boot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.penguin.boot.model.User;

@Mapper
public interface UserMapper {

    User selectUserAndOrganizationUsingResultTypeByUserId(Long userId);

    User selectUserAndRoleUsingResultMapByUserId(Long userId);

    User selectUserAndLazyOrganizationByUserId(Long userId);

    int createUser(User user);

    int updateUser(User user);

}
