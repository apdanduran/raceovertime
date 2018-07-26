package com.ngdroidapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.health.TimerStat;

import com.mycompany.myngdroidapp.R;

import java.sql.Timestamp;
import java.util.Random;

import istanbul.gamelab.ngdroid.base.BaseCanvas;
import istanbul.gamelab.ngdroid.util.Log;
import istanbul.gamelab.ngdroid.util.Utils;


/**
 * Created by noyan on 24.06.2016.
 * Nitra Games Ltd.
 */


public class GameCanvas extends BaseCanvas {


    private Bitmap background,ball,menu,healt,gameover,paused,bomb,bar;

    private Rect backgroundsource, backgrounddestination,barsource, bardestination, ballsource, balldestination, menusource, menudestination, healtsource, healtdestination, gameoversource,gameoverdestination, pausedsource, pauseddestination,bombsource,bombdestination;

    private int backdstx,backdsty,backdsth,backdstw;
    private int balldstx,balldsty;

    private int healtsrcx, healtsrcy,healtsrcw, healtsrch;
    private int healtdstx, healtdsty, healtdstw, healtdsth;

    private int menudstx, menudsty, menudstw, menudsth;
    private int menusrcx,menusrcy,menusrcw,menusrch;

    private int backsrcx,backsrcy,backsrch,backsrcw;
    private int ballsrcx,ballsrcy,ballsrch,ballsrcw;

    private int gameoversrcx, gameoversrcy, gameoversrcw, gameoversrch;
    private int  gameoverdstx, gameoverdsty, gameoverdstw, gameoverdsth;

    private int pausedsrcx, pausedsrcy, pausedsrcw, pausedsrch;
    private int pauseddstx, pauseddsty, pauseddstw, pauseddsth;

    private int bombsrcx, bombsrcy, bombsrcw, bombsrch;
    private int bombdstx, bombdsty, bombdstw, bombdsth;

    private int touchdownx, touchdowny;
    private int puanlar[] = {65,66,67,68,69,70};

    private int sira;
    private Rect r1,r2;
    private int sayac;
    private int indis;



    private boolean gecti, menum, resume, sayacinYazdiri,bamm, wrong, gameovercontrol, basla;
    private Paint paint, paint2, paint3;

    private MediaPlayer clicktrue, clickfalse;

    Functions func;


    Random rand ;

    public GameCanvas(NgApp ngApp) {
        super(ngApp);
    }

    public void BackandBallSetup(){
        //Arkaplan değişkenleri.
        background = Utils.loadImage(root,"back.PNG");
        ball = Utils.loadImage(root,"Denem.png");
        menu = Utils.loadImage(root, "Pauseicon.png");
        healt = Utils.loadImage(root, "heart.png");
        gameover = Utils.loadImage(root, "gameover.png");
        paused = Utils.loadImage(root, "Paused.png");
        bomb = Utils.loadImage(root,"Explosion.png");
        bar = Utils.loadImage(root , "Bar.png");

        clicktrue = MediaPlayer.create(root.activity , R.raw.game_sound_true);
        clickfalse = MediaPlayer.create(root.activity , R.raw.game_false);

        rand = new Random();

        barsource = new Rect();
        bardestination = new Rect();

        bombsource = new Rect();
        bombdestination = new Rect();

        backgroundsource = new Rect();
        backgrounddestination = new Rect();

        ballsource = new Rect();
        balldestination = new Rect();

        pausedsource = new Rect();
        pauseddestination = new Rect();

        menusource = new Rect();
        menudestination = new Rect();

        healtsource =new Rect();
        healtdestination = new Rect();

        gameoversource = new  Rect ();
        gameoverdestination = new Rect ();

        Functions.width = getWidth();
        Functions.height = getHeight();

        backsrcx = 0;
        backsrcy = 0;
        backsrch = background.getHeight();
        backsrcw = background.getWidth();

        backdstx = 0;
        backdsty = 0;
        backdsth = getHeight();
        backdstw = getWidth();

        ballsrcx = 0;
        ballsrcy = 0;
        ballsrch = 140;
        ballsrcw = 140;

        balldstx = 0;
        balldsty = 0;
        Functions.balldsth = 100;
        Functions.balldstw = 100;

        menusrcx = 0;
        menusrcy = 0;
        menusrcw = menu.getWidth();
        menusrch = menu.getHeight();

        menudstx = getWidth() - 80;
        menudsty = 10;
        menudstw = 70;
        menudsth = 70;

        healtsrcx = 0;
        healtsrcy = 0;
        healtsrcw = healt.getWidth();
        healtsrch = healt.getHeight();

        healtdstx = getWidth() / 2 - 96;
        healtdsty = 0;
        healtdstw = 64;
        healtdsth = 64;

        gameoversrcx = 0;
        gameoversrcy = 0;
        gameoversrcw = gameover.getWidth();
        gameoversrch = gameover.getHeight();

        gameoverdstx = (getWidth() - gameover.getWidth()) / 2;
        gameoverdsty = (getHeight()  - gameover.getHeight()) / 2;
        gameoverdstw = gameover.getWidth();
        gameoverdsth = gameover.getHeight();

        pausedsrcx = 0;
        pausedsrcy = 0;
        pausedsrcw = paused.getWidth();
        pausedsrch = paused.getHeight();

        pauseddstx = (getWidth() - paused.getWidth()) / 2;
        pauseddsty = (getHeight() - paused.getHeight()) / 2;
        pauseddstw = paused.getWidth() ;
        pauseddsth = paused.getHeight();

        rand = new Random();

        paint = new Paint();
        paint.setTextSize(64);
        paint.setColor(Color.WHITE);

        paint2 = new Paint();
        paint2.setTextSize(35);
        paint2.setColor(Color.WHITE);

        paint3 = new Paint();
        paint3.setTextSize(60);
        paint3.setColor(Color.RED);

        sira = 0;
        sayac = 3;
        sayacinYazdiri = false;
        gameovercontrol = false;

        func = new Functions();
        if (!Functions.rest){
            func.setSeviye(Functions.levelSay);
            Functions.levelSay++;
        }else{
            func.setSeviye(Functions.levelSay);
            Functions.rest = false;
        }

        func.Times(func.level[Functions.levelSay][2]);


    } // arka plan ve toplar

    public void setup() {
        Log.i(TAG, "setup");
        BackandBallSetup();
        func.secilecekToplar();
    }

    public void update() {
        // Top sırasını ve gösterilen topları kontrol edecekler.
        if (sira == Functions.ballcoordinat.length) {
            for (int i = 0; i < Functions.ballClick.length; i++) {
                if (i == 0) {
                    if (Functions.ballClick[i]) {
                        gecti = true;
                    }
                    if (gecti) {
                        GameCanvas mc = new GameCanvas(root);
                        root.canvasManager.setCurrentCanvas(mc);
                        gecti = false;
                    }
                }
            }
        }

    }

    public void draw(Canvas canvas) {
        Log.i(TAG, "draw");

        backgroundsource.set(backsrcx, backsrcy, backsrcw, backsrch);
        backgrounddestination.set(backdstx, backdsty, backdstw, backdsth);
        canvas.drawBitmap(background, backgroundsource, backgrounddestination,null);

        menusource.set(menusrcx, menusrcy, menusrcw, menusrch);
        menudestination.set(menudstx, menudsty, menudstw + menudstx, menudsth + menudsty);
        canvas.drawBitmap(menu, menusource, menudestination, null);

            balldsty = 136;
            balldstx = getWidth() - Functions.balldstw;

            barsource.set(0,0,bar.getWidth(),bar.getHeight());
            bardestination.set(getWidth() - 100 , 90, getWidth() , getHeight() - 36  );
            canvas.drawBitmap(bar, barsource, bardestination, null);

            if (!menum){
                for (int k = 0; k<Functions.ballcoordinat.length; k++) {
                    ballsource.set(Functions.ballcoordinat[k][0], Functions.ballcoordinat[k][1], ballsrcw + Functions.ballcoordinat[k][0], ballsrch + Functions.ballcoordinat[k][1]);
                    balldestination.set(balldstx, balldsty, (Functions.balldstw + balldstx), (Functions.balldsth + balldsty));
                    canvas.drawBitmap(ball, ballsource, balldestination, null);

                    balldsty += (getHeight() / Functions.ballcoordinat.length) - 20;

                    Functions.ballcoordinat[k][2] = balldstx;
                    Functions.ballcoordinat[k][3] = balldsty;
                }
            }


            for (int i = 0; i<Functions.ballcoordinat.length; i++){
                ballsource.set(Functions.ballcoordinat[i][0], Functions.ballcoordinat[i][1], ballsrcw + Functions.ballcoordinat[i][0], ballsrch + Functions.ballcoordinat[i][1]);

                if (!Functions.ballClick[i]){
                    if (wrong && i == indis){
                        for (int k = 0; k<4; k++){
                            int xi = func.xtitre(k,Functions.ballcoordinat[i][4]);
                            int yi = func.xtitre(k,Functions.ballcoordinat[i][5]);
                            balldestination.set(xi , yi, Functions.balldstw + xi , Functions.balldsth + yi );
                            canvas.drawBitmap(ball, ballsource, balldestination, paint);

                        }
                        wrong = false;
                    }else{
                        balldestination.set(Functions.ballcoordinat[i][4], Functions.ballcoordinat[i][5], Functions.balldstw + Functions.ballcoordinat[i][4] , Functions.balldsth + Functions.ballcoordinat[i][5] );
                        canvas.drawBitmap(ball, ballsource, balldestination, paint);
                    }

                }
            }

            balldsty = 0;
            balldstx = 0;

            for (int i=1; i<=sayac; i++){
                healtsource.set(healtsrcx, healtsrcy, healtsrcw, healtsrch);
                healtdestination.set(healtdstx, healtdsty, healtdstw + healtdstx, healtdsth + healtdsty);
                canvas.drawBitmap(healt,healtsource,healtdestination,null);
                healtdstx += 64;
            }

            healtdstx = getWidth() /2 - 96;

        if (sayacinYazdiri || Functions.timeend){
            gameoversource.set(gameoversrcx, gameoversrcy, gameoversrcw, gameoversrch);
            gameoverdestination.set(gameoverdstx, gameoverdsty, gameoverdstw + gameoverdstx, gameoverdsth + gameoverdsty);
            canvas.drawBitmap(gameover,gameoversource,gameoverdestination,null);
            canvas.drawText("" + Utils.skor ,((getWidth()- gameoversrcw)/ 2 ) + 255 , ((getHeight()- gameoversrch)/ 2 ) + 148 , paint2 );
            canvas.drawText("" +Functions.levelSay ,((getWidth()- gameoversrcw)/ 2 ) + 255 , ((getHeight()- gameoversrch)/ 2 ) + 230 , paint2 );
            gameovercontrol = true;
        }

        if (menum && !resume && !Functions.timeend){
            pausedsource.set(pausedsrcx, pausedsrcy, pausedsrcw, pausedsrch);
            pauseddestination.set(pauseddstx, pauseddsty, pauseddstw + pauseddstx, pauseddsth + pauseddsty);
            canvas.drawBitmap(paused, pausedsource, pauseddestination,null);
        }

        if (bamm){
            for (int i = 0; i<= bomb.getWidth(); i+=140){
                bombsource.set(i, 0, 140, 140);
                canvas.drawBitmap(bomb, bombsource, bombdestination, null);
            }
            bamm = false;
        }
            canvas.drawText("" + Utils.skor ,64 , 64, paint );

        if (!Functions.timeend)
        {
            canvas.drawText("" + (Functions.oyunzaman ), getWidth() - 180 ,60, paint3);
        }else {
            canvas.drawText("" + (Functions.oyunzaman ), getWidth() - 180,60,paint3);
        }
    }



    public void keyPressed(int key) {

    }

    public void keyReleased(int key) {

    }

    public boolean backPressed() {
        return true;
    }

    public void surfaceChanged(int width, int height) {

    }

    public void surfaceCreated() {

    }

    public void surfaceDestroyed() {

    }

    public void touchDown(int x, int y, int id) {

        if(!sayacinYazdiri )
        {
            touchdownx = x;
            touchdowny = y;

            if (!menum  && !Functions.timeend){
                siraKontrol(x, y);
                canKontrol(x, y);
            }
        }

        if (x >= menudstx && x <= menudstx + menudstw && y >= menudsty && y <= menudsty + menudsth && !gameovercontrol) {
            menum = true;
            func.MyTimer.cancel();
        }

        if (menum){
            if (x >= pauseddstx + 61 && x <= (pauseddstx + 61) + 70 && y >= pauseddsty + 75 && y <= (pauseddsty + 75) + 70  ){
              menum = false;
              func.Times(Functions.oyunzaman);

            }
            if (x >= pauseddstx + 161 && x <= (pauseddstx + 161) + 70 && y >= pauseddsty + 75 && y <= (pauseddsty + 75) + 70 ){
                    Functions.rest = true;
                    GameCanvas mc = new GameCanvas(root);
                    root.canvasManager.setCurrentCanvas(mc);
                    Utils.skor = 0;
                    Functions.levelSay = 0;
            }
            if (x >= pauseddstx + 267 && x <= (pauseddstx + 267) + 74 && y >= pauseddsty + 74 && y <= (pauseddsty + 74) + 70 ){
                    MenuCanvas dc = new MenuCanvas(root);
                    root.canvasManager.setCurrentCanvas(dc);
                    Utils.skor = 0;
                    Functions.levelSay = 0;
                    Functions.playgamesound.stop();
            }
        }


        if (sayacinYazdiri || Functions.timeend){
            if (x >= gameoverdstx + 115 && x <= (gameoverdstx + 115 + 89 ) && y >= gameoverdsty + 261 && y <= (gameoverdsty + 261 + 93) ){
                GameCanvas mc = new GameCanvas(root);
                root.canvasManager.setCurrentCanvas(mc);
                Utils.skor = 0;
                Functions.levelSay = 0;
                Functions.timeend = false;
            }
            if (x >= gameoverdstx + 282 && x <= (gameoverdstx + 282) + 92 && y >= gameoverdsty + 261 && y <= (gameoverdsty + 261) + 93 ){
                MenuCanvas dc = new MenuCanvas(root);
                root.canvasManager.setCurrentCanvas(dc);
                Functions.levelSay = 0;
                Utils.skor = 0;
                Functions.playgamesound.stop();
            }
        }
    }

    public void touchMove(int x, int y, int id) {
    }

    public void touchUp(int x, int y, int id) {
    }

    public void pause() {
    }

    public void resume() {

    }

    public void reloadTextures() {

    }

    public void showNotify() {
    }

    public void hideNotify() {
    }

    /**
     * Tüm topların doğru seçilip seçilmediğini kontrol eden fonksiyon.
     * @param x Tıklanan topun x koordinatı
     * @param y Tıklanan topun y koordinatı
     */
    private void siraKontrol(int x, int y){

        r1 = new Rect();
        r2 = new Rect();

        r1.set(x, y, x, y );
        r2.set(Functions.ballcoordinat[sira][4], Functions.ballcoordinat[sira][5], Functions.ballcoordinat[sira][4] + Functions.balldstw, Functions.ballcoordinat[sira][5] + Functions.balldsth );

        if (r1.intersect(r2))
        {
            Functions.ballClick[sira] = true;
            clicktrue.start();
            bombdestination.set(Functions.ballcoordinat[sira][4], Functions.ballcoordinat[sira][5], Functions.ballcoordinat[sira][4] + Functions.balldstw, Functions.ballcoordinat[sira][5] + Functions.balldsth);
            bamm = true;
            sira++;
            Utils.skor += puanlar[rand.nextInt(puanlar.length)];

        }

    }

    /**
     * Toplam can sayısını kontrol eden fonksiyon.
     * @param x
     * @param y
     */
    private void canKontrol (int x,int y){

        r1.set(x, y, x, y);

        for (int  i=0; i<Functions.ballcoordinat.length; i++){

            r2.set(Functions.ballcoordinat[i][4], Functions.ballcoordinat[i][5], Functions.ballcoordinat[i][4] + Functions.balldstw, Functions.ballcoordinat[i][5] + Functions.balldsth );

            if(!Functions.ballClick[i] && r1.intersect(r2) ){

                sayac--;
                wrong = true;
                indis = topBulma(touchdownx, touchdowny);
                clickfalse.start();

                if (sayac == 0){
                    sayacinYazdiri =true;
                }
            }

        }
    }

    public int topBulma(int x ,int y){
        r1 = new Rect();
        r2 = new Rect();
        r1.set(x, y, x , y );
        for (int i = 0; i<Functions.ballcoordinat.length; i++){
            r2.set(Functions.ballcoordinat[i][4], Functions.ballcoordinat[i][5], Functions.balldstw + Functions.ballcoordinat[i][4], Functions.balldsth + Functions.ballcoordinat[i][5]);
            if (r1.intersect(r2)){
                android.util.Log.i("mesaj", "topBulma: " + i);
                return i;
            }
        }
        return 0;
    }

}

