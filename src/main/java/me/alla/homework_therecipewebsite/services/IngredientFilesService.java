package me.alla.homework_therecipewebsite.services;

public interface IngredientFilesService {

    String readFromFile();

    void saveToFile(String json);
}
