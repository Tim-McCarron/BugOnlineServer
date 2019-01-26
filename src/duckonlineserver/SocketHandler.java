/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duckonlineserver;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
/**
 *
 * @author abe
 */
public class SocketHandler implements Runnable {
    private Socket client;
    private String clientId;
    public String index;
    private long start;
    private long elapsed;
    private long wait;
    private long targetTick = 80;
    private boolean running;
    
    public SocketHandler(Socket sock, String index) {
        client = sock;
        this.index = index;
        running = true;
    }
    
    public synchronized Socket getSocket() {
        return client;
    }
    
    public void run() {
        try {
            System.out.println("Just connected to " + client.getRemoteSocketAddress());
            DataInputStream in = new DataInputStream(client.getInputStream());
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            int counter = 0;
            clientId = in.readUTF();
            System.out.println(clientId);
            while (running) {
                try {
                    start = System.nanoTime();         
                    elapsed = System.nanoTime() - start;
                    wait = targetTick - elapsed / 1000000;
                    if (wait < 0) wait = 5;
                    Thread.sleep(wait);
                    System.out.println(in.readUTF());
                    out.writeUTF("213231:duck master 9:" + counter + ":150/132589:very cool duck:250:400");
                    counter = counter + 5;
                } catch(Exception e) {
//                    e.printStackTrace();
                    running = false;
                }
            }
            
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
}
