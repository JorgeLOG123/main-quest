package arg.jorge.mainquest.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController extends BaseController {
    public static final String LOGIN_URL = "/login";

    @GetMapping(value = LOGIN_URL)
    public String init() {
        return "login";
    }
}
