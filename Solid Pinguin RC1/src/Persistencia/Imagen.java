/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.awt.Component;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

/**
 *
 * @author andrew
 */
public class Imagen {

    /**
     * @param path Dirección donde se debe buscar el archivo de imagen
     */
    private String path;

    /**
     * Constructor de la clase Imagen que retorna una imagen de acuerdo a un path determinado
     */
    public Imagen() {
        this.path = null;
    }

    /**
     * Método que de acuerdo a la dirección de la clase verifica si hay o no una imagen en
     * el lugar indicado y si la hay retorna la imagen y si no lanza la excepción correspondiente
     *
     * @param component Observador de la imagen
     * @return Una imagen en caso de que la ruta especificada sea la correcta y en caso contrario se lanzará una excepcion
     * @exception Si ocurre un error al cargar la imagen o un error porque no se encontro un imagen de tipo jpg o gif
     */
    public Image getImagen(Component component) throws Exception {
        // si no hay ruta para buscar la imagen entonces retorna null
        if (this.path == null) {
            return null;
        } else {
            Image image = Toolkit.getDefaultToolkit().getImage("Imagenes/" + this.path);
            Toolkit.getDefaultToolkit().getImage(this.path);
            MediaTracker mt = new MediaTracker(component);
            mt.addImage(image, 1);
            try {
                mt.waitForAll();
            } catch (InterruptedException e) {
                return null;
            }
            if (image.getWidth(component) == -1) {
                return null;
            } else {
                return image;
            }
        }
    }

    /**
     * Obtiene el path actual de la imagen
     * 
     * @return La dirección de la imagen
     */
    public String getPath() {
        return path;
    }

    /**
     * Se modifica el path asignado a la clase imagen
     *
     * @param path Dirección donde se supone esta la imagen
     */
    public void setPath(String path) {
        this.path = path;
    }
}
