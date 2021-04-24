package org.penguin.boot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.penguin.boot.model.Role;

@Mapper
public interface RoleMapper {

    Role selectRoleAndUsersById(@Param("id") Long roleId);

    Role selectRoleAndLazyUsersById(@Param("id") Long roleId);

    Role selectRoleAndLazyUsersChooseRoleEnabledById(Long roleId);

}
