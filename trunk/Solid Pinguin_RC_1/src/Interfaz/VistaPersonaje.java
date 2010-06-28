/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Logica.ListaCircularImagenes;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author Grupo_05
 */
public class VistaPersonaje extends Nodo implements Runnable {

    /**
     * @param Arriba Imagen que representa al personaje cuando esta subiendo por el escenario
     */
    private ListaCircularImagenes Arriba;
    /**
     * @param Abajo[] Vector de imagenes que representan los distintos estados de caminata que tiene el personaje en descenso
     */
    private ListaCircularImagenes Abajo;
    /**
     * @param Izquierda[] Vector de imagenes que representan los distintos estados de caminata que tiene el personaje hacia la izquierda
     */
    private ListaCircularImagenes Izquierda;
    /**
     * @param Derecha[] Vector de imagenes que representan los distintos estados de caminata que tiene el personaje hacia la derecha
     */
    private ListaCircularImagenes Derecha;
    /**
     * @param Stop Imagen que representa el personaje cuando se encuentra detenido
     */
    private ListaCircularImagenes Stop;

    private ListaCircularImagenes Muerte;
    private int Accion;

    private int Tiempo;

    /**
     * Constructor de la clase VistaPersonaje, este constructor crea o instancia su padre, dado que esta clase es una extensión de
     * la clase nodo
     */
    public VistaPersonaje() {
        super(0);
        this.Tiempo = 10;
    }

    private int contador;

    public void setImagenes(ListaCircularImagenes Arriba, ListaCircularImagenes Abajo, ListaCircularImagenes Izquierda, ListaCircularImagenes Derecha, ListaCircularImagenes Stop, ListaCircularImagenes Muerte) {
        this.Arriba = Arriba;
        this.Abajo = Abajo;
        this.Izquierda = Izquierda;
        this.Derecha = Derecha;
        this.Stop = Stop;
        this.Muerte = Muerte;
        this.Accion = 0;
        this.contador = 0;
    }

    

    @Override
    public void run() {
        Actualizar = false;
        this.contador = 0;
        this.repaint();
        while (true) {
            try {
                Thread.sleep(this.Tiempo);
            } catch (InterruptedException x) {
                // ignore
            }
            if (Actualizar) {
                this.contador++;
                this.repaint();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        // se usa Graphics2D porque la calidad de imagen es mejor
        Graphics2D g2D = (Graphics2D) g;
        Image imagen = getImage();
        if (buffered) {
            // se crea un buffer de imagen de tamaño igual al canvas, obtiene la propiedad de canvas
            // de largo y ancho para crear el buffer del mismo tamaño
            BufferedImage bi = (BufferedImage) createImage(getWidth(), getHeight());

            // Draw into the memory buffer. Dibuja cada columna

            for (int i = 0; i < getWidth(); i = i + imagen.getWidth(this)) {

                for (int j = 0; j < getHeight(); j = j + imagen.getHeight(this)) {

                    bi.createGraphics().drawImage(imagen, i, j, this);

                }
            }
            // Draw the buffered Image on to the screen
            g2D.drawImage(bi, 0, 0, this);
        } // This block of code draws the texture directly onto the screen.
        else if (!buffered) {
            // carga la imgagen con el método g2D.drawImage(Imagen,x,y,el observador)
            for (int i = 0; i < getWidth(); i = i + imagen.getWidth(this)) {
                for (int j = 0; j < getHeight(); j = j + imagen.getHeight(this)) {
                    g2D.drawImage(imagen, i, j, this);
                }
            }
        }
    }

    // accion -1 significa muerte subita del personaje xD!!
    public Image getImage() {
        switch (this.Accion) {
            case -1:
                return this.Muerte.getNext();
            case 37:
                if(contador == 4){
                    this.contador = 0;
                    return this.Izquierda.getNext();
                }else{
                    return this.Izquierda.getActual();
                }
            case 38:
                if(contador == 4){
                    this.contador = 0;
                    return this.Arriba.getNext();
                }else{
                    return this.Arriba.getActual();
                }
            case 39:
                if(this.contador == 4){
                    this.contador = 0;
                    return this.Derecha.getNext();
                }else{
                    return this.Derecha.getActual();
                }
            case 40:
                if(this.contador == 4){
                    this.contador = 0;
                    return this.Abajo.getNext();
                }else{
                    return this.Abajo.getActual();
                }
            default:
                this.contador = 0;
                return this.Stop.getNext();
        }
    }

    public void MoverPersonaje(int accion, int PosicionRelativaX, int PosicionRelativaY) {
        if (!this.Actualizar) {
            this.Actualizar = true;
        }
        this.Accion = accion;
        this.contador = 0;
        this.setLocation(PosicionRelativaX, PosicionRelativaY);

    }

    @Override
    public void Detener() {
        this.Accion = 0;
        this.repaint();
        this.Actualizar = false;
    }

    public void AsesinarPersonaje(){
        this.Accion = -1;
        this.Actualizar = false;
        this.repaint();
    }
    
}
