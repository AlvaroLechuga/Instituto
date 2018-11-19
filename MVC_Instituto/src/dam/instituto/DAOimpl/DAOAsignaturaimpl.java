package dam.instituto.DAOimpl;

import dam.instituto.IDAO.IDAOAsignatura;
import dam.instituto.recursos.Asignatura;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOAsignaturaimpl implements IDAOAsignatura {

    private List<Asignatura> falsaBD;
    private static IDAOAsignatura dao = null;
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public DAOAsignaturaimpl() {
        conn = Conexion.getConnection();
    }

    @Override
    public int insertarAsignatura(Asignatura asignatura) {
        try {

            int codigo = asignatura.getCodigo();
            String nombre = asignatura.getNombre();
            int horas = asignatura.getHoras();

            String consulta = "INSERT INTO `asignatura` (`Codigo`, `Nombre_A`, `Horas`) VALUES ('" + codigo + "', '" + nombre + "', '" + horas + "')";

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
    public int modificarAsignatura(dam.instituto.recursos.Asignatura asignatura) {
        try {

            int codigo = asignatura.getCodigo();
            String nombre = asignatura.getNombre();
            int horas = asignatura.getHoras();

            String consulta = "UPDATE asignatura SET Nombre_A='" + nombre + "', Horas= " + horas + "  WHERE Codigo=" + codigo;

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
    public int EliminarAsignatura(int codigo) {

        String consulta = "DELETE FROM asignatura WHERE Codigo = '" + codigo + "'";
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

    public List<Asignatura> getAsignatura(int codigo) {

        try {

            String consulta = "SELECT * FROM asignatura WHERE Codigo = '" + codigo + "'";
            st = conn.createStatement();
            rs = st.executeQuery(consulta);
            imprimirConsulta(rs);
            rs.close();
            st.close();

        } catch (SQLException ex) {
            Logger.getLogger(DAOAlumnoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.falsaBD;
    }

    public static IDAOAsignatura getInstance() {
        if (dao == null) {
            dao = new DAOAsignaturaimpl();
        }

        return dao;
    }

    private void imprimirConsulta(ResultSet rs) {
        try {
            this.falsaBD = new ArrayList<Asignatura>();
            while (rs.next()) {
                falsaBD.add(new Asignatura(rs.getInt("Codigo"), rs.getString("Nombre_A"), rs.getInt("Horas")));
            }
        } catch (SQLException ex) {
            System.out.println("Error en el resultset");
            ex.printStackTrace();
        }
    }

    public List<Asignatura> getDatosAsignatura() {

        try {

            String consulta = "SELECT * FROM asignatura";
            st = conn.createStatement();
            rs = st.executeQuery(consulta);
            imprimirConsulta(rs);
            rs.close();
            st.close();

        } catch (SQLException ex) {
            Logger.getLogger(DAOAlumnoimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.falsaBD;
    }

}
