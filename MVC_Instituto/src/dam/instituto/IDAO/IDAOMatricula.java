package dam.instituto.IDAO;

import dam.instituto.recursos.Matricula;
import java.util.List;

public interface IDAOMatricula {
    
        public int insertarMatricula(Matricula matricula);
	public int modificarMatricula(Matricula matricula);
	public int EliminarMatricula(String dni, int codigo);
	public List<Matricula> getMatriculas(String dni);
        public void SacarCodigo(String nombre, String nombre_A, double nota, String fecha);

    public List<Matricula> getDatosMatricula();
}
