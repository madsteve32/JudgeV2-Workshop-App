package JudgeV2.demo.Service.impl;

import JudgeV2.demo.Service.ExerciseService;
import JudgeV2.demo.model.entity.Exercise;
import JudgeV2.demo.model.service.ExerciseServiceModel;
import JudgeV2.demo.repository.ExerciseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    @Override
    public List<String> findAllExercises() {
        return exerciseRepository.findAllExercises();
    }

    @Override
    public boolean checkIsLate(String exercise) {
        Exercise exerciseEntity = exerciseRepository.findByName(exercise).get();

        return exerciseEntity.getDueDate().isBefore(LocalDateTime.now());
    }

    @Override
    public Exercise findByName(String name) {

        return exerciseRepository.findByName(name).get();
    }
}
