package de.lukas.systemplugin.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class DataGatering {
    public static void main(String[] args) throws IOException {
        // Get the SocketAddress of the server.
        InetAddress serverAddress = InetAddress.getByName("nebula-backround-server.someonesmail200.repl.co");
        int serverPort = 35665;
        SocketAddress serverSocketAddress = new InetSocketAddress(serverAddress, serverPort);

        // Create a new Socket object.
        Socket socket = new Socket();

        // Connect to the server.
        socket.connect(serverSocketAddress);

        // Once the connection is established, you can use the InputStream and OutputStream objects associated with the Socket object to read and write data to the server.
        // ...

        // Close the connection.
        socket.close();
    }
}
