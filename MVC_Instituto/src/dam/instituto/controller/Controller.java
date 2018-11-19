package dam.instituto.controller;

import dam.instituto.DAOimpl.DAOAlumnoimpl;
import dam.instituto.DAOimpl.DAOAsignaturaimpl;
import dam.instituto.DAOimpl.DAOMatriculaimpl;
import dam.instituto.recursos.Alumno;
import dam.instituto.recursos.Asignatura;
import dam.instituto.recursos.Intermediario;
import dam.instituto.recursos.Matricula;
import dam.instituto.vista.FormAlumno;
import dam.instituto.vista.FormAsignatura;
import dam.instituto.vista.FormCalificarAlumno;
import dam.instituto.vista.FormDatosAlumno;
import dam.instituto.vista.FormDatosAsignatura;
import dam.instituto.vista.FormDatosMatricula;
import dam.instituto.vista.FormMatricula;
import dam.instituto.vista.FormNotasAlumno;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Controller {

    public static boolean insertarAlumno(FormAlumno frmAlumno, JTable tablaVehiculos) {
        boolean borrado = false;
        Alumno alumno = new Alumno();

        alumno.setDni(frmAlumno.getTxtDniAlumno().getText());

        alumno.setNombre(frmAlumno.getTxtNombreAlumno().getText());

        alumno.setDireccion(frmAlumno.getTxtDireccionAlumno().getText());

        if (DAOAlumnoimpl.getInstance().insertarAlumno(alumno) != 0) {
            borrado = true;
        }

        frmAlumno.getTxtDniAlumno().setText("");
        frmAlumno.getTxtNombreAlumno().setText("");
        frmAlumno.getTxtDireccionAlumno().setText("");
        JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
        return borrado;
    }

    public static boolean insertarAsignatura(FormAsignatura frmAsignatura, JTable tablaVehiculos) {
        boolean borrado = false;
        Asignatura asignatura = new Asignatura();

        asignatura.setCodigo(Integer.parseInt(frmAsignatura.getTxtCodigoAsignatura().getText()));

        asignatura.setNombre(frmAsignatura.getTxtNombreAsignatura().getText());

        asignatura.setHoras(Integer.parseInt(frmAsignatura.getTxtHorasAsignatura().getText()));

        if (DAOAsignaturaimpl.getInstance().insertarAsignatura(asignatura) != 0) {
            borrado = true;
            JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
        }

        frmAsignatura.getTxtCodigoAsignatura().setText("");
        frmAsignatura.getTxtNombreAsignatura().setText("");
        frmAsignatura.getTxtHorasAsignatura().setText("");
        
        return borrado;
    }

    public static boolean insertarMatricula(FormMatricula frmMatricula, JTable tablaVehiculos) {
        boolean borrado = false;
        Matricula matricula = new Matricula();

        matricula.setDniAlumno((frmMatricula.getTxtDniAlumno().getText()));

        matricula.setCodigoAsignatura(Integer.parseInt(frmMatricula.getTxtCodigoAsignatura().getText()));

        matricula.setNota(0);

        matricula.setFecha(frmMatricula.getTxtFecha().getText());

        if (DAOMatriculaimpl.getInstance().insertarMatricula(matricula) != 0) {
            borrado = true;
            JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
        }

        frmMatricula.getTxtDniAlumno().setText("");
        frmMatricula.getTxtCodigoAsignatura().setText("");
        frmMatricula.getTxtFecha().setText("");
        
        return borrado;
    }

    public static boolean eliminarAlumno(FormAlumno frmAlumno, JTable tablaVehiculos) {
        boolean borrado = false;
        String dni = "";

        Alumno alumno = new Alumno();

        if (tablaVehiculos.getSelectedColumn() != -1) {
            dni = (String) tablaVehiculos.getValueAt(tablaVehiculos.getSelectedRow(), 0);
            alumno.setDni(dni);
            alumno.setNombre("");
            alumno.setDireccion("");
        }

        if (DAOAlumnoimpl.getInstance().EliminarAlumno(alumno.getDni()) == 0) {
            borrado = true;
            cargarTablaAlumno(frmAlumno, tablaVehiculos);
            JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
        }

        frmAlumno.getTxtBusquedaAlumno().setText("");
        
        return borrado;
    }

    public static boolean eliminarAsignatura(FormAsignatura frAsignatura, JTable tablaVehiculos) {
        boolean borrado = false;

        int codigo = 0;

        Asignatura asignatura = new Asignatura();

        if (tablaVehiculos.getSelectedColumn() != -1) {
            codigo = (int) tablaVehiculos.getValueAt(tablaVehiculos.getSelectedRow(), 0);
            asignatura.setCodigo(codigo);
            asignatura.setNombre("");
            asignatura.setHoras(0);
        }

        if (DAOAsignaturaimpl.getInstance().EliminarAsignatura(asignatura.getCodigo()) == 0) {
            borrado = true;
            cargarTablaAsignatura(frAsignatura, tablaVehiculos);
            JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
        }

        frAsignatura.getTxtBusquedaAsignatura().setText("");
        return borrado;
    }

    public static boolean eliminarMatricula(FormMatricula frMatricula, JTable tablaVehiculos) {
        boolean borrado = false;

        String dni = "";
        int codigo = 0;
        Matricula matricula = new Matricula();

        if (tablaVehiculos.getSelectedColumn() != -1) {
            dni = (String) tablaVehiculos.getValueAt(tablaVehiculos.getSelectedRow(), 0);
            codigo = (int) tablaVehiculos.getValueAt(tablaVehiculos.getSelectedRow(), 1);
            matricula.setDniAlumno(dni);
            matricula.setCodigoAsignatura(codigo);
            matricula.setNota(0);
            matricula.setFecha("");
        }

        if (DAOMatriculaimpl.getInstance().EliminarMatricula(dni, codigo) == 0) {
            borrado = true;
            cargarTablaMatricula(frMatricula, tablaVehiculos);
            JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
        }

        frMatricula.getTxtBusquedaAsignatura().setText("");
        return borrado;
    }

    public static void cargarTablaAlumno(FormAlumno frAlumno, JTable tablaVehiculos) {
        List<Alumno> lstAlumno = DAOAlumnoimpl.getInstance().getAlumno(frAlumno.getTxtBusquedaAlumno().getText());

        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 1 || column == 2) {
                    return true;
                }
                return false;
            }
        };

        modelo.addColumn("DNI");

        modelo.addColumn("Nombre");

        modelo.addColumn("Direccion");

        for (Alumno alumno : lstAlumno) {

            Object[] registroLeido
                    = {
                        alumno.getDni(),
                        alumno.getNombre(),
                        alumno.getDireccion()

                    };

            modelo.addRow(registroLeido);

        }

        tablaVehiculos.setModel(modelo);

    }

    public static void cargarTablaAsignatura(FormAsignatura frAsignatura, JTable tablaVehiculos) {
        List<Asignatura> lstAsignatura = DAOAsignaturaimpl.getInstance().getAsignatura(Integer.parseInt(frAsignatura.getTxtBusquedaAsignatura().getText()));

        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 1 || column == 2) {
                    return true;
                }
                return false;
            }
        };

        modelo.addColumn("Codigo");

        modelo.addColumn("Nombre");

        modelo.addColumn("Horas");

        for (Asignatura asignatura : lstAsignatura) {

            Object[] registroLeido
                    = {
                        asignatura.getCodigo(),
                        asignatura.getNombre(),
                        asignatura.getHoras()

                    };

            modelo.addRow(registroLeido);

        }

        tablaVehiculos.setModel(modelo);
    }

    public static void cargarTablaMatricula(FormMatricula frMatricula, JTable tablaVehiculos) {
        List<Matricula> lstMatricula = DAOMatriculaimpl.getInstance().getMatriculas(frMatricula.getTxtBusquedaAsignatura().getText());

        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                
                if(column == 2){
                    return true;
                }
                
                return false;
            }
        };

        modelo.addColumn("DNI_Alumno");

        modelo.addColumn("Cod_Asignatura");

        modelo.addColumn("Fecha");

        for (Matricula matricula : lstMatricula) {

            Object[] registroLeido
                    = {
                        matricula.getDniAlumno(),
                        matricula.getCodigoAsignatura(),
                        matricula.getFecha()

                    };

            modelo.addRow(registroLeido);

        }

        tablaVehiculos.setModel(modelo);

    }

    public static boolean modificarAlumno(FormAlumno frmAlumno, JTable tablaVehiculos) {
        boolean borrado = false;

        String dni = "";
        String nombre = "";
        String direccion = "";

        Alumno alumno = new Alumno();

        if (tablaVehiculos.getSelectedColumn() != -1) {
            dni = (String) tablaVehiculos.getValueAt(tablaVehiculos.getSelectedRow(), 0);
            nombre = (String) tablaVehiculos.getValueAt(tablaVehiculos.getSelectedRow(), 1);
            direccion = (String) tablaVehiculos.getValueAt(tablaVehiculos.getSelectedRow(), 2);
            alumno.setDni(dni);
            alumno.setNombre(nombre);
            alumno.setDireccion(direccion);

        }

        if (DAOAlumnoimpl.getInstance().modificarAlumno(alumno) == 1) {
            borrado = true;
        }
        JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
        return borrado;

    }

    public static boolean modificarAsignatura(FormAsignatura frmAlumno, JTable tablaVehiculos) {
        boolean borrado = false;

        int codigo = 0;
        String nombre = "";
        int horas = 0;

        Asignatura asignatura = new Asignatura();

        if (tablaVehiculos.getSelectedColumn() != -1) {
            codigo = (int) tablaVehiculos.getValueAt(tablaVehiculos.getSelectedRow(), 0);
            nombre = (String) tablaVehiculos.getValueAt(tablaVehiculos.getSelectedRow(), 1);
            horas = (int) tablaVehiculos.getValueAt(tablaVehiculos.getSelectedRow(), 2);
            asignatura.setCodigo(codigo);
            asignatura.setNombre(nombre);
            asignatura.setHoras(horas);

        }

        if (DAOAsignaturaimpl.getInstance().modificarAsignatura(asignatura) == 1) {
            borrado = true;
            JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
        }
        
        return borrado;

    }

    public static boolean modificarMatricula(FormMatricula frMatricula, JTable tablaVehiculos) {
        boolean borrado = false;

        String dni = "";
        int codigo = 0;
        double nota = 0;
        String fecha = "";

        Matricula matricula = new Matricula();

        if (tablaVehiculos.getSelectedColumn() != -1) {

            dni = (String) tablaVehiculos.getValueAt(tablaVehiculos.getSelectedRow(), 0);
            codigo = (int) tablaVehiculos.getValueAt(tablaVehiculos.getSelectedRow(), 1);
            fecha = (String) tablaVehiculos.getValueAt(tablaVehiculos.getSelectedRow(), 2);
            nota = 0;

            matricula.setDniAlumno(dni);
            matricula.setCodigoAsignatura(codigo);
            matricula.setNota(nota);
            matricula.setFecha(fecha);

        }

        if (DAOMatriculaimpl.getInstance().modificarMatricula(matricula) == 1) {
            borrado = true;
            JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
        }
        
        return borrado;

    }

    public static void cargarDatosAlumno(FormDatosAlumno frAlumno, JTable tablaVehiculos) {
        List<Alumno> lstAlumno = DAOAlumnoimpl.getInstance().getDatosAlumno();

        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        modelo.addColumn("DNI");

        modelo.addColumn("Nombre");

        modelo.addColumn("Direccion");

        for (Alumno alumno : lstAlumno) {

            Object[] registroLeido
                    = {
                        alumno.getDni(),
                        alumno.getNombre(),
                        alumno.getDireccion()

                    };

            modelo.addRow(registroLeido);

        }

        tablaVehiculos.setModel(modelo);

    }

    public static void cargarDatosAsignatura(FormDatosAsignatura frAsignatura, JTable tablaVehiculos) {
        List<Asignatura> lstAsignatura = DAOAsignaturaimpl.getInstance().getDatosAsignatura();

        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        modelo.addColumn("Codigo");

        modelo.addColumn("Nombre");

        modelo.addColumn("Horas");

        for (Asignatura asignatura : lstAsignatura) {

            Object[] registroLeido
                    = {
                        asignatura.getCodigo(),
                        asignatura.getNombre(),
                        asignatura.getHoras()

                    };

            modelo.addRow(registroLeido);

        }

        tablaVehiculos.setModel(modelo);
    }

    public static void cargarDatosMatricula(FormDatosMatricula frMatricula, JTable tablaVehiculos) {
        List<Matricula> lstMatricula = DAOMatriculaimpl.getInstance().getDatosMatricula();

        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        modelo.addColumn("DNI_Alumno");

        modelo.addColumn("Cod_Asignatura");

        modelo.addColumn("Nota");

        modelo.addColumn("Fecha");

        for (Matricula matricula : lstMatricula) {

            Object[] registroLeido
                    = {
                        matricula.getDniAlumno(),
                        matricula.getCodigoAsignatura(),
                        matricula.getNota(),
                        matricula.getFecha()

                    };

            modelo.addRow(registroLeido);

        }

        tablaVehiculos.setModel(modelo);

    }

    public static void CargarCalificar(FormCalificarAlumno frCalificarAlumno, JTable tablaVehiculos) {
        List<Intermediario> lsIntermediario = DAOAlumnoimpl.getInstance().getCalificacion(frCalificarAlumno.getTxtDniCalificarAlumno().getText());

        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 2) {
                    return true;
                }
                return false;
            }
        };

        modelo.addColumn("Nombre Alumno");

        modelo.addColumn("Asignatura");

        modelo.addColumn("Nota");

        modelo.addColumn("Fecha");

        for (Intermediario intermediario : lsIntermediario) {

            Object[] registroLeido
                    = {
                        intermediario.getNombreAlumno(),
                        intermediario.getNombreAsignatura(),
                        intermediario.getNota(),
                        intermediario.getFecha()

                    };

            modelo.addRow(registroLeido);

        }

        tablaVehiculos.setModel(modelo);

    }

    public static void CargarCalificar2(FormNotasAlumno frNotasAlumno, JTable tablaVehiculos) {
        List<Intermediario> lsIntermediario = DAOAlumnoimpl.getInstance().getCalificacion(frNotasAlumno.getTxtDniNotasAlumno().getText());

        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        modelo.addColumn("Nombre Alumno");

        modelo.addColumn("Asignatura");

        modelo.addColumn("Nota");

        modelo.addColumn("Fecha");

        for (Intermediario intermediario : lsIntermediario) {

            Object[] registroLeido
                    = {
                        intermediario.getNombreAlumno(),
                        intermediario.getNombreAsignatura(),
                        intermediario.getNota(),
                        intermediario.getFecha()

                    };

            modelo.addRow(registroLeido);

        }

        tablaVehiculos.setModel(modelo);

    }

    public static void ModificarCalificacion(FormCalificarAlumno frCalificarAlumno, JTable tablaVehiculos) {

        String nombre;
        String nombre_A;
        double nota;
        String fecha;

        if (tablaVehiculos.getSelectedColumn() != -1) {

            nombre = (String) tablaVehiculos.getValueAt(tablaVehiculos.getSelectedRow(), 0);
            nombre_A = (String) tablaVehiculos.getValueAt(tablaVehiculos.getSelectedRow(), 1);
            nota = Double.parseDouble((String) tablaVehiculos.getValueAt(tablaVehiculos.getSelectedRow(), 2));
            fecha = (String) tablaVehiculos.getValueAt(tablaVehiculos.getSelectedRow(), 3);

            DAOMatriculaimpl.getInstance().SacarCodigo(nombre, nombre_A, nota, fecha);
            frCalificarAlumno.getTxtDniCalificarAlumno().setText("");
            JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
        }

    }

}
