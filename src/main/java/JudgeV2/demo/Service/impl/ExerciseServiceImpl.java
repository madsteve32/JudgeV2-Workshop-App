package JudgeV2.demo.Service.impl;

import JudgeV2.demo.Service.ExerciseService;
import JudgeV2.demo.model.entity.Exercise;
import JudgeV2.demo.model.service.ExerciseServiceModel;
import JudgeV2.demo.repository.ExerciseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ModelMapper modelMapper;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ModelMapper modelMapper) {
        this.exerciseRepository = exerciseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addExercise(ExerciseServiceModel exerciseServiceModel) {
        Exercise exercise = modelMapper.map(exerciseServiceModel, Exercise.class);

        exerciseRepository.save(exercise);
    }
}
