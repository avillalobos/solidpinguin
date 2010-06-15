/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Logica.ControladorLogica;
import Logica.Estadisticas;
import Logica.ListaCircularImagenes;
import Logica.Movimiento;
import java.awt.Component;
import java.awt.Image;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author andrew
 */
public class ControladorInterfaz {

    private ControladorLogica ControladorLogica;
    private ControladorPersonaje ControladorPersonaje;
    private Mapa Mapa;
    private JFrame FrameJuego;
    private Thread Juego;

    public ControladorInterfaz(JFrame jFrame) throws Exception {
        this.FrameJuego = jFrame;
        this.ControladorLogica = new ControladorLogica();
        CrearMapa();
    }
    public ControladorInterfaz()throws Exception{
        this.ControladorLogica = new ControladorLogica();
    }

    public boolean MoverPersonaje(int accion) throws IOException {
        // hago el movimiento, con esto estoy seguro de que se me va a retornar un movimiento
        // porque asi quedo estipulado en la capa de lógica, lo único que se debe hacer aqui es
        // conectar el movimiento de la logica con el de interfaz
        Movimiento movimiento = this.ControladorLogica.MoverPersonaje(accion);
        // si tomar acciones retorna true entonces se acabo el juego, si retorna false el juego aún sigue
        if (this.Mapa.TomarAcciones(this.ControladorLogica.getPosicionPersonajeMapaY(), this.ControladorLogica.getPosicionPersonajeMapaX())) {
            // perdió
            if (this.ControladorLogica.isPersonajeMuerto()) {
                Mensaje("GAME OVER", "Has perdido!");
                this.Mapa.EliminarImagenes();
                return true;
                // ganó
            } else {
                Mensaje("Nivel Superado", "Preparate para el siguiente nivel!");
                this.Mapa.EliminarImagenes();
                return true;
            }
        } else {
            this.ControladorPersonaje.MoverPersonaje(accion, movimiento);
            return false;
        }
    }

    public boolean Verificar_User(String User,String Pass){
        System.out.println("-.Verificar Usuario");
        return ControladorLogica.Verificar_User(User, Pass);
    }

    public boolean Crear_User(String User,String Pass){
        return ControladorLogica.IngresarUsuario(User,Pass);
    }
    public void Mensaje(String titulo, String mensaje) {
        Object[] opciones = {"ok"};
        JOptionPane.showOptionDialog(null, mensaje, titulo, JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opciones, opciones[0]);
    }

    public void CrearMapa() throws Exception{
        this.ControladorPersonaje = new ControladorPersonaje();
        this.ControladorPersonaje.setImagenesPersonaje(this.ControladorLogica.getImagenes(this.ControladorPersonaje.getVistaPersonaje(), "Personaje/PersonajeArriba"),
                this.ControladorLogica.getImagenes(this.ControladorPersonaje.getVistaPersonaje(), "Personaje/PersonajeAbajo"),
                this.ControladorLogica.getImagenes(this.ControladorPersonaje.getVistaPersonaje(), "Personaje/PersonajeIzquierda"),
                this.ControladorLogica.getImagenes(this.ControladorPersonaje.getVistaPersonaje(), "Personaje/PersonajeDerecha"),
                this.ControladorLogica.getImagenes(this.ControladorPersonaje.getVistaPersonaje(), "Personaje/PersonajeDetenido"),
                this.ControladorLogica.getImagenes(this.ControladorPersonaje.getVistaPersonaje(), "Personaje/PersonajeMuerto"));
        this.Mapa = new Mapa(this.ControladorPersonaje.getVistaPersonaje(), this.ControladorLogica, this.FrameJuego);
        this.Juego = new Thread(Mapa);
        this.ControladorLogica.setPosicionesPersonaje(this.Mapa.getPosicionInicialPersonajeX(), this.Mapa.getPosicionInicialPersonajeY());
        this.FrameJuego.setSize((this.Mapa.getTamX() + 1)*60, (this.Mapa.getTamY() + 1)*64);
    }

    public void AvanzarNivel() throws Exception {
        this.ControladorLogica.AumentarNivel();
        CrearMapa();
        CargarImagenesFrame();
    }

    public void DetenerPersonaje() {
        this.ControladorPersonaje.DetenerPersonaje();
    }

    public ListaCircularImagenes getImagenes(Component component, String path) throws Exception {
        return this.ControladorLogica.getImagenes(component, path);
    }

    public Image getImagen(Component component, String path) throws Exception {
        return this.ControladorLogica.getImagen(component, path);
    }

    public void CargarImagenesFrame() {
        this.FrameJuego.add(this.ControladorPersonaje.getVistaPersonaje());
        for (int i = 0; i < this.Mapa.getTamY(); i++) {
            for (int j = 0; j < this.Mapa.getTamX(); j++) {
                // get nodo x,y, por lo tanto en este caso x esta representado por j y y esta representado por i
                if (this.Mapa.getNodo(i, j) != null) {
                    System.out.print(this.Mapa.getNodo(i, j).getCodigo());
                    this.FrameJuego.add(this.Mapa.getNodo(i, j));
                }
            }
            System.out.println("");
        }
        this.Juego.start();
        this.ControladorPersonaje.Despertar();
    }

    public int getTamX() {
        return this.Mapa.getTamX();
    }

    public int getTamY() {
        return this.Mapa.getTamY();
    }

    public boolean isPersonajeMuerto(){
        return this.ControladorLogica.isPersonajeMuerto();
    }

    public Estadisticas getEstadisticas(){
        return this.ControladorLogica.getEstadisticas();
    }

    public void ResetNivel() throws Exception{
        this.ControladorLogica.ResetNivel();
        this.Mapa.EliminarImagenes();
        CrearMapa();
        CargarImagenesFrame();
    }

    public String getNombreUsuario(){
        return this.ControladorLogica.getNombreUsuario();
    }

    public void setNombreUsuario(String nombre){
        this.ControladorLogica.setNombreUsuario(nombre);
    }
}
