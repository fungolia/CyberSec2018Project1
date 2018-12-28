package sec.project.controller;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Signup;
import sec.project.repository.SignupRepository;
import sec.project.repository.EventRepository;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private SignupRepository signupRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    HttpSession session;

    @GetMapping("*")
    public String accountById(HttpServletRequest request, Model model, Principal principal) {
        Signup singup = signupRepository.findByName(principal.getName());
        model.addAttribute("events", singup.getEvents());
        model.addAttribute("account", singup);
        return "account";
    }

}
