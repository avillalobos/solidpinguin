package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Clase que controla el acceso y la conexión a la base de datos entregando de ella un
 * arraylist
 *
 * @author Vladimir
 * @author Grupo_05
 */
public class Conexion {

    /**
     * @param nombreBD indica el nombre de la Base de datos que usaremos con esta conexión
     */
    private String nombreBD;
    /**
     * @param usuario indica el usuario con el que nos conectaremos a la base de datos
     */
    private String usuario;
    /**
     * @param password indica la contraseña que se nos asigno para la cuenta en la base de datos
     */
    private String password;
    /**
     * @param server Servidor donde se encuentra nuestra base de datos
     */
    private String server;
    /**
     * @param port indica el puerto por donde nos conectaremos a la base de datos
     */
    private int port;
    /**
     * @param conexion indica el controlador que utilizaremos para mantener la conexión a la base de datos
     */
    private Connection conexion;

    /**
     * Constructor de la clase conexión, que instancia, realiza o mantiene una conexión directa con la
     * base de datos
     *
     * @param nombreBD indica el nombre de la base de datos
     * @param usuarioBD indica el nombre de la cuenta asignada a la conexión
     * @param password indica la contraseña con la que nos podemos conectar a la base de datos
     * @param server indica el servidor que hospeda nuestra base de datos
     * @param port indica el puerto por donde nos conectamos a la base de datos
     */
    public Conexion(String nombreBD, String usuarioBD, String password, String server, int port) {
        this.nombreBD = nombreBD;
        this.usuario = usuarioBD;
        this.password = password;
        this.server = server;
        this.port = port;

    }

    /**
     * Método que permite conectarse a la base de datos
     */
    public boolean conectar() {
        this.conexion = null;
        try {
            // Get connection
            Class.forName("org.postgresql.Driver");
            String URL = "jdbc:postgresql://" + this.server + ":" + this.port + "/" + this.nombreBD;
            this.conexion = DriverManager.getConnection(URL, this.usuario, this.password);

            if (this.conexion != null) {
                // Meta data no utilizado por lo tanto fue suprimido de este if
                return true;
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (ClassNotFoundException cnf) {
            cnf.printStackTrace();
        }
        return false;

    }

    /**
     * Cierra la conexion a la bd.
     *
     * @return verdadero si la operacion se realza exitosamente, falso en tros caso.
     */
    public boolean cerrar() {
        try {
            this.conexion.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * Ejecuta una query  en la bd y no espera un resultado de la operacion.
     *
     * @param query a ejecutar en la bd.
     * @return <li>true: si la operacion se realiza exitosamente</li>
     *         <li>false: si la opreacion no se realiza exitosamente </li>
     */
    public boolean ejecutarSinRetorno(String query) {
        System.out.println("Query = " + query);
        boolean result = true;
        if (this.conexion != null) {

            try {
                result = this.conexion.createStatement().execute(query);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Ejecuta una query en la bd, y espera un retorno desde la misma de acuerdo
     * a la query ejecutada.
     * @param query que se ejecuta en la bd.
     * @return un listado de mapas que contienen los datos de los objetos a ser
     * instanciados.
     */
    public ArrayList<HashMap> ejecutarConRetorno(String query) {
        ArrayList<HashMap> listado = null;

        if (this.conexion != null) {
            ResultSet result = null;
            try {
                result = this.conexion.createStatement().executeQuery(query);
                if (result != null) {
                    boolean valido = result.next();
                    listado = new ArrayList<HashMap>();
                    while (valido) {
                        HashMap<String, String> tupla = new HashMap<String, String>();
                        for (int i = result.getMetaData().getColumnCount(); i > 0; i--) {
                            String valorCampo = result.getString(i);
                            String nombreCampo = result.getMetaData().getColumnName(i);
                            System.out.println("NombreCampo:" + nombreCampo + "valorCampo" + valorCampo);
                            tupla.put(nombreCampo, valorCampo);
                        }
                        listado.add(tupla);
                        valido = result.next();
                    }
                    result.close();
                    return listado;
                }else{
                    result.close();
                    return null;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return null;
            }

        }else{
            return null;
        }
    }


    //Creacion de una nueva tabla
    public void createTabla(String NomTabla) throws SQLException {
        if (this.conexion != null) {

            String createString;
            createString = "create table " + NomTabla + " (" + "Rut VARCHAR(10), " + "Cantidad INTEGER," + "Moneda VARCHAR(15)" + ");";
            try {
                ResultSet rs = this.conexion.createStatement().executeQuery(createString);
            } catch (SQLException ex) {
                throw ex;
                //System.err.println("SQLException: " + ex.getMessage());
            }

        }
    }
}
