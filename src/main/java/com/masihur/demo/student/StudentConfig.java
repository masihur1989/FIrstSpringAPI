package com.masihur.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

/**
 * @author Masihur R. Maruf
 * @created 1/1/23 8:53 PM
 */
@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student tamanna = new Student("Tamanna", "tamanna@gmail.com", LocalDate.of(1998, Month.APRIL, 30));
            Student masihur = new Student("Masihur", "masihur@gmail.com", LocalDate.of(1989, Month.NOVEMBER, 24));

            repository.saveAll(List.of(tamanna, masihur));
        };
    }
}
