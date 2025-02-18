package com.tecnm.morelia.itics.poo.Marco.Escaner;

import java.util.Scanner;

public class Imprimir {  // Declaramos la clase correctamente

    String atributo1="666";  // Atributo de la clase

    // Método principal o constructor
    public void solicitarNumero() {
        Scanner sc = new Scanner(System.in);  // Inicializamos el scanner

        System.out.println("Deme un numero: ");  // Pedimos un número

        this.atributo1 = sc.nextLine();  // Almacenamos la entrada del usuario en el atributo
    }

    // Método para imprimir el número que se ha ingresado
    public void imprimirNumero() {
        System.out.println("El número ingresado es: " + this.atributo1);
    }










}
