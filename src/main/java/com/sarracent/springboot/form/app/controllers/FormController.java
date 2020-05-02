package com.sarracent.springboot.form.app.controllers;

import com.sarracent.springboot.form.app.editors.NombreMayusculaEditor;
import com.sarracent.springboot.form.app.models.domain.Usuario;
import com.sarracent.springboot.form.app.validation.UsuarioValidador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@SessionAttributes("usuario")
public class FormController {

    @Autowired
    private UsuarioValidador validador;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(validador);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, "fechaNacimiento",new CustomDateEditor(dateFormat, false));

        binder.registerCustomEditor(String.class,"nombre",new NombreMayusculaEditor());
    }

    @ModelAttribute("paises")
    public List<String> paises() {
        return Arrays.asList("Cuba", "Suiza", "Argentina", "Nigeria", "Congo", "Brasil");
    }

    @ModelAttribute("paisesMap")
    public Map<String, String> paisesMap() {
        Map<String, String> paises = new HashMap<String, String>();
        paises.put("CU", "Cuba");
        paises.put("SU", "Suiza");
        paises.put("AR", "Argentina");
        paises.put("NI", "Nigeria");
        paises.put("CO", "Congo");
        paises.put("BR", "Brasil");
        return paises;
    }

    @GetMapping("/form")
    public String form(Model model) {
        Usuario usuario = new Usuario();
        usuario.setNombre("Jhon");
        usuario.setApellido("Doe");
        usuario.setIdentificador("12.456.789-K");
        model.addAttribute("titulo", "Formulario usuarios");
        model.addAttribute("usuario", usuario);
        return "form";
    }

    @PostMapping("/form")
    public String procesar(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status) {

        model.addAttribute("titulo", "Resultado Form");

        if(result.hasErrors()) {
            return "form";
        }

        model.addAttribute("usuario", usuario);
        status.setComplete();
        return "resultado";
    }

}
