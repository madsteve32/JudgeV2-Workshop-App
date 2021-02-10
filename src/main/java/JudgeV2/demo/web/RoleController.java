package JudgeV2.demo.web;

import JudgeV2.demo.Service.UserService;
import JudgeV2.demo.model.entity.RoleNameEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/roles")
public class RoleController {

    private final UserService userService;

    public RoleController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("usernames", userService.findAllUsernames());

        return "role-add";
    }

    @PostMapping("/add")
    public String addConfirm(@RequestParam String username,
                             @RequestParam String role) {

        userService.changeRole(username, RoleNameEnum.valueOf(role.toUpperCase()));

        return "redirect:/";
    }
}
