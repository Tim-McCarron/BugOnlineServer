/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duckonlineserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author abe
 */
public class DuckOnlineServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        HashMap<String, SocketHandler> clients = new HashMap<String, SocketHandler>();
        SocketHandler currentSock;
        Thread newThread;
        String newIndex;

        try {
            serverSocket = new ServerSocket(9999);
        } catch (IOException e) {
            e.printStackTrace();

        }
            
        while (true) {
            try {
                socket = serverSocket.accept();
                // new thread for a client
                System.out.println("Client connected");
                newIndex = getRandomHexString(10);
                System.out.println(newIndex);
                currentSock = new SocketHandler(socket, newIndex);
                newThread = new Thread(currentSock);
                newThread.start();                
                clients.put(newIndex, currentSock);
//                System.out.println(clients.size());
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            
        }
    }
    
    private static String getRandomHexString(int numchars){
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while(sb.length() < numchars){
            sb.append(Integer.toHexString(r.nextInt()));
        }

        return sb.toString().substring(0, numchars);
    }
    
}
