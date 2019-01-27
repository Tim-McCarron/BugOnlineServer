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
                    String[] inbound = in.readUTF().split(":");
                    writeGamestate(clientId, Double.parseDouble(inbound[0]), Double.parseDouble(inbound[1]));
                    System.out.println(Gamestate.getPayload());
                    out.writeUTF(Gamestate.getPayload());
                    counter = counter + 5;
                } catch(Exception e) {
                    System.out.println("closing status... " + Gamestate.removeUnit(clientId));
                    running = false;
                }
            }
            
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void writeGamestate(String id, double x, double y) {
        Gamestate.submitState(id, x, y);
    }
    
    
}
