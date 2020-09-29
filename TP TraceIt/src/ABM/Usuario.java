package ABM;

public abstract class Usuario {

    String cuil;
    String tipoUsuario;

    public Usuario(String cuil) {
        this.cuil = cuil;
    }

    public String getNombreUsuario() {
        return cuil;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }
}