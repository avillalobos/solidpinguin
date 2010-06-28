/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Clase encargada de transportar los datos del nivel
 * 
 * @author Grupo_05
 */
public class Estadisticas {

    /**
     * @param ModeloMapa Recibe por parámetros un modelo de mapa del cual obtiene
     * los datos necesario para generar una estadística
     */
    private ModeloMapa ModeloMapa;

    /**
     * @param CamtodadDeJoyasRecuperadas indica la cantidad total de joyas obtenidas en el nivel
     */
    private int CantidadDeJoyasRecuperadas;

    /**
     * Indica la cantidad total de joyas que hay en un nivel
     */
    private int CantidadDeJoyasNivel;

    /**
     * @param NivelAlcanzado Indica el máximo nivel al que se logró llegar
     */
    private int NivelAlcanzado;

    /**
     *@param Vidas Indica la cantidad de vidas que le quedan al jugador
     */
    private int Vidas;

    /**
     * @param Muertes indica cuantas veces murio jugando el nivel
     */
    private int Muertes;

    private String NombreUsuario;

    /**
     * Constructor de la Estadística, esta clase para poder funcionar necesita por fuerza
     * un modelo de mapa para desde ahí obtener estadísticas
     *
     * @param modelo Modelo desde el cual se obtienen datos para generar estadísticas del juego
     */
    public Estadisticas(ModeloMapa modelo){
        this.ModeloMapa = modelo;
        GenerarEstadisticas();
    }

    /**
     * Método que genera la estadísticas a partir del modelo enviado por parámetros
     */
    private void GenerarEstadisticas(){
        this.CantidadDeJoyasRecuperadas = this.ModeloMapa.getContadorJoyas();
        this.CantidadDeJoyasNivel = this.ModeloMapa.getCantidadJoyasPorNivel();
        this.Vidas = this.ModeloMapa.getContadorVidas();
        this.NivelAlcanzado = this.ModeloMapa.getNivel();
        this.NombreUsuario = this.ModeloMapa.getNombreUsuario();
        System.out.println("Generar Estadisticas = " + this.NombreUsuario);
    }

    public int getCantidadDeJoyasNivel() {
        return CantidadDeJoyasNivel;
    }

    public int getCantidadDeJoyasRecuperadas() {
        return CantidadDeJoyasRecuperadas;
    }

    public int getNivelAlcanzado() {
        return NivelAlcanzado;
    }

    public int getVidas() {
        return Vidas;
    }

    public int getMuertes(){
        return Muertes;
    }

    public String getNombreUsuario(){
        return this.NombreUsuario;
    }

    public List getData(){
        System.out.println("antes nombre de usuario = " + this.NombreUsuario);
        ArrayList<HashMap> lista = new ArrayList();
        HashMap<String,String> datos = new HashMap();
        datos.put("joyas", String.valueOf(this.getCantidadDeJoyasRecuperadas()));
        datos.put("niveles", String.valueOf(this.getNivelAlcanzado()));
        datos.put("muertes", String.valueOf(this.getMuertes()));
        datos.put("user_id", "'"+System.getProperty("Usuario")+"'");
        lista.add(datos);
        System.out.println("despues nombre de usuario = " + this.NombreUsuario);
        return lista;
    }
}
