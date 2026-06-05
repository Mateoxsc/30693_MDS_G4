package ec.espe.edu.sft.ttpf.model;

public class Empleado {

    private int cedula;
    private String nombre;
    private String apellido;
    private String sucursal;
    private String usuario;
    private String contrasenia;

    public Empleado(int cedula, String nombre, String apellido, String sucursal, String usuario, String contrasenia) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sucursal = sucursal;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public int getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

}
