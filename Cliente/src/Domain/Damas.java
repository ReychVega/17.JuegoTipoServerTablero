package Domain;

import java.io.Serializable;

/**
 * Clase para manejo del juego de Damas
 * 0: posiciones inválidas
 * 1: fichas propias (peones blancos)
 * 2: fichas enemigas (peones negros)
 * 3: campos disponibles para movimiento
 * 11: reinas propias (reinas blancas)
 * 22: reinas enemigas (reinas negras)
 * 
 * Autor: reych
 */
public class Damas implements Serializable{
private static final long serialVersionUID=1L;
 
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
    public  Damas(){
        // El tablero se inicializa con las posiciones de las fichas
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
            if ((nuevaFila == filaOrigen + direccionMovimiento) &&
                (nuevaColumna == columnaOrigen + 1 || nuevaColumna == columnaOrigen - 1)) {
                return true;
            }

            // Verificar si puede comer ficha
            if ((nuevaFila == filaOrigen + 2 * direccionMovimiento) &&
                (nuevaColumna == columnaOrigen + 2 || nuevaColumna == columnaOrigen - 2)) {
                int filaComida = filaOrigen + direccionMovimiento;
                int columnaComida = (nuevaColumna + columnaOrigen) / 2; // Columna donde está la ficha enemiga a capturar
                int fichaComida = this.juego[filaComida][columnaComida];

                // Verificar si la ficha comida es del oponente
                if ((fichaOrigen == 1 && (fichaComida == 2 || fichaComida == 22)) ||
                    (fichaOrigen == 2 && (fichaComida == 1 || fichaComida == 11))) {
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
                if ((fichaOrigen == 11 && (fichaComida == 2 || fichaComida == 22)) ||
                    (fichaOrigen == 22 && (fichaComida == 1 || fichaComida == 11))) {
                    // Comer la ficha
                   // this.juego[filaComida][columnaComida] = 3;
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Método para realizar un movimiento en el tablero
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

        // Caso de captura (eliminar ficha enemiga)
        int filaCaptura = filaOrigen + direccionMovimiento;
        int columnaCaptura = (nuevaColumna + columnaOrigen) / 2; // Columna donde está la ficha enemiga a capturar

        if (Math.abs(nuevaFila - filaOrigen) == 2 && Math.abs(nuevaColumna - columnaOrigen) == 2) {
            
            this.juego[filaCaptura][columnaCaptura] = 3; // Eliminar ficha enemiga
            // Actualizar coordenadas de la ficha después de la captura
            nuevaFila = filaCaptura;
            nuevaColumna = columnaCaptura;
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
        String s="";
        for (int i = 0; i < juego.length; i++) {
            for (int j = 0; j < juego[0].length; j++) {
                s+=juego[i][j]+" ";
            }
            s+="\n";
        }
        return s;
    }

    
    
}
