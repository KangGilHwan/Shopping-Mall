package riverway.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/")
    public String goHome(){
        return "/loginForm.html";
    }

    @GetMapping("/loginForm")
    public String loginForm(){
        return "/loginForm";
    }
}
