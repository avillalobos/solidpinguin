package Interfaz;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author Grupo_05
 */
public class Nodo extends Canvas{

    protected int Codigo;
    /**
     * @param miImagen imagen que contedrá el panel, cualquiera de los 10 objetos del juego
     */
    protected Image miImagen;
    /**
     * @param keepRunning parametro que indica si la imagen esta activa o no
     */
    protected boolean Actualizar;
    /**
     * @param display Inidica si la imagen se esta mostrando o no
     */
    protected boolean display = false;
    /**
     * @param clear limpia el panel de la imagen
     */
    protected boolean clear = false;
    /**
     * @param buffered Variable que indica si la imagen fue cargada en el buffer para dibujar en el gráfico,
     * puesto que es el gráfico quien dibija la imagen, no es un copy - paste como yo pensaba
     */
    protected boolean buffered = false;

    protected int PosicionX;
    protected int PosicionY;

    protected boolean Personaje;

    public Nodo(int codigo) {
        this.Codigo = codigo;
        this.PosicionX = 0;
        this.PosicionY = 0;
        this.Personaje = false;
        setSize(60, 64);
    }

    /**
     * Método que dibuja la imagen de la clase
     *
     * @param g Grafico enviado por parametro para dibujar la imagen
     */
    @Override
    public void paint(Graphics g) {
        // se usa Graphics2D porque la calidad de imagen es mejor
        Graphics2D g2D = (Graphics2D) g;
        // si la imagen ya esta en el buffer entonces se procede a cargar la imagen en el gráfico g2D
        if (buffered) {
            // se crea un buffer de imagen de tamaño igual al canvas, obtiene la propiedad de canvas
            // de largo y ancho para crear el buffer del mismo tamaño
            BufferedImage bi = (BufferedImage) createImage(getWidth(), getHeight());

            // Draw into the memory buffer. Dibuja cada columna

            for (int i = 0; i < getWidth(); i = i + miImagen.getWidth(this)) {

                for (int j = 0; j < getHeight(); j = j + miImagen.getHeight(this)) {

                    bi.createGraphics().drawImage(miImagen, i, j, this);
                    
                }
            }
            // Draw the buffered Image on to the screen
            g2D.drawImage(bi, 0, 0, this);
        } // This block of code draws the texture directly onto the screen.
        else if (!buffered) {
            // carga la imgagen con el método g2D.drawImage(Imagen,x,y,el observador)
            try {
                for (int i = 0; i < getWidth(); i = i + miImagen.getWidth(this)) {

                    for (int j = 0; j < getHeight(); j = j + miImagen.getHeight(this)) {
                        g2D.drawImage(miImagen, i, j, this);
                    }

                }
            } catch (NullPointerException e) {
            }
        }
    }

    public void Actualizar(Image image) {
        this.Actualizar = true;
        this.miImagen = image;
    }

    public void Actualizar() {
        this.Actualizar = true;
    }

    public void Detener() {
        this.Actualizar = false;
        this.display = false;
        this.setDisplay(true);
        this.setBuffered(true);
        if (this.miImagen != null) {
            this.repaint();
        }
    }

    public void Animar() {
        if (!this.Actualizar) {
            this.Actualizar = true;
        }
    }

    void setBuffered(boolean b) {
        this.buffered = b;
    }

    void setDisplay(boolean b) {
        this.Actualizar = b;
    }

    public boolean isKeepRunning() {
        return Actualizar;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int codigo) {
        this.Codigo = codigo;
    }

    public int getPosicionX() {
        return PosicionX;
    }

    public void setPosicionX(int PosicionX) {
        this.PosicionX = PosicionX;
    }

    public int getPosicionY() {
        return PosicionY;
    }

    public void setPosicionY(int PosicionY) {
        this.PosicionY = PosicionY;
    }

    public boolean isPersonaje() {
        return Personaje;
    }

    public void setPersonaje(boolean Personaje) {
        this.Personaje = Personaje;
    }
 
}
