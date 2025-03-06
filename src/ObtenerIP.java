package tecnm.morelia.itic.poo.netscan;
import java.net.*;
import java.util.*;

/**
 * Clase para obtener información de la red local, incluyendo la dirección IP, la máscara de subred
 * y la cantidad de hosts utilizables. También sugiere un comando para escanear la red con nmap.
 *
 * @author Marco Medina
 * @version 1.1
 */
public class ObtenerIP {

    private static String networkCIDR; // Almacena la red CIDR

    /**
     * Método principal que inicia la ejecución del programa.
     * Recorre las interfaces de red disponibles para encontrar la dirección IPv4 local,
     * calcula la máscara de subred, los hosts utilizables y sugiere un comando para escanear la red.
     *
     * @param args Los argumentos de la línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        try {
            // Obtiene todas las interfaces de red disponibles
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            // Recorre cada interfaz de red
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();

                // Obtiene todas las direcciones asociadas a la interfaz
                Enumeration<InetAddress> addresses = ni.getInetAddresses();

                // Recorre cada dirección de la interfaz
                while (addresses.hasMoreElements()) {
                    InetAddress address = addresses.nextElement();

                    // Filtra para mostrar solo direcciones IPv4 que no sean de loopback
                    if (!address.isLoopbackAddress() && address instanceof Inet4Address) {
                        System.out.println("Tu dirección IP local es: " + address.getHostAddress());

                        // Obtiene la interfaz de red asociada a la dirección IP
                        NetworkInterface networkInterface = NetworkInterface.getByInetAddress(address);

                        if (networkInterface != null) {
                            // Recorre las direcciones asociadas a la interfaz para obtener más detalles
                            for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
                                if (interfaceAddress.getAddress() instanceof Inet4Address) {
                                    int prefixLength = interfaceAddress.getNetworkPrefixLength();
                                    System.out.println("Máscara: " + prefixLength);

                                    // Calcula los hosts utilizables en la red
                                    int totalHosts = (int) Math.pow(2, 32 - prefixLength);
                                    int usableHosts = totalHosts - 2; // Se restan la dirección de red y la de broadcast

                                    System.out.println("Hosts utilizables: " + usableHosts);

                                    // Almacena la red CIDR
                                    networkCIDR = address.getHostAddress() + "/" + prefixLength;

                                    // Sugiere un comando para escanear la red con nmap
                                    System.out.println("Para escanear la red usa: nmap -F " + networkCIDR);
                                }
                            }
                        }
                    }
                }
            }
        } catch (SocketException e) {
            // Maneja errores en caso de que no se puedan obtener las interfaces de red
            System.out.println("No se pudo obtener la dirección IP.");
            e.printStackTrace();
        }
    }

    /**
     * Método para obtener la dirección de red en formato CIDR.
     * @return La dirección en formato CIDR (por ejemplo, 192.168.1.0/24).
     */
    public static String getNetworkCIDR() {
        return networkCIDR; // Devuelve la red CIDR almacenada
    }
}
