package com.company.springframework.service.impl;

import com.company.springframework.model.Categoria;
import com.company.springframework.repository.CategoriaRepository;
import com.company.springframework.service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public Categoria guardarCategoria(Categoria categoria){
        validarCategoria(categoria);
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria actualizarCategoria(Categoria categoria){
        validarCategoria(categoria);
        if(!categoriaRepository.existsById(categoria.getIdCategoria())){
            throw new IllegalArgumentException("No existe Categoría con dicho ID");
        }
        return categoriaRepository.save(categoria);
    }

    @Override
    public void eliminarCategoria(Long idCategoria){
        if(!categoriaRepository.existsById(idCategoria)){
            throw new IllegalArgumentException("No existe Categoría con dicho ID");
        }
        categoriaRepository.deleteById(idCategoria);
    }

    @Override
    public Categoria obtenerCategoria(Long idCategoria){
        Optional<Categoria> categoria = categoriaRepository.findById(idCategoria);
        if(categoria.isEmpty()){
            throw new IllegalArgumentException("No existe Categoría con dicho ID");
        }
        return categoria.get();
    }

    @Override
    public List<Categoria> listarTodasLasCategorias(){
        return(List<Categoria>)categoriaRepository.findAll();
    }

    private void validarCategoria(Categoria categoria){
        if(categoria.getDescripcion()==null  || categoria.getDescripcion().trim().isEmpty()){
            throw new IllegalArgumentException("Descripción de Categoría no puede estar vacía");
        }

    }


}
