package dam.instituto.recursos;

public class Asignatura {
    private int codigo;
    private String nombre;
    private int horas;

    public Asignatura() {
    }

    public Asignatura(int codigo, String nombre, int horas) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.horas = horas;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getHoras() {
        return horas;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    @Override
    public String toString() {
        return "Asignatura{" + "codigo=" + codigo + ", nombre=" + nombre + ", horas=" + horas + '}';
    }
    
    
}
