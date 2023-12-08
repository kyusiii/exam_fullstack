package fr.fullstack.exam.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.fullstack.exam.api.entity.TagsEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRestaurantDto {
    @JsonProperty(value = "nom")
    String nom;

    @JsonProperty(value = "adresse")
    String adresse;

    @JsonProperty("tags")
    List<TagsEnum> tags;
}
