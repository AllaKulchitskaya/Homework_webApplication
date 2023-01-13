package me.alla.homework_therecipewebsite.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Контролер для проверки запуска приложения")
public class FirstController {
    @GetMapping("/")
    public String runApplication() {
        return "Приложение запущено";
    }

    @GetMapping("/info")
    public String getProjectInformation() {
        return "Имя ученика: Кульчицкая Алла. Название проекта: the Recipe Website. Дата создания проекта: 28.12.2022. Описание проекта: при создании проекта использовались Spring Framework и сборщик Maven, проект написан на Java.";
    }
}
