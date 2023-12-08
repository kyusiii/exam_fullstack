package fr.fullstack.exam.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEvaluationDto {
    @JsonProperty("nomEvaluateur")
    String nomEvaluateur;

    @JsonProperty("commentaire")
    String commentaire;

    @JsonProperty("note")
    @Min(0)
    @Max(3)
    Integer note;

    @JsonProperty("dateCreation")
    Date dateCreation;
}
