package com.atguigu.mp.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author: csliuping@163.com
 * @date: 2020/12/13 17:50
 * @description:
 */
@MapperScan("com.atguigu.mp.mapper") //扫描mapper文件夹
@EnableTransactionManagement  //默认就是激活的
@Configuration //配置类
public class MyBatisPlusConfig {

    /**
     * 注册插件
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        // 乐观锁插件 -------------------------------------------------------------
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());

        // 分页插件 ---------------------------------------------------------------
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        // paginationInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        // paginationInterceptor.setLimit(500);
        // 开启 count 的 join 优化,只针对部分 left join
        // paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        interceptor.addInnerInterceptor(paginationInnerInterceptor);

        return interceptor;
    }

}
