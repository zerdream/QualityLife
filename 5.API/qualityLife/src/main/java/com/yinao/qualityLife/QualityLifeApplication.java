package com.yinao.qualityLife;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.yinao.qualityLife.bean.User;

@SpringBootApplication
@MapperScan("com.yinao.qualityLife.mapper")
public class QualityLifeApplication {

	public static void main(String[] args) {
		SpringApplication.run(QualityLifeApplication.class, args);
	}
	
}
