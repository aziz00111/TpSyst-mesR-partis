import java.net.*;
import java.util.Date;

public class Serveur {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(1250);
            System.out.println("Serveur UDP démarré. En attente de datagrammes...");
            while (true) {

                byte[] buffer = new byte[1024];

                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                socket.receive(packet);

                InetAddress address = packet.getAddress();
                int port = packet.getPort();

                String date = new Date().toString();
                byte[] responseData = date.getBytes();

                DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, address, port);
                socket.send(responsePacket);
                System.out.println("Réponse envoyée à " + address + ":" + port);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
