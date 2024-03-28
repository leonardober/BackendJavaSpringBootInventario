package gm.inventarios.controlador;

import gm.inventarios.excepcion.RecursoNoEncontradoExcepcion;
import gm.inventarios.modelo.Producto;
import gm.inventarios.servicio.ProductoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//http://locahost:8080/inventario-app    Peticion Backend
@RequestMapping("inventario-app")  //identifica todas las versiones de inventario
@CrossOrigin(value = "http://localhost:4200") //Peticion Angular FronEnd
public class ProductoControlador {
  //enviamos informacion a la consola
    private static final Logger logger =
            LoggerFactory.getLogger(ProductoControlador.class);

    @Autowired //Inyeccion de dependencia  del servicio al controlador
    private ProductoServicio productoServicio;

    //http://locahost:8080/inventario-app/productos
    @GetMapping("/productos")  //METHODO OBTENER PRODUCTOS
    public List<Producto> obtenerProductos(){
        List<Producto> productos = this.productoServicio.listarProductos();
        logger.info("Productos obtenidos:"); //Imprime la informacion
        productos.forEach((producto -> logger.info(producto.toString())));
        return productos;
    }

    @PostMapping("/productos")  //METHODO AGREGAR PRODUCTOS
    //@RequestBody porque la peticon esta en angular
    public Producto agregarProducto(@RequestBody Producto producto){ //viene de public Producto guardarProducto(Producto producto);
    //viene de ProductoServicio Implementacion @Override
        //    public Producto guardarProducto(Producto producto)
        logger.info("Producto a agregar: " + producto); //Imprime en consola
        return this.productoServicio.guardarProducto(producto);
    }
    //METHODO EDITAR PRODUCTOS
    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(
            @PathVariable int id){
        Producto producto =
                this.productoServicio.buscarProductoPorId(id);
        //SE CREA EN CLASE EXEPCION
        if(producto != null)
            return ResponseEntity.ok(producto);
        else
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
    }
  //METHODO ACTUALIZAR PRODUCTO YA EXISTENTE
    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> actualizarProducto(
            @PathVariable int id,
            @RequestBody Producto productoRecibido){   //informacion del formulario
        Producto producto = this.productoServicio.buscarProductoPorId(id);
        if(producto == null)
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
        producto.setDescripcion(productoRecibido.getDescripcion()); //se actualiza
        producto.setPrecio(productoRecibido.getPrecio());
        producto.setExistencia(productoRecibido.getExistencia());
        this.productoServicio.guardarProducto(producto);
        return ResponseEntity.ok(producto);
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Map<String, Boolean>>
        eliminarProducto(@PathVariable int id){
        Producto producto = productoServicio.buscarProductoPorId(id);
        if (producto == null)  //DA ERROR 404 EXCEPTION NO FOUND
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
        this.productoServicio.eliminarProductoPorId(producto.getIdProducto());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
