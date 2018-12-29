package sec.project.controller;

import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Event;
import sec.project.domain.Signup;
import sec.project.repository.EventRepository;
import sec.project.repository.SignupRepository;

@Controller
@RequestMapping("/event")
public class EventController {

    @Autowired
    private SignupRepository signupRepository;

    @Autowired
    private EventRepository eventRepository;

    @RequestMapping(value = "*", method = RequestMethod.GET)
    public String events(Model model) {
        List<Event> events = eventRepository.findAll();
        model.addAttribute("events", events);
        return "eventList";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String eventLookup(@PathVariable("id") long id, Model model) {
        Event event = eventRepository.findOne(id);
        model.addAttribute("event", event);
        return "event";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String eventCommentPost(@PathVariable("id") long id, Model model, @RequestParam String comment) {
        Event event = eventRepository.findOne(id);
        event.addComment(comment);
        eventRepository.save(event);
        model.addAttribute("event", event);
        return "event";
    }

    @RequestMapping(value = "form/{id}", method = RequestMethod.GET)
    public String submitForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("id", id);
        return "form";
    }

    @RequestMapping(value = "/form/{id}", method = RequestMethod.POST)
    public String submitForm(@PathVariable("id") long id, @RequestParam String password, Model model, HttpServletRequest request, Principal principal) {
        Signup singup = signupRepository.findByName(principal.getName());
        if (password.equals(singup.getPassword())) {
            singup.getEvents().add(eventRepository.findOne(id));
            model.addAttribute("username", principal.getName());
            signupRepository.save(singup);
            return "done";
        } else {
            model.addAttribute("error", "error");
            return "redirect:/event/" + id;
        }

    }

}
