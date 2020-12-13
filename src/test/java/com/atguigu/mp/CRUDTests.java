package com.atguigu.mp;

import com.atguigu.mp.mapper.UserMapper;
import com.atguigu.mp.pojo.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class CRUDTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testSelectList() {
        System.out.println(new Date());
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setName("张三");
        user.setAge(3);
        user.setEmail("544339745@qq.com");

        int result = userMapper.insert(user);
        System.out.println(result);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(1337960044192444421L);
        user.setName("李四");
        user.setAge(18);
        int result = userMapper.updateById(user);
        System.out.println(result);
    }

    /**
     * 测试乐观锁
     */
    @Test
    public void testOptimisticLocker(){
        //1.查询用户信息
        User user = userMapper.selectById(1337960044192444421L);
        //2.修改用户信息
        user.setName("王五");
        //3.执行修改操作
        userMapper.updateById(user);
    }

    /**
     * 测试乐观锁
     */
    @Test
    public void testOptimisticLocker2(){
        //模拟线程1
        User user = userMapper.selectById(1337960044192444421L);
        user.setName("王五");

        //模拟线程2
        User user2 = userMapper.selectById(1337960044192444421L);
        user2.setName("王五2");

        //3.执行修改操作
        userMapper.updateById(user);
        userMapper.updateById(user2);
    }

    /**
     * 根据id查询
     */
    @Test
    public void testSelectById(){
        User user = userMapper.selectById(1337960044192444421L);
        System.out.println(user);
    }

    /**
     * 批量id进行查询
     */
    @Test
    public void testSelectBatchIds(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1337960044192444420L, 1337960044192444421L, 1337960044192444422L));
        users.forEach(System.out::println);
    }

    /**
     * 按条件查询之使用map设置条件操作
     */
    @Test
    public void testSelectByMap(){
        HashMap<String, Object> map = new HashMap<>();
        //自定义查询条件
        map.put("name","张三");
        map.put("age",3);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    /**
     * 分页查询
     */
    @Test
    public void testSelectPage(){
        //参数一：当前页   参数二：页面大小
        Page<User> page = new Page<>(1,5);
        userMapper.selectPage(page,null);
        page.getRecords().forEach(System.out::println);
    }

    /**
     * 根据id删除
     */
    @Test
    public void testDeleteById(){
        userMapper.deleteById(4L);
    }

    /**
     * 根据id进行批量删除
     */
    @Test
    public void testDeleteBatchId(){
        userMapper.deleteBatchIds(Arrays.asList(1L,2L,3L));
    }

    /**
     * 通过使用map设置条件进行删除
     */
    @Test
    public void testDeleteByMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("name","张三");
        userMapper.deleteByMap(map);
    }

}
