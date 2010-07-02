package Logica;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Clase que controla todo lo necesario para el flujo de datos entre el mapa y el personaje
 * 
 * @author Grupo_05
 */
public class ModeloMapa {

    /**
     * @param ModeloPersonaje Indica la parte lógica del personaje, ideada para mover al personaje dentro
     * del mapa del juego y realizar todas las acciones pertinentes
     */
    private ModeloPersonaje ModeloPersonaje;
    /**
     * @param ListaArenasMovedizas indica la lista total de arenas movedizas del mapa y el nivel actual, para utilizarlas
     * se ira iterando sobre ellas para que cada ciclo la siguiente arena valla infectando y no
     * siempre la misma
     */
    //private LinkedList<ArenaMovediza> ListaArenasMovedizas;
    /**
     * @param IteradorListas Iterador que se encarga de recorrer la lista de Arenas Movedizas
     * para que por cada infeccion una arena distinta sea la semilla
     */
    private Iterator<ArenaMovediza> IteradorArenasMovedizas;
    /**
     * @param Mapa indica lo que hay en cada posición del mapa en forma de char
     */
    private char MapaActual[][];
    /**
     * @param MapaX indica la dimensión de la matriz horizontalmente
     */
    private int MapaX;
    /**
     * @param MapaY indica la dimensión de la matriz verticalmente
     */
    private int MapaY;
    /**
     * @param CantidadVidasExtras indica la cantidad de vidas extras dentro del nivel
     */
    private int CantidadVidasExtras;
    /**
     * @param nivel indica el nivel actual en juego
     */
    private int nivel;
    /**
     * @param ListaMapas guarda todos los mapas para los niveles
     */
    
    private LinkedList<char[][]> ListaMapas;
    private boolean GameOver;
    private LinkedList<HashMap<String, String>> TamMapas;
    private LinkedList<LinkedList<ArenaMovediza>> ListaArenasMovedizasPorNivel;
    private LinkedList<ModeloPersonaje> ListaModelosPersonajes;

    /**
     * Constructor del Modelo del mapa, la parte lógica del Mapa
     *
     * @param Lista Esta lista contiene la lectura directa desde la persistencia del mapa
     * lo que se debe hacer es leer primero los dos tamaños que son la primera linea y luego utilzarlos
     * para dibujar la nueva matriz
     */
    public ModeloMapa(LinkedList<String> Lista) throws Exception {
        // parte en menos 1 para que siempre se utilice el método Avanzar nivel incluso cuando recien parte el juego
        this.nivel = 0;
        this.ListaMapas = new LinkedList<char[][]>();
        this.TamMapas = new LinkedList<HashMap<String, String>>();
        this.ListaArenasMovedizasPorNivel = new LinkedList<LinkedList<ArenaMovediza>>();
        this.ListaModelosPersonajes = new LinkedList<ModeloPersonaje>();
        CargarMapasDeTodosLosNiveles(Lista);
        ElegirDatosNivel();
    }

    /*Inicio sección de carga de valores y parametros de la clase Modelo Mapa
     * -------------------------------------------------------------------------
     */
    /**
     * Método que recibe la Lista del constructor y la descompone para generar la propia matriz
     * de char, junto con todos los demás parametros necesario para la correcta ejecución del programa
     *
     * @para Lista Lista que contiene los datos del mapa
     */
    public void CargarMapasDeTodosLosNiveles(LinkedList<String> Lista) throws Exception {
        Iterator<String> iterator = Lista.iterator();
        do {
            LinkedList ListaArenasMovedizas = new LinkedList<ArenaMovediza>();
            ModeloPersonaje personaje = null;
            int ContadorJoyas = 0;
            String tam = iterator.next();
            String size[] = tam.split(",");
            int y = Integer.parseInt(size[0]);
            int x = Integer.parseInt(size[1]);
            HashMap hm = new HashMap();
            hm.put("y", String.valueOf(y));
            hm.put("x", String.valueOf(x));
            this.TamMapas.add(hm);
            char mapa[][] = new char[y][x];
            for (int i = 0; i < y; i++) {
                String linea = iterator.next();
                char vector[] = linea.toCharArray();
                for (int j = 0; j < x; j++) {
                    mapa[i][j] = vector[j];
                    switch (mapa[i][j]) {
                        case '0':
                            personaje = new ModeloPersonaje(y, x, j, i);
                            break;
                        case '4':
                            ContadorJoyas++;
                            break;
                        case '6':
                            ListaArenasMovedizas.add(new ArenaMovediza(i, j, y, x));
                            break;
                        case '9':
                            this.CantidadVidasExtras++;
                            break;
                        default:
                            break;
                    }
                }
            }
            personaje.setCantidadJoyasPorNivel(ContadorJoyas);
            this.ListaModelosPersonajes.add(personaje);
            this.ListaMapas.add(mapa);
            this.ListaArenasMovedizasPorNivel.add(ListaArenasMovedizas);
        } while (iterator.hasNext());

    }
/**public Estadisticas GenerarEstadisticas(){



    return ;
}**/
    public boolean AumentarNivel() {
        this.nivel++;
        if (this.nivel < this.ListaMapas.size()) {
            ElegirDatosNivel();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Por problemas de referencias no puedo hacer que se resetee el la matriz porque una vez modifcado los datos
     * no puedo volver a obtener los originales :(
     */
    public void ResetNivel() {
        ElegirDatosNivel();
    }

    private void ElegirDatosNivel() {
        //CargarMapa();
        this.MapaActual = this.ListaMapas.get(this.nivel);
        HashMap<String, String> hm = this.TamMapas.get(this.nivel);
        this.MapaY = Integer.parseInt(hm.get("y"));
        this.MapaX = Integer.parseInt(hm.get("x"));
        this.IteradorArenasMovedizas = this.ListaArenasMovedizasPorNivel.get(this.nivel).iterator();
        this.ModeloPersonaje = this.ListaModelosPersonajes.get(this.nivel);
    }

    private void CargarMapa() {
        HashMap<String, String> hm = this.TamMapas.get(this.nivel);
        this.MapaY = Integer.parseInt(hm.get("y"));
        this.MapaX = Integer.parseInt(hm.get("x"));
        LinkedList<ArenaMovediza> ListaArenasMovedizas = new LinkedList<ArenaMovediza>();
        this.MapaActual = new char[this.MapaY][this.MapaX];
        char MapaTemporal[][] = this.ListaMapas.get(this.nivel);
        int CantidadJoyasPorNivel = 0;
        for (int i = 0; i < this.MapaY; i++) {
            for (int j = 0; j < this.MapaX; j++) {
                this.MapaActual[i][j] = MapaTemporal[i][j];
                switch (MapaTemporal[i][j]) {
                    case '0':
                        //this.ModeloPersonaje.setPosicionY(i);
                        //this.ModeloPersonaje.setPosicionX(j);
                        this.ModeloPersonaje = new ModeloPersonaje(MapaX, MapaY, j, i);
                        break;
                    case '4':
                        CantidadJoyasPorNivel++;
                        break;
                    case '6':
                        ListaArenasMovedizas.add(new ArenaMovediza(i, j, this.MapaY, this.MapaX));
                        break;
                    case '9':
                        this.CantidadVidasExtras++;
                        break;
                    default:
                        break;
                }
            }
        }
        //this.ModeloPersonaje.setCantidadJoyasPorNivel(CantidadJoyasPorNivel);
        this.ListaModelosPersonajes.set(this.nivel, ModeloPersonaje);
        this.ListaMapas.set(this.nivel, MapaActual);
        this.ListaArenasMovedizasPorNivel.set(this.nivel, ListaArenasMovedizas);
    }




    /*Fin sección de carga de valores y parametros de la clase Modelo Mapa
     * -------------------------------------------------------------------------
     */




    /*Inicio sección de movimientos y decisiones lógicas con relación al personaje
     * y su entorno, ya sea moviendo como capturando o siendo atacado por algo
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
        Movimiento m = this.ModeloPersonaje.MoverPersonaje(accion);
        // si el modelo de personaje retorna un movimiento es porque necesita saber que
        // hay en esa posición, pero rertorna solo la posición central, aqui se debe tomar
        // esa posición como eje para analizar las otras dos restantes del bloque frontal
        // de movimiento

        if (m != null) {

            boolean flag = Decision(accion, m);

            // si se logra determinar que no hay un obstaculo en frente del personaje relacionado a la posicion actual
            // entonces se retorna un movimiento con las posiciones relativas del personaje para luego en la capa de interfaz
            // solo utilizar

            if (flag) {
                // al hacer el llamado a este método se actualizan las variables, logrando realizar un movimiento o no dependiendo
                // de lo que tiene en frente, se envia el mapa para que si se toma una acción que modifique lo que hay en el mapa
                // sea el mismo personaje quien realice los cambios y no el mapa en forma omnipotente
                this.ModeloPersonaje.MoverConReturn(MapaActual, m);

                return new Movimiento(this.ModeloPersonaje.getPosicionRelativaX(), this.ModeloPersonaje.getPosicionRelativaY());
                // si no se pudo mover porque hay algún obstaculo, entonces el movimiento se irá con 0
            } else {
                // al retornar sin actualizar, las posiciones relativas del personaje quedaron igual que en la última llamada, por lo
                // tanto da la impresión de que no se ha movido
                return new Movimiento(this.ModeloPersonaje.getPosicionRelativaX(), this.ModeloPersonaje.getPosicionRelativaY());
            }
        } else {
            return new Movimiento(this.ModeloPersonaje.getPosicionRelativaX(), this.ModeloPersonaje.getPosicionRelativaY());
        }
    }

    /**
     * Método que verifica todo el frente del personaje para saber si se puede
     * mover o no, en caso de haber algo por cualquiera de las partes, el personaje
     * deberá estar justo en una posición para el camino para poder moverse
     * de lo contrario no podría, esto para no pasar por encima de los obstaculos
     *
     * @param y Posicion vertical del Mapa
     * @param x Posicion horizontal del Mapa
     */
    private boolean Entorno(int y, int x) {
        //Validar Posiciones
        if (y < 0 || y >= this.MapaY || x < 0 || x >= this.MapaX) {
            return false;
        } else {
            switch (this.MapaActual[y][x]) {
                case '3':
                    return true;
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                default:
                    return false;
            }
        }
    }

    /**
     * Algoritmo de decisión de movimiento en base a lo que tiene en frente
     *
     * @param accion Indica la acción que se esta realizando
     * @param m indica la posición central para verificar, sirve como pibote para analizar
     * el frente
     * @return <li>true: En el caso de que si se puede mover porque las variables asi lo indican </li>
     *         <li>false: En el caso de que no se pueda mover ya sea porque esta en medio del cuadro, o porque hay algo que se lo impide</li>
     */
    public boolean Decision(int accion, Movimiento m) {
        switch (accion) {
            // si se esta moviendo hacia la izquierda, entonces debo variar "y" en +/- 1
            case 37:
                return (Entorno(m.getY(), m.getX()) && Entorno(m.getY() - 1, m.getX()) && Entorno(m.getY() + 1, m.getX()) || !this.ModeloPersonaje.isBordeCriticoY());
            // si se esta moviendo hacia arriba, entonces debo variar "x" en +/- 1
            case 38:
                return (Entorno(m.getY(), m.getX()) && Entorno(m.getY(), m.getX() - 1) && Entorno(m.getY(), m.getX() + 1) || !this.ModeloPersonaje.isBordeCriticoX());
            case 39:
                return (Entorno(m.getY(), m.getX()) && Entorno(m.getY() - 1, m.getX()) && Entorno(m.getY() + 1, m.getX())) || !this.ModeloPersonaje.isBordeCriticoY();
            case 40:
                return (Entorno(m.getY(), m.getX()) && Entorno(m.getY(), m.getX() - 1) && Entorno(m.getY(), m.getX() + 1)) || !this.ModeloPersonaje.isBordeCriticoX();
            default:
                return false;
        }
    }

    /*Fin sección de movimientos y decisiones lógicas con relación al personaje
     * y su entorno, ya sea moviendo como capturando o siendo atacado por algo
     * -------------------------------------------------------------------------
     */
    /*Inicio sección de infección de la arena movediza
     * -------------------------------------------------------------------------
     */
    /**
     * Método que de acuerdo a las arenas movedizas ingresadas logra infectar dentro de la matriz logrando hacer crecer la cantidad
     * de arenas movedizas contenidas en el mapa
     */
    public Movimiento Infectar() {
        if (this.IteradorArenasMovedizas.hasNext()) {
            ArenaMovediza arena = ((ArenaMovediza) this.IteradorArenasMovedizas.next());
            Movimiento m = arena.Infectar(MapaActual);
            this.GameOver = arena.GameOver();
            return m;
        } else {
            this.IteradorArenasMovedizas = this.ListaArenasMovedizasPorNivel.get(nivel).iterator();
            if (this.IteradorArenasMovedizas.hasNext()) {
                ArenaMovediza arena = ((ArenaMovediza) this.IteradorArenasMovedizas.next());
                Movimiento m = arena.Infectar(MapaActual);
                this.GameOver = arena.GameOver();
                return m;
            } else {
                return null;
            }
        }
    }

    public boolean GameOver() {

        return this.GameOver;
    }

    /*Inicio sección de infección de la arena movediza
     * -------------------------------------------------------------------------
     */
    /*Inicio sección de getter y setter de la clase modelo de mapa
     * -------------------------------------------------------------------------
     */
    public void ModificarValorMatriz(char valor, int y, int x) {
        this.MapaActual[y][x] = valor;
    }

    public void ModificarValorMatriz(char valor, Movimiento movimiento) {
        this.MapaActual[movimiento.getY()][movimiento.getX()] = valor;
    }

    /**
     * Método que le pregunta al mapa lo que hay en la posición indicada
     *
     * @param m la posición a consultar
     * @return caracter que se encuentra en la posición consultada
     */
    public char ConsultaMapa(Movimiento m) {
        return this.MapaActual[m.getY()][m.getX()];
    }

    public int getMapaX() {
        return MapaX;
    }

    public int getMapaY() {
        return MapaY;
    }

    public char[][] getMapa() {
        return MapaActual;
    }

    public int getPosicionRelativaXPersonaje() {
        return this.ModeloPersonaje.getPosicionRelativaX();
    }

    public int getPosicionRelativaYPersonaje() {
        return this.ModeloPersonaje.getPosicionRelativaY();
    }

    // retorna la posición dentro de la matriz del personaje
    public int getPosicionPersonajeMapaX() {
        return this.ModeloPersonaje.getPosicionX();
    }

    // retorna la posición dentro de la matriz del personaje
    public int getPosicionPersonajeMapaY() {
        return this.ModeloPersonaje.getPosicionY();
    }

    public void setPosicionesPersonaje(int PosicionInicialX, int PosicionInicialY) {
        if (this.ModeloPersonaje == null) {
            this.ModeloPersonaje = new ModeloPersonaje(MapaX, MapaY, PosicionInicialX, PosicionInicialY);
        }
    }

    public boolean isPersonajeMuerto() {
        return this.ModeloPersonaje.isMuerto();
    }

    public boolean TodasLasJoyas() {
        return this.ModeloPersonaje.TodasLasJoyas();
    }

    public int getCantidadJoyasPorNivel() {
        return this.ModeloPersonaje.getCantidadJoyasPorNivel();
    }

    public int getContadorJoyas() {
        return this.ModeloPersonaje.getContadorJoyas();
    }

    public int getContadorVidas() {
        return this.ModeloPersonaje.getContadorVidas();
    }

    public int getNivel() {
        return nivel;
    }

    public String getNombreUsuario(){
        return this.ModeloPersonaje.getNombreUsuario();
    }

    public void setNombreUsuario(String NombreUsuario){
        this.ModeloPersonaje.setNombreUsuario(NombreUsuario);
    }

    /*Inicio sección de getter y setter de la clase modelo de mapa
     * -------------------------------------------------------------------------
     */
}
