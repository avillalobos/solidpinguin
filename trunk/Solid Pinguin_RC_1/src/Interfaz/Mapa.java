/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Logica.ControladorLogica;
import Logica.Movimiento;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Grupo_05
 */
public class Mapa implements Runnable {

    private int PosicionInicialPersonajeX;
    private int PosicionInicialPersonajeY;
    private int PosicionFinalPersonajeX;
    private int PosicionFinalPersonajeY;
    private int TamX;
    private int TamY;
    private Nodo Matriz[][];
    private VistaPersonaje Personaje;
    private Salida Salida;
    private Nodo VidaExtra;
    private ControladorLogica ControladorLogica;
    private JFrame jFrame;
    private int TasaInfeccion;
    private boolean VidaJuego;

    public Mapa(VistaPersonaje Personaje, ControladorLogica ControladorLogica, JFrame jFrame) throws FileNotFoundException, IOException, Exception {
        this.jFrame = jFrame;
        this.ControladorLogica = ControladorLogica;
        char mapa[][] = ControladorLogica.getMapa();
        this.TamX = ControladorLogica.getMapaX();
        this.TamY = ControladorLogica.getMapaY();
        this.Matriz = new Nodo[TamY][TamX];
        this.Personaje = Personaje;
        this.TasaInfeccion = 20;
        this.VidaJuego = true;
        DibujarObjetos(mapa);
    }

    /* Creación de las imagenes dentro de la matriz
     * -------------------------------------------------------------------------
     */
    private void DibujarObjetos(char mapa[][]) throws Exception {

        for (int i = 0; i < this.TamY; i++) {
            for (int j = 0; j < this.TamX; j++) {
                switch (mapa[i][j]) {
                    case '0':
                        this.PosicionInicialPersonajeY = j;
                        this.PosicionInicialPersonajeX = i;
                        this.Matriz[i][j] = new Nodo(3);
                        this.Matriz[i][j].Actualizar(ControladorLogica.getImagen(this.Matriz[i][j], "CaminoLibre/CaminoLibre"));
                        this.Matriz[i][j].setLocation(j * 60, i * 64);
                        this.Matriz[i][j].setBuffered(true);
                        this.Matriz[i][j].setDisplay(true);
                        this.Matriz[i][j].setPosicionY(i);
                        this.Matriz[i][j].setPosicionX(j);
                        this.Personaje.setLocation(j * 60, i * 64);
                        this.Personaje.setBuffered(false);
                        this.Personaje.setDisplay(true);
                        //this.ControladorLogica.ModificarValorMatriz('3', i, j);
                        // aqui se debe crear al personaje
                        break;
                    case '1':
                        this.Matriz[i][j] = new Nodo(1);
                        this.Matriz[i][j].Actualizar(ControladorLogica.getImagen(this.Matriz[i][j], "Muros/Muro1"));
                        this.Matriz[i][j].setLocation(j * 60, i * 64);
                        this.Matriz[i][j].setBuffered(true);
                        this.Matriz[i][j].setDisplay(true);
                        this.Matriz[i][j].setPosicionY(i);
                        this.Matriz[i][j].setPosicionX(j);
                        break;
                    case '2':
                        this.Matriz[i][j] = new Nodo(2);
                        this.Matriz[i][j].Actualizar(ControladorLogica.getImagen(this.Matriz[i][j], "Muros/Muro2"));
                        this.Matriz[i][j].setLocation(j * 60, i * 64);
                        this.Matriz[i][j].setBuffered(true);
                        this.Matriz[i][j].setDisplay(true);
                        this.Matriz[i][j].setPosicionY(i);
                        this.Matriz[i][j].setPosicionX(j);
                        break;
                    case '3':
                        this.Matriz[i][j] = new Nodo(3);
                        this.Matriz[i][j].Actualizar(ControladorLogica.getImagen(this.Matriz[i][j], "CaminoLibre/CaminoLibre"));
                        this.Matriz[i][j].setLocation(j * 60, i * 64);
                        this.Matriz[i][j].setBuffered(true);
                        this.Matriz[i][j].setDisplay(true);
                        this.Matriz[i][j].setPosicionY(i);
                        this.Matriz[i][j].setPosicionX(j);
                        break;
                    case '4':
                        Joyas joya = new Joyas();
                        joya.setImagenes(ControladorLogica.getImagenes(joya, "Focas/Foca"));
                        this.Matriz[i][j] = joya;
                        joya.setLocation(j * 60, i * 64);
                        joya.setBuffered(true);
                        joya.setDisplay(true);
                        joya.setPosicionY(i);
                        joya.setPosicionX(j);
                        break;
                    case '5':
                        Bomba bomba = new Bomba();
                        bomba.setImagenes(this.ControladorLogica.getImagenes(bomba, "Bomba/Cazador"));
                        this.Matriz[i][j] = bomba;
                        bomba.setLocation(j * 60, i * 64);
                        bomba.setBuffered(true);
                        bomba.setDisplay(true);
                        bomba.setPosicionY(i);
                        bomba.setPosicionX(j);
                        break;
                    case '6':
                        this.Matriz[i][j] = new Nodo(6);
                        this.Matriz[i][j].Actualizar(ControladorLogica.getImagen(this.Matriz[i][j], "ArenaMovediza/ArenaMovediza"));
                        this.Matriz[i][j].setLocation(j * 60, i * 64);
                        this.Matriz[i][j].setBuffered(true);
                        this.Matriz[i][j].setDisplay(true);
                        break;
                    case '7':

                        Piedra piedra = new Piedra();
                        piedra.setImagenes(this.ControladorLogica.getImagenes(piedra, "Piedra/Piedra"));
                        this.Matriz[i][j] = piedra;
                        piedra.setLocation(j * 60, i * 64);
                        piedra.setBuffered(true);
                        piedra.setDisplay(true);
                        break;
                    case '8':

                        this.Salida = new Salida();
                        this.Salida.setImagenes(this.ControladorLogica.getImagenes(this.Salida, "Salida/Salida"));
                        this.Matriz[i][j] = this.Salida;
                        this.Salida.setLocation(j * 60, i * 64);
                        this.Salida.setBuffered(true);
                        this.Salida.setDisplay(true);
                        this.Salida.setPosicionY(i);
                        this.Salida.setPosicionX(j);
                        break;
                    case '9':

                        this.VidaExtra = new Nodo(9);
                        this.Matriz[i][j] = this.VidaExtra;
                        this.Matriz[i][j].Actualizar(ControladorLogica.getImagen(this.Matriz[i][j], "Vidas/VidaExtra"));
                        this.Matriz[i][j].setLocation(j * 60, i * 64);
                        this.Matriz[i][j].setBuffered(true);
                        this.Matriz[i][j].setDisplay(true);

                        break;
                    default:

                        break;
                }
            }
        }
    }

    /* Fin de creacion de imagenes
     * -------------------------------------------------------------------------
     */
    /* Sección relacionada al Hilo
     * -------------------------------------------------------------------------
     */
    public void run() {
        long normalSleepTime = 100;
        int contadorInfecciones = 0;
        while (this.VidaJuego) {
            try {
                Thread.sleep(normalSleepTime);
            } catch (InterruptedException x) {
                // ignore
            }
            contadorInfecciones++;

            if (contadorInfecciones == this.TasaInfeccion) {
                Movimiento m = this.ControladorLogica.Infectar();
                if (m != null) {
                    Infectar(m.getY(), m.getX());
                }
                if (this.ControladorLogica.GameOver()) {
                    this.VidaJuego = false;
                }
                contadorInfecciones = 0;
            }

            for (int i = 0; i < this.TamY; i++) {
                for (int j = 0; j < this.TamX; j++) {
                    if (this.Matriz[i][j] != null) {
                        if (this.Matriz[i][j].getCodigo() == 7 && !((Piedra) this.Matriz[i][j]).isCayendo()) {
                            PiedraCayendo(i, j);
                        } else {
                            this.Matriz[i][j].repaint();
                        }
                    }
                }
            }
        }
        this.PerderJuego();
    }
    /* Fin Sección relacionada al Hilo
     * -------------------------------------------------------------------------
     */

    /* Seccion realcionada a acciones e interacciones del personaje
     * -------------------------------------------------------------------------
     */
    public boolean TomarAcciones(int y, int x) {
        System.out.println("Tomar Acciones y = " + y + " x = " + x + " codigo = " + this.Matriz[y][x].getCodigo());
        VerificarMovimientosPasadosPersonaje(y, x);
        switch (this.Matriz[y][x].getCodigo()) {
            case 4:
                CapturarJoya(y, x);
                return false;
            case 5:
                // Explota la Bomba
                return Bomba(y, x);
            case 8:
                if (this.ControladorLogica.TodasLasJoyas()) {
                    System.out.println("Ganar juego");
                    GanarJuego();
                    return true;
                }
                return false;
            case 9:
                CapturarVidaExtra(y, x);
                return false;
            default:
                return false;
        }
    }

    private void VerificarMovimientosPasadosPersonaje(int y, int x) {
        // pongo solo el código porque el porsonaje se esta moviendo en esa posición de la matriz
        this.Matriz[y][x].setPersonaje(true);
        // luego debo verificar al rededor si es que hay algún otro 0, lo que significa que desde ahí venia el personaje
        if (y - 1 >= 0 && this.Matriz[y - 1][x].isPersonaje()) {
            this.Matriz[y - 1][x].setPersonaje(false);
        } else if (y + 1 < this.TamY && this.Matriz[y + 1][x].isPersonaje()) {
            this.Matriz[y + 1][x].setPersonaje(false);
        } else if (x - 1 >= 0 && this.Matriz[y][x - 1].isPersonaje()) {
            this.Matriz[y][x - 1].setPersonaje(false);
        } else if (x + 1 < this.TamX && this.Matriz[y][x + 1].isPersonaje()) {
            this.Matriz[y][x + 1].setPersonaje(false);
        }
    }

    private void CapturarJoya(int y, int x) {
        this.jFrame.remove(this.Matriz[y][x]);
        System.out.println("joya");
        ((Joyas) this.Matriz[y][x]).TomarJoya();
        this.Matriz[y][x] = new Nodo(3);
        try {
            this.Matriz[y][x].Actualizar(ControladorLogica.getImagen(this.Matriz[y][x], "CaminoLibre/CaminoLibre"));
        } catch (Exception ex) {
            Logger.getLogger(Mapa.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.Matriz[y][x].setLocation(x * 60, y * 64);
        this.Matriz[y][x].setBuffered(true);
        this.Matriz[y][x].setDisplay(true);
        //this.ControladorLogica.ModificarValorMatriz('3', y, x);
        this.jFrame.add(this.Matriz[y][x]);
        ActivarSalida(this.ControladorLogica.TodasLasJoyas());
    }

    private void PerderJuego() {
        this.jFrame.setEnabled(false);
        this.jFrame.remove(this.Personaje);
        for (int i = 0; i < this.TamY; i++) {
            for (int j = 0; j < this.TamX; j++) {
                this.jFrame.remove(this.Matriz[i][j]);
            }
        }
        this.Personaje.AsesinarPersonaje();
        this.VidaJuego = false;
        this.ControladorLogica.GuardarBD("historial", this.ControladorLogica.getEstadisticas().getData());
    }

    private void GanarJuego() {
        this.jFrame.setEnabled(false);
        this.jFrame.remove(this.Personaje);
        for (int i = 0; i < this.TamY; i++) {
            for (int j = 0; j < this.TamX; j++) {
                this.jFrame.remove(this.Matriz[i][j]);
            }
        }
        this.VidaJuego = false;
        this.ControladorLogica.GuardarBD("historial", this.ControladorLogica.getEstadisticas().getData());
    }

    private void CapturarVidaExtra(int y, int x) {
        this.jFrame.remove(this.Matriz[y][x]);
        this.Matriz[y][x] = new Nodo(3);
        try {
            this.Matriz[y][x].Actualizar(ControladorLogica.getImagen(this.Matriz[y][x], "CaminoLibre/CaminoLibre"));
        } catch (Exception ex) {
            Logger.getLogger(Mapa.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.Matriz[y][x].setLocation(x * 60, y * 64);
        this.Matriz[y][x].setBuffered(true);
        this.Matriz[y][x].setDisplay(true);
        this.ControladorLogica.ModificarValorMatriz('3', y, x);
        this.jFrame.add(this.Matriz[y][x]);
    }

    public void ActivarSalida(boolean TodasLasJoyas) {
        if (TodasLasJoyas) {
            System.out.println("activar salida");
            this.Salida.ActivarSalida();
        }
    }

    public void CapturarVida(int y, int x) {
        try {
            this.VidaExtra.Actualizar(ControladorLogica.getImagen(this.VidaExtra, "CaminoLibre/CaminoLibre"));
            this.VidaExtra.setCodigo(3);
            this.ControladorLogica.ModificarValorMatriz('3', y, x);
        } catch (Exception ex) {
            Logger.getLogger(Mapa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void PiedraCayendo(int y, int x) {

        if (y + 1 < this.TamY && this.Matriz[y + 1][x].getCodigo() == 3 && !this.Matriz[y + 1][x].isPersonaje()) {

            if (this.ControladorLogica.getPosicionPersonajeMapaX() == x && this.ControladorLogica.getPosicionPersonajeMapaY() + 1 == y + 1 && this.Matriz[y + 1][x].isPersonaje()) {
                PerderJuego();
                return;
            }

            Piedra piedra = (Piedra) this.Matriz[y][x];
            int dy = this.Matriz[y + 1][x].getY();
            piedra.setTerminoY(dy);
            (new Thread(piedra)).start();
            this.jFrame.remove(this.Matriz[y + 1][x]);
            this.Matriz[y + 1][x] = piedra;
            this.ControladorLogica.ModificarValorMatriz('7', y + 1, x);
            this.Matriz[y][x] = new Nodo(3);
            this.ControladorLogica.ModificarValorMatriz('3', y, x);

            try {
                this.Matriz[y][x].Actualizar(ControladorLogica.getImagen(this.Matriz[y][x], "CaminoLibre/CaminoLibre"));
            } catch (Exception ex) {
                Logger.getLogger(Mapa.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            this.Matriz[y][x].setLocation(x * 60, y * 64);
            this.Matriz[y][x].setBuffered(true);
            this.Matriz[y][x].setDisplay(true);
            this.Matriz[y][x].setPosicionY(y);
            this.Matriz[y][x].setPosicionX(x);

            this.jFrame.add(this.Matriz[y][x]);
        }else if(this.Matriz[y + 1][x].isPersonaje()){
            this.Matriz[y + 1][x].setDisplay(false);
        }else{
            this.Matriz[y + 1][x].setDisplay(true);
        }
    }

    public void EliminarImagenes() throws IOException{
        System.out.println("Eliminando imagenes xD");
        for(int i = 0 ; i< this.TamY; i++){
            for(int j = 0 ; j < this.TamX; j++){
                System.out.print(this.Matriz[i][j].getCodigo());
                this.jFrame.remove(this.Matriz[i][j]);
            }
            System.out.println("");
        }
    }

    private boolean Bomba(int y, int x) {
        if (this.ControladorLogica.isPersonajeMuerto()) {
            this.Personaje.Detener();
            PerderJuego();
            return true;
        } else {
            this.Personaje.AsesinarPersonaje();
            Object[] opciones = {"ok"};
            JOptionPane.showOptionDialog(null, "Perdiste una vida", "Warning!", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opciones, opciones[0]);
            this.jFrame.remove(this.Matriz[y][x]);
            this.Matriz[y][x] = new Nodo(3);
            try {
                this.Matriz[y][x].Actualizar(ControladorLogica.getImagen(this.Matriz[y][x], "CaminoLibre/CaminoLibre"));
            } catch (Exception ex) {
                Logger.getLogger(Mapa.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.Matriz[y][x].setLocation(x * 60, y * 64);
            this.Matriz[y][x].setBuffered(true);
            this.Matriz[y][x].setDisplay(true);
            this.Matriz[y][x].setPosicionY(y);
            this.Matriz[y][x].setPosicionX(x);
            this.jFrame.add(this.Matriz[y][x]);
            return false;
        }
    }

    /* Fin Sección realcionada a acciones e interacciones del personaje
     * -------------------------------------------------------------------------
     */

    /*
     * Seccion relacionada a la interacción de la Arena Movediza con el mapa
     * -------------------------------------------------------------------------
     */
    public void Infectar(int y, int x) {
        if (this.Matriz[y][x].getCodigo() == 8) {
            return;
        } else {
            try {
                this.jFrame.remove(this.Matriz[y][x]);
                this.Matriz[y][x] = new Nodo(6);
                this.Matriz[y][x].Actualizar(ControladorLogica.getImagen(this.Matriz[y][x], "ArenaMovediza/ArenaMovediza"));
                this.Matriz[y][x].setLocation(x * 60, y * 64);
                this.Matriz[y][x].setBuffered(true);
                this.Matriz[y][x].setDisplay(true);
                this.Matriz[y][x].repaint();
                this.jFrame.add(this.Matriz[y][x]);
                this.ControladorLogica.ModificarValorMatriz('6', y, x);
            } catch (Exception ex) {
                Logger.getLogger(Mapa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


    /*
     * Seccion relacionada a la interacción de la Arena Movediza con el mapa
     * -------------------------------------------------------------------------
     */
    /* Inicio Sección de get y set de valores
     * -------------------------------------------------------------------------
     */
    public int getPosicionInicialPersonajeX() {
        return PosicionInicialPersonajeX;
    }

    public int getPosicionInicialPersonajeY() {
        return PosicionInicialPersonajeY;
    }

    public int Consulta(int y, int x) {
        // si es una joya, esta se detiene
        return this.Matriz[y][x].getCodigo();
    }

    public int getTamX() {
        return TamX;
    }

    public int getTamY() {
        return TamY;
    }

    public Nodo getNodo(int y, int x) {
        return this.Matriz[y][x];
    }
    /* Fin Sección de get y set de valores
     * -------------------------------------------------------------------------
     */
}
