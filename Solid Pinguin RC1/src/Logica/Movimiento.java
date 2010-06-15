/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Logica;

/**
 * Clase encargada de transmitir los movimientos desde el personaje hasta la interfaz.
 * Esta clase puede usarse para transmitir tanto posiciones dentro de la matriz como
 * posiciones reales dentro del frame, posee 2 atributos que son x e y, y es eso lo que
 * representan, los movimientos en X y Y respectivamente.
 *
 * @author andrew
 */
public class Movimiento {

    private int x;
    private int y;

    public Movimiento(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
}
