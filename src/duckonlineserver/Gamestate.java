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
    
}
