package me.alla.homework_therecipewebsite.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {
    private String name;
    private Integer number;
    private String measureUnit;

    @Override
    public String toString() {
        return name + " - " + number + " " + measureUnit;
    }
}
