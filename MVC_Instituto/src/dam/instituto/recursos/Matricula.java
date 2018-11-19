package dam.instituto.recursos;

import java.util.Date;

public class Matricula {
    
    private String dniAlumno;
    private int codigoAsignatura;
    private double nota;
    private String fecha;

    public Matricula() {
    }

    public Matricula(String dniAlumno, int codigoAsignatura, double nota, String fecha) {
        this.dniAlumno = dniAlumno;
        this.codigoAsignatura = codigoAsignatura;
        this.nota = nota;
        this.fecha = fecha;
    }

    public String getDniAlumno() {
        return dniAlumno;
    }

    public int getCodigoAsignatura() {
        return codigoAsignatura;
    }

    public double getNota() {
        return nota;
    }

    public String getFecha() {
        return fecha;
    }

    public void setDniAlumno(String dniAlumno) {
        this.dniAlumno = dniAlumno;
    }

    public void setCodigoAsignatura(int codigoAsignatura) {
        this.codigoAsignatura = codigoAsignatura;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Matricula{" + "dniAlumno=" + dniAlumno + ", codigoAsignatura=" + codigoAsignatura + ", nota=" + nota + ", fecha=" + fecha + '}';
    }
    
    
}
