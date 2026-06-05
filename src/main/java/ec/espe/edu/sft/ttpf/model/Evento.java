package ec.espe.edu.sft.ttpf.model;

public class Evento {

    private int idEvento;
    private static int id = 0;
    private String nombreEvento;
    private float precioEvento;

    public Evento(String nombreEvento, float precio) {
        this.idEvento = id++;
        this.nombreEvento = nombreEvento;
        this.precioEvento = precio;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public float getPrecioEvento() {
        return precioEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public void setPrecioEvento(float precioEvento) {
        this.precioEvento = precioEvento;
    }

}
