package com.company.springframework.service;

import com.company.springframework.model.Categoria;

import java.util.List;

public interface CategoriaService {

    Categoria guardarCategoria(Categoria categoria);

    Categoria actualizarCategoria(Categoria categoria);

    void eliminarCategoria(Long idCategoria);

    Categoria obtenerCategoria(Long idCategoria);

    List<Categoria> listarTodasLasCategorias();

}
