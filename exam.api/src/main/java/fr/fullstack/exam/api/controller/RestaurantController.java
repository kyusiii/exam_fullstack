package fr.fullstack.exam.api.controller;

import fr.fullstack.exam.api.dto.RestaurantDto;
import fr.fullstack.exam.api.dto.UrlDto;
import fr.fullstack.exam.api.dto.request.CreateRestaurantDto;
import fr.fullstack.exam.api.service.RestaurantService;
import fr.fullstack.exam.api.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@CrossOrigin("*")
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final S3Service s3Service;

    @GetMapping("/restaurants")
    public List<RestaurantDto> getRestaurants() {
        return restaurantService.getAllRestaurants().stream().map(entity -> RestaurantDto.entityToDto(entity)).toList();
    }

    @GetMapping("/restaurants/{id}")
    public RestaurantDto getRestaurantById(@PathVariable Integer id) {
        return RestaurantDto.entityToDto(restaurantService.getRestaurantById(id));
    }

    @PostMapping("/restaurants/create")
    public RestaurantDto createRestaurant(@RequestBody CreateRestaurantDto dto) {
        return RestaurantDto.entityToDto(restaurantService.createRestaurant(dto));
    }

    @PutMapping("/restaurants/update")
    public RestaurantDto updateRestaurant(@RequestBody RestaurantDto dto) {
        return RestaurantDto.entityToDto(restaurantService.updateRestaurant(dto));
    }

    @DeleteMapping("/restaurants/delete/{id}")
    public void deleteRestaurantById(@PathVariable Integer id) {
        restaurantService.deleteRestaurantById(id);
    }

    @GetMapping("/restaurants/illustration/{id}")
    public UrlDto getIllustrationById(@PathVariable Integer id) {
        return s3Service.generateGetIllustrationUrl(id);
    }

    @PutMapping("/restaurants/illustration/{id}")
    public UrlDto putIllustrationById(@PathVariable Integer id) {
        return s3Service.generatePutIllustrationUrl(id);
    }
}
