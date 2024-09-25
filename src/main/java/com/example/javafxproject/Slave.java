package com.example.javafxproject;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Slave extends Thread {

        int chunk;  //Chunk size
        int atX; int atY; //Chunk at

        int[][][] array;
        int[][] dataV;int[][] dataT;
        int temperature=2;

        GraphicsContext gc;

        public Slave(int chunk, int[][][] array, int tiles,int atX,int atY, GraphicsContext gc) {
            Data d=new Data();dataT=d.dataT[tiles];dataV=d.dataV[tiles];
            this.chunk=chunk;this.atX=atX;this.atY=atY;
            this.array = array;
            this.gc = gc;
        }

        @Override
        public void run() {
            ThroughDiagonal();
        }

        public void draw(int size){

            int offsetX = atX * (chunk - 1) * size;
            int offsetY = atY * (chunk - 1) * size;

            for (int j = 1; j < chunk; j++) {
                for (int l = 1; l < chunk; l++) {

                    Color color = Data.colors[array[atX][atY][l+j*chunk]];
                    if(color == Color.WHITE)continue;

                    gc.setFill(color);
                    gc.fillRect(
                            l * size + offsetX,
                            j * size + offsetY,

                            size, size);
                }
            }
        }




        public void ThroughDiagonal(){
            for (int i = 1; i < chunk; i++) {
                ThroughAll(i,1);
            }
            for (int i = 2; i < chunk; i++) {
                ThroughAll(chunk-1,i);
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    draw(1);
                }
            });
        }


        public void ThroughAll(int x,int y){
            while (y!=chunk&&x!=0){
                SetTiles(x,y);
                x--;y++;
            }
        }



        public void SetTiles(int x,int y){
            for (int i = 2; i < dataV.length; i++) {

                if (dataV[array[atX][atY][x-1+y*chunk]][1]==dataV[i][0]&&
                        dataV[array[atX][atY][x+(y-1)*chunk]][3]==dataV[i][2]&&
                        dataT[array[atX][atY][x-1+y*chunk]][1]+dataT[array[atX][atY][x+(y-1)*chunk]][3]>=temperature){
                    array[atX][atY][x+y*chunk]=i;return;
                }

            }
        }
    }


