/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duckonlineserver;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
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
    private long targetTick = 100;
    private boolean running;
    
    public SocketHandler(Socket sock, String index) {
        client = sock;
        this.index = index;
        System.out.println("instantiated");
        running = true;
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
                    System.out.println(wait);
                    if (wait < 0) wait = 5;
                    Thread.sleep(wait);
                    
                    System.out.println(in.readUTF());
                    out.writeUTF("ticked");
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
            
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
}
