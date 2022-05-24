package ru.yandex.practicum.catsgram.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class SimpleController {

    private static final Logger log = LoggerFactory.getLogger(SimpleController.class);
    @GetMapping
    public String homePage() {
        log.info("Запрос получен.");
        return "Котограм";
    }
}
