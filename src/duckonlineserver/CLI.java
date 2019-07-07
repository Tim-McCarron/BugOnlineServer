/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duckonlineserver;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;

/**
 *
 * @author abe
 */
public class CLI extends javax.swing.JFrame implements Runnable {

    private int width;
    private int height;
    private boolean running;
    
    public CLI(int width, int height) {
        
        initComponents();
        this.width = width;
        this.height = height;
        running = true;
        
//        commands.add("add");
//        commands.add("gamestate");
//        commands.add("remove");
//        commands.add("exit");
    
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        container = new javax.swing.JPanel();
        cli_inputScroller = new javax.swing.JScrollPane();
        cli_input = new javax.swing.JTextPane();
        outputScrollPane = new java.awt.ScrollPane();
        cli_scroller = new javax.swing.JScrollPane();
        cli_output = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cli_input.setMaximumSize(new java.awt.Dimension(2147483647, 1));
        cli_input.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cli_inputKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cli_inputKeyReleased(evt);
            }
        });
        cli_inputScroller.setViewportView(cli_input);

        cli_output.setColumns(20);
        cli_output.setRows(5);
        cli_output.setEnabled(false);
        cli_scroller.setViewportView(cli_output);

        outputScrollPane.add(cli_scroller);

        javax.swing.GroupLayout containerLayout = new javax.swing.GroupLayout(container);
        container.setLayout(containerLayout);
        containerLayout.setHorizontalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerLayout.createSequentialGroup()
                .addGroup(containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(outputScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                    .addComponent(cli_inputScroller))
                .addContainerGap())
        );
        containerLayout.setVerticalGroup(
            containerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, containerLayout.createSequentialGroup()
                .addComponent(outputScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cli_inputScroller, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cli_inputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cli_inputKeyPressed
        
    }//GEN-LAST:event_cli_inputKeyPressed

    private void cli_inputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cli_inputKeyReleased
        if (evt.getKeyCode() == 10) {
            cli_input.setEnabled(false);
            executeCommand();
        }
    }//GEN-LAST:event_cli_inputKeyReleased

    public void run() {
        setSize(width, height);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cli_output.append("\n");
    }
    
    public boolean getIsRunning() {
        return running;
    }
    
    public void pushOutput(String msg) {
        cli_output.append(msg + "\n");
    }
    
    public void executeCommand() {
        String command = cli_input.getText().replace("\n", "").replace("\r", "");
        String[] split = command.split(" ");
        if (split.length > 0) {
            if (split[0].equals("add")) {
                commandAddUnit(split);
            } else if (split[0].equals("remove")) {
                commandRemoveUnit(split);
            }
        }
        cli_input.setEnabled(true);
        cli_input.setText("");
    }
    
    // commands
    private void commandAddUnit(String[] parameters) {
        if (parameters.length > 4) {
            String id = parameters[1];
            double x = Double.parseDouble(parameters[2]);
            double y = Double.parseDouble(parameters[3]);
            double z = Double.parseDouble(parameters[4]);
            float direction = 0;
            if (parameters.length > 5) {
                direction = Float.parseFloat(parameters[5]);
            }
            Gamestate.addPlayer(id, x, y, z, direction);
            pushOutput("Added player: " + id + "at location:(xyz) " + x + "-" + y + "-" + z);
        } else {
            pushOutput("Invalid params for add. Proper syntax is.... ID X Y Z");
        }
    }
    
    private void commandRemoveUnit(String[] parameters) {
        if (parameters.length == 2) {
            Gamestate.removeUnit(parameters[1]);
            pushOutput("Removed player: " + parameters[1]);
        }
    } 
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane cli_input;
    private javax.swing.JScrollPane cli_inputScroller;
    private javax.swing.JTextArea cli_output;
    private javax.swing.JScrollPane cli_scroller;
    private javax.swing.JPanel container;
    private java.awt.ScrollPane outputScrollPane;
    // End of variables declaration//GEN-END:variables
}
