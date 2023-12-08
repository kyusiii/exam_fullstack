package fr.fullstack.exam.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Entity
@Table(name = "evaluation_finale")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EvaluationFinaleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column(name = "nom_decideur", nullable = false, columnDefinition = "varchar(90)")
    String nomDecideur;

    @Column(name = "note", nullable = false)
    @Min(0)
    @Max(3)
    Integer note;

    @Column(name = "description", nullable = false, columnDefinition = "text")
    String description;
}
