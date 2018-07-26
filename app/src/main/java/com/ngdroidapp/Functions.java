package com.ngdroidapp;

import android.graphics.Rect;
import android.media.MediaPlayer;

import com.mycompany.myngdroidapp.R;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Functions {
    public static int ballcoordinat[][];
    public static boolean ballClick[];
    public static boolean soundgame = true;
    public int level[][];
    Random rand;
    private int seviye = 0, randx, randy;
    Rect r1, r2;
    public Timer MyTimer;
    public static int levelSay = 0, oyunzaman = 0;
    public static boolean rest;
    public static int balldstw, balldsth, width, height;
    public static boolean soundon = true,timeend;
    public static MediaPlayer music, playgamesound;


    public Functions() {

        level = new int[][]{
                {3, 5, 4},
                {3, 4, 3},
                {3, 3, 3},
                {4, 6, 5},
                {4, 5, 4},
                {4, 4, 3},
                {4, 3, 2},
                {5, 7, 6},
                {5, 6, 5},
                {5, 6, 4},
                {6, 8, 7},
                {6, 7, 6},
                {6, 6, 5},
                {7, 9, 8},
                {7, 8, 7},
                {7, 7, 6},
                {8, 10, 9},
                {8, 9, 8},
                {8, 8, 7},
                {9, 11, 10},
                {9, 11, 9},
                {9, 11, 8},
                {9, 11, 7},
                {9, 11, 6},
                {10, 14, 11},
                {10, 13, 10},
                {10, 12, 10},
                {10, 11, 8},
                {10, 10, 6},
                {10, 10, 4}
        };


    }

    public void setSeviye(int seviye) {
        this.seviye = seviye;
    }

    public int getSeviye() {
        return seviye;
    }

    /**
     * Ekranda gösterilecek topları hem de nereye yerleştirileceğini benzersiz yapan metod.
     */
    public void secilecekToplar() {
        ballcoordinat = new int[level[seviye][0]][6];
        ballClick = new boolean[level[seviye][0]];
        rand = new Random();
        for (int i = 0; i < level[getSeviye()][0]; i++) {
            randx = rand.nextInt(5);
            randy = rand.nextInt(4);

            if (topKarsilastir(randx * 140, randy * 140)) {
                ballcoordinat[i][0] = randx * 140;// Topların resimde bulunan x eksenindeki koordinatını diziye atıyoruz.
                ballcoordinat[i][1] = randy * 140;// Topların resimde bulunan y eksenindeki koordinatını diziye atıyoruz.
                tumToplar(i); // Her topun random koordinatlarını yüklemek için çağırılan fonksiyon.
            } else {
                i--;
            }
        }
    } // Topları Random ve benzersiz seçen metod.

    /**
     * Topların benzerliklerini karşılaştıran metod.
     *
     * @param x Bu parametre top için gelen random pixel değeri alır.
     * @param y Bu parametre top için gelen random pixel değeri alır.
     * @return Eğer fonksiyon true dönderir ise benzer top yok , false dönderir ise benzer top var demektir.
     */
    private boolean topKarsilastir(int x, int y) {
        for (int i = 0; i < ballcoordinat.length; i++) {
            if (ballcoordinat[i][0] == x && ballcoordinat[i][1] == y)
                return false;
        }
        return true;
    }


    /**
     * Seçilen herbir top ekrana random dağıtma işinin yapan metod.
     *
     * @param indis ==> kaçıncı top olduğunu alan parametre.
     */
    private void tumToplar(int indis) {
        boolean durum = true;
        while (durum) {
            randx = rand.nextInt(width - 300);
            randy = rand.nextInt(height - balldsth) + 100;
            if (ballcoordinat[indis][4] == 0 && ballcoordinat[indis][5] == 0) {

                if (konumKontrol(randx, randy, randx + balldstw, randy + balldsth)) {
                    ballcoordinat[indis][4] = randx;
                    ballcoordinat[indis][5] = randy;
                    ballClick[indis] = false;
                    durum = false;
                }


            }
        }
    } // Topların orta bölge için random topları seçmek için kullanılır.

    public  void Times(int zaman){
            oyunzaman = zaman + 1;
            MyTimer = new Timer();
        final TimerTask myTask = new TimerTask() {
            @Override
            public void run() {
                    oyunzaman --;
                    if (oyunzaman == 0 ){
                        timeend = true;
                        MyTimer.cancel();
                    }
            }
        };

            MyTimer.schedule(myTask,0,1000);


    }
    /**
     * Her top için oluşturulan rastgele konumun benzersiz olması için diziyi kontrol eden metod.
     *
     * @param x
     * @param y
     * @param w
     * @param h
     * @return
     */
    private boolean konumKontrol(int x, int y, int w, int h) {
        r1 = new Rect();
        r2 = new Rect();
        for (int i = 0; i < ballcoordinat.length; i++) {
            if (h <= height) {
                r1.set(x, y, w, h);
                r2.set(ballcoordinat[i][4], ballcoordinat[i][5], ballcoordinat[i][4] + balldstw, ballcoordinat[i][5] + balldsth);
                if (r1.intersect(r2)) {
                    return false;
                }
            } else {
                return false;
            }

        }
        return true;
    }

    public int xtitre(int i, int x) {
        if (i == 0) {
            return x;
        } else if (i == 1) {
            return x;
        } else if (i == 2) {
            return x + 10;
        } else if (i == 3) {
            return x - 10;
        }


        return 0;
    }

    public int ytitre(int i, int y) {
        if (i == 0) {
            return y - 10;
        } else if (i == 1) {
            return y + 10;
        } else if (i == 2) {
            return y;
        } else if (i == 3) {
            return y;
        }

        return 0;
    }
}













