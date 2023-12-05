package com.company.springframework;

import static org.assertj.core.api.Assertions.assertThat;

import com.company.springframework.model.Producto;
import com.company.springframework.model.Categoria;

import com.company.springframework.repository.CategoriaRepository;
import com.company.springframework.repository.ProductoRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class SpringframeworkInitialApplicationTests {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Test
    void insertarCategoria() {

        Categoria categoria = new Categoria();
        categoria.setDescripcion("LIMPIEZA");

        Categoria categoriaRegistrada = categoriaRepository.save(categoria);

        assertThat(categoriaRegistrada).isNotNull();
        assertThat(categoriaRegistrada.getIdCategoria()).isPositive();
        assertThat(categoriaRegistrada.getDescripcion()).isNotEmpty();

    }

    @Test
    void insertarProducto() {

        Producto producto = new Producto();

        // create value for attributes
        producto.setDescripcion("Laptop HP ABC123 Intel i9900k 1TB M2 16GB RAM");
        producto.setPrecio(new BigDecimal("2994.50"));
        producto.setStock(60);
        producto.setEstado("SI");



        // create value for relationship
        Categoria categoria = new Categoria();
        categoria.setDescripcion("ELECTRODOMÉSTICOS");
        Categoria categoriaRegistrada = categoriaRepository.save(categoria);

        producto.setIdCategoria(categoriaRegistrada);

        Producto productoRegistrado = productoRepository.save(producto);

        assertThat(productoRegistrado).isNotNull();
        assertThat(productoRegistrado.getIdProducto()).isPositive();
        assertThat(productoRegistrado.getDescripcion()).isNotEmpty();
        assertThat(productoRegistrado.getPrecio()).isNotNull();
        assertThat(productoRegistrado.getStock()).isLessThan(0);
        assertThat(productoRegistrado.getEstado()).isNotNull();
        assertThat(productoRegistrado.getIdCategoria().getIdCategoria()).isPositive();
        //assertThat(productoRegistrado.getCategoria().getDescripcion()).isNotEmpty();
    }

    @Test
    void actualizarProducto() {

        // editar el empleado con id 1
        Producto producto = productoRepository.findById(4L).orElse(null);

        // modificar el nombre del empleado
        producto.setDescripcion("Laptop HP AB12 i7700k 2TB M2 32GB RAM");

        // guardar los cambios
        productoRepository.save(producto);

        // verificar que el nombre del empleado se actualizo
        Producto productoActualizado = productoRepository.findById(4L).orElse(null);
        assertThat(productoActualizado.getDescripcion()).isEqualTo("Laptop HP AB12 i7700k 2TB M2 32GB RAM");
    }

    @Test
    void eliminarProducto() {

        // eliminar el empleado con id 1
        productoRepository.deleteById(4L);

        // verificar que el empleado con id 1 ya no existe
        Producto productoEliminado = productoRepository.findById(4L).orElse(null);
        assertThat(productoEliminado).isNull();

    }

    @Test
    void listarCategorias(){
        Iterable<Categoria> categorias = categoriaRepository.findAll();
        assertThat(categorias).isNotEmpty();
    }

    @Test
    void jpa_query_methods(){
        //Iterable<Empleado> empleados = empleadoRepository.findByNombre("Fernando Ruiz");
        //assertThat(empleados).isNotEmpty();

        //Iterable<Empleado> empleados = empleadoRepository.findByNombreContaining("Perez");
        //assertThat(((List<Empleado>) empleados).size()).isEqualTo(2);

        //Iterable<Empleado> empleados = empleadoRepository.findByNombreStartingWith("E");
        //assertThat(((List<Empleado>) empleados).size()).isEqualTo(2);

        //Iterable<Empleado> empleados = empleadoRepository.findBySalario(new BigDecimal("50000"));
        //assertThat(((List<Empleado>) empleados).size()).isEqualTo(2);

        //Iterable<Empleado> empleados = empleadoRepository.findBySalarioGreaterThan(new BigDecimal("58000"));
        //assertThat(((List<Empleado>) empleados).size()).isEqualTo(1);

        //Iterable<Empleado> empleados = empleadoRepository.findBySalarioGreaterThanEqual(new BigDecimal("55000"));
        //assertThat(((List<Empleado>) empleados).size()).isEqualTo(3);

        List<Producto> productos = productoRepository.findByPrecioBetween(new BigDecimal("10"), new BigDecimal("20"));
        //assertThat(empleados).hasSize(2);
        assertThat(productos).size().isEqualTo(2);


        //List<Empleado> empleados = empleadoRepository.findFirst2BySalarioBetween(new BigDecimal("50000"), new BigDecimal("55000"));
        //assertThat(empleados).hasSize(2);
    }
}
