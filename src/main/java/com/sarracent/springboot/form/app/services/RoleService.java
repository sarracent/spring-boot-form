package com.sarracent.springboot.form.app.services;

import com.sarracent.springboot.form.app.models.domain.Role;
import java.util.List;

public interface RoleService {
    public List<Role> listar();
    public Role obtenerPorId(Integer id);
}
