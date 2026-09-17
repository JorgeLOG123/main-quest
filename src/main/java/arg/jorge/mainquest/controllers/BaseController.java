package arg.jorge.mainquest.controllers;

import org.springframework.stereotype.Controller;

@Controller
public abstract class BaseController {

    // Clave compartida por todos los controladores para el atributo de formulario en el Model
    protected static final String FORM_ATTRIBUTE = "form";

    /**
     * Helper para redirigir a otra URL.
     * Hace que el navegador realice una nueva petición GET a esa ruta,
     * en lugar de renderizar una vista sin cambiar la URL actual.
     */
    protected String redirect(String path) {
        return "redirect:" + path;
    }
}
