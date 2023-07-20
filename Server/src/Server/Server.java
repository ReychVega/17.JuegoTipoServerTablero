package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*@author reych
 * Esta clase representa un servidor que será utilizado como comunicación entre 
 *  los diversos usuarios.
 *Atributos necesarios:
 * socketPortNumber: Almacena el número de puerto en el que el servidor escucha
 *  las conexiones entrantes.
 * serverSocket: Representa el socket del servidor que escucha las conexiones 
 *  entrantes.
 * address: Almacena la dirección IP local del servidor.
 */   

public class Server {
        
    private static final int PORT = 5025;
    private boolean running;
    private ServerSocket serverSocket;

    public Server() {
        running = false;
    }

    /*Crea un nuevo objeto ServerSocket en el número de puerto especificado.
    *  Obtiene la dirección IP local del servidor y la asigna a la variable 
    *       address.
    */
    public void start() {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Servidor escuchando en el puerto " + PORT);
            running = true;

            while (running) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde " + clientSocket.getInetAddress().getHostAddress());

                // Manejo de clientes en subprocesos para permitir múltiples clientes
                Handler handler = new Handler(clientSocket);
                handler.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            stop();
        }
    }

    /*Detiene el server*/
    public void stop() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
            running = false;
            System.out.println("Servidor detenido");
        } catch (IOException e) {
            System.out.println("Clase Server. Error al detener el server");
        }
    }

}
