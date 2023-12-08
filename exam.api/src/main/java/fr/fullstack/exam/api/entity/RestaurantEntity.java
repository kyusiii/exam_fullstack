package fr.fullstack.exam.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "restaurant")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column(name = "nom", nullable = false, columnDefinition = "varchar(90)")
    String nom;

    @Column(name = "adresse", nullable = false, columnDefinition = "varchar(255)")
    String adresse;

    @Column(name = "moyenne_evaluations", nullable = true)
    Integer moyenneEvalutations;

    @OneToMany(mappedBy = "restaurant")
    List<EvaluationEntity> evaluations;

    @OneToOne
    @JoinColumn(name = "evaluation_finale", nullable = true)
    EvaluationFinaleEntity evaluationFinale;

    @Column(name = "image_id", nullable = true)
    UUID imageId;

    @JoinTable(name = "restaurants_jn_tags", joinColumns = @JoinColumn(name = "restaurant_id"))
    @Column(name = "tags", nullable = false)
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = TagsEnum.class)
    List<TagsEnum> tags;
}
