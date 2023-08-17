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
                    return true;
                }
            }
        }

        // Caso 3: Movimiento válido para reinas (fichas coronadas)
        if (fichaOrigen == 11 || fichaOrigen == 22) {
            int filaDistancia = Math.abs(nuevaFila - filaOrigen);
            int columnaDistancia = Math.abs(nuevaColumna - columnaOrigen);
            int counter = 0;
            int indice = 0;
            if (filaDistancia-1>=0) {
            
                int[] filas = new int[filaDistancia-1];
                int[] columnas = new int[columnaDistancia-1];
                //reina propia come.
                //Caso 1. abajo, derecha  
                if ((this.juego[filaOrigen][columnaOrigen] == 11)
                        && nuevaFila > filaOrigen && nuevaColumna > columnaOrigen) {
                    int filaComida = filaOrigen+1;
                    int columnaComida = columnaOrigen+1;
                    while (filaComida < nuevaFila && columnaComida < nuevaColumna) {
                        filas[indice] = filaComida;
                        columnas[indice] = columnaComida;
                        if (this.juego[filaComida][columnaComida] == 1
                                || this.juego[filaComida][columnaComida] == 11) {
                            counter++;
                        }
                        filaComida++;
                        columnaComida++;
                        indice++;
                    }

                    //valida que coma solo una ficha
                    return evaluaPosicion(filas, columnas, "Caso1", counter);
                }
            
                //caso 2. arriba, izq.
                if ((this.juego[filaOrigen][columnaOrigen] == 11)
                        && nuevaFila < filaOrigen && nuevaColumna < columnaOrigen) {
                    int filaComida = filaOrigen - 1; 
                    int columnaComida = columnaOrigen - 1; 
                    while (filaComida > nuevaFila && columnaComida > nuevaColumna) {
                        filas[indice] = filaComida;
                        columnas[indice] = columnaComida;
                        if (this.juego[filaComida][columnaComida] == 1
                              || this.juego[filaComida][columnaComida] == 11) {
                            counter++;
                        }
                        filaComida--;
                        columnaComida--;
                        indice++;
                    }
                    return evaluaPosicion(filas, columnas, "Caso2", counter);
                }
                
                // Caso 3. abajo, izquierda
                if ((this.juego[filaOrigen][columnaOrigen] == 11)
                        && nuevaFila > filaOrigen && nuevaColumna < columnaOrigen) {
                    int filaComida = filaOrigen + 1; 
                    int columnaComida = columnaOrigen - 1; 
                    while (filaComida < nuevaFila && columnaComida > nuevaColumna) {
                        filas[indice] = filaComida;
                        columnas[indice] = columnaComida;
                        if (this.juego[filaComida][columnaComida] == 1
                                 || this.juego[filaComida][columnaComida] == 11) {
                            counter++;
                        }
                        filaComida++;
                        columnaComida--;
                        indice++;
                    }
                    return evaluaPosicion(filas, columnas, "Caso3", counter);
                }
                //  Caso 4. arriba, derecha
                if ((this.juego[filaOrigen][columnaOrigen] == 11)
                        && nuevaFila < filaOrigen && nuevaColumna > columnaOrigen) {
                    int filaComida = filaOrigen - 1;
                    int columnaComida = columnaOrigen + 1;
                    while (filaComida > nuevaFila && columnaComida < nuevaColumna) {
                        filas[indice] = filaComida;
                        columnas[indice] = columnaComida;
                        if (this.juego[filaComida][columnaComida] == 1
                                || this.juego[filaComida][columnaComida] == 11) {
                            counter++;
                        }
                        filaComida--;
                        columnaComida++;
                        indice++;
                    }
                    return evaluaPosicion(filas, columnas, "Caso4", counter);    
                }
                  //Caso 5. abajo, derecha  
                if ((this.juego[filaOrigen][columnaOrigen] == 22)
                        && nuevaFila > filaOrigen && nuevaColumna > columnaOrigen) {
                    int filaComida = filaOrigen+1;
                    int columnaComida = columnaOrigen+1;
                    while (filaComida < nuevaFila && columnaComida < nuevaColumna) {
                        filas[indice] = filaComida;
                        columnas[indice] = columnaComida;
                        if (this.juego[filaComida][columnaComida] == 2
                                || this.juego[filaComida][columnaComida] == 22) {
                            counter++;
                        }
                        filaComida++;
                        columnaComida++;
                        indice++;
                    }
                    //valida que coma solo una ficha
                    return evaluaPosicion(filas, columnas, "Caso5", counter);
                }
             //caso 6. arriba, izq.
                if ((this.juego[filaOrigen][columnaOrigen] == 22)
                        && nuevaFila < filaOrigen && nuevaColumna < columnaOrigen) {
                    int filaComida = filaOrigen - 1; 
                    int columnaComida = columnaOrigen - 1; 
                    while (filaComida > nuevaFila && columnaComida > nuevaColumna) {
                        filas[indice] = filaComida;
                        columnas[indice] = columnaComida;
                        if (this.juego[filaComida][columnaComida] == 2
                              || this.juego[filaComida][columnaComida] == 22) {
                            counter++;
                        }
                        filaComida--;
                        columnaComida--;
                        indice++;
                    }
                    return evaluaPosicion(filas, columnas, "Caso6", counter);
                }
                // Caso 7. abajo, izquierda
                if ((this.juego[filaOrigen][columnaOrigen] == 22)
                        && nuevaFila > filaOrigen && nuevaColumna < columnaOrigen) {
                    int filaComida = filaOrigen + 1; 
                    int columnaComida = columnaOrigen - 1; 
                    while (filaComida < nuevaFila && columnaComida > nuevaColumna) {
                        filas[indice] = filaComida;
                        columnas[indice] = columnaComida;
                        if (this.juego[filaComida][columnaComida] == 2
                                 || this.juego[filaComida][columnaComida] == 22) {
                            counter++;
                        }
                        filaComida++;
                        columnaComida--;
                        indice++;
                    }
                    return evaluaPosicion(filas, columnas, "Caso7", counter);
                }
                //  Caso 8. arriba, derecha
                if ((this.juego[filaOrigen][columnaOrigen] == 22)
                        && nuevaFila < filaOrigen && nuevaColumna > columnaOrigen) {
                    int filaComida = filaOrigen - 1;
                    int columnaComida = columnaOrigen + 1;
                    while (filaComida > nuevaFila && columnaComida < nuevaColumna) {
                        filas[indice] = filaComida;
                        columnas[indice] = columnaComida;
                        if (this.juego[filaComida][columnaComida] == 2
                                || this.juego[filaComida][columnaComida] == 22) {
                            counter++;
                        }
                        filaComida--;
                        columnaComida++;
                        indice++;
                    }
                    return evaluaPosicion(filas, columnas, "Caso8", counter);    
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
                if (filaCaptura<juego.length && columnaCaptura<=juego.length) {
                this.juego[filaCaptura][columnaCaptura] = 3; // Eliminar ficha enemiga                                    
                }
            }

            //reina propia come.
            //Caso 1. abajo, derecha          
            if ((this.juego[nuevaFila][nuevaColumna] == 11)
                    && nuevaFila > filaOrigen && nuevaColumna > columnaOrigen) {
                int filaComida = filaOrigen + 1; // Inicializamos la fila desde la que empezaremos a comer
                int columnaComida = columnaOrigen + 1; // Inicializamos la columna desde la que empezaremos a comer
                while (filaComida < nuevaFila && columnaComida < nuevaColumna) {
                    if (this.juego[filaComida][columnaComida] != 1
                            && this.juego[filaComida][columnaComida] != 11) {
                        this.juego[filaComida][columnaComida] = 3; // Comemos las fichas enemigas en línea recta
                    }
                    filaComida++;
                    columnaComida++;
                }
            }
            //caso 2. arriba, izq.
            if ((this.juego[nuevaFila][nuevaColumna] == 11)
                    && nuevaFila < filaOrigen && nuevaColumna < columnaOrigen) {
                int filaComida = filaOrigen - 1; // Inicializamos la fila desde la que empezaremos a comer
                int columnaComida = columnaOrigen - 1; // Inicializamos la columna desde la que empezaremos a comer
                while (filaComida > nuevaFila && columnaComida > nuevaColumna) {
                    if (this.juego[filaComida][columnaComida] != 1
                            && this.juego[filaComida][columnaComida] != 11) {
                        this.juego[filaComida][columnaComida] = 3; // Comemos las fichas enemigas en línea recta
                    }

                    filaComida--;
                    columnaComida--;
                }
            }
            // Caso 3. abajo, derecha
            if ((this.juego[nuevaFila][nuevaColumna] == 11)
                    && nuevaFila > filaOrigen && nuevaColumna < columnaOrigen) {
                int filaComida = filaOrigen + 1; // Inicializamos la fila desde la que empezaremos a comer
                int columnaComida = columnaOrigen - 1; // Inicializamos la columna desde la que empezaremos a comer
                while (filaComida < nuevaFila && columnaComida > nuevaColumna) {
                    if (this.juego[filaComida][columnaComida] != 1
                            && this.juego[filaComida][columnaComida] != 11) {
                        this.juego[filaComida][columnaComida] = 3; // Comemos las fichas enemigas en línea recta
                    }
                    filaComida++;
                    columnaComida--;
                }
            }

            // Caso 4. arriba, derecha
            if ((this.juego[nuevaFila][nuevaColumna] == 11)
                    && nuevaFila < filaOrigen && nuevaColumna > columnaOrigen) {
                int filaComida = filaOrigen - 1; // Inicializamos la fila desde la que empezaremos a comer
                int columnaComida = columnaOrigen + 1; // Inicializamos la columna desde la que empezaremos a comer
                while (filaComida > nuevaFila && columnaComida < nuevaColumna) {
                    if (this.juego[filaComida][columnaComida] != 1
                            && this.juego[filaComida][columnaComida] != 11) {
                        this.juego[filaComida][columnaComida] = 3; // Comemos las fichas enemigas en línea recta
                    }
                    filaComida--;
                    columnaComida++;
                }
            }

            //Caso 5. abajo, derecha          
            if ((this.juego[nuevaFila][nuevaColumna] == 22)
                    && nuevaFila > filaOrigen && nuevaColumna > columnaOrigen) {
                System.out.println("caso 5");
                int filaComida = filaOrigen + 1; // Inicializamos la fila desde la que empezaremos a comer
                int columnaComida = columnaOrigen + 1; // Inicializamos la columna desde la que empezaremos a comer
                while (filaComida < nuevaFila && columnaComida < nuevaColumna) {
                    if (this.juego[filaComida][columnaComida] != 2
                            && this.juego[filaComida][columnaComida] != 22) {
                        this.juego[filaComida][columnaComida] = 3; // Comemos las fichas enemigas en línea recta
                    }
                    filaComida++;
                    columnaComida++;
                }
            }
            //caso 6. arriba, izq.
            if ((this.juego[nuevaFila][nuevaColumna] == 22)
                    && nuevaFila < filaOrigen && nuevaColumna < columnaOrigen) {
                System.out.println("caso 6");
                int filaComida = filaOrigen - 1; // Inicializamos la fila desde la que empezaremos a comer
                int columnaComida = columnaOrigen - 1; // Inicializamos la columna desde la que empezaremos a comer
                while (filaComida > nuevaFila && columnaComida > nuevaColumna) {
                    if (this.juego[filaComida][columnaComida] != 2
                            && this.juego[filaComida][columnaComida] != 22) {
                        this.juego[filaComida][columnaComida] = 3; // Comemos las fichas enemigas en línea recta
                    }

                    filaComida--;
                    columnaComida--;
                }
            }
            // Caso 7. abajo, derecha
            if ((this.juego[nuevaFila][nuevaColumna] == 22)
                    && nuevaFila > filaOrigen && nuevaColumna < columnaOrigen) {
                System.out.println("caso 7");
                int filaComida = filaOrigen + 1; // Inicializamos la fila desde la que empezaremos a comer
                int columnaComida = columnaOrigen - 1; // Inicializamos la columna desde la que empezaremos a comer
                while (filaComida < nuevaFila && columnaComida > nuevaColumna) {
                    if (this.juego[filaComida][columnaComida] != 2
                            & this.juego[filaComida][columnaComida] != 22) {
                        this.juego[filaComida][columnaComida] = 3; // Comemos las fichas enemigas en línea recta
                    }
                    filaComida++;
                    columnaComida--;
                }
            }

            // Caso 8. arriba, derecha
            if ((this.juego[nuevaFila][nuevaColumna] == 22)
                    && nuevaFila < filaOrigen && nuevaColumna > columnaOrigen) {
                System.out.println("caso 8");
                int filaComida = filaOrigen - 1; // Inicializamos la fila desde la que empezaremos a comer
                int columnaComida = columnaOrigen + 1; // Inicializamos la columna desde la que empezaremos a comer
                while (filaComida > nuevaFila && columnaComida < nuevaColumna) {
                    if (this.juego[filaComida][columnaComida] != 2
                            && this.juego[filaComida][columnaComida] != 22) {
                        this.juego[filaComida][columnaComida] = 3; // Comemos las fichas enemigas en línea recta
                    }
                    filaComida--;
                    columnaComida++;
                }
            }

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
    public ArrayList<Movimiento> obtenerMovimientosValidos() {
        ArrayList<Movimiento> movimientosValidos = new ArrayList<>();
        Movimiento m;
        //movimientos de peones
        for (int filaOrigen = 0; filaOrigen < juego.length; filaOrigen++) {
            for (int columnaOrigen = 0; columnaOrigen < juego[0].length; columnaOrigen++) {
                if (juego[filaOrigen][columnaOrigen] == 2) {
                    // Movimientos válidos para fichas normales
                        //Movimiento de peones sin comer
                        //Caso 1. izq
                        if (columnaOrigen - 1 >= 0
                                && juego[filaOrigen + 1][columnaOrigen - 1] == 3) {
                            movimientosValidos.add(new Movimiento(filaOrigen, columnaOrigen, filaOrigen + 1, columnaOrigen - 1));
                        }
                        //Caso 2. derecha
                        if (columnaOrigen + 1 < juego[0].length 
                                && juego[filaOrigen + 1][columnaOrigen + 1] == 3) {
                            movimientosValidos.add(new Movimiento(filaOrigen, columnaOrigen, filaOrigen + 1, columnaOrigen + 1));
                        }
                        
                        // Movimiento de peones que comen
                        //Caso 1. izq
                        if (columnaOrigen - 2 >= 0
                                && (filaOrigen + 2 < juego.length)
                                && (juego[filaOrigen+1][columnaOrigen-1] == 1
                                || juego[filaOrigen+1][columnaOrigen-1] == 11)
                                && (juego[filaOrigen+2][columnaOrigen-2] == 3)) {
                            m = new Movimiento(filaOrigen, columnaOrigen, filaOrigen+2, columnaOrigen-2);
                            m.setPeonCome(true);
                            movimientosValidos.add(m);
                        }
                        //Caso 2. derecha
                        if (columnaOrigen + 2 < juego[0].length
                                && (filaOrigen + 2 < juego.length)
                                && (juego[filaOrigen+1][columnaOrigen+1] == 1
                                || juego[filaOrigen+1][columnaOrigen+1] == 11)
                                && juego[filaOrigen+2][columnaOrigen+2] == 3) {
                            m = new Movimiento(filaOrigen, columnaOrigen, filaOrigen+2, columnaOrigen+2);
                            m.setPeonCome(true);
                            movimientosValidos.add(m);
                        }
                        
                    }

                  // Movimientos válidos para reinas (fichas coronadas)
                if (juego[filaOrigen][columnaOrigen] == 22) {
                    for (int distancia = 1; distancia < juego.length; distancia++) {
                        int[] filas = new int[distancia];
                        int[] columnas = new int[distancia];
                        int filaAux=filaOrigen;
                        int columnaAux=columnaOrigen;
                        int nuevaFila=filaAux + distancia;
                        int nuevaColumna=columnaAux + distancia;
                        int cuentaFichasPropias=0;
                        int cuentaFichasEnemigas=0;
                        int index=0;
                        
                        //Caso 1. Abajo++, derecha++
                        if (nuevaFila < juego.length
                                && nuevaColumna < juego.length
                                    && this.juego[nuevaFila][nuevaColumna]==3) {
                            filaAux++;
                            columnaAux++;
                           
                            while (filaAux <= nuevaFila && columnaAux <= nuevaColumna) {
                                filas[index] = filaAux;
                                columnas[index] = columnaAux;
                                if (this.juego[filaAux][columnaAux] == 2
                                        || this.juego[filaAux][columnaAux] == 22) {
                                    cuentaFichasPropias++;
                                }
                                if (this.juego[filaAux][columnaAux] == 1
                                        || this.juego[filaAux][columnaAux] == 11) {
                                    cuentaFichasEnemigas++;
                                }
                                filaAux++;
                                columnaAux++;
                                index++;
                            } 
                            if (juego[filaOrigen][columnaOrigen]==22 && cuentaFichasPropias==0
                                     && evaluaPosicion(filas, columnas, "Caso5", cuentaFichasEnemigas)==true){
                                if (cuentaFichasEnemigas==0){
                                  m = new Movimiento(filaOrigen, columnaOrigen, nuevaFila, nuevaColumna);
                                  movimientosValidos.add(m);
                                }else if(cuentaFichasEnemigas>0){
                                   m = new Movimiento(filaOrigen, columnaOrigen, nuevaFila, nuevaColumna);
                                   m.setReinaCome(true);
                                   movimientosValidos.add(m);
                                }
                                
                            }
                        }
                        
                        filaAux=filaOrigen;
                        columnaAux=columnaOrigen;
                        nuevaFila=filaAux - distancia;
                        nuevaColumna=columnaAux - distancia;
                        cuentaFichasEnemigas=0;
                        cuentaFichasPropias=0;
                        index=0;
                       
                        //Caso 2. arriba--, izquierda--
                        if (nuevaFila >=0
                                && nuevaColumna >=0
                                    && this.juego[nuevaFila][nuevaColumna]==3) {
                            
                            filaAux--;
                            columnaAux--;
                           
                            while (filaAux > nuevaFila && columnaAux > nuevaColumna) {
                                filas[index] = filaAux;
                                columnas[index] = columnaAux;
                                if (this.juego[filaAux][columnaAux] == 2
                                        || this.juego[filaAux][columnaAux] == 22) {
                                    cuentaFichasPropias++;
                                }
                                if (this.juego[filaAux][columnaAux] == 1
                                        || this.juego[filaAux][columnaAux] == 11) {
                                    cuentaFichasEnemigas++;
                                }
                                filaAux--;
                                columnaAux--;
                                index++;
                            } 
                            if (juego[filaOrigen][columnaOrigen]==22 && cuentaFichasPropias==0
                                     && evaluaPosicion(filas, columnas, "Caso6", cuentaFichasEnemigas)==true){
                                if (cuentaFichasEnemigas==0){
                                  m = new Movimiento(filaOrigen, columnaOrigen, nuevaFila, nuevaColumna);
                                 //System.out.println("no come "+m.toString());
                                  movimientosValidos.add(m);
                                }else if(cuentaFichasEnemigas>0){
                                   m = new Movimiento(filaOrigen, columnaOrigen, nuevaFila, nuevaColumna);
                                   m.setReinaCome(true);
                                   movimientosValidos.add(m);
                                 //  System.out.println(" come= "+m.toString());
                                }
                                
                            }
                        }
                        filaAux=filaOrigen;
                        columnaAux=columnaOrigen;
                        nuevaFila=filaAux - distancia;
                        nuevaColumna=columnaAux + distancia;
                        cuentaFichasEnemigas=0;
                        cuentaFichasPropias=0;
                        index=0;
                       
                        //Caso 3. arriba--, derecha++
                        if (nuevaFila >=0
                               && nuevaColumna < juego.length
                                    && this.juego[nuevaFila][nuevaColumna]==3) {
                            
                            filaAux--;
                            columnaAux++;
                           
                            while (filaAux > nuevaFila && columnaAux <= nuevaColumna) {
                                filas[index] = filaAux;
                                columnas[index] = columnaAux;
                                if (this.juego[filaAux][columnaAux] == 2
                                        || this.juego[filaAux][columnaAux] == 22) {
                                    cuentaFichasPropias++;
                                }
                                if (this.juego[filaAux][columnaAux] == 1
                                        || this.juego[filaAux][columnaAux] == 11) {
                                    cuentaFichasEnemigas++;
                                }
                                filaAux--;
                                columnaAux++;
                                index++;
                            } 
                            if (juego[filaOrigen][columnaOrigen]==22 && cuentaFichasPropias==0
                                     && evaluaPosicion(filas, columnas, "Caso7", cuentaFichasEnemigas)==true){
                                if (cuentaFichasEnemigas==0){
                                  m = new Movimiento(filaOrigen, columnaOrigen, nuevaFila, nuevaColumna);
                                  movimientosValidos.add(m);
                                }else if(cuentaFichasEnemigas>0){
                                   m = new Movimiento(filaOrigen, columnaOrigen, nuevaFila, nuevaColumna);
                                   m.setReinaCome(true);
                                   movimientosValidos.add(m);
                                }
                                
                            }
                        }
                        filaAux=filaOrigen;
                        columnaAux=columnaOrigen;
                        nuevaFila=filaAux+distancia;
                        nuevaColumna=columnaAux-distancia;
                        cuentaFichasEnemigas=0;
                        cuentaFichasPropias=0;
                        index=0;
                       
                        //Caso 4. abajo++, izquierda--
                        if (nuevaColumna >=0
                               && nuevaFila < juego.length
                                    && this.juego[nuevaFila][nuevaColumna]==3) {
                            
                            filaAux++;
                            columnaAux--;
                           
                            while (filaAux <= nuevaFila && columnaAux > nuevaColumna) {
                                filas[index] = filaAux;
                                columnas[index] = columnaAux;
                                if (this.juego[filaAux][columnaAux] == 2
                                        || this.juego[filaAux][columnaAux] == 22) {
                                    cuentaFichasPropias++;
                                }
                                if (this.juego[filaAux][columnaAux] == 1
                                        || this.juego[filaAux][columnaAux] == 11) {
                                    cuentaFichasEnemigas++;
                                }
                                filaAux++;
                                columnaAux--;
                                index++;
                            } 
                            if (juego[filaOrigen][columnaOrigen]==22 && cuentaFichasPropias==0
                                     && evaluaPosicion(filas, columnas, "Caso8", cuentaFichasEnemigas)==true){
                                if (cuentaFichasEnemigas==0){
                                  m = new Movimiento(filaOrigen, columnaOrigen, nuevaFila, nuevaColumna);
                                  movimientosValidos.add(m);
                                }else if(cuentaFichasEnemigas>0){
                                   m = new Movimiento(filaOrigen, columnaOrigen, nuevaFila, nuevaColumna);
                                   m.setReinaCome(true);
                                   movimientosValidos.add(m);
                                }
                                
                            }
                        }
                        cuentaFichasEnemigas=0;
                        cuentaFichasPropias=0;
                        index=0;
                    }
                }
            }
        }

        return movimientosValidos;
    }

    private Movimiento seleccionarMovimientoAlAzar(ArrayList<Movimiento> movimientosValidos) {
        Random random = new Random();
        int index = random.nextInt(movimientosValidos.size());
        return movimientosValidos.get(index);
    }

    public ArrayList<Movimiento> moverComputadora() {
        ArrayList<Movimiento> movimientosValidos = obtenerMovimientosValidos(); // Jugador 2: fichas rojas
        ArrayList<Movimiento> move = new ArrayList<>(); // Jugador 2: fichas rojas
        Movimiento movimiento;
        boolean flag = false;
                
        //priorizamos que coma 
        for (int i = 0; i < movimientosValidos.size(); i++) {
            if ((movimientosValidos.get(i).isPeonCome() == true
                    || movimientosValidos.get(i).isReinaCome()==true)
                    && flag == false) {
                movimiento = movimientosValidos.get(i);
                movimiento(movimiento.filaOrigen, movimiento.columnaOrigen, movimiento.nuevaFila, movimiento.nuevaColumna);
                move.add(movimiento);
                flag = true;
            }
        }

        //si no hay casos para comer, elige de forma random
        if (!movimientosValidos.isEmpty() && flag == false) {
            movimiento = seleccionarMovimientoAlAzar(movimientosValidos);
           // System.out.println(movimiento.toString());
            move.add(movimiento);
            movimiento(movimiento.filaOrigen, movimiento.columnaOrigen, movimiento.nuevaFila, movimiento.nuevaColumna);
        }
        return move;
    }

    private boolean evaluaPosicion(int[] filas, int[] columnas, String caso, int counter) {
       int filaComida=-1;
       int columnaComida=-1;
        switch(caso){
            //casos de la reina 11. 
            
            //Abajo++, derecha++
            case "Caso1":
                System.out.println(caso);
                for (int i = 0; i < filas.length; i++) {
                    if (juego[filas[i]][columnas[i]]==2
                            || juego[filas[i]][columnas[i]]==22) {
                     filaComida=filas[i];
                     columnaComida=columnas[i];
                    }
                    
                    if ((filas[i]+1<juego.length 
                            && columnas[i]+1<juego[0].length
                            && filaComida!=-1 && columnaComida!=-1
                            && (juego[filas[i]+1][columnas[i]+1]==2
                            ||juego[filas[i]+1][columnas[i]+1]==22 )) 
                            || counter>0 ) {
                        return false;
                    }
                    filaComida=-1;
                    columnaComida=-1;
                }
                return true;
            //Arriba--, izquierda-- 
            case "Caso2":
               System.out.println(caso);
               for (int i = 0; i < filas.length; i++) {
                    if (juego[filas[i]][columnas[i]]==2
                            || juego[filas[i]][columnas[i]]==22) {
                     filaComida=filas[i];
                     columnaComida=columnas[i];
                    }
                    
                    if ((filas[i]-1>=0
                            && columnas[i]-1>=0
                            && filaComida!=-1 && columnaComida!=-1
                            && (juego[filas[i]-1][columnas[i]-1]==2
                            ||juego[filas[i]-1][columnas[i]-1]==22))
                            || counter>0 ) {
                        return false;
                    }
                    filaComida=-1;
                    columnaComida=-1;
                }
                return true;   
          //abajo++, izquierda--      
         case "Caso3":
               System.out.println(caso);
               for (int i = 0; i < filas.length; i++) {
                    if (juego[filas[i]][columnas[i]]==2
                            || juego[filas[i]][columnas[i]]==22) {
                     filaComida=filas[i];
                     columnaComida=columnas[i];
                    }
                    
                    if ((filas[i]+1<juego.length
                            && columnas[i]-1>=0
                            && filaComida!=-1 && columnaComida!=-1
                            && (juego[filas[i]+1][columnas[i]-1]==2
                            || juego[filas[i]+1][columnas[i]-1]==22))
                            || counter>0 ) {
                        return false;
                    }
                    filaComida=-1;
                    columnaComida=-1;
                }
                return true;  
         //Caso 4. arriba--, derecha++        
         case "Caso4":
               System.out.println(caso);
               for (int i = 0; i < filas.length; i++) {
                    if (juego[filas[i]][columnas[i]]==2
                            || juego[filas[i]][columnas[i]]==22) {
                     filaComida=filas[i];
                     columnaComida=columnas[i];
                    }
                    
                    if ((filas[i]-1>=0
                            && columnas[i]+1<juego[0].length
                            && filaComida!=-1 && columnaComida!=-1
                            && (juego[filas[i]-1][columnas[i]+1]==2
                            ||juego[filas[i]-1][columnas[i]+1]==22))
                            || counter>0 ) {
                        return false;
                    }
                    filaComida=-1;
                    columnaComida=-1;
                }
                return true; 
                
            //reinas 22    
            //Caso 5. abajo++, derecha++ 
            case "Caso5": 
                for (int i = 0; i < filas.length; i++) {
                    if (juego[filas[i]][columnas[i]]==1
                            || juego[filas[i]][columnas[i]]==11) {
                     filaComida=filas[i];
                     columnaComida=columnas[i];
                    }
                  
                    if (filas[i]+1<juego.length 
                            && columnas[i]+1<juego[0].length
                            && filaComida!=-1 && columnaComida!=-1
                            && (juego[filas[i]+1][columnas[i]+1]==1
                            || juego[filas[i]+1][columnas[i]+1]==11)) {
                        return false;
                    }
                   
                    filaComida=-1;
                    columnaComida=-1;
                }
                return true;
            //Caso 6. arriba--, izquierda--     
            case "Caso6":
                 for (int i = 0; i < filas.length; i++) {
                    
                    if (juego[filas[i]][columnas[i]]==1
                            || juego[filas[i]][columnas[i]]==11) {
                     filaComida=filas[i];
                     columnaComida=columnas[i];
                    }
                    
                    if (filas[i]+1<juego.length
                            && columnas[i]+1<juego.length
                            && filas[i]-1>=0
                            && columnas[i]-1>=0
                            && filaComida!=-1 && columnaComida!=-1
                            && (juego[filas[i]-1][columnas[i]-1]==1
                            || juego[filas[i]-1][columnas[i]-1]==1)) {
                        return false;
                    }
                    filaComida=-1;
                    columnaComida=-1;
                }
                return true; 
           //Caso 7. arriba--, derecha++      
            case "Caso7":
                 for (int i = 0; i < filas.length; i++) {
                    
                    if (juego[filas[i]][columnas[i]]==1
                            || juego[filas[i]][columnas[i]]==11) {
                     filaComida=filas[i];
                     columnaComida=columnas[i];
                    }
                    
                    if (filas[i]+1<juego.length
                            && columnas[i]+1<juego.length
                            && filas[i]-1>=0
                           && filaComida!=-1 && columnaComida!=-1
                            && (juego[filas[i]-1][columnas[i]+1]==1
                            || juego[filas[i]-1][columnas[i]+1]==1)) {
                        return false;
                    }
                    filaComida=-1;
                    columnaComida=-1;
                }
                return true; 
           //Caso 8. abajo++, izquierda++      
            case "Caso8":
                 for (int i = 0; i < filas.length; i++) {
                    
                    if (juego[filas[i]][columnas[i]]==1
                            || juego[filas[i]][columnas[i]]==11) {
                     filaComida=filas[i];
                     columnaComida=columnas[i];
                    }
                    
                    if (filas[i]+1<juego.length
                            && columnas[i]+1<juego.length
                            && columnas[i]-1>=0
                           && filaComida!=-1 && columnaComida!=-1
                            && (juego[filas[i]+1][columnas[i]-1]==1
                            || juego[filas[i]+1][columnas[i]-1]==1)) {
                        return false;
                    }
                    filaComida=-1;
                    columnaComida=-1;
                }
                return true; 
        }
        return false;

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
        if (contadorFichasAzules>0) {
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
        if (contadorFichasRojas>0) {
            return false;
        }else{
            return true;
        }
        
    } 
}
