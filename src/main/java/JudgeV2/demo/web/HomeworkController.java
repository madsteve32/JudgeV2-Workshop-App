package JudgeV2.demo.web;

import JudgeV2.demo.Service.ExerciseService;
import JudgeV2.demo.Service.HomeworkService;
import JudgeV2.demo.model.binding.HomeworkAddBindingModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/homework")
public class HomeworkController {

    private final ExerciseService exerciseService;
    private final HomeworkService homeworkService;

    public HomeworkController(ExerciseService exerciseService, HomeworkService homeworkService) {
        this.exerciseService = exerciseService;
        this.homeworkService = homeworkService;
    }

    @GetMapping("/add")
    public String add(Model model) {
        if (!model.containsAttribute("homeworkAddBindingModel")) {
            model.addAttribute("homeworkAddBindingModel", new HomeworkAddBindingModel());
            model.addAttribute("isLate", false);
        }

        model.addAttribute("exercises", exerciseService.findAllExercises());

        return "homework-add";
    }

    @PostMapping("/add")
    public String addConfirm(@Valid HomeworkAddBindingModel homeworkAddBindingModel,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("homeworkAddBindingModel", homeworkAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.homeworkAddBindingModel", bindingResult);

            return "redirect:add";
        }

        boolean isLate = exerciseService.checkIsLate(homeworkAddBindingModel.getExercise());

        if (isLate) {
            redirectAttributes.addFlashAttribute("homeworkAddBindingModel", homeworkAddBindingModel);
            redirectAttributes.addFlashAttribute("isLate", true);
        }

        // save to DB
        homeworkService.addHomework(homeworkAddBindingModel.getExercise(), homeworkAddBindingModel.getGitAddress());

        return "redirect:/";
    }
}
