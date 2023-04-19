package com.example.fxdev.controller;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.fxdev.init.SQL.NOW;
import static com.example.fxdev.init.SQL.SELECT_USER;

@RestController
@AllArgsConstructor
public class TestController {
    final Environment environment;
    final JdbcTemplate jdbcTemplate;

    @RequestMapping("/test")
    public void test() {
        System.out.println("test");
        System.out.println(environment.getProperty("app.appName"));
    }

    @RequestMapping("/jdbc")
    public Object testJdbc() {
        return jdbcTemplate.queryForList(SELECT_USER);
    }

    @RequestMapping("/now")
    public Object testNow() {
        return jdbcTemplate.queryForList(NOW);
    }
}
