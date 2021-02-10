package JudgeV2.demo.Service;

import JudgeV2.demo.model.entity.Exercise;
import JudgeV2.demo.model.service.ExerciseServiceModel;

import java.util.List;

public interface ExerciseService {
    void addExercise(ExerciseServiceModel exerciseServiceModel);

    List<String> findAllExercises();

    boolean checkIsLate(String exercise);


    Exercise findByName(String name);
}
