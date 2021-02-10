package JudgeV2.demo.Service.impl;

import JudgeV2.demo.Service.ExerciseService;
import JudgeV2.demo.Service.HomeworkService;
import JudgeV2.demo.Service.UserService;
import JudgeV2.demo.model.entity.Homework;
import JudgeV2.demo.repository.HomeworkRepository;
import JudgeV2.demo.security.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class HomeworkServiceImpl implements HomeworkService {

    private final HomeworkRepository homeworkRepository;
    private final ExerciseService exerciseService;
    private final CurrentUser currentUser;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public HomeworkServiceImpl(HomeworkRepository homeworkRepository, ExerciseService exerciseService, CurrentUser currentUser, UserService userService, ModelMapper modelMapper) {
        this.homeworkRepository = homeworkRepository;
        this.exerciseService = exerciseService;
        this.currentUser = currentUser;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addHomework(String exercise, String gitAddress) {
        Homework homework = new Homework();

        homework.setGitAddress(gitAddress);
        homework.setAddedOn(LocalDateTime.now());
        homework.setExercise(exerciseService.findByName(exercise));
        homework.setAuthor(userService.findById(currentUser.getId()));

        homeworkRepository.save(homework);
    }
}
