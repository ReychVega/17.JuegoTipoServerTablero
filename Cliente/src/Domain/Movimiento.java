package Domain;

/**
 *
 * @author reych
 */
public class Movimiento {
    int filaOrigen;
    int columnaOrigen;
    int nuevaFila;
    int nuevaColumna;
    boolean comeFicha;
    
    public Movimiento(int filaOrigen, int columnaOrigen, int nuevaFila, int nuevaColumna) {
        this.filaOrigen = filaOrigen;
        this.columnaOrigen = columnaOrigen;
        this.nuevaFila = nuevaFila;
        this.nuevaColumna = nuevaColumna;
        this.comeFicha=false;
    }

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

    public boolean isComeFicha() {
        return comeFicha;
    }

    public void setComeFicha(boolean comeFicha) {
        this.comeFicha = comeFicha;
    }
    
    @Override
    public String toString() {
        return "Movimiento{" + "filaOrigen=" + filaOrigen + ", columnaOrigen=" + columnaOrigen + ", nuevaFila=" + nuevaFila + ", nuevaColumna=" + nuevaColumna + '}';
    }
    
    
}
