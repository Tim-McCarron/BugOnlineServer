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
    
    public static String getPayload() {
        String payload = "";
        for (int i = 0; i < mapletonPark.size(); i++) {
            payload = payload + mapletonPark.get(i).getId() + ":" + mapletonPark.get(i).getName() + ":" + mapletonPark.get(i).getX() + ":" + mapletonPark.get(i).getY();
            if (i < mapletonPark.size() - 1) {
                payload = payload + "/";
            }
        }
        System.out.println(payload);
        return payload;
    }
    
    public static void addUnit(Unit unit) {
         
     
    }
    
    public static boolean removeUnit(String unitId) {
        int index = -1;
        for (int i = 0; i < mapletonPark.size(); i++) {
            if (unitId == mapletonPark.get(i).getId()) {
                
                index = i;
            }
        }
        if (index == -1) {
            return false;
        } else {
            mapletonPark.remove(index);
            return true;
        }
    }
    
    public static void submitState(String id, double x, double y) {
        for (int i = 0; i < mapletonPark.size(); i++) {
            if (mapletonPark.get(i).getId() == id) {
                double newX = mapletonPark.get(i).getX();
                double newY = mapletonPark.get(i).getY();
                newX += x;
                newY += y;
                mapletonPark.get(i).setX(newX);
                mapletonPark.get(i).setY(newY);
                return;
            }
        }
        Player player = new Player();
        player.setId(id);
        player.setX(x);
        player.setY(y);
        mapletonPark.add(player);
    }
    
}
