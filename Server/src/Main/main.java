package Main;

import Server.Server;
/**
 *
 * @author reych
 */
// Clase main que contiene el punto de entrada del programa
public class main {

    /**
     * Método principal que inicia el servidor.
     * @param args Los argumentos de la línea de comandos (no se utilizan en este caso).
     */
    public static void main(String[] args) {
        // Crear una instancia del servidor
        Server server = new Server();
        // Iniciar el servidor llamando al método start()
        server.start();
    }
}

