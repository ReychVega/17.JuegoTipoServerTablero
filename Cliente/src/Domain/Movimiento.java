package Domain;

/**
 *
 * @author reych
 */
public class Movimiento {
    // Atributos para almacenar las coordenadas del movimiento
    int filaOrigen;
    int columnaOrigen;
    int nuevaFila;
    int nuevaColumna;

    // Constructor que recibe las coordenadas del movimiento y las asigna a los atributos
    public Movimiento(int filaOrigen, int columnaOrigen, int nuevaFila, int nuevaColumna) {
        this.filaOrigen = filaOrigen;
        this.columnaOrigen = columnaOrigen;
        this.nuevaFila = nuevaFila;
        this.nuevaColumna = nuevaColumna;
    }

    // Métodos para acceder y modificar las coordenadas del movimiento
    public int getFilaOrigen() {
        return filaOrigen;
    }

    public void setFilaOrigen(int filaOrigen) {
        this.filaOrigen = filaOrigen;
    }

    public int getColumnaOrigen() {
        return columnaOrigen;
    }

    public void setColumnaOrigen(int columnaOrigen) {
        this.columnaOrigen = columnaOrigen;
    }

    public int getNuevaFila() {
        return nuevaFila;
    }

    public void setNuevaFila(int nuevaFila) {
        this.nuevaFila = nuevaFila;
    }

    public int getNuevaColumna() {
        return nuevaColumna;
    }

    public void setNuevaColumna(int nuevaColumna) {
        this.nuevaColumna = nuevaColumna;
    }

    // Sobrescritura del método toString para obtener una representación de cadena del objeto
    @Override
    public String toString() {
        return "Movimiento{" + "filaOrigen=" + filaOrigen + ", columnaOrigen=" + columnaOrigen
                + ", nuevaFila=" + nuevaFila + ", nuevaColumna=" + nuevaColumna + '}';
    }
}
