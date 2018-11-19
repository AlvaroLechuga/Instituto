package dam.instituto.recursos;

public class Alumno {
    
    private String dni;
    private String nombre;
    private String direccion;

    public Alumno() {
    }

    
    
    public Alumno(String dni, String nombre, String direccion) {
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public Alumno(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Alumno{" + "dni=" + dni + ", nombre=" + nombre + ", direccion=" + direccion + '}';
    }
    
}
