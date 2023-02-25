package model;

public class Order {
    //public static String schemaFile = "resources/json/order.json";
    //@JsonProperty("cantidadBonificacion")
    private String cantidadBonificacion;

    //@JsonProperty("cantidadVenta")
    private String cantidadVenta;

    public String getCantidadBonificacion() {
        return cantidadBonificacion;
    }

    public String getCantidadVenta() {
        return cantidadVenta;
    }
}
