package Logica;

/**
 * Clase lógica de la Arena movediza encargada de realizar todas las infecciones
 * de acuerdo a un patrón no establecido necesariamente, los contagios se hacen
 * mediante un random que selecciona uno de los 4 movimientos posibles, es decir:
 *      - Arriba
 *      - Abajo
 *      - Izquierda
 *      - Derecha
 *
 *  Luego de tener uno de estos movimientos seleccionados, se elige otro número random
 * que se mueve entre el factor del nivel (vease definición de @param FactorDeNivel) para
 * determinar el punto medio de este factor, y asi moverme de acuerdo al pibote elegido anteriomente
 * en la dirección perpendicular a la elegida anteriormente por lo tanto se logra aumentar o
 * situar en distintos lugares randome cada una de las arenas movedizas y SIEMPRE se llena un
 * nivel primero para luego comenzar a llenar desde uno mas lejos
 *
 * @author andrew
 */
public class ArenaMovediza {

    /**
     * @param PosicionInicialX indica la posición inicial de la semilla dentro de
     * la matriz para usarla como referencia e infectar a su alrededor.
     */
    private int PosicionInicialX;
    /**
     * @param PosicionInicialY indica la posición inicial de la semilla dentro de
     * la matriz para usarla como referencia e infectar a su alrededor.
     */
    private int PosicionInicialY;
    /**
     * @param LargoMatrizX indica el ancho de la matriz en la que se esta insertando
     * la arena movediza.
     */
    private int LargoMatrizX;
    /**
     * @param LargoMatrizX indica el largo de la matriz en la que se esta insertando
     * la arena movediza.
     */
    private int LargoMatrizY;
    /**
     * @param FactorDeNivel indica cuantas infecciones se pueden realizar sin irse
     * un nivel mas lejos. Nivel se refiere a la cantidad de elementos a su alrededor
     * que pueden ser puesto sin aumentar en uno la distancia desde el centro o desde
     * la posición inicial de la arena movediza. Factor de nivel esta definido mediante
     * una función simple pero eficaz y que tiene directa relación con el crecimiento exponencial
     * de la infección, en este caso, la función relacionada al factor de nivel es:
     *
     *      FactorDeNivel = FactorDeNivel + 2
     *
     *  Esto debido a que cada vez que el nivel de infección, es decir
     * que todo lo que tiene a su alrededor mas cercano fue contagiado, aumenta su
     * nivel de contagio, es decir puede contagiar desde mas lejos, por lo tanto, para
     * hacer que contagie a su alrededor, debo ser capaz de moverme uno mas de forma horizontal
     * y uno mas de forma vertical, por lo tanto dado que para cada lado debo sumar 1, entonces
     * el nuevo factor de nivel no aumenta en 1 si no que en dos para poder rellenar como si siempre
     * fuese una matriz cuadrada
     */
    private int FactorDeNivel;
    /**
     * @param Nivel indica el nivel de contagio de la arena movediza. En este caso indica
     * que tan lejos puede contagiar la arena movediza desde el centro o posición inicial
     */
    private int Nivel;
    /**
     * @param CantidadDeInfectados Indica la cantidad global de arenas movedizas infectadas
     * de esta semilla en particular.
     */
    private int CantidadDeInfectados;

    /**
     * @param Codigo Indica el codigo que representará a la Arena movediza
     */
    private char Codigo;

    private boolean MatrizRespuesta[][];

    /**
     * Constructor de la arena movediza
     *
     * @param PosicionInicialX indica la posición dentro de la matriz en la cual fue creada la arena
     * @param PosicionInicialY indica la posición dentro de la matriz en la cual fue creada la arena
     * @param LargoMatrizY Indica cuanto mide la matriz verticalmente
     * @param LargoMatrizX Indica cuanto mide la matriz horizontalmente
     */
    public ArenaMovediza(int PosicionInicialY, int PosicionInicialX, int LargoMatrizY, int LargoMatrizX) {
        this.PosicionInicialX = PosicionInicialX;
        this.PosicionInicialY = PosicionInicialY;
        this.LargoMatrizX = LargoMatrizX;
        this.LargoMatrizY = LargoMatrizY;
        this.MatrizRespuesta = new boolean[this.LargoMatrizY][this.LargoMatrizX];
        this.MatrizRespuesta[this.PosicionInicialY][this.PosicionInicialX] = true;
        this.Nivel = 1;
        this.FactorDeNivel = 3;
        this.CantidadDeInfectados = 1;
        this.Codigo = '6';
    }



    
    /* Inicio de sección de la Lógica usada para contagiar o infectar dentro del
     * -------------------------------------------------------------------------
     */


    
    /**
     * Método que infecta dentro de la matriz convirtiedo cualquier cosa del mapa
     * en una nueva arena movediza
     *
     * @param Mapa mapa en el cual se está infectando
     */
    public Movimiento Infectar(char Mapa[][]) {
        // si la cantidad de infectados es igual a todas las posiciones dentro de
        // la matriz entonces ya se lleno la matriz completa

        if (this.CantidadDeInfectados == this.LargoMatrizX * this.LargoMatrizY) {
            System.out.println("Game Over");
            return null;
        // si el factor de nivel es mayor o igual a NumeroCritico + 2, entonces ya se llenó
        // todo el cuadrado máximo dentro del mapa, ahora solo que llenar los bordes
        } else{
            return ValidarNuevaInfeccion(Mapa);
        }
    }

    /**
     * Si se va a realizar una infección random entonces se utiliza este método que siempre
     * ubica una arena movediza en alguna parte, ya sea por el método randon asociado
     * o por la recursión que busca el mas cercano
     *
     * @param Mapa mapa donde se esta realizando la infección
     *
     */
    private Movimiento ValidarNuevaInfeccion(char Mapa[][]) {
        int movimiento = 0;
        int dif = 0;
        int Dy = 0;
        int Dx = 0;
        do {
            Dx = Dy = 0;
            movimiento = Randomize(1, 4, 10);
            dif = (int) ((this.FactorDeNivel -1) / 2) - Randomize(0, this.FactorDeNivel - 1, 100);
            switch (movimiento) {
                // Arriba
                case 1:
                    Dy = this.PosicionInicialY - this.Nivel;
                    Dx = this.PosicionInicialX + dif;
                    break;
                // Derecha
                case 2:
                    Dy = this.PosicionInicialY + dif;
                    Dx = this.PosicionInicialX + this.Nivel;
                    break;
                // Abajo
                case 3:
                    Dy = this.PosicionInicialY + this.Nivel;
                    Dx = this.PosicionInicialX + dif;
                    break;
                // Izquierda
                case 4:
                    Dy = this.PosicionInicialY + dif;
                    Dx = this.PosicionInicialX - this.Nivel;
                    break;
                default:
                    // lo dejo en las posiciones iniciales, aunque a pesar de que nunca se llega al default
                    // en el determinado caso que llega, movimiento es mayor que 4 dejo los valores inciales por lo
                    // que para la condicion del while esto siempre será igual a 6
                    Dy = this.PosicionInicialY;
                    Dx = this.PosicionInicialX;
                    break;
            }
            //System.out.println("Mapa[y][x] = " + Mapa[m.getY()][m.getX()]);
        } while (Dx < 0 || Dx >= this.LargoMatrizX || Dy < 0 || Dy >= this.LargoMatrizY || Mapa[Dy][Dx] == this.Codigo && this.MatrizRespuesta[Dy][Dx]);
        // si en esta matriz en esa posición no habia un 6 entonces es porque nadie ha puesto un 6 ahí, por lo tanto yo lo seteo

        // Si el codigo del Mapa enviado es 6 pero yo no lo guardé, asumo que otra arena movediza la insertó
        // y tomo esa infección como propia, por lo tanto cuenta igual al actualizar el nivel, si no habia una
        // arena movediza y por ende yo no la puse, entonces se inserta en esa posición

        // siempre que se sale del while se debe actualizar el nivel, luego se verifica si habia antes o no por lo explicado anteriormente
        ActualizarDatosDeNivel();
        
        if(Mapa[Dy][Dx] != this.Codigo && !this.MatrizRespuesta[Dy][Dx]){
            //System.out.println("Insertando 6 en Dy = " + Dy + " Dx = " + Dx);
            Mapa[Dy][Dx] = '6';
            this.MatrizRespuesta[Dy][Dx] = true;
            return new Movimiento(Dx, Dy);
        }else{
            System.out.println("Dy = " + Dy + " Dx = " + Dx);
            System.out.println("habia un 6 antes");
            this.MatrizRespuesta[Dy][Dx] = true;
            return null;
        }

    }

    private int Randomize(int inicio, int termino, int Potencia10) {
        int valor = (int) (Math.random() * Potencia10);
        while (valor < inicio || valor > termino) {
            valor = (int) (Math.random() * Potencia10);
        }
        return valor;
    }

    /**
     * Para no repetir tantas veces los mismos pasos, la actualización de nivel que consta de
     * el aumento en la cantidad de infectado y la verificación del factor de nivel se realizan
     * por medio de este método
     */
    private void ActualizarDatosDeNivel() {
        this.CantidadDeInfectados++;
        // se le debe restar para no ser la misma posición inicial
        int BordeMenorX = this.PosicionInicialX - this.Nivel;
        int BordeMenorY = this.PosicionInicialY - this.Nivel;
        int BordeMayorX = this.PosicionInicialX + this.Nivel;
        int BordeMayorY = this.PosicionInicialY + this.Nivel;
        int CantidadRealInfectadosPorNivel;
        // en el mejor caso todo esta dentro de los margenes
        if (BordeMenorX >= 0 && BordeMenorY >= 0 && BordeMayorX < this.LargoMatrizX && BordeMayorY < this.LargoMatrizY) {

            CantidadRealInfectadosPorNivel = (int) Math.pow(this.FactorDeNivel, 2);

            // luego estas 4 condiciones verifican que solo sea uno el que se pasa

        } else if (BordeMenorX < 0 && BordeMenorY >= 0 && BordeMayorX < this.LargoMatrizX && BordeMayorY < this.LargoMatrizY) {

            CantidadRealInfectadosPorNivel = (int) Math.pow(this.FactorDeNivel, 2) + BordeMenorX*this.FactorDeNivel;

        } else if (BordeMenorX >= 0 && BordeMenorY < 0 && BordeMayorX < this.LargoMatrizX && BordeMayorY < this.LargoMatrizY) {

            CantidadRealInfectadosPorNivel = (int) Math.pow(this.FactorDeNivel, 2) + BordeMenorY*this.FactorDeNivel;

        } else if (BordeMenorX >= 0 && BordeMenorY >= 0 && BordeMayorX >= this.LargoMatrizX && BordeMayorY < this.LargoMatrizY) {

            int diferenciaX = BordeMayorX - this.LargoMatrizX;

            CantidadRealInfectadosPorNivel = (int) Math.pow(this.FactorDeNivel, 2) - diferenciaX*this.FactorDeNivel;

        } else if (BordeMenorX >= 0 && BordeMenorY >= 0 && BordeMayorX < this.LargoMatrizX && BordeMayorY >= this.LargoMatrizY) {

            int diferenciaY = BordeMayorY - this.LargoMatrizY;

            CantidadRealInfectadosPorNivel = (int) Math.pow(this.FactorDeNivel, 2) - diferenciaY*this.FactorDeNivel;


            // Ahora se verifica que dos de los margenes no sean compatibles


        } else if (BordeMenorX < 0 && BordeMenorY < 0 && BordeMayorX < this.LargoMatrizX && BordeMayorY < this.LargoMatrizY) {

            // x < 0 e y < 0

            // en esta condición ambos borde son negativos por lo tanto el resultado es negativo, entonces si los sumo a la larga
            // los resto, y al multiplicar ambos negativos me dará uno positivo por lo tanto ese si debo sumarselo porque es
            // la cantidad de bloques o cuadros que reste dos veces
            CantidadRealInfectadosPorNivel = (int) Math.pow(this.FactorDeNivel, 2) + this.FactorDeNivel*BordeMenorX + this.FactorDeNivel*BordeMenorY + BordeMenorX*BordeMenorY;

        } else if (BordeMenorX < 0 && BordeMenorY >= 0 && BordeMayorX >= this.LargoMatrizX && BordeMayorY < this.LargoMatrizY) {

            // x < 0 y x >= Largo de la matriz

            // En esta condición los bordes no se cruzan por lo que hay que restar los repetidos

            // a diferencia le resto 1 porque si es igual entonces al menos me pase 1 linea fuera de la matriz
            int diferencia = BordeMayorX - this.LargoMatrizX + 1;
            // en este caso BordeMenorX sigue siendo menor por lo tanto para poder restarlo simplemente lo sumo y para el LargoMatrizX
            // BordeMayorX es siempre mayor o el mismo por lo tanto se hace la diferencia y se resta 
            CantidadRealInfectadosPorNivel = (int) Math.pow(this.FactorDeNivel, 2) + this.FactorDeNivel*BordeMenorX - this.FactorDeNivel*diferencia;

        } else if (BordeMenorX < 0 && BordeMenorY >= 0 && BordeMayorX < this.LargoMatrizX && BordeMayorY >= this.LargoMatrizY) {

            // x < 0 y >= Largo de la matriz

            // en este caso le debo quitar los repetidos porque las lineas no se cruzan

            // a diferencia le resto 1 porque si es igual entonces al menos me pase 1 linea fuera de la matriz
            int diferencia = BordeMayorY - this.LargoMatrizY + 1;
            // en este caso BordeMenorX sigue siendo menor por lo tanto para poder restarlo simplemente lo sumo y para el LargoMatrizY
            // BordeMayorY es siempre mayor o el mismo por lo tanto se hace la diferencia y se resta
            CantidadRealInfectadosPorNivel = (int) Math.pow(this.FactorDeNivel, 2) + this.FactorDeNivel*BordeMenorX - this.FactorDeNivel*diferencia -(BordeMenorX*diferencia);


        } else if (BordeMenorX >= 0 && BordeMenorY < 0 && BordeMayorX >= this.LargoMatrizX && BordeMayorY < this.LargoMatrizY) {

            // y < 0 y x >=Largo de la Matriz

            // en este caso le debo quitar los repetidos porque las lineas no se cruzan

            // a diferencia le resto 1 porque si es igual entonces al menos me pase 1 linea fuera de la matriz
            int diferencia = BordeMayorX - this.LargoMatrizX + 1;
            // en este caso BordeMenorX sigue siendo menor por lo tanto para poder restarlo simplemente lo sumo y para el LargoMatrizX
            // BordeMayorX es siempre mayor o el mismo por lo tanto se hace la diferencia y se resta
            CantidadRealInfectadosPorNivel = (int) Math.pow(this.FactorDeNivel, 2) + this.FactorDeNivel*BordeMenorY - this.FactorDeNivel*diferencia -(BordeMenorY*diferencia);

        } else if (BordeMenorX >= 0 && BordeMenorY < 0 && BordeMayorX < this.LargoMatrizX && BordeMayorY >= this.LargoMatrizY) {

            // y < 0 e y >= Largo de la Matriz

            // aqui no le resto porque estoy quitando lineas paralelas


            // a diferencia le resto 1 porque si es igual entonces al menos me pase 1 linea fuera de la matriz
            int diferencia = BordeMayorY - this.LargoMatrizY + 1;
            // en este caso BordeMenorX sigue siendo menor por lo tanto para poder restarlo simplemente lo sumo y para el LargoMatrizX
            // BordeMayorX es siempre mayor o el mismo por lo tanto se hace la diferencia y se resta
            CantidadRealInfectadosPorNivel = (int) Math.pow(this.FactorDeNivel, 2) + this.FactorDeNivel*BordeMenorY - this.FactorDeNivel*diferencia;

        } else if (BordeMenorX >= 0 && BordeMenorY >= 0 && BordeMayorX >= this.LargoMatrizX && BordeMayorY >= this.LargoMatrizY) {

            // x >= Largo de la Matriz e y >= Largo de la Matriz

            // aqui si se le resta porque estoy cruzando lineas paralelas
            int diferenciaX = BordeMayorX - this.LargoMatrizX + 1;
            int diferenciaY = BordeMayorY - this.LargoMatrizY + 1;

            // En este caso le debo restar lo que me pase en x y lo que me pase en y sumarle una de las repeticiones, dado que reste dos
            // veces la misma superficie
            CantidadRealInfectadosPorNivel = (int) Math.pow(this.FactorDeNivel, 2) - this.FactorDeNivel*(diferenciaX + diferenciaY) + diferenciaX*diferenciaY;


            // Ahora se verifica que 3 de los 4 margenes se rompan


        } else if (BordeMenorX < 0 && BordeMenorY < 0 && BordeMayorX >= this.LargoMatrizX && BordeMayorY < this.LargoMatrizY) {

            // x < 0 y < 0 x >= Largo de la Matriz e y < Largo de la Matriz

            int diferenciaX = BordeMayorX - this.LargoMatrizX + 1;

            // Al cuadrado del factor de nivel le debo restar lo que me sali hacia la izquierda en x y lo
            // que me sali havia arriba en y además restarle lo que me sali hacia la derecha en x, y sumarle
            // todos los elementos que reste dos veces, en este caso la interseccion de los bordes en x y e y la
            // intersección entre lo que me sali hacia arriba con lo que me sali hacia la derecha
            CantidadRealInfectadosPorNivel = (int) Math.pow(this.FactorDeNivel, 2) + this.FactorDeNivel*(BordeMenorX + BordeMenorY) - this.FactorDeNivel*diferenciaX + BordeMenorX*BordeMenorY - diferenciaX*BordeMenorY;

        } else if (BordeMenorX < 0 && BordeMenorY < 0 && BordeMayorX < this.LargoMatrizX && BordeMayorY >= this.LargoMatrizY) {

            // x < 0 e y < 0 y x < LargoMatrizX e y >= Largo

            int diferenciaY = BordeMayorY - this.LargoMatrizY + 1;

            // Al cuadrado del factor de nivel le debo restar lo que me sali hacia la izquierda en x y lo
            // que me sali hacia arriba en y además debo restarle lo que me sali hacia abajo en y, y debo sumarle
            // todos los elementos que reste dos veces, en este caso la intersección de los bordes superiores x e y
            // y la intersección entre borde superior x y el tope inferiorY
            CantidadRealInfectadosPorNivel = (int) Math.pow(this.FactorDeNivel, 2) + this.FactorDeNivel*(BordeMenorX + BordeMenorY) - this.FactorDeNivel*diferenciaY + BordeMenorX*BordeMenorY - (diferenciaY*BordeMenorX);

        } else if (BordeMenorX < 0 && BordeMenorY >= 0 && BordeMayorX >= this.LargoMatrizX && BordeMayorY >= this.LargoMatrizY) {

            // x < 0 e y >= 0 y x >= Largo de la Matriz e y >= Largo de la Matriz

            int diferenciaX = BordeMayorX - this.LargoMatrizX + 1;
            int diferenciaY = BordeMayorY - this.LargoMatrizY + 1;

            /**
             * Al cuadrado del factor de nivel le debo restar lo que me sali hacia la izquierda en x y lo que me sali hacia
             * la derecha en x y además debo restarle lo que me sali hacia abajo en y, y debo sumarle
             * todos los elementos que reste dos veces, en este caso la intersección de los bordes inferiores y las
             * intersecciones entre lo que me sali a la izquierda y lo que me sali hacia abajo
             */
            CantidadRealInfectadosPorNivel = (int) Math.pow(this.FactorDeNivel, 2) + this.FactorDeNivel*BordeMenorX - this.FactorDeNivel*(diferenciaX + diferenciaY) -(diferenciaY*BordeMenorX) + diferenciaX*diferenciaY;

        } else if (BordeMenorX >= 0 && BordeMenorY < 0 && BordeMayorX >= this.LargoMatrizX && BordeMayorY >= this.LargoMatrizY) {

            // x >= 0 e y < 0 y x >= Largo de la Matriz e y >= Largo de la Matriz

            int diferenciaX = BordeMayorX - this.LargoMatrizX + 1;
            int diferenciaY = BordeMayorY - this.LargoMatrizY + 1;

            /**
             * Al cuadrado del factor de nivel le debo restar lo que me sali hacia la derecha en x y además debo restarle lo que
             * me sali hacia arriba en y lo que me sali hacia abajo en y, y debo sumarle todos los elementos que reste
             * dos veces, en este caso la interseccion entre ambos topes y lo que me pase hacia arriba y lo que me pase hacia la derecha
             */
            CantidadRealInfectadosPorNivel = (int) Math.pow(this.FactorDeNivel, 2) - this.FactorDeNivel*(diferenciaX + diferenciaY) + this.FactorDeNivel*BordeMenorX + diferenciaX*diferenciaY - diferenciaX*BordeMenorY;
            
            // todos los margenes se rompen


        } else {
            CantidadRealInfectadosPorNivel = (int) Math.pow(this.FactorDeNivel - 1, 2);
        }
        
        if (this.CantidadDeInfectados == CantidadRealInfectadosPorNivel) {
            this.Nivel++;
            this.FactorDeNivel = this.FactorDeNivel + 2;
        }
    }

    /**
     * Método que retorna verdadero en caso de que el juego acabe por que la arena movediza mató al personaje y
     * falso en caso de que aun queden espacios por cubrir a la arena
     *
     * @return <li>true: En el caso de que termina el juego porque la arena contagio todo el mapa </li>
     *         <li>false: En el caso de que aun quede espacio para contaqiar dentro del mapa </li>
     */
    public boolean GameOver(){
        if(this.CantidadDeInfectados == this.LargoMatrizX*this.LargoMatrizY){
            return true;
        }else{
            return false;
        }
    }
    
    /* Fin de sección de la Lógica usada para contagiar o infectar dentro del
     * -------------------------------------------------------------------------
     */

}
