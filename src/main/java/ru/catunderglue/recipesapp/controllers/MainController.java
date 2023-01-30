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
        return """
                <h1>Информация</h1>
                <ul>
                <li>Имя: <b><a href="https://www.youtube.com/watch?v=dQw4w9WgXcQ" target="_blank">Максим</a></b></li>
                <li>Название проекта: <b>Сайт с рецептами</b></li>
                <li>Дата начала создания проекта: <b>31.01.2023г.</b></li>
                <li>Описание: <b>Сайт, который будет показывать интересующий пользователя рецепт.</b></li>
                <li>ЯП: <b>Java (corretto-17.0.6)</b>. Фреймворк <b>Spring</b></li>
                </ul>
                """;

    }
}
