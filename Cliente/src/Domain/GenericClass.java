
package Domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 *
 * @author Aaronjo
 */
public class GenericClass {


    
    public void guardarImagenEnArchivo(String base64Imagen, String nombreUsuario) {
        // Leer la lista de usuarios existente
        List<UserIMG> usuarios = leerUsuariosDesdeArchivo();

        // Agregar un nuevo usuario a la lista
        usuarios.add(new UserIMG(nombreUsuario, base64Imagen));

        // Escribir la lista actualizada en el archivo
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("usuarios.txt"))) {
            writer.write(gson.toJson(usuarios));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ImageIcon mostrarImagen(String nombreUsuario) {
        // Ruta del archivo usuarios.txt
        String rutaArchivo = "usuarios.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            Gson gson = new Gson();

            while ((linea = br.readLine()) != null) {
                // Parsear la línea como un objeto UserIMG
                UserIMG userIMG = gson.fromJson(linea, UserIMG.class);

                // Obtener el nombre de usuario y la base64Imagen
                String nombreUsuario2 = userIMG.getNombreUsuario();
                String base64Imagen = userIMG.getBase64Imagen();

  
                System.out.println("Nombre de usuario: " + nombreUsuario);
                System.out.println("Base64 de la imagen: " + base64Imagen);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<UserIMG> leerUsuariosDesdeArchivo() {
        List<UserIMG> usuarios = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("usuarios.txt"))) {
            Gson gson = new GsonBuilder().create();
            Type listType = new TypeToken<List<UserIMG>>() {
            }.getType();
            usuarios = gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public UserIMG obtenerUsuarioPorNombreUsuario(List<UserIMG> usuarios, String nombreUsuario) {
        for (UserIMG usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                return usuario;
            }
        }
        return null;
    }

    public ImageIcon obtenerBase64ImagenPorNombreUsuario(List<UserIMG> usuarios, String nombreUsuario) {

        for (UserIMG usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                String base64Imagen = usuario.getBase64Imagen();
                BufferedImage imagen = decodeBase64ToImage(base64Imagen);
                if (imagen != null) {
                    return new ImageIcon(imagen);
                }
            }
        }
        return null;
    }

    public BufferedImage decodeBase64ToImage(String base64Imagen) {
        try {
            byte[] imageBytes = Base64.getDecoder().decode(base64Imagen);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
            return ImageIO.read(bis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean verificarExistenciaArchivo(String archivo) { // este metodo se utiliza para verificar si un archivo existe en los achivos
        File file = new File(archivo);                           // Si existe recibiremos true y si no false 
        return file.exists();
    }
}
