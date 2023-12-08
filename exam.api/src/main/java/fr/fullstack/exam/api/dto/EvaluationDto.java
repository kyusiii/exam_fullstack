package fr.fullstack.exam.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.fullstack.exam.api.entity.EvaluationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationDto {
    @JsonProperty("id")
    Integer id;

    @JsonProperty("nomEvaluateur")
    String nomEvaluateur;

    @JsonProperty("commentaire")
    String commentaire;

    @JsonProperty("note")
    Integer note;

    @JsonProperty("dateCreation")
    Date dateCreation;

    @JsonProperty("dateUpdate")
    Date dateUpdate;

    @JsonProperty("photosIds")
    List<UUID> photosIds;

    public static EvaluationDto entityToDto(EvaluationEntity entity) {
        return EvaluationDto.builder()
                .id(entity.getId())
                .nomEvaluateur(entity.getNomEvaluateur())
                .commentaire(entity.getCommentaire())
                .note(entity.getNote())
                .dateCreation(entity.getDateCreation())
                .dateUpdate(entity.getDateUpdate())
                .photosIds(entity.getPhotosIds())
                .build();
    }
}
