package dam.instituto.recursos;

public class Intermediario {
    private String nombreAlumno;
    private String nombreAsignatura;
    private double nota;
    private String fecha;

    public Intermediario() {
    }

    public Intermediario(String nombreAlumno, String nombreAsignatura, double nota, String fecha) {
        this.nombreAlumno = nombreAlumno;
        this.nombreAsignatura = nombreAsignatura;
        this.nota = nota;
        this.fecha = fecha;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public double getNota() {
        return nota;
    }

    public String getFecha() {
        return fecha;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Intermediario{" + "nombreAlumno=" + nombreAlumno + ", nombreAsignatura=" + nombreAsignatura + ", nota=" + nota + ", fecha=" + fecha + '}';
    }
    
    
}
