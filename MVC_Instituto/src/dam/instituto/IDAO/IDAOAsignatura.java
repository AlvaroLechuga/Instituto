package dam.instituto.IDAO;

import dam.instituto.recursos.Asignatura;
import java.util.List;

public interface IDAOAsignatura {
 
    public int insertarAsignatura(Asignatura asignatura);
    public int modificarAsignatura(Asignatura aignatura);
    public int EliminarAsignatura(int codigo);
    public  List<Asignatura> getAsignatura(int codigo);

    public List<Asignatura> getDatosAsignatura();
    
}
