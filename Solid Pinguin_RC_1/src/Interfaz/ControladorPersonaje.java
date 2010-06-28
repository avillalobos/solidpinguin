/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Interfaz;

import Logica.ListaCircularImagenes;
import Logica.Movimiento;

/**
 *
 * @author Grupo_05
 */
public class ControladorPersonaje {
    private VistaPersonaje VistaPersonaje;
    private Thread VidaPersonaje;

    public ControladorPersonaje() {
        this.VistaPersonaje = new VistaPersonaje();
        this.VidaPersonaje = new Thread(this.VistaPersonaje);
        this.VistaPersonaje.setBuffered(false);
        this.VistaPersonaje.setDisplay(true);
    }

    public VistaPersonaje getVistaPersonaje() {
        return VistaPersonaje;
    }

    public void setImagenesPersonaje(ListaCircularImagenes Arriba, ListaCircularImagenes Abajo, ListaCircularImagenes Izquierda, ListaCircularImagenes Derecha, ListaCircularImagenes Stop, ListaCircularImagenes Muerte){
        this.VistaPersonaje.setImagenes(Arriba, Abajo, Izquierda, Derecha, Stop,Muerte);
    }
    /*
    public void MoverPersonaje(int accion, int PosicionRelativaX, int PoscionRelativaY){
        this.VistaPersonaje.MoverPersonaje(accion, PosicionRelativaX, PoscionRelativaY);
    }
    
     */

    public void MoverPersonaje(int accion, Movimiento movimiento){
        // dado que la posición de canvas se elige primero en x y luego en y y yo estoy trabajando al reves
        // para no complicarme dejo esta nota y recuerdo que yo trabajo primero la coordenada vertical y luego
        // la horizontal y en canvas primero la horizontal y luego la vertical
        this.VistaPersonaje.MoverPersonaje(accion, movimiento.getX(), movimiento.getY());
    }

    public void DetenerPersonaje(){
        this.VistaPersonaje.Detener();
    }

    public void Despertar(){
        this.VidaPersonaje.start();
    }

    public void AsesinarPersonaje(){
        this.VistaPersonaje.AsesinarPersonaje();
    }
}
