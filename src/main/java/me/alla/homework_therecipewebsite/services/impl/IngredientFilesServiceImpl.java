package me.alla.homework_therecipewebsite.services.impl;

import me.alla.homework_therecipewebsite.services.IngredientFilesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class IngredientFilesServiceImpl implements IngredientFilesService {
    @Value("${path.to.ingredients.file}")
    private String ingredientsFilePath;
    @Value("${name.of.ingredients.file}")
    private String ingredientsFileName;

    @Override
    public void saveToFile(String json) {
        try {
            cleanFile();
            Files.writeString(Path.of(ingredientsFilePath, ingredientsFileName), json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String readFromFile() {
        try {
            return Files.readString(Path.of(ingredientsFilePath, ingredientsFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void cleanFile() {
        try {
            Path path = Path.of(ingredientsFilePath, ingredientsFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public File getIngredientsFile() {
        return new File(ingredientsFilePath + "/" + ingredientsFileName);
    }
}
