package fr.fullstack.exam.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "evaluation")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EvaluationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column(name = "nom_evaluateur", nullable = false, columnDefinition = "varchar(50)")
    String nomEvaluateur;

    @Column(name = "commentaire", nullable = false, columnDefinition = "varchar(255)")
    String commentaire;

    @Column(name = "note", nullable = false)
    @Min(0)
    @Max(3)
    Integer note;

    @Column(name = "date_creation", nullable = false, columnDefinition = "date")
    Date dateCreation;

    @Column(name = "date_update", nullable = false, columnDefinition = "date")
    Date dateUpdate;

    @Column(name = "photos_ids", nullable = true)
    List<UUID> photosIds;

    @ManyToOne
    @JoinColumn(name = "restaurant")
    RestaurantEntity restaurant;
}
