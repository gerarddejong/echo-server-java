package za.net.dejong;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

class DatagramServer
{
    public static void main(String args[]) throws Exception
    {
        DatagramSocket serverSocket = new DatagramSocket(5006);
        byte[] buffer = new byte[1024];
        while(true)
        {
            System.out.println("Started Datagram Echo Server...");
            DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
            serverSocket.receive(receivePacket);

            String message = new String(receivePacket.getData(), StandardCharsets.US_ASCII).trim();
            System.out.println("Received from client: " + message);

            System.out.println("Responding to client...");
            InetAddress IPAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();

            buffer = message.getBytes(StandardCharsets.US_ASCII);

            DatagramPacket sendPacket = new DatagramPacket(buffer, message.length(), IPAddress, port);
            serverSocket.send(sendPacket);
        }
    }
}
