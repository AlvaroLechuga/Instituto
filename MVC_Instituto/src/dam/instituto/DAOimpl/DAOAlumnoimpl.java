package dam.instituto.DAOimpl;

import dam.instituto.IDAO.IDAOAlumno;
import dam.instituto.recursos.Alumno;
import dam.instituto.recursos.Intermediario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOAlumnoimpl implements IDAOAlumno {

    private List<Alumno> falsaBD;
    private List<Intermediario> falsaBD2;
    private static IDAOAlumno dao = null;

    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public DAOAlumnoimpl() {

        conn = Conexion.getConnection();

    }

    @Override
    public int insertarAlumno(Alumno alumno) {

        try {

            String dni = alumno.getDni();
            String nombre = alumno.getNombre();
            String direccion = alumno.getDireccion();

            String consulta = "INSERT INTO `alumno` (`DNI`, `Nombre`, `Direccion`) VALUES ('" + dni + "', '" + nombre + "', '" + direccion + "')";

            conn.setAutoCommit(false);
            st = conn.createStatement();
            st.executeUpdate(consulta);

            conn.rollback();

            st.close();

        } catch (SQLException ex) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException sqle) {
                    System.out.println(sqle.toString());
                }
            }
            return 0;
        }
        return 1;
    }

    @Override
    public int modificarAlumno(Alumno alumno) {

        try {

            String nombre = alumno.getNombre();
            String direccion = alumno.getDireccion();
            String dni = alumno.getDni();

            String consulta = "UPDATE alumno SET Nombre='" + nombre + "', Direccion='" + direccion + "' WHERE DNI='" + dni + "'";

            conn.setAutoCommit(false);

            st = conn.createStatement();
            st.executeUpdate(consulta);

            conn.commit();

            st.close();

        } catch (SQLException ex) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException sqle) {
                    System.out.println(sqle.toString());
                }
            }
            return 0;
        }
        return 1;
    }

    @Override
    public int EliminarAlumno(String dni) {
        try {

            String consulta = "DELETE FROM alumno WHERE DNI = '" + dni + "'";

            conn.setAutoCommit(false);

            st = conn.createStatement();
            int confirmar = st.executeUpdate(consulta);
            if (confirmar == 1) {
                conn.commit();
                return 0;

            } else {
                conn.rollback();
                return 1;
            }

        } catch (SQLException ex) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException sqle) {
                    System.out.println(sqle.toString());
                }
            }
            return 1;
        }

    }

    @Override
    public List<Alumno> getAlumno(String dni) {
        try {

            String consulta = "SELECT * FROM alumno WHERE DNI = '" + dni + "'";
            st = conn.createStatement();
            rs = st.executeQuery(consulta);
            imprimirConsulta(rs);
            st.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(DAOAlumnoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.falsaBD;
    }

    public static IDAOAlumno getInstance() {
        if (dao == null) {
            dao = new DAOAlumnoimpl();
        }

        return dao;
    }

    private void imprimirConsulta(ResultSet rs) {
        try {
            this.falsaBD = new ArrayList<Alumno>();
            while (rs.next()) {
                falsaBD.add(new Alumno(rs.getString("DNI"), rs.getString("Nombre"), rs.getString("Direccion")));
            }
        } catch (SQLException ex) {
            System.out.println("Error en el resultset");
            ex.printStackTrace();
        }
    }

    public List<Alumno> getDatosAlumno() {
        try {

            String consulta = "SELECT * FROM alumno";
            st = conn.createStatement();
            rs = st.executeQuery(consulta);
            imprimirConsulta(rs);
            st.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(DAOAlumnoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.falsaBD;
    }

    public List<Intermediario> getCalificacion(String dni) {

        try {
            String consulta = "SELECT alumno.Nombre, asignatura.Nombre_A, matricula.Nota, matricula.Fecha FROM ((alumno INNER JOIN matricula ON alumno.DNI = matricula.DNI_Alumno) INNER JOIN asignatura ON matricula.Cod_Asignatura = asignatura.Codigo) WHERE DNI = '" + dni + "'";
            st = conn.createStatement();
            rs = st.executeQuery(consulta);
            imprimirConsulta2(rs);
            st.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(DAOAlumnoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return falsaBD2;
    }

    private void imprimirConsulta2(ResultSet rs) {
        try {
            this.falsaBD2 = new ArrayList<Intermediario>();
            while (rs.next()) {
                falsaBD2.add(new Intermediario(rs.getString("Nombre"), rs.getString("Nombre_A"), rs.getDouble("Nota"), rs.getString("Fecha")));
            }
        } catch (SQLException ex) {
            System.out.println("Error en el resultset");
            ex.printStackTrace();
        }
    }
}
