package fr.fullstack.exam.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.fullstack.exam.api.entity.RestaurantEntity;
import fr.fullstack.exam.api.entity.TagsEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDto {
    @JsonProperty("id")
    Integer id;

    @JsonProperty("nom")
    String nom;

    @JsonProperty("moyenneEvaluations")
    Integer moyenneEvaluation;

    @JsonProperty("adresse")
    String adresse;

    @JsonProperty("evaluations")
    List<EvaluationDto> evaluations;

    @JsonProperty("evaluationFinale")
    EvaluationFinaleDto evaluationFinale;

    @JsonProperty("imageId")
    UUID imageId;

    @JsonProperty("tags")
    List<TagsEnum> tags;

    public static RestaurantDto entityToDto(RestaurantEntity entity) {
        if (entity.getEvaluationFinale() == null) {
            return RestaurantDto.builder()
                    .id(entity.getId())
                    .nom(entity.getNom())
                    .adresse(entity.getAdresse())
                    .moyenneEvaluation(entity.getMoyenneEvalutations())
                    .evaluations(entity.getEvaluations().stream().map(evaluationEntity -> EvaluationDto.entityToDto(evaluationEntity)).toList())
                    .evaluationFinale(null)
                    .imageId(entity.getImageId())
                    .tags(entity.getTags())
                    .build();
        }

        return RestaurantDto.builder()
                .id(entity.getId())
                .nom(entity.getNom())
                .adresse(entity.getAdresse())
                .moyenneEvaluation(entity.getMoyenneEvalutations())
                .evaluations(entity.getEvaluations().stream().map(evaluationEntity -> EvaluationDto.entityToDto(evaluationEntity)).toList())
                .evaluationFinale(EvaluationFinaleDto.entityToDto(entity.getEvaluationFinale()))
                .imageId(entity.getImageId())
                .tags(entity.getTags())
                .build();
    }
}
