package Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/*
 * Clase para manejo del juego de Damas 0: posiciones inválidas 1: fichas
 * propias (peones blancos) 2: fichas enemigas (peones negros) 3: campos
 * disponibles para movimiento 11: reinas propias (reinas blancas) 22: reinas
 * enemigas (reinas negras)
 *
 * Autor: reych
 */
public class Damas implements Serializable {

    private static final long serialVersionUID = 1L;
    public int contadorFichasAzules;
    public int contadorFichasRojas;

    // Matriz de juego (tablero)
    public int[][] juego = {
        {2, 0, 2, 0, 2, 0, 2, 0},
        {0, 2, 0, 2, 0, 2, 0, 2},
        {2, 0, 2, 0, 2, 0, 2, 0},
        {0, 3, 0, 3, 0, 3, 0, 3},
        {3, 0, 3, 0, 3, 0, 3, 0},
        {0, 1, 0, 1, 0, 1, 0, 1},
        {1, 0, 1, 0, 1, 0, 1, 0},
        {0, 1, 0, 1, 0, 1, 0, 1}
    };

    // Constructor de la clase
    public Damas() {
        // El tablero se inicializa con las posiciones de las fichas
        contadorFichasAzules = 12;
        contadorFichasRojas = 12;
    }
     

    // Método para obtener el tablero de juego
    public int[][] getJuego() {
        return this.juego;
    }

    // Método para establecer el tablero de juego
    public void setJuego(int[][] juego) {
        this.juego = juego;
    }

    /**
     * Método para validar si un movimiento es válido
     *
     * @param filaOrigen La fila de origen de la ficha
     * @param columnaOrigen La columna de origen de la ficha
     * @param nuevaFila La nueva fila a la que se quiere mover la ficha
     * @param nuevaColumna La nueva columna a la que se quiere mover la ficha
     * @return true si el movimiento es válido, false en caso contrario
     */
    public boolean validaMovimiento(int filaOrigen, int columnaOrigen, int nuevaFila, int nuevaColumna) {
        // Caso 1: Verificar si es una posición válida en el tablero (3 es una posición válida)
        if (this.juego[nuevaFila][nuevaColumna] != 3) {
            return false;
        }

        int fichaOrigen = this.juego[filaOrigen][columnaOrigen];
        // Dirección del movimiento de las fichas normales (1: hacia arriba, 2: hacia abajo) 
        int direccionMovimiento = (fichaOrigen == 1) ? -1 : 1;

        // Caso 2: Movimiento válido para fichas normales (no reinas)
        if (fichaOrigen == 1 || fichaOrigen == 2) {
            if ((nuevaFila == filaOrigen + direccionMovimiento)
                    && (nuevaColumna == columnaOrigen + 1 || nuevaColumna == columnaOrigen - 1)) {
                return true;
            }

            // Verificar si puede comer ficha
            if ((nuevaFila == filaOrigen + 2 * direccionMovimiento)
                    && (nuevaColumna == columnaOrigen + 2 || nuevaColumna == columnaOrigen - 2)) {
                int filaComida = filaOrigen + direccionMovimiento;
                int columnaComida = (nuevaColumna + columnaOrigen) / 2; // Columna donde está la ficha enemiga a capturar
                int fichaComida = this.juego[filaComida][columnaComida];

                // Verificar si la ficha comida es del oponente
                if ((fichaOrigen == 1 && (fichaComida == 2 || fichaComida == 22))
                        || (fichaOrigen == 2 && (fichaComida == 1 || fichaComida == 11))) {
                    // Comer la ficha
                    //this.juego[filaComida][columnaComida] = 3;
                    return true;
                }
            }
        }

        // Caso 3: Movimiento válido para reinas (fichas coronadas)
        if (fichaOrigen == 11 || fichaOrigen == 22) {
            int filaDistancia = Math.abs(nuevaFila - filaOrigen);
            int columnaDistancia = Math.abs(nuevaColumna - columnaOrigen);
            if (filaDistancia == columnaDistancia) {
                return true;
            }

            // Verificar si puede comer ficha
            if (filaDistancia == 2 && columnaDistancia == 2) {
                int filaComida = (nuevaFila > filaOrigen) ? filaOrigen + 1 : filaOrigen - 1;
                int columnaComida = (nuevaColumna > columnaOrigen) ? columnaOrigen + 1 : columnaOrigen - 1;
                int fichaComida = this.juego[filaComida][columnaComida];

                // Verificar si la ficha comida es del oponente
                if ((fichaOrigen == 11 && (fichaComida == 2 || fichaComida == 22))
                        || (fichaOrigen == 22 && (fichaComida == 1 || fichaComida == 11))) {
                    // Comer la ficha
                    //this.juego[filaComida][columnaComida] = 3;
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Método para realizar un movimiento en el tablero
     *
     * @param filaOrigen La fila de origen de la ficha
     * @param columnaOrigen La columna de origen de la ficha
     * @param nuevaFila La nueva fila a la que se quiere mover la ficha
     * @param nuevaColumna La nueva columna a la que se quiere mover la ficha
     */
    public void movimiento(int filaOrigen, int columnaOrigen, int nuevaFila, int nuevaColumna) {
        if (validaMovimiento(filaOrigen, columnaOrigen, nuevaFila, nuevaColumna)) {
            int fichaOrigen = this.juego[filaOrigen][columnaOrigen];
            int direccionMovimiento = (fichaOrigen == 1) ? -1 : 1; // Dirección del movimiento de las
            // Realizar el movimiento
            this.juego[nuevaFila][nuevaColumna] = fichaOrigen;
            this.juego[filaOrigen][columnaOrigen] = 3; // La posición de origen queda vacía

            //Peon come.
            //Caso de captura (eliminar ficha enemiga)
            int filaCaptura = filaOrigen + direccionMovimiento;
            int columnaCaptura = (nuevaColumna + columnaOrigen) / 2; // Columna donde está la ficha enemiga a capturar
            if (Math.abs(nuevaFila - filaOrigen) == 2 && Math.abs(nuevaColumna - columnaOrigen) == 2) {
                if (filaCaptura<juego.length && columnaCaptura<juego.length) {
                    this.juego[filaCaptura][columnaCaptura] = 3; // Eliminar ficha enemiga
                }
            }

            //reina propia come.
            //Caso 1. abajo, derecha          
            if ((this.juego[nuevaFila][nuevaColumna] == 11)
                    && nuevaFila > filaOrigen && nuevaColumna > columnaOrigen) {
                //System.out.println("yyy");
                int filaComida = filaOrigen + 1; // Inicializamos la fila desde la que empezaremos a comer
                int columnaComida = columnaOrigen + 1; // Inicializamos la columna desde la que empezaremos a comer
                while (filaComida < nuevaFila && columnaComida < nuevaColumna) {
                    if (this.juego[filaComida][columnaComida] != 1) {
                        this.juego[filaComida][columnaComida] = 3; // Comemos las fichas enemigas en línea recta
                    }
                    filaComida++;
                    columnaComida++;
                }
            }
            //caso 2. arriba, izq.
            if ((this.juego[nuevaFila][nuevaColumna] == 11)
                    && nuevaFila < filaOrigen && nuevaColumna < columnaOrigen) {
                //System.out.println("xxx");
                int filaComida = filaOrigen - 1; // Inicializamos la fila desde la que empezaremos a comer
                int columnaComida = columnaOrigen - 1; // Inicializamos la columna desde la que empezaremos a comer
                while (filaComida > nuevaFila && columnaComida > nuevaColumna) {
                    if (this.juego[filaComida][columnaComida] != 1) {
                        this.juego[filaComida][columnaComida] = 3; // Comemos las fichas enemigas en línea recta
                    }

                    filaComida--;
                    columnaComida--;
                }
            }
            // Caso 3. abajo, derecha
            if ((this.juego[nuevaFila][nuevaColumna] == 11)
                    && nuevaFila > filaOrigen && nuevaColumna < columnaOrigen) {
               // System.out.println("zzz");
                int filaComida = filaOrigen + 1; // Inicializamos la fila desde la que empezaremos a comer
                int columnaComida = columnaOrigen - 1; // Inicializamos la columna desde la que empezaremos a comer
                while (filaComida < nuevaFila && columnaComida > nuevaColumna) {
                    if (this.juego[filaComida][columnaComida] != 1) {
                        this.juego[filaComida][columnaComida] = 3; // Comemos las fichas enemigas en línea recta
                    }
                    filaComida++;
                    columnaComida--;
                }
            }

            // Caso 4. arriba, derecha
            if ((this.juego[nuevaFila][nuevaColumna] == 11)
                    && nuevaFila < filaOrigen && nuevaColumna > columnaOrigen) {
                //System.out.println("www");
                int filaComida = filaOrigen - 1; // Inicializamos la fila desde la que empezaremos a comer
                int columnaComida = columnaOrigen + 1; // Inicializamos la columna desde la que empezaremos a comer
                while (filaComida > nuevaFila && columnaComida < nuevaColumna) {
                    if (this.juego[filaComida][columnaComida] != 1) {
                        this.juego[filaComida][columnaComida] = 3; // Comemos las fichas enemigas en línea recta
                    }
                    filaComida--;
                    columnaComida++;
                }
            }

            //System.out.println(direccionMovimiento);
            // Comprobar si un peón alcanza el borde opuesto del tablero y convertirlo en reina
            if (fichaOrigen == 1 && nuevaFila == 0) {
                this.juego[nuevaFila][nuevaColumna] = 11; // Peón blanco se convierte en reina blanca
            } else if (fichaOrigen == 2 && nuevaFila == 7) {
                this.juego[nuevaFila][nuevaColumna] = 22; // Peón negro se convierte en reina negra
            }
        }

    }

    //mostramos el array
    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < juego.length; i++) {
            for (int j = 0; j < juego[0].length; j++) {
                s += juego[i][j] + " ";
            }
            s += "\n";
        }
        return s;
    }

    //para jugar vs la computadora
    // Método para obtener movimientos válidos para la computadora
    public ArrayList<Movimiento> obtenerMovimientosValidos(int jugador) {
        ArrayList<Movimiento> movimientosValidos = new ArrayList<>();
        int direccionMovimiento = (jugador == 1) ? -1 : 1; // Dirección del movimiento de las fichas normales

        for (int filaOrigen = 0; filaOrigen < juego.length; filaOrigen++) {
            for (int columnaOrigen = 0; columnaOrigen < juego[0].length; columnaOrigen++) {
                if (juego[filaOrigen][columnaOrigen] == jugador) {
                    // Movimientos válidos para fichas normales
                    if ((filaOrigen + direccionMovimiento >= 0) && (filaOrigen + direccionMovimiento < juego.length)) {
                        
                        if (columnaOrigen - 1 >= 0 && juego[filaOrigen + direccionMovimiento][columnaOrigen - 1] == 3) {
                            movimientosValidos.add(new Movimiento(filaOrigen, columnaOrigen, filaOrigen + direccionMovimiento, columnaOrigen - 1));
                        }
                        if (columnaOrigen + 1 < juego[0].length && juego[filaOrigen + direccionMovimiento][columnaOrigen + 1] == 3) {
                            movimientosValidos.add(new Movimiento(filaOrigen, columnaOrigen, filaOrigen + direccionMovimiento, columnaOrigen + 1));
                        }
                                          
                        
                        // Comer fichas enemigas a la izq
                        if (columnaOrigen - 3 >= 0 
                                &&  (filaOrigen+3<juego.length)
                                && (juego[filaOrigen + 2][columnaOrigen - 2] == 1
                                || juego[filaOrigen + 2][columnaOrigen - 2] == 11)
                                && (juego[filaOrigen + 3][columnaOrigen - 3] == 3 )) {
                            Movimiento m = new Movimiento(filaOrigen + 1, columnaOrigen - 1, filaOrigen + 3, columnaOrigen - 3);
                            int fila = filaOrigen;
                            int col = columnaOrigen;
                            while (fila < juego.length
                                    && col > 0) {
                                if (this.juego[fila][col] == 1) {
                                    m.setComeFicha(true);
                                }
                                fila++;
                                col--;
                            }
                            if (m.isComeFicha()==true){
                                movimientosValidos.add(m);
                            }
                        }

                        //comer fichas enemigas a la derecha
                        if (columnaOrigen + 2 < juego[0].length
                                &&  (filaOrigen+3<juego.length)
                                && (juego[filaOrigen + 1][columnaOrigen + 1] == 1
                                || juego[filaOrigen + 1][columnaOrigen + 1] == 11)
                                && juego[filaOrigen + 2][columnaOrigen + 2] == 3 ) {
                            Movimiento m=new Movimiento(filaOrigen, columnaOrigen, filaOrigen + 2, columnaOrigen + 2);
                           int fila = filaOrigen;
                            int col = columnaOrigen;
                            while (fila < juego.length
                                    && col <juego[0].length) {
                                if (this.juego[fila][col] == 1) {
                                    m.setComeFicha(true);
                                }
                                fila++;
                                col++;
                            }
                            if (m.isComeFicha()==true){
                                movimientosValidos.add(m);
                            }
                                                
                        }
                    }

                    // Movimientos válidos para reinas (fichas coronadas)
                    if (juego[filaOrigen][columnaOrigen] == 11 || juego[filaOrigen][columnaOrigen] == 22) {
                        for (int distancia = 1; distancia < juego.length; distancia++) {
                            if (filaOrigen - distancia >= 0 && columnaOrigen - distancia >= 0 && juego[filaOrigen - distancia][columnaOrigen - distancia] == 3) {
                                movimientosValidos.add(new Movimiento(filaOrigen, columnaOrigen, filaOrigen - distancia, columnaOrigen - distancia));
                            }
                            if (filaOrigen - distancia >= 0 && columnaOrigen + distancia < juego[0].length && juego[filaOrigen - distancia][columnaOrigen + distancia] == 3) {
                                movimientosValidos.add(new Movimiento(filaOrigen, columnaOrigen, filaOrigen - distancia, columnaOrigen + distancia));
                            }
                            if (filaOrigen + distancia < juego.length && columnaOrigen - distancia >= 0 && juego[filaOrigen + distancia][columnaOrigen - distancia] == 3) {
                                movimientosValidos.add(new Movimiento(filaOrigen, columnaOrigen, filaOrigen + distancia, columnaOrigen - distancia));
                            }
                            if (filaOrigen + distancia < juego.length && columnaOrigen + distancia < juego[0].length && juego[filaOrigen + distancia][columnaOrigen + distancia] == 3) {
                                movimientosValidos.add(new Movimiento(filaOrigen, columnaOrigen, filaOrigen + distancia, columnaOrigen + distancia));
                            }
                            // Comer fichas enemigas
                            if (filaOrigen - distancia >= 0 && columnaOrigen - distancia >= 0 && juego[filaOrigen - distancia][columnaOrigen - distancia] == 3
                                    && (juego[filaOrigen - distancia - 1][columnaOrigen - distancia - 1] == 2 || juego[filaOrigen - distancia - 1][columnaOrigen - distancia - 1] == 22)) {
                                movimientosValidos.add(new Movimiento(filaOrigen, columnaOrigen, filaOrigen - distancia, columnaOrigen - distancia));
                            }
                            if (filaOrigen - distancia >= 0 && columnaOrigen + distancia < juego[0].length && juego[filaOrigen - distancia][columnaOrigen + distancia] == 3
                                    && (juego[filaOrigen - distancia - 1][columnaOrigen + distancia + 1] == 2 || juego[filaOrigen - distancia - 1][columnaOrigen + distancia + 1] == 22)) {
                                movimientosValidos.add(new Movimiento(filaOrigen, columnaOrigen, filaOrigen - distancia, columnaOrigen + distancia));
                            }
                            if (filaOrigen + distancia < juego.length && columnaOrigen - distancia >= 0 && juego[filaOrigen + distancia][columnaOrigen - distancia] == 3
                                    && (juego[filaOrigen + distancia + 1][columnaOrigen - distancia - 1] == 2 || juego[filaOrigen + distancia + 1][columnaOrigen - distancia - 1] == 22)) {
                                movimientosValidos.add(new Movimiento(filaOrigen, columnaOrigen, filaOrigen + distancia, columnaOrigen - distancia));
                            }
                            if (filaOrigen + distancia < juego.length && columnaOrigen + distancia < juego[0].length && juego[filaOrigen + distancia][columnaOrigen + distancia] == 3
                                    && (juego[filaOrigen + distancia + 1][columnaOrigen + distancia + 1] == 2 || juego[filaOrigen + distancia + 1][columnaOrigen + distancia + 1] == 22)) {
                                movimientosValidos.add(new Movimiento(filaOrigen, columnaOrigen, filaOrigen + distancia, columnaOrigen + distancia));
                            }
                        }
                    }
                }
            }
        }

        return movimientosValidos;
    }

// Método para hacer un movimiento en un tablero temporal
    public void hacerMovimientoEnTableroTemporal(int[][] tablero, int filaOrigen, int columnaOrigen, int nuevaFila, int nuevaColumna) {
        int fichaOrigen = tablero[filaOrigen][columnaOrigen];
        int direccionMovimiento = (fichaOrigen == 1) ? -1 : 1; // Dirección del movimiento de las fichas normales

        // Realizar el movimiento en el tablero temporal
        tablero[nuevaFila][nuevaColumna] = fichaOrigen;
        tablero[filaOrigen][columnaOrigen] = 3; // La posición de origen queda vacía

        // Caso de captura (eliminar ficha enemiga)
        int filaCaptura = filaOrigen + direccionMovimiento;
        int columnaCaptura = (nuevaColumna + columnaOrigen) / 2; // Columna donde está la ficha enemiga a capturar
        if (Math.abs(nuevaFila - filaOrigen) == 2 && Math.abs(nuevaColumna - columnaOrigen) == 2) {
            tablero[filaCaptura][columnaCaptura] = 3; // Eliminar ficha enemiga
        }

        // Caso de coronación (peón se convierte en reina)
        if (fichaOrigen == 1 && nuevaFila == 0) {
            tablero[nuevaFila][nuevaColumna] = 11; // Peón blanco se convierte en reina blanca
        } else if (fichaOrigen == 2 && nuevaFila == 7) {
            tablero[nuevaFila][nuevaColumna] = 22; // Peón negro se convierte en reina negra
        }
    }

// Método para copiar el tablero
    public int[][] copiarTablero(int[][] tablero) {
        int[][] copia = new int[tablero.length][tablero[0].length];
        for (int i = 0; i < tablero.length; i++) {
            System.arraycopy(tablero[i], 0, copia[i], 0, tablero[0].length);
        }
        return copia;
    }

    private Movimiento seleccionarMovimientoAlAzar(ArrayList<Movimiento> movimientosValidos) {
        Random random = new Random();
        int index = random.nextInt(movimientosValidos.size());
        return movimientosValidos.get(index);
    }

    public ArrayList<Movimiento> moverComputadora() {
        ArrayList<Movimiento> movimientosValidos = obtenerMovimientosValidos(2); // Jugador 2: fichas rojas
        ArrayList<Movimiento> move=new ArrayList<>(); // Jugador 2: fichas rojas
        Movimiento movimiento;
        boolean flag=false;
        for (int i = 0; i < movimientosValidos.size(); i++) {
            if (movimientosValidos.get(i).isComeFicha()==true && flag==false) {
                movimiento=movimientosValidos.get(i);
                movimiento(movimiento.filaOrigen, movimiento.columnaOrigen, movimiento.nuevaFila, movimiento.nuevaColumna);
                move.add(movimiento);
            //    System.out.println("*peon come");
                flag=true;
            }
        }
        
        if (!movimientosValidos.isEmpty() && flag==false) {
           // System.out.println("mueve al azar");
            movimiento = seleccionarMovimientoAlAzar(movimientosValidos);
            move.add(movimiento);
            movimiento(movimiento.filaOrigen, movimiento.columnaOrigen, movimiento.nuevaFila, movimiento.nuevaColumna);
        }
        System.out.println("");
        return move;
    }
    
    //cuenta fichas azules
    public boolean contadorFichasAzules(){
        int counter=0;
        for (int i = 0; i < 64; i++) {
            if (juego[i/8][i%8]==1) {
             counter+=1;   
            }
          // System.out.println(juego[i/8][i%8]);
        }
        
        contadorFichasAzules=counter;
       // System.out.println(counter);
        if (counter>0) {
            return false;
        }else{
            return true;
        }
        
    } 
    
    //cuenta fichas rojas
    public boolean contadorFichasRojas(){
        int counter=0;
        for (int i = 0; i < 64; i++) {
            if (juego[i/8][i%8]==2) {
             counter+=1;   
            }
        }
        
        contadorFichasRojas=counter;
        if (counter>0) {
            return false;
        }else{
            return true;
        }
        
    } 
}
