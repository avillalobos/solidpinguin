/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * Clase controlador de persistencia, encargada de gestionar y relacionar
 * la capa de lógica con la capa de persistencia, enviando los datos necesarios
 * con la mejor estructura posible, en este caso la estructura mas utilizada es
 * la de listas con nexoz o arraylist que contienen datos strings, y listas con nexo que contienen
 * hashmap con los datos para subir a la capa de lógica
 *
 * @author Grupo_05
 */
public class ControladorPersistencia {

    /**
     * @param ControladorAlmacenamiento controlador utilizado recoger los datos desde
     * los distintos almacenamientos utilizados
     */
    private ControladorAlmacenamiento ControladorAlmacenamiento;

    /**
     * Método constructor de la clase Controlador de Persistencia
     */
    public ControladorPersistencia() {
        this.ControladorAlmacenamiento = new ControladorAlmacenamiento();
    }

    /**
     * Método que retorna un ArrayList de imagenes para subir las imagenes obtenidas
     * a la capa de lógica y luego a la capa de interfaz para ser utilizadas
     *
     * @param component "Observador" de la imagen, quien supuestamente contendrá la imagen
     * requerida. El supuesto está en que no necesariamente quien utilizará la imagen es
     * el componente enviado por parámetros
     * @param path indica la ruta donde se encuentra el archivo
     * @return Un arraylist de imagenes para que en una sola estructura se contengan
     * una o varias imagenes, lo que da la posibilidad de poder agregar mas imagenes
     * para hacer la animación mas elegante
     */
    public ArrayList<Image> getImagenes(Component component, String path) throws Exception {
        return this.ControladorAlmacenamiento.getImagenes(component, path);
    }

    /**
     * Método que retorna un ArrayList de imagenes para subir las imagenes obtenidas
     * a la capa de lógica y luego a la capa de interfaz para ser utilizadas
     *
     * @param component "Observador" de la imagen, quien supuestamente contendrá la imagen
     * requerida. El supuesto está en que no necesariamente quien utilizará la imagen es
     * el componente enviado por parámetros
     * @param path indica la ruta donde se encuentra el archivo
     * @return Imagen que se encontraba en la ruta enviada por parámetros
     */
    public Image getImagen(Component component, String path) throws Exception {
        return this.ControladorAlmacenamiento.getImagen(component, path);
    }

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
        return this.ControladorAlmacenamiento.getMapa(path);
    }

    /**
     * Método que se conecta a la base de datos y obtiene los datos contenidos
     * en la tabla solicitada
     *
     * @param tabla indica la tabla donde se desea conectar
     */
    public ArrayList<HashMap> leerDB(String Tabla) {
        return this.ControladorAlmacenamiento.LeerBD(Tabla);
    }

    /**
     * Método que guarda en la base de datos la lista enviada por parámetros en la
     * tabla enviada por parámetros
     *
     * @param Tabla indica la tabla donde se va a insertar la información contenida en la lista
     * @param lista Datos a guardar en la tabla indicada
     * @return <li>true: Si se logran insertar los datos en la tabla indicada por parámnetros </li>
     *         <li>false: Si no se logran isnertar los datos en la tabla indicada por parámetros </li>
     */
    public boolean GuardarBD(String Tabla, List Datos) {
        return this.ControladorAlmacenamiento.GuardarBD(Tabla, Datos);
    }

    /**
     * Método que guarda toda la información relacionada con el usuario
     * en su tabla correspondiente en la base de datos
     *
     * @param List la información a guardar del usuario en su tabla
     * @return <li>true: Si se logran insertar los datos en la tabla usuarios </li>
     *         <li>false: Si no se logran isnertar los datos en la tabla usuarios </li>
     */
    public boolean GuardarUser(ArrayList List) {
        return this.ControladorAlmacenamiento.GuardarBD("usuarios", List);
    }
}
