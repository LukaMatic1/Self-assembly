package com.example.javafxproject;

import javafx.scene.canvas.GraphicsContext;

public class Run extends Thread{

    private final GraphicsContext gc;
    private final int mode;

    public Run(GraphicsContext gc, int mode){
        this.gc = gc;
        this.mode = mode;
    }

    @Override
    public void run() {

        int X=30;
        int Y=30;
        int chunk=200;
        int[][][] array=new int[X][Y][chunk*chunk];

        array[0][0][chunk+1]=1;

        long startTime = System.currentTimeMillis();
        new Master(X,Y,array,chunk, gc,mode);
        long endTime = System.currentTimeMillis();


        System.out.println("Cas: "+ (endTime - startTime));




    }
}
