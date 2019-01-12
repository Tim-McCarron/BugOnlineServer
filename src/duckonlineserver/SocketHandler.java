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
    public String index;
    private long start;
    private long elapsed;
    private long wait;
    private long targetTick = 1000;
    private boolean running;
    
    public SocketHandler(Socket sock, String index) {
        client = sock;
        this.index = index;
        System.out.println("instantiated");
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
            while (running) {
                try {
                    start = System.nanoTime();         
                    elapsed = System.nanoTime() - start;
                    wait = targetTick - elapsed / 1000000;
                    if (wait < 0) wait = 5;
                    Thread.sleep(wait);
                    System.out.println(in.readUTF());
                    out.writeUTF("[{\"id\" : \"213231\",\"name\" : \"duck master 9\",\"x\" : \"100\",\"y\" : \"150\"},{\"id\" : \"132589\",\"name\" : \"very cool duck\",\"x\" : \"250\",\"y\" : \"400\"}]");
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
