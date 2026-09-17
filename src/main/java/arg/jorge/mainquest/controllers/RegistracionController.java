package arg.jorge.mainquest.controllers;

import arg.jorge.mainquest.forms.RegistracionForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistracionController extends BaseController {
    public static final String SIGN_UP_URL = "/registro";
    public static final String REGISTRACION_URL = "/registro";

    @GetMapping(value = REGISTRACION_URL)
    public String init(Model model) {
        model.addAttribute(FORM_ATTRIBUTE, new RegistracionForm());
        return "signup";
    }

    @PostMapping(value = REGISTRACION_URL)
    public String registracion(@ModelAttribute(FORM_ATTRIBUTE) RegistracionForm formulario) {
        // Aquí iría la lógica: validar, guardar en BD, etc.
        String email = formulario.getEmail();
        String contrasena = formulario.getContrasena();
        String nombre = formulario.getNombre();

        System.out.println("Registro de: " + nombre + " (" + email + ")");

        // Redirige (GET) en vez de renderizar la vista directamente,
        // así la URL queda en /home y un refresh no reenvía el POST.
        return redirect(HomeController.HOME_URL);
    }
}
