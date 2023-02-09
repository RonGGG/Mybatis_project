package org.itheima.mapper;

import org.itheima.pojo.User;

import java.util.List;

public interface UserMapper {

    List<User> selectAll();
    User selectById(int id);
}
