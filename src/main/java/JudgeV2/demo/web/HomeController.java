package JudgeV2.demo.web;

import JudgeV2.demo.security.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CurrentUser currentUser;

    public HomeController(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @GetMapping("/")
    public String index() {

        return currentUser.isAnonymous() ? "index" : "home";
    }
}
