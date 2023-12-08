package fr.fullstack.exam.api.repository;

import fr.fullstack.exam.api.entity.EvaluationFinaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationFinaleRepository extends JpaRepository<EvaluationFinaleEntity, Integer> {
}
