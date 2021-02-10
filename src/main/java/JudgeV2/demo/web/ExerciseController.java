package JudgeV2.demo.web;

import JudgeV2.demo.Service.ExerciseService;
import JudgeV2.demo.model.binding.AddExerciseBindingModel;
import JudgeV2.demo.model.service.ExerciseServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/exercise")
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final ModelMapper modelMapper;

    public ExerciseController(ExerciseService exerciseService, ModelMapper modelMapper) {
        this.exerciseService = exerciseService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/add")
    public String add(Model model) {
        if (!model.containsAttribute("addExerciseBindingModel")) {
            model.addAttribute("addExerciseBindingModel", new AddExerciseBindingModel());
        }

        return "exercise-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid AddExerciseBindingModel addExerciseBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addExerciseBindingModel", addExerciseBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addExerciseBindingModel", bindingResult);

            return "redirect:add";
        }

        ExerciseServiceModel exerciseServiceModel = modelMapper
                .map(addExerciseBindingModel, ExerciseServiceModel.class);

        exerciseService.addExercise(exerciseServiceModel);

        return "redirect:/";
    }
}
