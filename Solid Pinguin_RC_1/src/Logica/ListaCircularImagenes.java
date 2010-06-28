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
public class ListaCircularImagenes {

    private NodoListaCircular First;
    private NodoListaCircular Last;
    private NodoListaCircular Iterador;
    private int CantidadElementos;

    public ListaCircularImagenes(){
        this.First = null;
        this.Last = null;
        this.Iterador = null;
        this.CantidadElementos = 0;
    }

    public void Push(Image image){
        if(this.First == null){
            this.First = new NodoListaCircular(image);
            this.Last = this.First;
            this.First.setSiguiente(this.Last);
            this.First.setAnterior(this.Last);
            this.Last.setSiguiente(this.First);
            this.Last.setAnterior(this.First);
            this.Iterador = this.First;
            this.CantidadElementos++;
        }else{
            NodoListaCircular temp = new NodoListaCircular(image);
            temp.setSiguiente(this.First);
            temp.setAnterior(this.Last);
            this.Last.setSiguiente(temp);
            this.First.setAnterior(temp);
            this.First = temp;
            this.Iterador = this.First;
            this.CantidadElementos++;
        }
    }

    public Image getNext(){
        if(this.Iterador!= null){
            Image image = this.Iterador.getImagen();
            this.Iterador = this.Iterador.getSiguiente();
            return image;
        }else{
            return null;
        }
    }

    public Image getActual(){
        if(this.Iterador != null){
            return this.Iterador.getImagen();
        }else{
            return null;
        }
    }
}
