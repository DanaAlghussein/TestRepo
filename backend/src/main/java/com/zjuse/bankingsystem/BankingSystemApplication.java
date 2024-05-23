package com.zjuse.bankingsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.baomidou.mybatisplus.samples.quickstart.mapper")
@MapperScan("com.zjuse.bankingsystem.mapper.*")
public class  BankingSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(BankingSystemApplication.class);
	}

}
