package com.tecnm.morelia.itics.poo.Marco.Escaner;
import java.io.*;
import java.util.*;

public class NmapScanner {







    private static List<String> runNmapScan(String network) throws IOException {
        List<String> activeHosts = new ArrayList<>();
        // Ejecutar el comando nmap
        String command = "nmap -F " + network;  // -sn es para hacer un escaneo de solo ping
        Process process = Runtime.getRuntime().exec(command);

        // Leer la salida de nmap
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Buscar líneas que indican hosts activos
                if (line.startsWith("Nmap scan report for")) {
                    String ip = line.split(" ")[4];  // Extraer la IP
                    activeHosts.add(ip);
                }
            }
        }
        return activeHosts;
    }






    public static void main(String[] args) {
        try {
            // Definir la red a escanear
            String network = "10.28.204.0/24";  // Cambia esto por tu red
            List<String> activeHosts = runNmapScan(network);
            System.out.println("Hosts activos:");
            for (String host : activeHosts) {
                System.out.println(host);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
