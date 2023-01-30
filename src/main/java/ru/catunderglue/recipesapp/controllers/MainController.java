package ru.catunderglue.recipesapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String defaultPage() {
        return "Приложение запущено";
    }

    @GetMapping("/info")
    public String infoPage() {
        return "Имя: <b>Максим</b><br>" +
                "Название проекта: <b>Сайт с рецептами</b><br>" +
                "Дата начала создания проекта: <b>31.01.2023г.</b><br>" +
                "Описание: <b>Сайт, который будет показывать интересующий пользователя рецепт.</b><br>" +
                "ЯП: <b>Java (corretto-17.0.6)</b>. Фреймворк <b>Spring</b>";
    }
}
