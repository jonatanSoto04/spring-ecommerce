package com.soto.ecommerce.springecommerce.model;

import java.util.Date;

public class Order {
    private Integer id;
    private String number;
    private Date fechaCreacion;
    private Date fechaRecibido;
    private Double total;

    public Order() {

    }

    public Order(Integer id, String number, Date fechaCreacion, Date fechaRecibido, double total) {
        this.id = id;
        this.number = number;
        this.fechaCreacion = fechaCreacion;
        this.fechaRecibido = fechaRecibido;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaRecibido() {
        return fechaRecibido;
    }

    public void setFechaRecibido(Date fechaRecibido) {
        this.fechaRecibido = fechaRecibido;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", fechaRecibido=" + fechaRecibido +
                ", total=" + total +
                '}';
    }
}
