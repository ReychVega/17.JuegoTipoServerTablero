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
    boolean peonCome;
    boolean reinaCome;
    
    public Movimiento(int filaOrigen, int columnaOrigen, int nuevaFila, int nuevaColumna) {
        this.filaOrigen = filaOrigen;
        this.columnaOrigen = columnaOrigen;
        this.nuevaFila = nuevaFila;
        this.nuevaColumna = nuevaColumna;
        this.peonCome=false;
        this.reinaCome=false;
        
    }

    public boolean isPeonCome() {
        return peonCome;
    }

    public void setPeonCome(boolean peonCome) {
        this.peonCome = peonCome;
    }

    public boolean isReinaCome() {
        return reinaCome;
    }

    public void setReinaCome(boolean reinaCome) {
        this.reinaCome = reinaCome;
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
    
    @Override
    public String toString() {
        return "Movimiento{" + "filaOrigen=" + filaOrigen + ", columnaOrigen=" + columnaOrigen + ", nuevaFila=" + nuevaFila + ", nuevaColumna=" + nuevaColumna + '}';
    }
    
    
}
