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
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

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
        ArrayList<String> indicesToRemove = new ArrayList<String>();
        CLI gui = new CLI(800, 600);

        try {
            serverSocket = new ServerSocket(9999);
        } catch (IOException e) {
            e.printStackTrace();

        }
        
        Thread cli = new Thread(gui);
        cli.start();
        gui.pushOutput("Initializing server..");
        while (gui.getIsRunning()) {
            try {
                // do this bull shit just to forEach through the fckin hashmap
                Set<String> keys = clients.keySet();
                Iterator it = keys.iterator();
                indicesToRemove.clear();
                while (it.hasNext()) {
                    String key = (String) it.next();
                    Socket thisSock = clients.get(key).getSocket();
                    if (thisSock.isClosed()) {
                        indicesToRemove.add(key);
                    }
                    
                }
                for (int i = 0; i < indicesToRemove.size(); i++) {
                    clients.remove(indicesToRemove.get(i));
                    System.out.println("removed sock " + indicesToRemove.get(i));
                }
                
                System.out.println("updated client size ---> " + clients.size());
                
                socket = serverSocket.accept();
                // new thread for a client
                System.out.println("Client connected");
                newIndex = getRandomHexString(10);
                System.out.println("new sock index " + newIndex);
                currentSock = new SocketHandler(socket, newIndex, gui);
                newThread = new Thread(currentSock);
                newThread.start();                
                clients.put(newIndex, currentSock);
                
                
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            
        }
    }
    
    private static String getRandomHexString(int numchars){
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while (sb.length() < numchars){
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0, numchars);
    }
    
}
