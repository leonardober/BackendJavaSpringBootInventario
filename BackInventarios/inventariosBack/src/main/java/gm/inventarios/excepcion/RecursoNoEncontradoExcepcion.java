package gm.inventarios.excepcion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND) //RESPUESTA TIPO REST NOT_FOUND
public class RecursoNoEncontradoExcepcion extends RuntimeException{  //EXCEPCION NO OBLIGATORIA
    public RecursoNoEncontradoExcepcion(String mensaje){
        super(mensaje);
    } //CONSTRUCTOR Y SUPER PASA INFORMACION A CLASE PADRE
}
