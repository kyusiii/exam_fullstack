package fr.fullstack.exam.api.controller;

import fr.fullstack.exam.api.dto.EvaluationDto;
import fr.fullstack.exam.api.dto.UrlDto;
import fr.fullstack.exam.api.dto.request.CreateEvaluationDto;
import fr.fullstack.exam.api.service.EvaluationService;
import fr.fullstack.exam.api.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin("*")
public class EvaluationController {
    private final EvaluationService evaluationService;
    private final S3Service s3Service;

    @PostMapping("/evaluations/{restaurantId}/create")
    public EvaluationDto createEvaluation(@PathVariable Integer restaurantId, @RequestBody CreateEvaluationDto dto) {
        return EvaluationDto.entityToDto(evaluationService.createEvaluationFor(restaurantId, dto));
    }

    @GetMapping("/evaluations/photos/{id}")
    public UrlDto getPhotoByPhotoId(@PathVariable UUID id) {
        return s3Service.generateGetPhotoUrl(id);
    }

    @PutMapping("/evaluation/photos/{id}")
    public UrlDto putPhotoByEvalId(@PathVariable Integer id) {
        return s3Service.generatePutPhotoUrl(id);
    }
}
