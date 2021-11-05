package com.company;

import javax.swing.*;

public class Main {
    static int capacidad=0;
    static boolean pasa=true;
    public static void main(String[] args) {
        for (int i=0;i<500;i++){
            Entrada h=new Entrada("persona");
            h.start();

            Salida h2=new Salida("persona");
            h2.start();
        }

    }
    public static class Entrada extends Thread {

        public Entrada(String name) {
            super(name);
        }

        public synchronized void run() {
            //si el pasa esta false el hilo se duerme hasta que alguien lo despierte
            try {
                sleep((long) Math.floor(Math.random()*3000+1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if ((capacidad>=40)|!pasa) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            pasa=false;
            capacidad ++;
            System.out.println("Entra una persona");
            System.out.println(capacidad);
            pasa=true;
        }
    }
    public static class Salida extends Thread {

        public Salida(String name) {
            super(name);
        }

        public synchronized void run() {
            //si el pasa esta false el hilo se duerme hasta que alguien lo despierte
            try {
                sleep((long) Math.floor(Math.random()*3000+1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if ((capacidad<1)|!pasa) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            capacidad --;
            System.out.println("Sale una persona");
            System.out.println(capacidad);
            try {
                notify();
            } catch (IllegalMonitorStateException e) {
                e.toString();
            }
        }
    }
}


