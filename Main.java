package com.tecnm.morelia.itics.poo.Marco.Escaner;
public class Main{
    public static void main(String[] args) {
        // Crear un objeto de la clase Imprimir
        com.tecnm.morelia.itics.poo.Marco.Escaner.Imprimir obj = new com.tecnm.morelia.itics.poo.Marco.Escaner.Imprimir();
        obj.imprimirNumero();   // Imprimir el número ingresado

        // Llamar a los métodos
        obj.solicitarNumero();  // Pedir el número
        obj.imprimirNumero();   // Imprimir el número ingresado
    }
}