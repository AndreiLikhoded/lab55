package com.example.lab_55.util;

import com.example.lab_55.dao.TaskDao;
import com.example.lab_55.dao.UserDao;
import com.example.lab_55.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Configuration
public class DataBaseManipulation {
    @Bean
    CommandLineRunner run(UserDao userDao, TaskDao taskDao) {
        return (args) -> {
            userDao.createTable();
            taskDao.createTable();

            userDao.deleteAll();
            taskDao.deleteAll();

            List<User> users = Stream.generate(User::random)
                    .limit(10)
                    .collect(toList());
            userDao.saveAll(users);

        };
    }
}
