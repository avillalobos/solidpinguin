package Interfaz;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Interfaz.Juego;
import Interfaz.Menuprincipal;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vladimir
 */
public class SingletonFrames {

    private static Menuprincipal __menuPrincipal;
    private static Juego __juego;
    private static  String __Usuario;
    //Intocable
    private static SingletonFrames __instance;

    private SingletonFrames() {
        try {
            try {
                __menuPrincipal = new Menuprincipal();
            } catch (IOException ex) {
                Logger.getLogger(SingletonFrames.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(SingletonFrames.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setJuegoFocus() {
        __juego.requestFocus();
        __juego.toFront();
    }

    //Intocable
    public static SingletonFrames getInstance() {
        if (__instance == null) {
            __instance = new SingletonFrames();
        }
        return __instance;
    }

    public void loadMenu() {
        hideAll();
        __menuPrincipal.setVisible(true);
    }

    public void loadJuego() {
        try {
            hideAll();
            __juego = new Juego();
            __juego.setVisible(true);


            setJuegoFocus();
        } catch (Exception ex) {
            Logger.getLogger(SingletonFrames.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void hideAll() {
        __menuPrincipal.setVisible(false);
        try {
            __juego.setVisible(false);
        } catch (Exception ex) {
           // Logger.getLogger(SingletonFrames.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/*
 * @return Devuelve un controlador interfaz
 */
    public String getUsuario(){
        return __Usuario;
    }
    public void setUsuario(String user__){
        __Usuario=user__;
    }
   

}


