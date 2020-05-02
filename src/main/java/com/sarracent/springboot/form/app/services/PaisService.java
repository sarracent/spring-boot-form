package com.sarracent.springboot.form.app.services;

import com.sarracent.springboot.form.app.models.domain.Pais;

import java.util.List;

public interface PaisService {
    public List<Pais> listar();
    public Pais obtenerPorId(Integer id);
}
