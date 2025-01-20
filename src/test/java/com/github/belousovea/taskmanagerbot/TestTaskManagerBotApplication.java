package com.github.belousovea.taskmanagerbot;

import org.springframework.boot.SpringApplication;

public class TestTaskManagerBotApplication {

    public static void main(String[] args) {
        SpringApplication.from(TaskManagerBotApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
