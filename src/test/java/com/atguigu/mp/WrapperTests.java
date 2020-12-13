package com.atguigu.mp;

import com.atguigu.mp.mapper.UserMapper;
import com.atguigu.mp.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * @author: csliuping@163.com
 * @date: 2020/12/13 22:32
 * @description:
 */
@SpringBootTest
public class WrapperTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void test01() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .isNotNull("name")
                .isNotNull("email")
                .ge("age",12);

        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    void test02(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name","李四");
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    @Test
    void test03(){
        // 查询年龄在20~30岁之间的用户
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age",20,30); //区间
        Integer count = userMapper.selectCount(wrapper);
        System.out.println(count);
    }

    /**
     * 模糊查询
     */
    @Test
    void test04(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .notLike("name","B") // %t%
                .likeRight("email",5); // left与right代表百分号 加的位置 t%

        List<Map<String, Object>> list = userMapper.selectMaps(wrapper);
        list.forEach(System.out::println);
    }

    /**
     * inSQL
     */
    @Test
    void test05(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //id集合 在子查询中查出来
        wrapper.inSql("id","select id from user where id<3");

        List<Object> list = userMapper.selectObjs(wrapper);
        list.forEach(System.out::println);
    }

    /**
     * 排序
     */
    @Test
    void test06(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //根据id排序
        wrapper.orderByDesc("id");

        List<Object> list = userMapper.selectObjs(wrapper);
        list.forEach(System.out::println);
    }

}
