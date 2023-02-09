package org.itheima.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.itheima.pojo.Brand;

import java.util.List;
import java.util.Map;

public interface BrandMapper {
    List<Brand> selectAll();
    Brand selectById(int id);

//    散装参数
//    List<Brand> selectByCondition(@Param("status") int status, @Param("companyName") String companyName, @Param("brandName") String brandName);

    // 封装对象
//    List<Brand> selectByCondition(Brand brand);

    // 封装map
    List<Brand> selectByCondition(Map map);

    List<Brand> selectBySingleCondition(Map map);

    void add(Brand brand);

    int update(Brand brand);

    void delById(Brand brand);

    void delByIds(int[] Ids);

    @Select("select * from tb_brand where id = #{id};")
    Brand selectByIdAfter(int id);
}
