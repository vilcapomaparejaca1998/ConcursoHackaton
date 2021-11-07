package com.example.concurso;

public class Contacto {
    //Atributos
    String nombre;
    String numero;
    //Constructor
    public Contacto(String nom, String num){
        nombre = nom;
        numero = num;
    }

    //Metodos Getter and Setter
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
}
