package com.company.springframework.service.impl;

import com.company.springframework.model.Producto;
import com.company.springframework.model.Categoria;
import com.company.springframework.repository.ProductoRepository;
import com.company.springframework.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
public class ProductoServiceImpl implements  ProductoService{

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Producto guardarProducto(Producto producto){
        validarProducto(producto);
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizarProducto(Producto producto){
        validarProducto(producto);
        if (!productoRepository.existsById(producto.getIdProducto())){
            throw new IllegalArgumentException("No se encuentra Producto con dicho ID");
        }
        return productoRepository.save(producto);
    }

    @Override
    public void eliminarProducto(Long idProducto){
        if (!productoRepository.existsById(idProducto)){
            throw new IllegalArgumentException("No se encuentra Producto con dicho ID");
        }
        productoRepository.deleteById(idProducto);
    }

    @Override
    public Producto obtenerProducto(Long idProducto){
        Optional<Producto> producto = productoRepository.findById(idProducto);
        if(producto.isEmpty()){
            throw new IllegalArgumentException("No se encuentra Producto con dicho ID");
        }
        return producto.get();
    }


    @Override
    public List<Producto> listarTodosLosProductos(){
        return (List<Producto>)productoRepository.findAll();
    }

    private void validarProducto(Producto producto){
        if(producto.getDescripcion() == null || producto.getDescripcion().trim().isEmpty()){
            throw new IllegalArgumentException(("La descripción del Producto no puede estar vacía"));
        }
        if(producto.getPrecio() == null || producto.getPrecio().compareTo(BigDecimal.ZERO)<0){
            throw new IllegalArgumentException(("El precio del Producto tiene un formato incorrecto"));
        }
        if(producto.getStock()<0){
            throw new IllegalArgumentException(("El stock del Producto tiene un formato incorrecto"));
        }
        if(producto.getDescripcion().trim().isEmpty()){
            throw new IllegalArgumentException(("La estado del Producto no puede estar vacía"));
        }
        if(producto.getCategoria() == null ){
            throw new IllegalArgumentException("La Categoría asignada no es válida");
        }


    }





}
