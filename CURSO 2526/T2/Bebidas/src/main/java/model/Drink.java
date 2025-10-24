package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Drink {
    private String idDrink, strDrink, strTags, strCategory, strInstructionsES, strDrinkThumb, strIngredient1, strIngredient2;
}
