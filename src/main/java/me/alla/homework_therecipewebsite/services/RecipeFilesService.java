package me.alla.homework_therecipewebsite.services;

import java.io.File;
import java.nio.file.Path;

public interface RecipeFilesService {

    String readFromFile();

    void saveToFile(String json);

    void cleanFile();

    File getRecipesFile();
}
