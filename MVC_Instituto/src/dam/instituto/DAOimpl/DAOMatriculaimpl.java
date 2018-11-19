package dam.instituto.DAOimpl;

import dam.instituto.IDAO.IDAOMatricula;
import dam.instituto.recursos.Matricula;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOMatriculaimpl implements IDAOMatricula {

    private List<Matricula> falsaBD;
    private static IDAOMatricula dao = null;
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public DAOMatriculaimpl() {
        conn = Conexion.getConnection();
    }

    @Override
    public int insertarMatricula(Matricula matricula) {
        try {

            String dni = matricula.getDniAlumno();
            int codigo = matricula.getCodigoAsignatura();
            double nota = matricula.getNota();
            String fecha = matricula.getFecha();

            String consulta = "INSERT INTO `matricula` (`DNI_Alumno`, `Cod_Asignatura`, `Nota`, `Fecha`) VALUES ('" + dni + "', '" + codigo + "', '" + nota + "', '" + fecha + "')";

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
    public int modificarMatricula(Matricula matricula) {
        try {

            String dni = matricula.getDniAlumno();
            int codigo = matricula.getCodigoAsignatura();
            String fecha = matricula.getFecha();
            
            String consulta = "UPDATE matricula " + "SET Fecha = '" + fecha + "' WHERE DNI_Alumno = '" + dni + "' AND " + "Cod_Asignatura =" + codigo;
            
            conn.setAutoCommit(false);
            
            st = conn.createStatement();
            st.executeUpdate(consulta);
            
            conn.commit();
            
            st.close();
            
            return 1;

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
        
    }

    @Override
    public int EliminarMatricula(String dni, int codigo) {

        String consulta = "DELETE FROM matricula WHERE DNI_Alumno = '" + dni + "' AND Cod_Asignatura =  '" + codigo + "'";
        try {

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
    public List<Matricula> getMatriculas(String dni) {

        try {

            String consulta = "SELECT * FROM matricula WHERE DNI_Alumno = '" + dni + "'";
            st = conn.createStatement();
            rs = st.executeQuery(consulta);
            imprimirConsulta(rs);
            rs.close();
            st.close();

        } catch (SQLException ex) {
            Logger.getLogger(DAOMatriculaimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.falsaBD;
    }

    public static IDAOMatricula getInstance() {
        if (dao == null) {
            dao = new DAOMatriculaimpl();
        }

        return dao;
    }

    private void imprimirConsulta(ResultSet rs) {
        try {
            this.falsaBD = new ArrayList<Matricula>();
            while (rs.next()) {
                falsaBD.add(new Matricula(rs.getString("DNI_Alumno"), rs.getInt("Cod_Asignatura"), rs.getDouble("Nota"), rs.getString("Fecha")));
            }
        } catch (SQLException ex) {
            System.out.println("Error en el resultset");
            ex.printStackTrace();
        }
    }

    public List<Matricula> getDatosMatricula() {

        try {

            String consulta = "SELECT * FROM matricula";
            st = conn.createStatement();
            rs = st.executeQuery(consulta);
            imprimirConsulta(rs);
            rs.close();
            st.close();

        } catch (SQLException ex) {
            Logger.getLogger(DAOMatriculaimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.falsaBD;
    }

    @Override
    public void SacarCodigo(String nombre, String nombre_A, double nota, String fecha) {

        try {
            int codigo = 0;
            String dni = "";
            String consulta = "SELECT DNI FROM alumno WHERE Nombre = '" + nombre + "'";
            st = conn.createStatement();
            rs = st.executeQuery(consulta);
            dni = Nombre(rs);
            consulta = "SELECT Codigo FROM asignatura WHERE Nombre_A = '" + nombre_A + "'";
            rs = st.executeQuery(consulta);
            codigo = Codigo(rs);
            consulta = "UPDATE matricula " + "SET Fecha = '" + fecha + "', " + "Nota =" + nota + " WHERE DNI_Alumno = '" + dni + "' AND " + "Cod_Asignatura =" + codigo;
            st.executeUpdate(consulta);
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAOMatriculaimpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private String Nombre(ResultSet rs) {
        try {
            String dni = "";
            while (rs.next()) {
                dni = rs.getString("DNI");

            }
            return dni;
        } catch (SQLException ex) {
            System.out.println("Error en el resultset");
            ex.printStackTrace();
            return "";
        }

    }

    public int Codigo(ResultSet rs) {
        try {
            int codigo = 0;
            while (rs.next()) {
                codigo = rs.getInt("Codigo");

            }
            return codigo;
        } catch (SQLException ex) {
            System.out.println("Error en el resultset");
            ex.printStackTrace();
            return -1;
        }
    }

}
