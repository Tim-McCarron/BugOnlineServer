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
    private long targetTick = 60;
    private boolean running;
    private CLI gui;
    
    public SocketHandler(Socket sock, String index, CLI gui) {
        client = sock;
        this.index = index;
        this.gui = gui;
        running = true;
    }
    
    public synchronized Socket getSocket() {
        return client;
    }
    
    public void run() {
        try {
            gui.pushOutput("Just connected to " + client.getRemoteSocketAddress());
            DataInputStream in = new DataInputStream(client.getInputStream());
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            int counter = 0;
            clientId = in.readUTF();
            gui.pushOutput("new clientId: " + clientId);
            while (running) {
                try {
                    start = System.nanoTime();         
                    if (wait < 0) wait = 5;
                    Thread.sleep(wait);
                    String[] inbound = in.readUTF().split(":");
                    if (inbound.length == 4) {
                        writeGamestate(clientId, Double.parseDouble(inbound[0]), Double.parseDouble(inbound[1]), Double.parseDouble(inbound[2]), Float.parseFloat(inbound[3]));
                    }
                    out.writeUTF(Gamestate.getPayload());
                    counter = counter + 5;
                    elapsed = System.nanoTime() - start;
                    wait = targetTick - elapsed / 1000000;
                } catch(Exception e) {
                    gui.pushOutput("closing status... " + Gamestate.removeUnit(clientId) + " for clientId: " + clientId);
                    running = false;
                }
            }
            
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void writeGamestate(String id, double x, double y, double z, float d) {
        Gamestate.submitState(id, x, y, z, d);
    }
    
    
}
