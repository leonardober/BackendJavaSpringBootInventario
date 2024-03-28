package gm.inventarios.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
//Proyecto lombok permite agregar las clases simplificado
@Entity //crea la tabla de base datos con conexion en application.properties
@Data  //agrega methodos get y set
@NoArgsConstructor //agrega constructor vacio
@AllArgsConstructor //agrega constructor con argumentos
@ToString  //Methodo
public class Producto {
    @Id   //llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //Autoincementable
    Integer idProducto;
    String descripcion;
    Double precio;
    Integer existencia;
}
