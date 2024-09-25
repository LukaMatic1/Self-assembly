package com.example.javafxproject;


import javafx.scene.paint.Color;

public class Data {

        //Prva je nedefinorana in druga je seme

        // gor_vrsta,dol_vrsta,levo_vrsta,desno_vrsta
        //-1 predstavlja null (prime se z vsem)
        // gor_vrsta,dol_vrsta,levo_vrsta,desno_vrsta
        //-1 predstavlja null (prime se z vsem)
        static int[][][] dataV={
                {{-1,-1,-1,-1}, {-1 ,0 ,-1 ,1}, {0, 0, -1, 3}, {-1, 3, 1, 1},{ 2, 3, 3, 3}, {3, 3, 2, 3}, {2, 2, 2, 2}, {3, 2, 3, 2}}, //trikotniki
                {{-1,-1,-1,-1}, {-1, 1, -1, 0}, {-1, 2, 0, 0}, {1, 1, -1, 3}, {3, 3, 2, 2}, {2, 3, 3, 2}, {3, 2, 3, 3}, {2, 2, 2, 2}} //binarna števila
        };

        // gor_temperatura,dol_temperatura,levo_temperatura,desno_temperatura
        static int[][][] dataT={
                {{0, 0, 0, 0}, {0, 2, 0, 2}, {2, 2, 0, 1}, {0, 1, 2, 2}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}}, //trikotniki
                {{0, 0, 0, 0}, {0, 2, 0, 2}, {0, 1, 2, 2}, {2, 2, 0, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}} //binarna števila
        };

        static Character[] dataC={' ','S','N','W','X','X',' ',' '};
        static Color[] colors={Color.WHEAT,Color.RED,Color.ORANGE,Color.BLUE,Color.BLACK,Color.BLACK,Color.WHEAT,Color.WHEAT};
        //static Color[] colors={Color.WHITE,Color.RED,Color.ORANGE,Color.BLUE,Color.BLACK,Color.BLACK,Color.WHITE,Color.WHITE};
    }


