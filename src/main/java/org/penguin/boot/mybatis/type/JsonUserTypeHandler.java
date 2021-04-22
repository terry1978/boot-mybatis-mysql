package org.penguin.boot.mybatis.type;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.penguin.boot.BootApplication;
import org.penguin.boot.model.User;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@MappedJdbcTypes(JdbcType.VARCHAR)
public class JsonUserTypeHandler extends BaseTypeHandler<User> {

    private User parse(String json) {
        try {
            if (json == null || json.length() == 0) {
                return null;
            }
            // 此处全局引用Spring Boot中的ObjectMapper实例，避免如Date类等的格式问题
            return BootApplication.getObjectMapper().readValue(json, User.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String toJsonString(User user) {
        try {
            return user == null ? null : BootApplication.getObjectMapper().writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int columnIndex, User user, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(columnIndex, toJsonString(user));
    }

    @Override
    public User getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        String value = resultSet.getString(columnName);
        System.out.println("Terry -getNullableResult1> " + columnName + " <> " + value);
        return value == null ? null : parse(value);
    }

    @Override
    public User getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        String value = resultSet.getString(columnIndex);
        System.out.println("Terry -getNullableResult2> " + columnIndex + " <> " + value);
        return value == null ? null : parse(value);
    }

    @Override
    public User getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        String value = callableStatement.getString(columnIndex);
        System.out.println("Terry -> getNullableResult3" + columnIndex + " <> " + value);
        return value == null ? null : parse(value);
    }
}
