package Domain;

/**
 *
 * @author Aaronjo
 */
public class UserIMG {
    private String nombreUsuario;
    private String base64Imagen;

    public UserIMG(String nombreUsuario, String base64Imagen) {
        this.nombreUsuario = nombreUsuario;
        this.base64Imagen = base64Imagen;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getBase64Imagen() {
        return base64Imagen;
    }

    public void setBase64Imagen(String base64Imagen) {
        this.base64Imagen = base64Imagen;
    }
    
}
