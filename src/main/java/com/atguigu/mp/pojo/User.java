package com.atguigu.mp.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: csliuping@163.com
 * @date: 2020/12/12 17:27
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    //对应数据库中的主键（uuid、自增id、雪花算法、redis、zookeeper）
    @TableId(type= IdType.AUTO) //IdType.INPUT 需要自己设置id
    private Long id;
    private String name;
    private Integer age;
    private String email;

    @Version //乐观锁Version注解
    private Integer version;

    //字段添加填充内容
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableLogic //逻辑删除
    private Integer deleted;

}
