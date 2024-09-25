package com.example.javafxproject;


import javafx.scene.canvas.GraphicsContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

    public class Master{

        int X;
        int Y;
        int[][][] array;
        int chunk;
        int threads;
        int tiles=0;
        GraphicsContext gc;


        public Master(int x, int y, int[][][] array, int chunk, GraphicsContext gc, int mode) {
            X = x;
            Y = y;
            this.array = array;
            this.chunk = chunk;
            this.gc = gc;
            threads = Runtime.getRuntime().availableProcessors();

            ThroughDiagonal(mode);
        }

        //dela chunke
        public void ThroughDiagonal(int mode){
            for (int i = 0; i < X; i++) {
                ThroughDiagonal(i,0,mode);
            }
            for (int i = 1; i < Y; i++) {
                ThroughDiagonal(X-1,i,mode);
            }

        }

        public void ThroughDiagonal(int x, int y, int mode){
            switch(mode) {
                case 0:        //Sequental

                        while (y!=Y&&x!=-1){
                            Slave s=new Slave(chunk,array,tiles,x,y,gc);
                            s.ThroughDiagonal();
                            ConnectChunks(x,y);
                            x--;y++;

                    }
                    break;
                case 1:        //Paralel

                        int xT=x;int yT=y;
                        ExecutorService executorService = Executors.newFixedThreadPool(threads);
                        while (yT!=Y&&xT!=-1){
                            executorService.execute(new Slave(chunk,array,tiles,xT,yT,gc));
                            xT--;yT++;
                        }
                        executorService.shutdown();
                        while (!executorService.isTerminated()) {}
                        while (y!=Y&&x!=-1){
                            ConnectChunks(x,y);
                            x--;y++;
                        }

                    break;

            }
        }


        public void ConnectChunks(int x,int y){
            for (int i = 0; i < chunk; i++) {
                if(y+1<array[0].length){array[x][y+1][i]=array[x][y][i+(chunk-1)*chunk];}
                if(x+1<array.length){array[x+1][y][i*chunk]=array[x][y][i*chunk+chunk-1];}
            }
        }
    }

