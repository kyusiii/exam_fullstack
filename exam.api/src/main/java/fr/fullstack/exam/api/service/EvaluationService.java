package fr.fullstack.exam.api.service;

import fr.fullstack.exam.api.dto.request.CreateEvaluationDto;
import fr.fullstack.exam.api.entity.EvaluationEntity;
import fr.fullstack.exam.api.entity.RestaurantEntity;
import fr.fullstack.exam.api.exception.ResourceNotFoundException;
import fr.fullstack.exam.api.repository.EvaluationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class EvaluationService {
    private final EvaluationRepository evaluationRepository;
    private final RestaurantService restaurantService;

    public EvaluationEntity createEvaluationFor(Integer restaurantId, CreateEvaluationDto dto) {
        RestaurantEntity restaurant = restaurantService.getRestaurantById(restaurantId);

        EvaluationEntity evaluation = EvaluationEntity.builder()
                .nomEvaluateur(dto.getNomEvaluateur())
                .commentaire(dto.getCommentaire())
                .dateCreation(dto.getDateCreation())
                .dateUpdate(dto.getDateCreation())
                .note(dto.getNote())
                .restaurant(restaurant)
                .build();

        evaluation = evaluationRepository.save(evaluation);
        restaurantService.addEvaluation(restaurantId, evaluation);


        return evaluation;
    }

    public EvaluationEntity getEvaluationById(Integer id) {
        return evaluationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("L'évaluation avec l'id " + id + " n'existe pas"));
    }

    public void addPhotoToEval(EvaluationEntity eval, UUID illustrationId) {
        eval.getPhotosIds().add(illustrationId);
        evaluationRepository.save(eval);
    }
}
