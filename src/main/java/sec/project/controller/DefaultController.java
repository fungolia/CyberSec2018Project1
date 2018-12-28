package sec.project.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Signup;
import sec.project.repository.SignupRepository;

@Controller
@RequestMapping("/")
public class DefaultController {

    @Autowired
    private SignupRepository signupRepository;

    @RequestMapping("*")
    public String defaultMapping() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String indexSite() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "redirect:/login";
    }

    @RequestMapping("/new")
    public String newUser() {
        return "new";
    }

    @PostMapping("/new")
    public String createNewAccount(@RequestParam String name, @RequestParam String address,
            @RequestParam String password, Model model,
            HttpServletRequest request) {
        Signup signup = new Signup(name, address, password);
        signupRepository.save(signup);
        return "redirect:/";
    }
}
