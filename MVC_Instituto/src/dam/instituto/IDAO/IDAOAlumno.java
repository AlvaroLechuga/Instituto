package dam.instituto.IDAO;

import dam.instituto.recursos.Alumno;
import dam.instituto.recursos.Intermediario;
import java.util.List;
import javax.swing.JTextField;


public interface IDAOAlumno {
    
    public int insertarAlumno(Alumno matricula);
    public int modificarAlumno(Alumno matricula);
    public int EliminarAlumno(String dni);
    public  List<Alumno> getAlumno(String dni);
    public List<Alumno> getDatosAlumno();
    public List<Intermediario> getCalificacion(String dni);

    
    
}
