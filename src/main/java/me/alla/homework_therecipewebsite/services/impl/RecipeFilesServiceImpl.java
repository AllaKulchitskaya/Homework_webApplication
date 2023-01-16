package me.alla.homework_therecipewebsite.services.impl;

import me.alla.homework_therecipewebsite.services.RecipeFilesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class RecipeFilesServiceImpl implements RecipeFilesService {

    @Value("${path.to.recipes.file}")
    private String recipesFilePath;
    @Value("${name.of.recipes.file}")
    private String recipesFileName;

    @Override
    public void saveToFile(String json) {
        try {
            cleanFile();
            Files.writeString(Path.of(recipesFilePath, recipesFileName), json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String readFromFile() {
        try {
            return Files.readString(Path.of(recipesFilePath, recipesFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cleanFile() {
        try {
            Path path = Path.of(recipesFilePath, recipesFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public File getRecipesFile() {
        return new File(recipesFilePath + "/" + recipesFileName);
    }
}
