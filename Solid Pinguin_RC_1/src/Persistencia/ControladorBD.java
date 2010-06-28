package Persistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Clase que contiene todo lo necesario para conectarse con la base de datos.
 * Realiza el análisis de los datos obtenidos por la base de datos
 * 
 * @author Vladimir
 * @author Grupo_05
 */
public class ControladorBD {

    /**
     * @param Conexion Objeto encargado de realizar todas las conexiónes e interacciones
     * directas con la base de datos para luego ser procesadas y analizadas en esta clase
     */
    private Conexion Conexion;

    /**
     * Constructor de la clase ControladorBD que crea la conexión con los parámetros de sesión
     * para poder ingreasr a la base de datos
     */
    public ControladorBD() {
        Conexion = new Conexion("grupo05tp2010","grupo05tp2010","p4ss05","svnserver.disc.ucn.cl",5432);
    }

    /**
     * Método que realiza una conexión y una lectura de la base de datos para obtener
     * resultados de acuerdo a la tabla enviada por parámetros
     *
     * @param Tabla indica la tabla a la que se le desea hacer la consulta
     * @return Un arraylist de hashmpa en el caso de que encuentre o logre hacer la conexión
     * y retornará null en caso de que algo falle
     */
    public ArrayList<HashMap> LeerBD(String Tabla) {
        Conexion.conectar();
        String consulta = "select * from " + Tabla + ";";
        ArrayList<HashMap> listaDatos = Conexion.ejecutarConRetorno(consulta);
        Conexion.cerrar();
        return listaDatos;
    }

    /**
     * Método que guarda la información contenida en la lista en la tabla tabla
     * en la base de datos representada por conexión.
     *
     * @param Tabla indica en que lugar se desean guardar los datos
     * @param lista indica los datos a insertar en la tabla indicada
     * @return <li>true: si logra guardar correctamente todos los datos en la base de datos </li>
     *         <li>false: si ocurre cualquier error durante la carga de datos en la base de datos</li>
     */
    public boolean GuardarBD(String Tabla, List lista) {
        Conexion.conectar();
        String dat = "";
        Iterator itDatos = lista.iterator();
        while (itDatos.hasNext()) {

            HashMap datos = (HashMap) itDatos.next();
            int cantCampos = datos.size();
            Iterator it = datos.keySet().iterator();
            String camp = "";

            int cant = 0;
            while (it.hasNext()) {
                cant++;
                String campo = (String) it.next();
                String valor = datos.get(campo).toString();
                if (cant != cantCampos) {
                    camp = camp.concat(campo).concat(", ");
                    dat = dat.concat(valor).concat(", ");
                } else {
                    camp = camp.concat(campo);
                    dat = dat.concat(valor);
                }
            }

            String consulta = "insert into " + Tabla + " (" + camp + ") values (" + dat + ");";
            Conexion.ejecutarSinRetorno(consulta);
            Conexion.cerrar();
        }
        return true;
    }
}
