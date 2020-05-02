package com.sarracent.springboot.form.app.services;

import com.sarracent.springboot.form.app.models.domain.Pais;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PaisServiceImpl implements PaisService {
    private List<Pais> lista;

    public PaisServiceImpl() {
        this.lista = Arrays.asList(
                new Pais(1,"CU","Cuba"),
                new Pais(2,"CH","Suiza"),
                new Pais(3,"AR","Argentina"),
                new Pais(4,"NG","Nigeria"),
                new Pais(5,"CO","Congo"),
                new Pais(6,"BR","Brasil"));
    }

    @Override
    public List<Pais> listar() {
        return lista;
    }

    @Override
    public Pais obtenerPorId(Integer id) {
        Pais resultado = null;
        for (Pais pais: this.lista) {
            if (pais.getId() == id) {
                resultado = pais;
                break;
            }
        }
        return resultado;
    }
}
