package Domain;

/**
 *
 * @author reych
 */
// Clase que representa un movimiento en el juego
public class Movimiento {
    // Atributos para almacenar información sobre el movimiento
    int filaOrigen; // Fila de origen del movimiento
    int columnaOrigen; // Columna de origen del movimiento
    int nuevaFila; // Nueva fila del movimiento
    int nuevaColumna; // Nueva columna del movimiento
    boolean comeFicha;
    
    // Constructor para crear un objeto Movimiento con los valores de fila y columna de origen,
    // así como la nueva fila y columna del movimiento
    public Movimiento(int filaOrigen, int columnaOrigen, int nuevaFila, int nuevaColumna) {
        this.filaOrigen = filaOrigen;
        this.columnaOrigen = columnaOrigen;
        this.nuevaFila = nuevaFila;
        this.nuevaColumna = nuevaColumna;
        this.comeFicha=false;

    }

    // Métodos para obtener y establecer la fila de origen del movimiento
    public int getFilaOrigen() {
        return filaOrigen;
    }

    public void setFilaOrigen(int filaOrigen) {
        this.filaOrigen = filaOrigen;
    }

    // Métodos para obtener y establecer la columna de origen del movimiento
    public int getColumnaOrigen() {
        return columnaOrigen;
    }

    public void setColumnaOrigen(int columnaOrigen) {
        this.columnaOrigen = columnaOrigen;
    }

    // Métodos para obtener y establecer la nueva fila del movimiento
    public int getNuevaFila() {
        return nuevaFila;
    }

    public void setNuevaFila(int nuevaFila) {
        this.nuevaFila = nuevaFila;
    }

    // Métodos para obtener y establecer la nueva columna del movimiento
    public int getNuevaColumna() {
        return nuevaColumna;
    }

    public void setNuevaColumna(int nuevaColumna) {
        this.nuevaColumna = nuevaColumna;
    }

    public boolean isComeFicha() {
        return comeFicha;
    }

    public void setComeFicha(boolean comeFicha) {
        this.comeFicha = comeFicha;
    }
   
    // Método para representar el objeto Movimiento en formato de cadena
    @Override
    public String toString() {
        return "Movimiento{" + "filaOrigen=" + filaOrigen + ", columnaOrigen=" + columnaOrigen + ", nuevaFila=" + nuevaFila + ", nuevaColumna=" + nuevaColumna + '}';
    }
}
