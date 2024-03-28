package gm.inventarios.repositorio;

import gm.inventarios.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepositorio extends JpaRepository<Producto, Integer> {
}
///con este Interface ya podemos hacer el Crud  y el resto lo agrega con Spring
//Recupera los productos entidad