package fr.fullstack.exam.api.service;

import fr.fullstack.exam.api.dto.RestaurantDto;
import fr.fullstack.exam.api.dto.request.CreateRestaurantDto;
import fr.fullstack.exam.api.entity.EvaluationEntity;
import fr.fullstack.exam.api.entity.RestaurantEntity;
import fr.fullstack.exam.api.exception.ResourceNotFoundException;
import fr.fullstack.exam.api.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public List<RestaurantEntity> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public RestaurantEntity getRestaurantById(Integer id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Le restaurant avec l'id " + id + " n'existe pas"));
    }

    public RestaurantEntity createRestaurant(CreateRestaurantDto dto) {
        final RestaurantEntity restaurant = RestaurantEntity.builder()
                .nom(dto.getNom())
                .adresse(dto.getAdresse())
                .tags(dto.getTags())
                .evaluations(new ArrayList<>())
                .build();

        return restaurantRepository.save(restaurant);
    }

    public void deleteRestaurantById(Integer id) {
        if (!restaurantRepository.existsById(id)) {
            throw new ResourceNotFoundException("Le restaurant avec l'id " + id + " n'existe pas");
        }

        restaurantRepository.deleteById(id);
    }

    public RestaurantEntity updateRestaurant(RestaurantDto restaurantDto) {
        RestaurantEntity restaurant = getRestaurantById(restaurantDto.getId());

        restaurant.setNom(restaurantDto.getNom());
        restaurant.setAdresse(restaurantDto.getAdresse());


        return restaurantRepository.save(restaurant);
    }

    public void addEvaluation(Integer id, EvaluationEntity evaluation) {
        RestaurantEntity restaurant = getRestaurantById(id);
        restaurant.getEvaluations().add(evaluation);
        calculMoyenneEvaluations(restaurant);

        restaurantRepository.save(restaurant);
    }

    private void calculMoyenneEvaluations(RestaurantEntity restaurant) {
        Integer sum = 0;
        for (EvaluationEntity eval : restaurant.getEvaluations()) {
            sum += eval.getNote();
        }

        final Integer moyenne = (sum / restaurant.getEvaluations().size());
        restaurant.setMoyenneEvalutations(moyenne);

        restaurantRepository.save(restaurant);
    }

    public void setIllustration(RestaurantEntity restaurant, UUID illustrationId) {
        restaurant.setImageId(illustrationId);
        restaurantRepository.save(restaurant);
    }
}
