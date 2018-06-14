package yinao.qualityLife;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = {"yinao.qualityLife.dao"})
public class QualityLifeApplication {

	public static void main(String[] args) {
		SpringApplication.run(QualityLifeApplication.class, args);
	}
}
