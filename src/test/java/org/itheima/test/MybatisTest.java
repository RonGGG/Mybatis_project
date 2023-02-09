package org.itheima.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.itheima.mapper.BrandMapper;
import org.itheima.mapper.UserMapper;
import org.itheima.pojo.Brand;
import org.itheima.pojo.User;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisTest {

    @Test
    public void testSelectAll() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);


        // 2. 获取SqlSession对象，执行SQL
        SqlSession sqlSession = sqlSessionFactory.openSession();


        // 3. 执行sql
//        List<User>users = sqlSession.selectList("test.selectAll");

        // 3. 执行sql（代理方法, 推荐这种方法）
        // 3.1 获取UserMapper代理对象
//        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//        List<User> users = userMapper.selectAll();

        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = brandMapper.selectAll();

//        System.out.println(users);
        System.out.println(brands);
        // 4. 释放资源
        sqlSession.close();
    }

    @Test
    public void testSelectById() throws IOException {
        int id = 1;

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取SqlSession对象，执行SQL
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 执行sql（代理方法, 推荐这种方法）
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
//        Brand brand = brandMapper.selectById(id);
        Brand brand = brandMapper.selectByIdAfter(id);

        System.out.println(brand);
        // 4. 释放资源
        sqlSession.close();
    }
    @Test
    public void testSelectByCondition() throws IOException {
        int status = 1;
        String brandName = "";
        String companyName = "华为";

        // 散装参数传参法
        brandName = "%"+brandName+"%";
        companyName = "%"+companyName+"%";

        // 封装对象传参法
//        Brand brand = new Brand();
//        brand.setBrandName(brandName);
//        brand.setCompanyName(companyName);
//        brand.setStatus(status);

        // Map传参法
        Map map = new HashMap();
//        map.put("status",status);
        map.put("companyName",companyName);
//        map.put("brandName", brandName);

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取SqlSession对象，执行SQL
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 执行sql（代理方法, 推荐这种方法）
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // 散装法
//        List<Brand> brands = brandMapper.selectByCondition(status,companyName,brandName);

        // 封装法
//        List<Brand> brands = brandMapper.selectByCondition(brand);

        // map法
        List<Brand> brands = brandMapper.selectByCondition(map);

        System.out.println(brands);
        // 4. 释放资源
        sqlSession.close();
    }
    @Test
    public void testSelectBySingleCondition() throws IOException {
        int status = 1;
        String brandName = "";
        String companyName = "华为";

        // 散装参数传参法
        brandName = "%"+brandName+"%";
        companyName = "%"+companyName+"%";

        // Map传参法
        Map map = new HashMap();
//        map.put("status",status);
//        map.put("companyName",companyName);
//        map.put("brandName", brandName);

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取SqlSession对象，执行SQL
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 执行sql（代理方法, 推荐这种方法）
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // map法
        List<Brand> brands = brandMapper.selectBySingleCondition(map);

        System.out.println(brands);
        // 4. 释放资源
        sqlSession.close();
    }
    @Test
    public void testSelectByAdd() throws IOException {
        Brand brand = new Brand();
        brand.setBrandName("iPhone");
        brand.setDescription("Think different");
        brand.setCompanyName("Apple");
        brand.setStatus(1);
        brand.setOrdered(100);

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取SqlSession对象，执行SQL
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        // 3. 执行sql（代理方法, 推荐这种方法）
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // map法
        brandMapper.add(brand);
//        sqlSession.commit();
        // 4. 释放资源
        sqlSession.close();
    }
    @Test
    public void testSelectByUpdate() throws IOException {
        Brand brand = new Brand();
//        brand.setBrandName("iPhone");
//        brand.setDescription("Think different");
        brand.setCompanyName("Apple!!");
//        brand.setStatus(1);
//        brand.setOrdered(100);
        brand.setId(5);

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取SqlSession对象，执行SQL
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        // 3. 执行sql（代理方法, 推荐这种方法）
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // map法
        brandMapper.update(brand);
//        sqlSession.commit();
        // 4. 释放资源
        sqlSession.close();
    }
    @Test
    public void testSelectByDelete() throws IOException {
        Brand brand = new Brand();
        brand.setId(7);

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取SqlSession对象，执行SQL
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        // 3. 执行sql（代理方法, 推荐这种方法）
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // map法
        brandMapper.delById(brand);
//        sqlSession.commit();
        // 4. 释放资源
        sqlSession.close();
    }
    @Test
    public void testSelectByDeleteByIds() throws IOException {
        int[] ids = {8};

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 2. 获取SqlSession对象，执行SQL
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        // 3. 执行sql（代理方法, 推荐这种方法）
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // map法
        brandMapper.delByIds(ids);
//        sqlSession.commit();
        // 4. 释放资源
        sqlSession.close();
    }
}
