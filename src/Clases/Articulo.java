package Clases;

public class Articulo extends Promocion{

    protected int id;
    protected String image;
    protected String marca;
    private String genero;
    protected String tipo;
    protected String talla;
    protected double precio;
    protected int stock;
    
    public Articulo (int id, String marca, String tipo, String talla, double precio, int stock, int id_Promocion){
        this.id = id;
        this.marca = marca;
        this.tipo = tipo;
        this.talla = talla;
        this.precio = precio;
        this.stock = stock;
        this.id_promocion = id_Promocion;
    }

    public Articulo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
}
