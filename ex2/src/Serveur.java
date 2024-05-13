import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Serveur {
    public static void main(String[] args) {
        Integer qte;
        Scanner keyb = new Scanner(System.in);
        try {
            DatagramSocket socket = new DatagramSocket(12345); // Port d'écoute

            // Réception de l'objet sérialisé du client
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            // Désérialisation de l'objet
            ByteArrayInputStream inputStream = new ByteArrayInputStream(packet.getData());
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            voiture car = (voiture) objectInputStream.readObject();

            // Fixation de la quantité de carburant côté serveur
            System.out.println("quantité de carburant a fixer : ");
            qte = keyb.nextInt();
            car.setCarburant(qte);

            // Utilisation de l'objet reçu
            System.out.println("Type de voiture : " + car.type);
            System.out.println("Modèle de voiture : " + car.model);
            System.out.println("Quantité de carburant : " + car.getCarburant());
            System.out.println("Capacité du réservoir : " + car.getCapacite());

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
