package Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/*@author reych
 * Este código define una clase llamada "Server" que extiende de la clase Thread 
 *  lo que significa que se puede ejecutar como un hilo separado en un programa.
 * Esta clase representa un servidor que será utilizado como comunicación entre 
 *  los diversos usuarios.
 */

public class Server extends Thread{
 /*Atributos necesarios:
  * socketPortNumber: Almacena el número de puerto en el que el servidor escucha
  *  las conexiones entrantes.
  * serverSocket: Representa el socket del servidor que escucha las conexiones 
  *  entrantes.
  * address: Almacena la dirección IP local del servidor.
  */   
    private int socketPortNumber;
    private ServerSocket serverSocket;	
    private InetAddress address;

    
   /*Constructor:
    *  El constructor recibe el número de puerto como parámetro y lo asigna a la
    *       variable socketPortNumber.
    *  Crea un nuevo objeto ServerSocket en el número de puerto especificado.
    *  Obtiene la dirección IP local del servidor y la asigna a la variable 
    *       address.
    */
	public Server(int socketPortNumber) throws IOException {
		this.socketPortNumber=socketPortNumber;
		this.serverSocket=new ServerSocket(this.socketPortNumber);
		this.address=InetAddress.getLocalHost();
	} // fin de constructor
        
        
   /*Método run():
    *Este método se ejecuta cuando se inicia el hilo del servidor.
    *Dentro de un bucle infinito, espera y acepta las conexiones entrantes 
    *    utilizando el método accept() de ServerSocket.
    *Por cada conexión entrante, crea un nuevo objeto Handler y lo inicia como 
    *    un nuevo hilo para manejar la comunicación con el cliente.
    */     
    @Override
	public void run() {
		while(true) {
			try {
				Socket socket=this.serverSocket.accept();
				Handler client=new Handler(socket);
				client.start();
                                
			} catch (IOException e) {
                            //controlar en caso de error
                            System.out.println("Error");
                          
                        } 
		} // while true
	} // run
	
        
      /*Metodos gets and sets
       *Estos métodos permiten acceder y modificar las variables de instancia 
       * del servidor
       */
	public int getSocketPortNumber() {
		return socketPortNumber;
	}

	public void setSocketPortNumber(int socketPortNumber) {
		this.socketPortNumber = socketPortNumber;
	}

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	public InetAddress getAddress() {
		return address;
	}

	public void setAddress(InetAddress address) {
		this.address = address;
	}
	    
}
