package Persistencia;

import java.awt.Component;
import java.awt.Image;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Controlador que comunica parte de la persistencia entre si, en este caso el enlace entre
 * la bd, los archivos y las imagenes
 * 
 * @author Grupo_05
 */
public class ControladorAlmacenamiento {

    /**
     * @param Archivo clase utilizada para leer los datos desde el archivo, en este caso
     * el mapa
     */
    private Archivo Archivo;
    /**
     * @param Imagen clase que se instancia una vez y luego se le cambia la dirección o path
     * de la imagen para poder obtener nuevas imagenes al igual que el archivo si asi se
     * necesitase
     */
    private Imagen Imagen;
    /**
     * @param ControladorBD clase que regula todo el flujo de datos entre la base de datos
     * y el juego en cuanto a los datos de usuario y estadisticas asociadas
     */
    private ControladorBD ControladorBD;

    public ControladorAlmacenamiento() {
        System.out.println("Propiedad offline = " + System.getProperty("BaseDato"));
        if (!System.getProperty("BaseDato").equalsIgnoreCase("OFFLINE")) {
            this.ControladorBD = new ControladorBD();
        }
        this.Archivo = new Archivo();
        this.Imagen = new Imagen();

    }


    /*
     * Inicio bloque de obtención de Imagenes
     * -------------------------------------------------------------------------
     */
    /**
     * El método getImagenes retorna un arraylist con todas las imagenes econtradas con el mismo patrón para
     * no dejar estática la cantidad de imagenes guardadas en un arreglo estático, se decidió utilizar
     * un ArrayList para transportar las imagenes, entonces si hay una o muchas no importa, pero estan contenidas
     * todas en una misma estructura de datos
     *
     * @param component Es el objeto que esta "mirando" a la imagen, que en este caso deberia ser la clase VistaPersonaje
     * @param path Inidica la dirección de la Imagen. Los nombres de los archivos estan definidos de la siguiente manera
     *          Nombre_iteración.gif
     */
    public ArrayList<Image> getImagenes(Component component, String path) throws Exception {

        ArrayList<Image> lista = new ArrayList<Image>();
        int i = 0;
        this.Imagen.setPath(path + "_" + i + ".gif");
        while (this.Imagen.getImagen(component) != null) {
            lista.add(this.Imagen.getImagen(component));
            i++;
            this.Imagen.setPath(path + "_" + i + ".gif");
        }
        return lista;
    }

    /**
     * Método que retorna una imagen de acuerdo al observador y a una ruta enviada por parametros
     *
     * @param component indica la clase que esta "observando" la imagen, en este caso la imagen donde
     * supuestamente se irá a insertar
     *
     * @param path indica la ruta de la imagen, solo viene el nombre, en esta parte se le agrega
     * el "iterador" que es el número de la imagen para obtener todas las imagenes que coincidan con
     * el nombre como una expresión regular.
     *
     * @return La imagen indicada en la ruta, null en caso no existir una imagen que coincida
     * con todos los parámetros
     */
    public Image getImagen(Component component, String path) throws Exception {
        this.Imagen.setPath(path + "_0" + ".gif");
        return this.Imagen.getImagen(component);
    }

    /**
     * Método que retorna la ruta de la imagen actual
     *
     * @return un string que contiene la ruta de la última imagen pedida
     */
    public String getPathImagen() {
        return this.Imagen.getPath();
    }


    /*
     * Fin bloque de obtención de Imagenes
     * -------------------------------------------------------------------------
     */
    /*
     * Inicio bloque de obtención de datos desde persistencia
     * -------------------------------------------------------------------------
     */
    /**
     * Método que retorna una lista con nexo, esta representa en la primera linea
     * el largo y ancho de la matriz, y desde la segunda linea en adelante estan
     * los códigos para formar el mapa, dentro del archivo no viene solo un mapa
     * vienen muchos, por lo que este lista esta formada de la siguiente manera
     *
     *      y,x
     *      matriz
     *      y,x
     *      matriz
     *      ...
     *
     * @param path ruta donde se encuentra ubicado el archivo de mapa
     * @return Una lista con nexo que representa un conjunto de mapas a cargar
     * dentro del nivel
     */
    public LinkedList<String> getMapa(String path) throws FileNotFoundException, IOException, Exception {
        if (this.Archivo.setPath(path)) {
            return this.Archivo.LeerCompleto();
        } else {
            return null;
        }

    }

    /*
     * Fin bloque de obtención de datos desde archivo
     * -------------------------------------------------------------------------
     */
    /*
     * Inicio bloque de obtención de datos desde Base de datos
     * -------------------------------------------------------------------------
     */
    /**
     * Método que se conecta a la base de datos y obtiene los datos contenidos
     * en la tabla solicitada
     *
     * @param tabla indica la tabla donde se desea conectar
     */
    public ArrayList<HashMap> LeerBD(String tabla) {
        if (this.ControladorBD == null) {
            return null;
        } else {
            return this.ControladorBD.LeerBD(tabla);
        }

    }

    /**
     * Método que guarda en la base de datos la lista enviada por parámetros en la
     * tabla enviada por parámetros
     *
     * @param Tabla indica la tabla donde se va a insertar la información contenida en la lista
     * @param lista Datos a guardar en la tabla indicada
     */
    public boolean GuardarBD(String Tabla, List lista) {
        if (this.ControladorBD == null) {
            return false;
        } else {
            return this.ControladorBD.GuardarBD(Tabla, lista);
        }
    }
    /*
     * Fin bloque de obtención de datos desde Base de datos
     * -------------------------------------------------------------------------
     */
}
