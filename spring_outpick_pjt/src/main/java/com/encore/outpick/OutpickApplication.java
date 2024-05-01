package com.encore.outpick;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.encore.outpick.product.mapper")
public class OutpickApplication {
	public static void main(String[] args) {
		SpringApplication.run(OutpickApplication.class, args);
	}
}
