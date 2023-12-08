package fr.fullstack.exam.api.service;

import fr.fullstack.exam.api.dto.UrlDto;
import fr.fullstack.exam.api.entity.EvaluationEntity;
import fr.fullstack.exam.api.entity.RestaurantEntity;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.StatObjectArgs;
import io.minio.errors.*;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class S3Service {
    private MinioClient client;
    private final RestaurantService restaurantService;
    private final EvaluationService evaluationService;
    private String illustrationBucket;
    private String photosBucket;

    public S3Service(Environment env, RestaurantService restaurantService, EvaluationService evaluationService) {
        this.client = MinioClient.builder()
                .endpoint(Objects.requireNonNull(env.getProperty("s3.endpoint")))
                .credentials(Objects.requireNonNull(env.getProperty("s3.accessKey")), Objects.requireNonNull(env.getProperty("s3.secretKey")))
                .build();

        this.illustrationBucket = Objects.requireNonNull(env.getProperty("s3.buckets.illustrations"));
        this.photosBucket = Objects.requireNonNull(env.getProperty("s3.buckets.photos"));
        this.restaurantService = restaurantService;
        this.evaluationService = evaluationService;
    }

    public UrlDto generateGetIllustrationUrl(Integer restaurantId) {
        RestaurantEntity restaurant = restaurantService.getRestaurantById(restaurantId);

        GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket(this.illustrationBucket)
                .object(restaurant.getImageId().toString() + ".png")
                .build();

        StatObjectArgs stateArgs = StatObjectArgs.builder()
                .bucket(this.illustrationBucket)
                .object(restaurant.getImageId().toString() + ".png")
                .build();

        try {
            client.statObject(stateArgs);

            return UrlDto.builder()
                    .url(client.getPresignedObjectUrl(args))
                    .build();
        } catch (ErrorResponseException | InsufficientDataException | InternalException
                 | InvalidKeyException | InvalidResponseException | IOException |
                 NoSuchAlgorithmException | XmlParserException | ServerException e) {
            throw new RuntimeException(e);
        }
    }

    public UrlDto generateGetPhotoUrl(UUID photoId) {
        GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
                .method(Method.GET)
                .bucket(this.photosBucket)
                .object(photoId.toString() + ".png")
                .build();

        StatObjectArgs stateArgs = StatObjectArgs.builder()
                .bucket(this.photosBucket)
                .object(photoId.toString() + ".png")
                .build();

        try {
            client.statObject(stateArgs);

            return UrlDto.builder()
                    .url(client.getPresignedObjectUrl(args))
                    .build();
        } catch (ErrorResponseException | InsufficientDataException | InternalException
                 | InvalidKeyException | InvalidResponseException | IOException |
                 NoSuchAlgorithmException | XmlParserException | ServerException e) {
            throw new RuntimeException(e);
        }
    }

    public UrlDto generatePutIllustrationUrl(Integer restaurantId) {
        RestaurantEntity restaurant = restaurantService.getRestaurantById(restaurantId);
        UUID imageId = UUID.randomUUID();

        GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
                .method(Method.PUT)
                .bucket(this.illustrationBucket)
                .object(imageId.toString() + ".png")
                .build();

        try {
            UrlDto url = UrlDto.builder()
                    .url(client.getPresignedObjectUrl(args))
                    .build();
            restaurantService.setIllustration(restaurant, imageId);
            return url;
        } catch (ErrorResponseException | InsufficientDataException | InternalException
                 | InvalidKeyException | InvalidResponseException | IOException |
                 NoSuchAlgorithmException | XmlParserException | ServerException e) {
            throw new RuntimeException(e);
        }
    }

    public UrlDto generatePutPhotoUrl(Integer evalId) {
        EvaluationEntity evaluation = evaluationService.getEvaluationById(evalId);
        UUID imageId = UUID.randomUUID();

        GetPresignedObjectUrlArgs args = GetPresignedObjectUrlArgs.builder()
                .method(Method.PUT)
                .bucket(this.photosBucket)
                .object(imageId.toString() + ".png")
                .build();

        try {
            UrlDto url = UrlDto.builder()
                    .url(client.getPresignedObjectUrl(args))
                    .build();
            evaluationService.addPhotoToEval(evaluation, imageId);
            return url;
        } catch (ErrorResponseException | InsufficientDataException | InternalException
                 | InvalidKeyException | InvalidResponseException | IOException |
                 NoSuchAlgorithmException | XmlParserException | ServerException e) {
            throw new RuntimeException(e);
        }
    }
}
