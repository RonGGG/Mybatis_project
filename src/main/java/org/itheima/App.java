package org.itheima;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.itheima.mapper.BrandMapper;
import org.itheima.mapper.UserMapper;
import org.itheima.pojo.Brand;
import org.itheima.pojo.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        // 1. 加载mybatis，配置SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);


        // 2. 获取SqlSession对象，执行SQL
        SqlSession sqlSession = sqlSessionFactory.openSession();


        // 3. 执行sql
//        List<User>users = sqlSession.selectList("test.selectAll");

        // 3. 执行sql（代理方法, 推荐这种方法）
        // 3.1 获取UserMapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.selectAll();

        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = brandMapper.selectAll();

        System.out.println(users);
        System.out.println(brands);
        // 4. 释放资源
        sqlSession.close();
    }
}
