package org.penguin.boot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.penguin.boot.model.User;

@Mapper
public interface UserMapper {

    User selectUserAndRoleById1(Long userId);

    User selectUserAndRoleById2(Long userId);

    User selectUserAndRoleById3(Long userId);

    User selectUserAndRoleById4(Long userId);

    User selectUserAndRoleById5(Long userId);

    User selectUserAndRoleById6(Long userId);

    int createUser(User user);

    int updateUser(User user);

}
