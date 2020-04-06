package com.sarracent.springboot.form.app.validation;

import com.sarracent.springboot.form.app.models.domain.Usuario;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UsuarioValidador implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return Usuario.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Usuario usuario = (Usuario) o;

        ValidationUtils.rejectIfEmpty(errors, "nombre", "NotEmpty.usuario.nombre");

        if (!usuario.getIdentificador().matches("[0-9]{2}[.][\\d]{3}[.][\\d]{3}[-][A-Z]{1}")){
            errors.rejectValue("identificador", "Pattern.usuario.identificador");
        }
    }
}
