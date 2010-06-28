/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Logica;

import java.awt.Image;

/**
 *
 * @author Grupo_05
 */
public class NodoListaCircular {

    private NodoListaCircular Siguiente;
    private NodoListaCircular Anterior;
    private Image Imagen;

    public NodoListaCircular(Image image){
        this.Imagen = image;
        this.Siguiente = null;
        this.Anterior = null;
    }

    public NodoListaCircular getAnterior() {
        return Anterior;
    }

    public void setAnterior(NodoListaCircular Anterior) {
        this.Anterior = Anterior;
    }

    public Image getImagen() {
        return Imagen;
    }

    public void setImagen(Image Imagen) {
        this.Imagen = Imagen;
    }

    public NodoListaCircular getSiguiente() {
        return Siguiente;
    }

    public void setSiguiente(NodoListaCircular Siguiente) {
        this.Siguiente = Siguiente;
    }
    
}
