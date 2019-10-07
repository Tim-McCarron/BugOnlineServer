/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duckonlineserver;

import java.util.ArrayList;
import Unit.*;
/**
 *
 * @author abe
 */
public class Gamestate {
    
    private static ArrayList<Unit> mapletonPark = new ArrayList();
    
    public static ArrayList<Unit> getMapleton() {
        return mapletonPark;
    }
    
//    public static ArrayList getState(String map) {
//        return mapletonPark;
//    }
    
    public static String getPayload() {
//        String payload = "13h111:coolguy:5.0:0.0:0.0/3j9gl1:notsocoolGUy:7.0:0.0:0.0";
        String payload = "";
        for (int i = 0; i < mapletonPark.size(); i++) {
            String name;
            if (mapletonPark.get(i).getName() != null) {
                name = mapletonPark.get(i).getName();
            } else {
                name = "defaultName";
            }
            payload = payload + mapletonPark.get(i).getId() + ":" + name + ":" + mapletonPark.get(i).getX() + ":" + 
                mapletonPark.get(i).getY() + ":" + mapletonPark.get(i).getZ() + ":" + mapletonPark.get(i).getDir();
            if (i < mapletonPark.size() - 1) {
                payload = payload + "/";
            }
        }
        return payload;
    }
    
    public static void addPlayer(String id, double x, double y, double z, float direction) {
        Player player = new Player();
        player.setId(id);
        player.setX(x);
        player.setY(y);
        player.setY(z);
        player.setDir(direction);
        mapletonPark.add(player);
    }
    
    public static boolean removeUnit(String unitId) {
        int index = -1;
        for (int i = 0; i < mapletonPark.size(); i++) {
            System.out.println("id to find " + unitId);
            System.out.println("id to check " + mapletonPark.get(i).getId());
            if (unitId.equals(mapletonPark.get(i).getId())) {
                System.out.println("ok?");
                index = i;
            }
        }
        System.out.println("index to remove " + index);
        if (index == -1) {
            return false;
        } else {
            mapletonPark.remove(index);
            return true;
        }
    }
    
    public static void submitState(String id, double x, double y, double z, float d) {
        // submit player id and location stuff
        // if exists in arraylist then set data else create new player obj and push
        for (int i = 0; i < mapletonPark.size(); i++) {
            if (mapletonPark.get(i).getId() == id) {
                double newX = mapletonPark.get(i).getX();
                double newY = mapletonPark.get(i).getY();
                double newZ = mapletonPark.get(i).getZ();
                newX += x;
                newY += y;
                newZ += z;
                mapletonPark.get(i).setX(Double.parseDouble(String.format("%.2f", newX)));
                mapletonPark.get(i).setY(Double.parseDouble(String.format("%.2f", newY)));
                mapletonPark.get(i).setZ(Double.parseDouble(String.format("%.2f", newZ)));
                mapletonPark.get(i).setDir(Float.parseFloat(String.format("%.2f", d)));
                return;
            }
        }
        Player player = new Player();
        player.setId(id);
        player.setX(x);
        player.setY(y);
        player.setZ(z);
        player.setDir(d);
        mapletonPark.add(player);
    }
    
}
