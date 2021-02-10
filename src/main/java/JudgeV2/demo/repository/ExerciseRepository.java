package JudgeV2.demo.repository;

import JudgeV2.demo.model.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    @Query("SELECT e.name FROM Exercise e ORDER BY e.name ")
    List<String> findAllExercises();

    Optional<Exercise> findByName(String name);
}
