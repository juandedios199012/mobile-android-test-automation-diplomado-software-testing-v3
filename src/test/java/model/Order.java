package model;

public class Order {

    private String empresa;
    private String usuario;
    private String clave;
    private String cantidadBonificacion;
    private String cantidadVenta;

    public String getCantidadBonificacion() {
        return cantidadBonificacion;
    }

    public String getCantidadVenta() {
        return cantidadVenta;
    }

    public String getEmpresa() {
        return empresa;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getClave() {
        return clave;
    }
}
