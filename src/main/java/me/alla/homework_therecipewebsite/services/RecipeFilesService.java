package me.alla.homework_therecipewebsite.services;

public interface RecipeFilesService {

    String readFromFile();

    void saveToFile(String json);
}
