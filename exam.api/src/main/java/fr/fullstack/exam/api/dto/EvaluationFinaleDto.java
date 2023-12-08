package fr.fullstack.exam.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.fullstack.exam.api.entity.EvaluationFinaleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationFinaleDto {
    @JsonProperty("id")
    Integer id;

    @JsonProperty("nomDecideur")
    String nomDecideur;

    @JsonProperty("note")
    Integer note;

    @JsonProperty("description")
    String description;

    public static EvaluationFinaleDto entityToDto(EvaluationFinaleEntity entity) {
        return EvaluationFinaleDto.builder()
                .id(entity.getId())
                .nomDecideur(entity.getNomDecideur())
                .note(entity.getNote())
                .description(entity.getDescription())
                .build();
    }
}
