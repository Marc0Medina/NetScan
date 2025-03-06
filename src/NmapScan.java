package tecnm.morelia.itic.poo.netscan;

import java.io.*;

/**
 * Clase para realizar un escaneo de red con nmap.
 *
 * @author Marco Medina
 * @version 1.1
 */
public class NmapScan {

    /**
     * Ejecuta un escaneo rápido de la red usando nmap.
     *
     * Obtiene la red y máscara directamente desde la clase ObtenerIP.
     */
    public static void main(String[] args) {
        try {
            // Ejecuta la clase ObtenerIP para que obtenga y almacene la red CIDR
            ObtenerIP.main(args);

            // Luego obtenemos la red CIDR de la clase ObtenerIP
            String network = ObtenerIP.getNetworkCIDR();

            if (network != null && !network.isEmpty()) {
                scanNetwork(network);
            } else {
                System.out.println("No se pudo obtener la red/MASCARA.");
            }
        } catch (Exception e) {
            System.out.println("Error al obtener la red o ejecutar el escaneo.");
            e.printStackTrace();
        }
    }

    /**
     * Ejecuta el escaneo de la red.
     *
     * @param network La red en formato CIDR (por ejemplo, 192.168.1.0/24).
     */
    public static void scanNetwork(String network) {
        try {
            System.out.println("Escaneando la red: " + network);

            // Ejecuta el comando nmap
            Process process = Runtime.getRuntime().exec("nmap -F " + network);

            // Lee la salida del comando
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error al ejecutar nmap");
            e.printStackTrace();
        }
    }
}
