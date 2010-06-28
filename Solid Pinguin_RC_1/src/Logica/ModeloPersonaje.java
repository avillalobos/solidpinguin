/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author Grupo_05
 */
public class ModeloPersonaje {


    /**
     * @param NombreUsuario nombre del usuario almacenado en la base de datos
     */
    private String NombreUsuario;

    /**
     * @param Accion indica la acción actual del personaje
     */
    private int Accion;
    /**
     * @param PosicionX Indica la posición inicial en la matriz del personaje y es la misma variable que se usa
     * para indicar la posición actual dentro de la matriz. Hay que tomar en cuenta que la variable PosicionX
     * indica un número entero para moverse entre cuadros dentro de la matriz mientras que la variable PosicionRelativaX
     * y PosicionRelativaY indican posiciones dentro del frame, es decir la posición final de personaje a desplegar
     */
    private int PosicionX;
    /**
     * @param PosicionY Indica la posición inicial en la matriz del personaje y es la misma variable que se usa
     * para indicar la posición actual dentro de la matriz. Hay que tomar en cuenta que la variable PosicionY
     * indica un número entero para moverse entre cuadros dentro de la matriz mientras que la variable PosicionRelativaX
     * y PosicionRelativaY indican posiciones dentro del frame, es decir la posición final de personaje a desplegar
     */
    private int PosicionY;
    /**
     * @param PosicionRelativaX Indica la posición del personaje dentro del frame a desplegar
     */
    private int PosicionRelativaX;
    /**
     * @param PosicionRelativaY Indica la posición del personaje dentro del frame a desplegar
     */
    private int PosicionRelativaY;
    /**
     * @param FactordeMovimiento Indica cuan rápido se va a mover el personaje
     */
    private int FactordeMovimiento;
    /**
     * @param Altura indica la altura del dibujo del personaje
     */
    private int Altura;
    /**
     * @param Ancho indica el ancho del dibujo del personaje
     */
    private int Ancho;
    /**
     * @param Ancho indica el ancho del mapa, esto es necesario para saber si el personaje esta o no dentro del rango
     */
    private int TamMatrizX;
    /**
     * @param Altura indica la altura del mapa, esto es necesario para saber si el personaje esta o no dentro del rango
     */
    private int TamMatrizY;
    private int CantidadJoyasPorNivel;
    private int ContadorJoyas;
    private int ContadorVidas;
    private boolean BordeCriticoX;
    private boolean BordeCriticoY;

    /**
     * Constructor del Modelo de Personaje, encargado de mover y contener toda la lógica del personaje Pinguino
     * El personaje inicial mente se situa en las posiciones enviadas como parametro al constructor y si situación
     * o acción es detenido porque cuando el Juego se inicia, el personaje deberia estar detenido puesto que no
     * he interactuado con él aún
     *
     * @param TamMatrizX necesario para saber si el personaje esta o no dentro de los margenes del mapa, esta variable
     * indica efectivamente el ancho del mapa
     *
     * @param TamMatrizY necesario para saber si el personaje esta o no dentro de los margenes del mapa, esta variable
     * indica efectivamente la altura del mapa
     *
     * @param PosicionX Posición X donde parte el personaje y desde ahí se comienza a mover, hay que recordar
     * que las posiciones dentro de los Frame se toma como columna los "X" y como filas los "Y", por lo tanto
     * X recorre de manera horizontal, mientras que Y lo hace de forma vertical
     *
     * @param PosicionY Posicion Y donde parte el personaje y desde ahí se comienza a mover, hay que recordar
     * que las posiciones dentro de los Frame se toma como columna los "X" y como filas los "Y", por lo tanto
     * X recorre de manera horizontal, mientras que Y lo hace de forma vertical
     */
    public ModeloPersonaje(int TamMatrizX, int TamMatrizY, int PosicionX, int PosicionY) {
        this.TamMatrizX = TamMatrizX;
        this.TamMatrizY = TamMatrizY;
        this.PosicionX = PosicionX;
        this.PosicionY = PosicionY;
        this.PosicionRelativaX = this.PosicionX * 60;
        this.PosicionRelativaY = this.PosicionY * 64;
        this.FactordeMovimiento = 10;
        this.Accion = 0;
        this.Altura = 64;
        this.Ancho = 60;
        // esto debe ser modificado para ingresarle la cantidad de joyas de acuerdo al nivel
        this.CantidadJoyasPorNivel = 4;
        this.ContadorVidas = 1;
        this.BordeCriticoX = false;
        this.BordeCriticoY = false;

        System.out.println("TamMatrizX = " + this.TamMatrizX);
        System.out.println("TamMatrizY = " + this.TamMatrizY);
        System.out.println("PosicionX = " + this.PosicionX);
        System.out.println("PosicionY = " + this.PosicionY);
        System.out.println("PosicionRelativaX = " + this.PosicionRelativaX);
        System.out.println("PosicionRelativaY = " + this.PosicionRelativaY);
    }



    /*
     * Inicio sección lógica relacionada al movimiento y la toma de acciones del personaje
     * -------------------------------------------------------------------------
     */


    
    /**
     * Método que mueve un las posiciones del personaje en la capa de lógica, se necesita
     * la accion realizada para poder determinar el movimiento, se retorna un movimiento
     * que siempre esta dentro de las posiciones de la matriz, en el determinado caso
     * de que las posiciones se salgan de la matriz, se dejan arbitrariamente en el borde
     * para que en una proxima iteración, se retorne un movimiento en el que se verifique
     * que no se puede mover.
     *
     * @param accion Indica la accion por la cual se requiere nover el personaje
     * @return Inidica una posicion a consultar en la capa de logica o bien nulo para
     * indicar que se puede mover sin problemas todavia.
     */
    public Movimiento MoverPersonaje(int accion) {
        this.Accion = accion;
        //System.out.println("PosicionY = " + this.PosicionY);
        //System.out.println("PosicionX = " + this.PosicionX);
        switch (this.Accion) {
            case 37:
                /*
                 * El primer caso es que, si me paso de cuadro entonces debo ajustar
                 * el movimiento a realizar para no pasarme de cuadro y quedar encima
                 * de otra imagen
                 *
                 * Ojo que esto se esta moviendo hacia la izquierda, por lo tanto
                 * la variable PosicionRelativaX va decrementando, esto implica que
                 * mientras PosicionRelativaX sea mayor que el tope del cuadro de la matriz
                 * se puede mover porque se encuentra en su cuadro pero si es igual o menor
                 * entonces se pasó y hay que preguntar que hay en ese cuadro para luego recien
                 * verificar si se puede mover o no. Se le resta 1 porque tiene que llegar hasta
                 * un bloque antes
                 */
                if (this.PosicionX > 0 && this.PosicionRelativaX > (this.PosicionX) * this.Ancho) {
                    // si con el movimiento que voy a hacer ahora me paso, entonces debo ajustar el movimiento
                    // para no salirme o del margen o entrar en otra fi                    System.out.println("retorna consulta");gura saltandome la validación de bloque

                    // se guarda la distancia que hay entre la posción actual del personaje y el tope de movimiento
                    // para dicho evento
                    int dx = this.PosicionRelativaX - (this.PosicionX) * this.Ancho;
                    // si no me paso, me puedo mover
                    if (dx > this.FactordeMovimiento) {
                        this.PosicionRelativaX = this.PosicionRelativaX - this.FactordeMovimiento;
                        // si estoy entre medio de dos bloques moviendome hacia la izquierda, entonces si se considera un borde critico
                    } else {
                        this.PosicionRelativaX = this.PosicionRelativaX - dx;
                        // si estoy justo en el borde del bloque que me corresponde, entonces no se considera un borde critico
                    }
                    setBordesCriticos('y');
                    return null;
                    // se supone que el else solo representa al igual porque aqui arriba me encargue de
                    // de dejar el personaje justo en el borde
                } else if (this.PosicionX <= 0) {
                    this.PosicionRelativaX = 0;
                    setBordesCriticos('y');
                    // si estoy entre medio de dos bloques moviendome hacia la derecha, entonces si se considera un borde critico
                    return null;
                } else {
                    // si estoy justo en el borde, entonces envio una consulta en las posiciones indicadas para saber
                    // si me puedo mover o no
                    return new Movimiento(this.PosicionX - 1, this.PosicionY);
                }
            case 38:

                // si es posible aun moverse hacia arriba sin llegar al tope del bloque permitido
                if (this.PosicionY > 0 && this.PosicionRelativaY > (this.PosicionY) * this.Altura) {
                    int dy = this.PosicionRelativaY - (this.PosicionY) * this.Altura;

                    if (dy > this.FactordeMovimiento) {
                        this.PosicionRelativaY = this.PosicionRelativaY - this.FactordeMovimiento;
                        // si estoy entre medio de dos bloques subiendo, entonces si se considera un borde critico
                    } else {
                        this.PosicionRelativaY = this.PosicionRelativaY - dy;
                        // si estoy justo en el borde del bloque que me corresponde, entonces no se considera un borde critico
                    }
                    setBordesCriticos('x');
                    return null;
                    // se supone que el else solo representa al igual porque aqui arriba me encargue de
                    // de dejar el personaje justo en el borde
                } else if (this.PosicionY == 0) {
                    this.PosicionRelativaY = 0;
                    setBordesCriticos('x');
                    return null;
                } else {
                    // si esto justo en el borde, entonces envio una consulta en las posiciones indicadas para saber
                    // si me puedo mover o no
                    return new Movimiento(this.PosicionX, this.PosicionY - 1);
                }

            case 39:
                if (this.PosicionX < this.TamMatrizX - 1 && this.PosicionRelativaX < (this.PosicionX) * this.Ancho) {
                    // si con el movimiento que voy a hacer ahora me paso, entonces debo ajustar el movimiento
                    // para no salirme o del margen o entrar en otra figura saltandome la validación de bloque

                    // se guarda la distancia que hay entre la posción actual del personaje y el tope de movimiento
                    // para dicho evento
                    int dx = (this.PosicionX) * this.Ancho - this.PosicionRelativaX;

                    // si no me paso, me puedo mover
                    if (dx > this.FactordeMovimiento) {
                        this.PosicionRelativaX = this.PosicionRelativaX + this.FactordeMovimiento;
                        // si estoy entre medio de dos bloques moviendome hacia la derecha, entonces si se considera un borde critico
                    } else {
                        this.PosicionRelativaX = this.PosicionRelativaX + dx;
                        // si estoy justo en el borde del bloque que me corresponde, entonces no se considera un borde critico
                        //this.BordeCriticoX = false;
                    }
                    setBordesCriticos('y');
                    return null;
                    // se supone que el else solo representa al igual porque aqui arriba me encargue de
                    // de dejar el personaje justo en el borde
                } else if (this.PosicionX >= this.TamMatrizX - 1) {
                    this.PosicionRelativaX = (this.TamMatrizX - 1) * this.Ancho;
                    setBordesCriticos('y');
                    return null;
                } else {
                    // si esto justo en el borde, entonces envio una consulta en las posiciones indicadas para saber
                    // si me puedo mover o no
                    return new Movimiento(this.PosicionX + 1, this.PosicionY);
                }
            case 40:
                // si es posible aun moverse hacia arriba sin llegar al tope del bloque permitido
                if (this.PosicionY < this.TamMatrizX - 1 && this.PosicionRelativaY < (this.PosicionY) * this.Altura) {
                    int dy = (this.PosicionY) * this.Altura - this.PosicionRelativaY;

                    if (dy > this.FactordeMovimiento) {
                        this.PosicionRelativaY = this.PosicionRelativaY + this.FactordeMovimiento;
                        // si estoy entre medio de dos bloques subiendo, entonces si se considera un borde critico
                    } else {
                        this.PosicionRelativaY = this.PosicionRelativaY + dy;
                        // si estoy justo en el borde del bloque que me corresponde, entonces no se considera un borde critico
                    }
                    setBordesCriticos('x');
                    return null;
                    // se supone que el else solo representa al igual porque aqui arriba me encargue de
                    // de dejar el personaje justo en el borde
                } else if (this.PosicionY == this.TamMatrizY - 1) {
                    this.PosicionRelativaY = this.PosicionY * this.Altura;
                    setBordesCriticos('x');
                    return null;
                } else {
                    // si esto justo en el borde, entonces envio una consulta en las posiciones indicadas para saber
                    // si me puedo mover o no
                    return new Movimiento(this.PosicionX, this.PosicionY + 1);
                }
            default:
                return null;
        }
    }

    /**
     * Método ejectuado siempre que el método MoverPersonaje retorne un objeto movimiento
     * para consultar la matriz, esto sucede cada vez que el personaje se va a mover a otro
     * cuadro de la matriz, por lo tanto, luego de analizado esto, el personaje sabe que tiene
     * todo el espacio hacia adelante para moverse, de lo contrario el jugador
     * deberá ajustar su posición para moverse.
     *
     * @param codigo código que indica lo que habia en la posción retornada del método MoverPersonaje
     */
    public void MoverConReturn(char Mapa[][], Movimiento m) {
        char codigo = Mapa[m.getY()][m.getX()];
        //System.out.println("Mover con return");
        //System.out.println("PosicionY = " + this.PosicionY);
        //System.out.println("PosicionX = " + this.PosicionX);

        if (codigo == '3') {
            switch (this.Accion) {
                case 37:
                    // si aun no me salgo de la matriz
                    if (this.PosicionX > 0) {
                        this.PosicionRelativaX = this.PosicionRelativaX - this.FactordeMovimiento;
                        // si me movi muy a la izquierda y me salgo del mapa, me devuelvo al tope del mapa
                        if (this.PosicionRelativaX < 0) {
                            this.PosicionRelativaX = 0;
                        }
                        this.PosicionX--;
                    } else if (this.PosicionRelativaX != 0) {
                        this.PosicionX = 0;
                        this.PosicionRelativaX = this.PosicionRelativaX - this.FactordeMovimiento;
                        // si me movi muy a la izquierda y me salgo del mapa, me devuelvo al tope del mapa
                        if (this.PosicionRelativaX < 0) {
                            this.PosicionRelativaX = 0;
                        }
                    }
                    break;
                case 38:
                    // si aun no me salgo de la matriz
                    if (this.PosicionY > 0) {
                        this.PosicionRelativaY = this.PosicionRelativaY - this.FactordeMovimiento;
                        // si me movi muy hacia arriba y me salgo del mapa, me devuelvo al tope del mapa
                        if (this.PosicionRelativaY < 0) {
                            this.PosicionRelativaY = 0;
                        }
                        this.PosicionY--;
                    } else if (this.PosicionRelativaY != 0) {
                        this.PosicionY = 0;
                        this.PosicionRelativaY = this.PosicionRelativaY - this.FactordeMovimiento;
                        // si me movi muy hacia arriba y me salgo del mapa, me devuelvo al tope del mapa
                        if (this.PosicionRelativaY < 0) {
                            this.PosicionRelativaY = 0;
                        }
                    }
                    break;
                case 39:
                    // si aun no me salgo de la matriz
                    if (this.PosicionX < this.TamMatrizX - 1) {
                        this.PosicionRelativaX = this.PosicionRelativaX + this.FactordeMovimiento;
                        // si me movi muy a la izquierda y me salgo del mapa, me devuelvo al tope del mapa
                        if (this.PosicionRelativaX > (this.TamMatrizX - 1) * this.Ancho) {
                            this.PosicionRelativaX = (this.TamMatrizX - 1) * this.Ancho;
                        }
                        this.PosicionX++;
                    } else if (this.PosicionRelativaX < (this.TamMatrizX - 1) * this.Ancho) {
                        this.PosicionX = this.TamMatrizX - 1;
                        this.PosicionRelativaX = this.PosicionRelativaX + this.FactordeMovimiento;
                        // si me movi muy a la izquierda y me salgo del mapa, me devuelvo al tope del mapa
                        if (this.PosicionRelativaX > (this.TamMatrizX - 1) * this.Ancho) {
                            this.PosicionRelativaX = (this.TamMatrizX - 1) * this.Ancho;
                        }
                    }
                    break;
                case 40:
                    // si aun no me salgo de la matriz
                    if (this.PosicionY < this.TamMatrizY - 1) {
                        this.PosicionRelativaY = this.PosicionRelativaY + this.FactordeMovimiento;
                        if (this.PosicionRelativaY > (this.TamMatrizY - 1) * this.Altura) {
                            this.PosicionRelativaY = (this.TamMatrizY - 1) * this.Altura;
                        }
                        this.PosicionY++;
                    } else if (this.PosicionRelativaY != (this.TamMatrizY - 1) * this.Altura) {
                        this.PosicionY = this.TamMatrizY - 1;
                        this.PosicionRelativaY = this.PosicionRelativaY + this.FactordeMovimiento;
                        if (this.PosicionRelativaY > (this.TamMatrizY - 1) * this.Altura) {
                            this.PosicionRelativaY = (this.TamMatrizY - 1) * this.Altura;
                        }
                    }
                    break;
                default:
                    // stop
                    break;
            }
        } else {
            switch (codigo) {
                // si es una joya
                case '4':
                    this.ContadorJoyas++;
                    System.out.println("aumentando joyas");
                    System.out.println("Cantidad de joyas = " + this.ContadorJoyas + " Joyas por nivel = " + this.CantidadJoyasPorNivel);
                    Mapa[m.getY()][m.getX()] = '3';
                    break;
                case '5':
                    System.out.println("Personaje debe Morir!!!");
                    PerderVida();
                    Mapa[m.getY()][m.getX()] = '3';
                    break;
                case '8':
                    Mapa[m.getY()][m.getX()] = '3';
                    System.out.println("Personaje avanza o pasa el nivel");
                    break;
                case '9':
                    AumentarVida();
                    System.out.println("Aumentar vidas, vidas = " + this.ContadorVidas);
                    Mapa[m.getY()][m.getX()] = '3';
                    break;
                default:
                    System.out.println("Codigo " + codigo +" no permite moverse");
                    break;
            }
            return;
            // por definir
        }
    }

    private void setBordesCriticos(char variable) {
        switch (variable) {
            case 'x':
                if (this.PosicionRelativaY == this.PosicionY * this.Altura) {
                    this.BordeCriticoY = false;
                } else {
                    this.BordeCriticoY = true;
                }
                break;
            case 'y':
                if (this.PosicionRelativaX == this.PosicionX * this.Ancho) {
                    this.BordeCriticoX = false;
                } else {
                    this.BordeCriticoX = true;
                }
                break;
            default:
                break;
        }
    }



    /*
     * Fin sección lógica relacionada al movimiento y la toma de acciones del personaje
     * -------------------------------------------------------------------------
     */



    /*
     * Inicio sección getter and setter
     * -------------------------------------------------------------------------
     */



    public int getAccion() {
        return Accion;
    }

    public void setAccion(int Accion) {
        this.Accion = Accion;
    }

    public int getFactordeMovimiento() {
        return FactordeMovimiento;
    }

    public void setFactordeMovimiento(int FactordeMovimiento) {
        this.FactordeMovimiento = FactordeMovimiento;
    }

    public int getPosicionRelativaX() {
        return PosicionRelativaX;
    }

    public void setPosicionRelativaX(int PosicionRelativaX) {
        this.PosicionRelativaX = PosicionRelativaX;
    }

    public int getPosicionRelativaY() {
        return PosicionRelativaY;
    }

    public void setPosicionRelativaY(int PosicionRelativaY) {
        this.PosicionRelativaY = PosicionRelativaY;
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

    public boolean isMuerto() {
        if (this.ContadorVidas == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean TodasLasJoyas() {
        if (this.CantidadJoyasPorNivel == this.ContadorJoyas) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isBordeCriticoX() {
        return BordeCriticoX;
    }

    public boolean isBordeCriticoY() {
        return BordeCriticoY;
    }

    public void AumentarVida() {
        this.ContadorVidas++;
    }

    public void PerderVida() {
        this.ContadorVidas--;
    }

    public int getCantidadJoyasPorNivel() {
        return CantidadJoyasPorNivel;
    }

    public int getContadorJoyas() {
        return ContadorJoyas;
    }

    public int getContadorVidas() {
        return ContadorVidas;
    }

    public void setCantidadJoyasPorNivel(int CantidadJoyasPorNivel) {
        this.CantidadJoyasPorNivel = CantidadJoyasPorNivel;
    }

    public String getNombreUsuario() {
        System.out.println("Retornar nombre de usuario = " + this.NombreUsuario);
        return NombreUsuario;
    }

    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }

    

    /*
     * Fin sección getter and setter
     * -------------------------------------------------------------------------
     */
}
