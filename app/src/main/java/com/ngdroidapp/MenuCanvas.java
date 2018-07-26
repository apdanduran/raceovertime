package com.ngdroidapp;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.media.MediaPlayer;

import com.mycompany.myngdroidapp.R;

import istanbul.gamelab.ngdroid.base.BaseCanvas;
import istanbul.gamelab.ngdroid.util.Log;
import istanbul.gamelab.ngdroid.util.Utils;

/**
 * Created by noyan on 27.06.2016.
 * Nitra Games Ltd.
 */

public class MenuCanvas extends BaseCanvas {

    private Bitmap buttonPlay, background, settingsButton,settinginButton[];
    private Rect butonPlaysource, butonPlaydestination, backgroundsource, backgrounddestination, settingssource, settingdestination, settinginsource, settingindestination, bansource, bandestination;

    private int playsrcx, playsrcy, playsrcw, playsrch, backsrcx, backsrcy, backsrcw, backsrch, settingsrcx, settingsrcy, settingsrcw, settingsrch, settinginsrcx, settinginsrcy, settinginsrcw, settinginsrch, bansrcx, bansrcy, bansrcw, bansrch;
    private int playdstx, playdsty, playdstw, playdsth, backdstx, backdsty, backdstw, backdsth, settingdstx, settingdsty, settingdstw, settingdsth, settingindstx, settingindsty, settingindstw, settingindsth, bandstx, bandsty, bandstw, bandsth;



    private int settingCoordinat[][], settinginCoordinat[];

    private int touchx,touchy;
    private static boolean playon,settingon, settingon1, musicon;

    public MenuCanvas(NgApp ngApp) {
        super(ngApp);
    }

    public void setup() {
        setupLogin();
    }

    public void update() {
    }

    public void draw(Canvas canvas) {
        // ARKA PLAN EKRANA ÇİZDİRİLİYOR.
        backgroundsource.set(backsrcx, backsrcy, backsrcw, backsrch);
        backgrounddestination.set(backdstx, backdsty, backdstw, backdsth);
        canvas.drawBitmap(background, backgroundsource, backgrounddestination,null);

        //PLAY BUTONU EKRANA ÇİZDİRİLİYOR.
        butonPlaysource.set(playsrcx,playsrcy,playsrcw,playsrch);
        butonPlaydestination.set(playdstx,playdsty,playdstw + playdstx,playdsth + playdsty);
        canvas.drawBitmap(buttonPlay,butonPlaysource,butonPlaydestination,null);


        //SETTİNGS BUTONU EKRANA ÇİZDİRİLİYOR.
        if (!settingon){
            butonKonum(settingCoordinat[3][0],settingCoordinat[3][1]);
            settingssource.set(settingsrcx, settingsrcy, settingsrcw, settingsrch);
            settingdestination.set(settingdstx, settingdsty, settingdstw + settingdstx, settingdsth + settingdsty);
            canvas.drawBitmap(settingsButton, settingssource, settingdestination, null);
        }else{
            for (int i = settingCoordinat.length - 1; i > -1 ; i--)
            {
                butonKonum(settingCoordinat[i][0],settingCoordinat[i][1]);
                settingssource.set(settingsrcx, settingsrcy, settingsrcw, settingsrch);
                settingdestination.set(settingdstx, settingdsty, settingdstw + settingdstx, settingdsth + settingdsty);
                canvas.drawBitmap(settingsButton, settingssource, settingdestination, null);

                if (i == 0){ // Ayar butonu içi yerleştirme.
                    for (int l = 0; l<settinginCoordinat.length; l++){
                        ayaricKonum(settinginCoordinat[l], l);
                        settinginsource.set(settinginsrcx, settinginsrcy, settinginsrcw, settinginsrch);
                        settingindestination.set(settingindstx, settingindsty, settingindstw + settingindstx, settingindsth + settingindsty);
                        canvas.drawBitmap(settinginButton[l], settinginsource, settingindestination, null);
                    }

                    if (!Functions.soundon){
                        banKonum(settinginCoordinat[2]);
                        bansource.set(bansrcx, bansrcy, bansrcw, bansrch);
                        bandestination.set(bandstx, bandsty, bandstw, bandsth);
                        canvas.drawBitmap(settinginButton[3], bansource, bandestination, null);
                    }

                    if (!musicon){
                        banKonum(settinginCoordinat[1]);
                        bansource.set(bansrcx, bansrcy, bansrcw, bansrch);
                        bandestination.set(bandstx, bandsty, bandstw, bandsth);
                        canvas.drawBitmap(settinginButton[3], bansource, bandestination, null);
                    }
                }
            }
        }

    }

    public void setupLogin(){
        background = Utils.loadImage(root, "bg.png");
        buttonPlay = Utils.loadImage(root, "Play.png");
        settingsButton = Utils.loadImage(root, "Settings.png");
        Functions.music = MediaPlayer.create(root.activity , R.raw.game_soun_play);

        settinginButton = new Bitmap[]{
                Utils.loadImage(root, "Infoicon.png") ,
                Utils.loadImage(root, "Musicicon.png") ,
                Utils.loadImage(root, "Soundicon.png") ,
                Utils.loadImage(root, "Banicon.png")
        };

        butonPlaysource = new Rect();
        butonPlaydestination = new Rect();

        settingindestination = new Rect();
        settinginsource = new Rect();

        backgroundsource = new Rect();
        backgrounddestination = new Rect();

        settingssource = new Rect();
        settingdestination = new Rect();

        bansource = new Rect();
        bandestination = new Rect();

        playsrcx = 0;
        playsrcy = 0;
        playsrcw = buttonPlay.getWidth();
        playsrch = buttonPlay.getHeight();

        playdstx = (getWidth() / 2) - 192;
        playdsty = (getHeight() / 2) + 256;
        playdstw = 384;
        playdsth = 128;

        backsrcx = 0;
        backsrcy = 0;
        backsrcw = background.getWidth();
        backsrch = background.getHeight();

        backdstx = 0;
        backdsty = 0;
        backdstw = getWidth();
        backdsth = getHeight();

        settingCoordinat = new int[][]{
                {0,280},
                {280,210},
                {490,140},
                {630,70}
        };

        settinginCoordinat = new int[]{120,180,240};

        settingon = false;
        settingon1 = false;
        musicon = true;

        if (Functions.soundon)
            Functions.music.start();

    } // Nesne tanımlayan fonksiyon.

    public void butonKonum(int srcx, int x){
        settingsrcx = srcx;
        settingsrcy = 0;
        settingsrcw = settingsrcx + x;
        settingsrch = settingsButton.getHeight();

        settingdstx = getWidth() - x ;
        settingdsty = 0;
        settingdstw = x ;
        settingdsth = 70;
    } // ayar butonu on-off kordinatlarını ayarlayan fonksiyon.

    public void ayaricKonum( int x, int indis){
        settinginsrcx = 0;
        settinginsrcy = 0 ;
        settinginsrcw = settinginButton[indis].getWidth() ;
        settinginsrch = settinginButton[indis].getHeight();

        settingindstx = getWidth() - x;
        settingindsty = 17;
        settingindstw = 40;
        settingindsth = 40;
    }

    public void banKonum(int x){
        bansrcx = 0;
        bansrcy = 0;
        bansrcw = settinginButton[3].getWidth();
        bansrch = settinginButton[3].getWidth();

        bandstx = getWidth() - x;
        bandsty = 17;
        bandstw = bandstx + 40;
        bandsth = bandsty + 40;
    }

    public void keyPressed(int key) {

    }

    public void keyReleased(int key) {
    }

    public boolean backPressed() {
        return true;
    }

    public void touchDown(int x, int y, int id) {
        touchx = x;
        touchy = y;
        if (x >= playdstx  && x <= playdstx + playdstw && y >= playdsty && y <= playdsth + playdsty){
            GameCanvas mc = new GameCanvas(root);
            root.canvasManager.setCurrentCanvas(mc);
            Functions.music.stop();

            if(Functions.soundgame){

                Functions.playgamesound = MediaPlayer.create(root.activity , R.raw.playgame);

                Functions.playgamesound.start();

            }else{
                Functions.playgamesound.stop();
            }


        }else if (x >= getWidth() - 70 && x <= getWidth() && y >= 0 && y <= 70 && !settingon1)
        {
            settingon = true;
            settingon1 = true;
        }else if (x >= getWidth() - 70 && x <= getWidth() && y >= 0 && y <= 70 && settingon1){
            settingon = false;
            settingon1 = false;
        }else if (x >= getWidth() - settinginCoordinat[2] && x <= (getWidth() - settinginCoordinat[2]) + 40 && y >= 17 && y <= 57 && settingon1 && Functions.soundon ){
            Functions.soundon =false;
            Functions.music.stop();
            Functions.soundgame = false;
        }else if (x >= getWidth() - settinginCoordinat[2] && x <= (getWidth() - settinginCoordinat[2]) + 40 && y >= 17 && y <= 57 && settingon1 && !Functions.soundon ){
            Functions.soundon = true;
            Functions.music = MediaPlayer.create(root.activity , R.raw.game_soun_play);
            Functions.music.start();
            Functions.soundgame = true;
        }
        else if (x >= getWidth() - settinginCoordinat[1] && x <= (getWidth() - settinginCoordinat[1]) + 40 && y >= 17 && y <= 57 && settingon1 && musicon ){
            musicon =false;
        }else if (x >= getWidth() - settinginCoordinat[1] && x <= (getWidth() - settinginCoordinat[1]) + 40 && y >= 22 && y <= 57 && settingon1 && !musicon ){
            musicon = true;
        }

    }

    public void touchMove(int x, int y, int id) {
    }

    public void touchUp(int x, int y, int id) {
    }

    public void surfaceChanged(int width, int height) {
    }

    public void surfaceCreated() {
    }

    public void surfaceDestroyed() {
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

}
