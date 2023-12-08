package fr.fullstack.exam.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessageDto {
    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

}
