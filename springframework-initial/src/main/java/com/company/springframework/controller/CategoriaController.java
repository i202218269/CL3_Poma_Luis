package com.company.springframework.controller;

import com.company.springframework.model.Categoria;
import com.company.springframework.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;
/*
    @GetMapping
    public List<Categoria> listarTodasLasCategorias() {
        return categoriaService.listarTodasLasCategorias();
    }
*/
    @GetMapping("/{idCategoria}")
    public Categoria obtenerCategoria(@PathVariable("idCategoria") Long idCategoria){
        return categoriaService.obtenerCategoria(idCategoria);
    }
/*
    @PostMapping
    public Categoria guardarCategoria(@RequestBody Categoria categoria){
        return categoriaService.guardarCategoria(categoria);
    }

    @PutMapping("/{idCategoria}")
    public Categoria atualizarCategoria(@PathVariable("idCategoria") Long idCategoria, @RequestBody Categoria categoria){
        categoria.setIdCategoria(idCategoria);
        return categoriaService.actualizarCategoria(categoria);
    }

    @DeleteMapping("/{idCategoria}")
    public void eliminarCategoria(@PathVariable("idCategoria") Long idCategoria){
        categoriaService.eliminarCategoria(idCategoria);

    }
*/
}
