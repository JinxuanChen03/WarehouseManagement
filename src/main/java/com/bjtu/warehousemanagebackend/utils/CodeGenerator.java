package com.bjtu.warehousemanagebackend.utils;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create(
                        "jdbc:mysql://localhost:3306/warehouse"    // url
                        , "root"        // username
                        , "root"      // password
                )
                .globalConfig(builder -> {
                    builder.author("Jinxuan Chen") // 设置作者
//                          .enableSwagger() // 开启 swagger 模式
//                          .fileOverride() // 覆盖已生成文件 (已被弃用)
                            .outputDir("src\\main\\java\\"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.bjtu.warehousemanagebackend") // 设置父包名
                            .moduleName("") // 设置父包模块名 (可以为空串 "")
                            .controller("controller")
                            .mapper("mapper")
                            .service("service")
                            .serviceImpl("serviceImpl")
                            .entity("entity")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("customer", "provider", "goods", "administer", "storage") // 设置需要生成的表名
                            .addTablePrefix("") // 设置过滤表前缀
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .entityBuilder()
                            .enableLombok()	// 启用 Lombok 注解简化开发
                            .enableLombok()//启用lombok
                            .logicDeleteColumnName("deleted")//说明逻辑删除是哪个字段
                            .enableTableFieldAnnotation()
                            .controllerBuilder()
                            .formatFileName("%sController")
                            .enableRestStyle()//开启RestController
                            .mapperBuilder()
                            .superClass(BaseMapper.class)
                            .formatMapperFileName("%sMapper")
                            .enableMapperAnnotation()//开启@mapper
                            .formatXmlFileName("%sMapper");
                })
//              .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}


