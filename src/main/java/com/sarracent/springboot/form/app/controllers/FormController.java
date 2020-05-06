package com.sarracent.springboot.form.app.controllers;

import com.sarracent.springboot.form.app.editors.NombreMayusculaEditor;
import com.sarracent.springboot.form.app.editors.PaisPropertyEditor;
import com.sarracent.springboot.form.app.editors.RolesEditor;
import com.sarracent.springboot.form.app.models.domain.Pais;
import com.sarracent.springboot.form.app.models.domain.Role;
import com.sarracent.springboot.form.app.models.domain.Usuario;
import com.sarracent.springboot.form.app.services.PaisService;
import com.sarracent.springboot.form.app.services.RoleService;
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

    @Autowired
    private PaisService paisService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PaisPropertyEditor paisPropertyEditor;

    @Autowired
    private RolesEditor rolesEditor;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(validador);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, "fechaNacimiento",new CustomDateEditor(dateFormat, false));

        binder.registerCustomEditor(String.class,"nombre",new NombreMayusculaEditor());

        binder.registerCustomEditor(Pais.class,"pais", paisPropertyEditor);
        binder.registerCustomEditor(Role.class,"roles", rolesEditor);
    }

    @ModelAttribute("genero")
    public List<String> genero() {
        return Arrays.asList("Hombre", "Mujer");
    }

    @ModelAttribute("listaPaises")
    public List<Pais> listaPaises() {
        return paisService.listar();
    }

    @ModelAttribute("listaRoles")
    public List<Role> listaRoles() {
        return roleService.listar();
    }

    @ModelAttribute("listaRolesString")
    public List<String> listaRolesString() {
        return Arrays.asList("ROLE_ADMIN", "ROLE_USER", "ROLE_MODERATOR");
    }

    @ModelAttribute("listaRolesMap")
    public Map<String, String> listaRolesMap() {
        Map<String, String> roles = new HashMap<String, String>();
        roles.put("ROLE_ADMIN", "Administrador");
        roles.put("ROLE_USER", "Usuario");
        roles.put("ROLE_MODERATOR", "Moderador");
        return roles;
    }

    @ModelAttribute("paises")
    public List<String> paises() {
        return Arrays.asList("Cuba", "Suiza", "Argentina", "Nigeria", "Congo", "Brasil");
    }

    @ModelAttribute("paisesMap")
    public Map<String, String> paisesMap() {
        Map<String, String> paises = new HashMap<String, String>();
        paises.put("CU", "Cuba");
        paises.put("CH", "Suiza");
        paises.put("AR", "Argentina");
        paises.put("NG", "Nigeria");
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
        usuario.setHabilitar(true);
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
