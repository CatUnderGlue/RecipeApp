package ru.catunderglue.recipesapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Главная", description = "Стартовая страница и страница с информацией")
public class MainController {

    @GetMapping()
    @Operation(
            summary = "Главная страница приложения"
    )
    public String defaultPage() {
        return """
                <p>Приложение запущено</p>
                <form action="/info"><input type="submit" value="Info page"></form>
                <form action="/recipe"><input type="submit" value="Recipes"></form>
                <form action="/ingredient"><input type="submit" value="Ingredients"></form>
                """;
    }

    @GetMapping("/info")
    @Operation(
            summary = "Страница с информацией"
    )
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
                <form action="/"><input type="submit" value="Main page"></form>
                """;

    }
}
