package ec.espe.edu.sft.ttpf.model;

public class Extra {

    private int idExtra;
    private static int id = 0;
    private String articuloExtra;
    private float precioExtra;

    public Extra(String articuloExtra, float precioExtra) {
        this.idExtra = id++;
        this.articuloExtra = articuloExtra;
        this.precioExtra = precioExtra;
    }

    public String getArticuloExtra() {
        return articuloExtra;
    }

    public void setArticuloExtra(String articuloExtra) {
        this.articuloExtra = articuloExtra;
    }

    public float getPrecioExtra() {
        return precioExtra;
    }

    public void setPrecioExtra(float precioExtra) {
        this.precioExtra = precioExtra;
    }

}
