import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Client {
    public static void main(String[] args) {
        String type,model;
        Scanner keyb = new Scanner(System.in);
        try {
            DatagramSocket socket = new DatagramSocket();


            System.out.println("type de voiture : ");
            type = keyb.next();
            System.out.println("model de voiture : ");
            model = keyb.next();
            voiture car = new voiture(type, model);

            // Sérialisation de l'objet
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(car);
            byte[] data = outputStream.toByteArray();

            // Envoi de l'objet sérialisé au serveur
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 12345; // Port du serveur
            DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, serverPort);
            socket.send(packet);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
