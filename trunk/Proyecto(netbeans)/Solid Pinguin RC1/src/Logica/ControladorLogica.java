package Logica;

import Persistencia.ControladorPersistencia;
import java.awt.Component;
import java.awt.Image;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Clase controlador logica encargada de gestionar y enviar datos desde la
 * persistencia a la capa de interfaz para ser desplegados, o bien generar datos desde
 * la capa lógica y subirlos a la interfaz para ser representados graficamente
 * en el caso de lo que es lógica del personaje y de las arenas
 *
 * @author andrew
 */
public class ControladorLogica {

    /**
     * @param ControladorPersistencia encargado de traer todo el flujo de datos hasta la capa de lógica
     */
    private ControladorPersistencia ControladorPersistencia;
    /**
     * @param ModeloMapa realiza todas las operaciones lógicas asociadas al mapa y su entorno
     */
    private ModeloMapa ModeloMapa;

    /**
     * Constructor del controlador de lógica
     */
    public ControladorLogica() throws FileNotFoundException, Exception {
        this.ControladorPersistencia = new ControladorPersistencia();
        this.ModeloMapa = new ModeloMapa(this.ControladorPersistencia.getMapa("mapa"));
    }

    /**
     * Inicio bloque de obtención y captura de datos desde persitencia
     * -------------------------------------------------------------------------
     */
    /**
     * Método que retorna una lista circular de imagenes para utilizar en la capa de interfaz
     *
     * @param component indica el observador que requiere la imagen
     * @paran path la ruta donde se irá a buscar la imagen
     * @return Una lista circular de imagenes
     */
    public ListaCircularImagenes getImagenes(Component component, String path) throws Exception {
        ArrayList<Image> lista = this.ControladorPersistencia.getImagenes(component, path);
        ListaCircularImagenes Lista = new ListaCircularImagenes();
        Iterator<Image> i = lista.iterator();
        while (i.hasNext()) {
            Lista.Push(i.next());
        }
        return Lista;
    }

    /**
     * Método que retorna solo una imagen para utilizar en la capa de interfaz
     *
     * @param component indica el observador que requiere la imagen
     * @paran path la ruta donde se irá a buscar la imagen
     * @return la inagen encontrada en la ruta especificada o null en caso de encontrar nada
     */
    public Image getImagen(Component component, String path) throws Exception {
        return this.ControladorPersistencia.getImagen(component, path);
    }

    /**
     * Método que obtiene el mapa del juego
     *
     * @return una matriz de char que representa el mapa del juego
     */
    public char[][] getMapa() {
        return this.ModeloMapa.getMapa();
    }

    /**
     * Método que guarda en la base de datos la lista enviada por parámetros en la
     * tabla enviada por parámetros
     *
     * @param Tabla indica la tabla donde se va a insertar la información contenida en la lista
     * @param lista Datos a guardar en la tabla indicada
     * @return <li>true: Si se logran insertar los datos en la tabla indicada por parámnetros </li>
     *         <li>false: Si no se logran isnertar los datos en la tabla indicada por parámetros </li>
     */
    public boolean GuardarBD(String Tabla, List Datos) {
        return this.ControladorPersistencia.GuardarBD(Tabla, Datos);
    }

    /**
     * Método que guarda toda la información relacionada con el usuario
     * en su tabla correspondiente en la base de datos
     *
     * @param List la información a guardar del usuario en su tabla
     * @return <li>true: Si se logran insertar los datos en la tabla usuarios </li>
     *         <li>false: Si no se logran isnertar los datos en la tabla usuarios </li>
     */
    public boolean NuevoUsuario(String User, String Pass) {
        List Lista = null;
        Lista.add(User);
        Lista.add(Pass);
        return this.ControladorPersistencia.GuardarBD("usuarios", Lista);
    }

    /**
     * Método que verifica si un usuario esta correcto o no verificando si su usuario
     * y contraseña son los mismos que los almacenados en la base de datos
     *
     * @param User nombre del usuario a verificar
     * @param Pass contraseña asociada al usuario en cuestión
     * @return <li>true: Si los valores coinciden </li>
     *         <li>false: Si los valores no coinciden</li>
     */
    public boolean Verificar_User(String User, String Pass) {
        ArrayList<HashMap> Datos = this.ControladorPersistencia.leerDB("usuarios");
        if (Datos == null) {
            return false;
        } else {
            for (int i = 0; i < Datos.size(); i++) {
                if (User.equalsIgnoreCase((String) Datos.get(i).get("usuario"))
                        && Pass.equalsIgnoreCase((String) Datos.get(i).get("pass"))) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Método que ingresa un usuario a la base de datos
     *
     * @param User nombre del usuario a registrar
     * @param Pass contraseña de inicio de sesión del usuario ingresado
     * @return <li>true: Si se logran insertar los datos en la tabla usuarios y no existe replica de usuario</li>
     *         <li>false: Si no se logran isnertar los datos en la tabla usuarios </li>
     */
    public boolean IngresarUsuario(String User, String Pass) {
        ArrayList<HashMap> lista = new ArrayList();
        HashMap NuevoUsuario = new HashMap();
        NuevoUsuario.put("usuario", "'" + User + "'");
        NuevoUsuario.put("pass", "'" + Pass + "'");
        lista.add(NuevoUsuario);
        return ControladorPersistencia.GuardarUser(lista);
    }

    /**
     * Inicio bloque de obtención y captura de datos desde persitencia
     * -------------------------------------------------------------------------
     */
    /**
     * Inicio bloque de lógica y obtención de datos del personaje
     * -------------------------------------------------------------------------
     */
    /**
     * Método que realiza un movimiento o mejor dicho realiza los calculos y verificaciones
     * necesario para decidir si el personaje puede o no avanzar al espacio solicitado
     * definido por la variable accion
     *
     * @param accion indica lo que se quiere hacer o hacia donde se necesita avanzar
     * @return un movimiento para mover el personaje, en caso de no moverse retorna un movimiento
     * que contiene los valores de posicion del personaje pero sin variaciones por lo tanto
     * da la impresión de no movimiento
     */
    public Movimiento MoverPersonaje(int accion) {
        return this.ModeloMapa.MoverPersonaje(accion);
    }

    /**
     * Responde a la pregunta ¿Murio el personaje?
     *
     * @return <li>true: Si el personaje efectivamente murió </li>
     *         <li>false: Si el personaje aún no ha muerto</li>
     */
    public boolean isPersonajeMuerto() {
        return this.ModeloMapa.isPersonajeMuerto();
    }

    /**
     * Responde a la pregunta ¿Capturé todas las joyas?
     *
     * @return <li>true: Si el personaje efectivamente capturó todas las joyas </li>
     *         <li>false: Si el personaje no ha capturado aún todas las joyas </li>
     */
    public boolean TodasLasJoyas() {
        return this.ModeloMapa.TodasLasJoyas();
    }

    /**
     * Método que retorna la posición Y del personaje dentro del frame
     *
     * @param retorna la posición Y dentro del frame del personaje
     */
    public int getPosicionRelativaXPersonaje() {
        return this.ModeloMapa.getPosicionRelativaXPersonaje();
    }

    /**
     * Método que retorna la posición X del personaje dentro del frame
     *
     * @param retorna la posición X dentro del frame del personaje
     */
    public int getPosicionRelativaYPersonaje() {
        return this.ModeloMapa.getPosicionRelativaYPersonaje();
    }

    /**
     * Método que retorna la posición X del personaje dentro del mapa
     *
     * @return posición X del personaje dentro del mapa
     */
    public int getPosicionPersonajeMapaX() {
        return this.ModeloMapa.getPosicionPersonajeMapaX();
    }

    /**
     * Método que retorna la posición Y del personaje dentro del mapa
     *
     * @return posición Y del personaje dentro del mapa
     */
    public int getPosicionPersonajeMapaY() {
        return this.ModeloMapa.getPosicionPersonajeMapaY();
    }

    public void setPosicionesPersonaje(int PosicionInicialX, int PosicionInicialY) {
        this.ModeloMapa.setPosicionesPersonaje(PosicionInicialX, PosicionInicialY);
    }

    /**
     * Método que le pregunta al mapa lo que hay en la posición indicada
     *
     * @param m la posición a consultar
     * @return caracter que se encuentra en la posición consultada
     */
    public char ConsultaMapa(Movimiento m) {
        return this.ModeloMapa.ConsultaMapa(m);
    }

    public int getMapaX() {
        return this.ModeloMapa.getMapaX();
    }

    public int getMapaY() {
        return this.ModeloMapa.getMapaY();
    }

    /**
     * Método que aumenta en uno el nivel para cargar las nuevas variables del nivel
     * @return <li>true: en caso de existir otro nivel </li>
     *         <li>false: cuando ya no hay mas niveles </li>
     */
    public boolean AumentarNivel() {
        return this.ModeloMapa.AumentarNivel();
    }

    public void ResetNivel() {
        this.ModeloMapa.ResetNivel();
    }

    public Movimiento Infectar() {
        return this.ModeloMapa.Infectar();
    }

    public void ModificarValorMatriz(char valor, int y, int x) {
        this.ModeloMapa.ModificarValorMatriz(valor, y, x);
    }

    public void ModificarValorMatriz(char valor, Movimiento movimiento) {
        this.ModeloMapa.ModificarValorMatriz(valor, movimiento);
    }

    public Estadisticas getEstadisticas() {
        return new Estadisticas(this.ModeloMapa);
    }

    public boolean GameOver() {
        return this.ModeloMapa.GameOver();
    }

    public String getNombreUsuario() {
        return this.ModeloMapa.getNombreUsuario();
    }

    public void setNombreUsuario(String nombre) {
        this.ModeloMapa.setNombreUsuario(nombre);
    }
}
