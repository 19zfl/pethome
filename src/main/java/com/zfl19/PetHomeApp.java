package com.zfl19;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.zfl19.*.mapper")
public class PetHomeApp {

    public static void main(String[] args) {

        SpringApplication.run(PetHomeApp.class);

    }

}
