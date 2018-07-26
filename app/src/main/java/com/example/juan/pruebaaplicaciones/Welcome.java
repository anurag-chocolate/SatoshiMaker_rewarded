package com.example.juan.pruebaaplicaciones;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings.Secure;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.vdopia.ads.lw.Chocolate;
import com.vdopia.ads.lw.InitCallback;
import com.vdopia.ads.lw.LVDOAdRequest;
import com.vdopia.ads.lw.LVDOConstants;
import com.vdopia.ads.lw.LVDOInterstitialAd;
import com.vdopia.ads.lw.LVDOInterstitialListener;
import com.vdopia.ads.lw.LVDORewardedAd;
import com.vdopia.ads.lw.RewardedAdListener;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
//import com.vungle.mediation.BuildConfig;

public class Welcome extends AppCompatActivity implements RewardedAdListener, LVDOInterstitialListener {
    private static final String AD_UNIT_ID = "ca-app-pub-1631324640043952/1002848629";
    private static final String AD_UNIT_ID_Interestacial = "ca-app-pub-1631324640043952/6376998227";
    private static final String APP_ID = "ca-app-pub-1631324640043952~8970537826";
    private static final String API_KEY = "wxvs4o";
    static String ConsultUA = null;
    private static final String FORMAT = "%02d:%02d";
    static String struser;
    int AjusteNotificacionSTR;
    String AndroidIDSTR;
    String Anuncio;
    String BTCAddressCoinbaseSTR;
    String BTCAddressSTR;
    String BTCAddressXapoSTR;
    String BitCoinsSTR;
    String Coin2;
    int ConfigRewarded;
    int ConsultCI;
    int ConsultCV;
    int ConsultCount;
    String ConsultMultiplicador;
    String ConsultDC;
    String ConsultMR;
    int ConsultMantenimiento;
    String ConsultSR;
    static String ConsultSRef;
    String ConsultTT;
    int ConsultVC;
    String ConsultVS;
    String Consultnotificacion;
    int CountConseguir = 1;
    int CountRetirar = 1;
    String EmailProcesador;
    int ErrorCarga = 0;
    String EstadoSTR;
    String FechaInicioSTR;
    String FechaTerminaSTR;
    String IDSTR;
    long NumRestante;
    String Procesador;
    String ReferidoSTR;
    private RewardedVideoAd RewardedVideoAdConseguir;
    String SatoshisTotalSTR;
    String Satoshistotal;
    String UsernameSTR;
    int bit;
    int bit2;
    Spinner comboprocesador;
    String comboprocesadorstring;
    Button conseguirbitbutton;
    Dialog dialog;
    Dialog dialogNotificacion;
    Dialog dialogcaptcha;
    Dialog dialogversion;
    Dialog dialogwithdraw;
    String editbtctxt;
    String idandroid;
    InterstitialAd interstitialbit;
    private boolean mIsRewardedVideoLoading;
    private final Object mLock = new Object();
    String milliscronometro;
    long myNum;
    int numeroalea;
    private ProgressDialog pDialogconsultardatos;
    private ProgressDialog pDialogconsultardatos2;
    private ProgressDialog pDialogconsultarusuario;
    private ProgressDialog pDialoginsertardatos;
    Button probarboton;
    Button probarboton2;
    Button reclamarbutton;
    TextView textView4;
    TextView textView8;
    String token;
    int versionCode;
    String versionName;
    String answer;

    private LVDORewardedAd mRewardedAd;
    private LVDOInterstitialAd mInterstitialAd;
    private LVDOAdRequest mAdRequest;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_welcome);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        idandroid = Secure.getString(getBaseContext().getContentResolver(), "android_id");
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);

        if (false == Chocolate.isInitialized(this)) {
            Chocolate.init(this, API_KEY, new InitCallback() {
                @Override
                public void onSuccess() {

                    LVDOAdRequest adRequest = new LVDOAdRequest(Welcome.this);
                    LVDORewardedAd.prefetch(Welcome.this, API_KEY, adRequest);
                    LVDOInterstitialAd.prefetch(Welcome.this,API_KEY, adRequest);
                    /*
                    If you like, here is a good time to make an ad request OR make a pre-fetch request.
                    But, do NOT do both at same time; too resource-intensive.

                    //Depending on your needs, pre-fetch one of the ad unit types.
                    //LVDOInterstitialAd.prefetch(MainActivity.this, API_KEY, adRequest);
                    //LVDORewardedAd.prefetch(MainActivity.this, API_KEY, adRequest);
                    //LVDOBannerAd.prefetch(MainActivity.this, LVDOAdSize.INVIEW_LEADERBOARD, API_KEY, adRequest);

                    //OR make a normal ad request.  For example:
                    //rewardedAd.loadAd(adRequest);
                    */

                }

                @Override
                public void onError(String message) {
                    //It's ok.  Our mediation sdk will still work!  Network must be down.
                }
            });
        } else {
            //Chocolate is already initialized.  Here is also a good opportunity to make ad request or pre-fetch.
        }


        struser = "Admin";
        MobileAds.initialize(this, APP_ID);
        ((TextView) findViewById(R.id.txtuser)).setText(struser);
        textView4 = (TextView) findViewById(R.id.textView4);
        textView8 = (TextView) findViewById(R.id.textView8);
        conseguirbitbutton = (Button) findViewById(R.id.ConseguirBit);
        reclamarbutton = (Button) findViewById(R.id.ReclaimBit);
        ((AdView) findViewById(R.id.adViewWelcome)).loadAd(new AdRequest.Builder().build());

        conseguirbitbutton.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {

                loadRewardedVideoAd();
            }
        });
        reclamarbutton.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                System.out.println("click boton reclamar bitcoins");
                /*if (!Integer.toString(numeroalea).equals(((TextView) findViewById(R.id.editcaptcha)).getText().toString()))
                {
                    Toast.makeText(Welcome.this, "Captcha error!!", Toast.LENGTH_SHORT).show();
                    numeroalea = ((int) (Math.random() * 999999)) + 1;
                    ((TextView) findViewById(R.id.textcaptcha)).setText(Integer.toString(numeroalea));
                }
                else*/
                if (mRewardedAd != null && mRewardedAd.isReady())
                {
                    try
                    {
                        ConfigRewarded = 1;
                        mRewardedAd.showRewardAd("JK69GBeXDtYOldrZ", "Chocolate1", "coin", "30");
                    }
                    catch(com.vdopia.ads.lw.ChocolateAdException e)
                    {
                        Log.e("SouravTest RewardedAd", "show failed ",e);
                    }
                }
                else if (mInterstitialAd!= null && mInterstitialAd.isReady())
                {
                    try
                    {
                        ConfigRewarded = 1;
                        mInterstitialAd.show();
                    }
                    catch(com.vdopia.ads.lw.ChocolateAdException e2)
                    {
                        Log.e("SouravTest Interstitial", "show failed ",e2);
                    }
                }
            }
        });
    }



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_history:
                    startActivity(new Intent(Welcome.this, History.class));
                    return true;
                case R.id.navigation_top:
                    startActivity(new Intent(Welcome.this, Top.class));
                    return true;
                case R.id.navigation_message:
                    Intent emailIntent = new Intent("android.intent.action.SEND");
                    emailIntent.putExtra("android.intent.extra.EMAIL", new String[]{"badwolfcorporation2016@gmail.com"});
                    emailIntent.putExtra("android.intent.extra.SUBJECT", getString(R.string.soporteemail) + " SatoshiMaker");
                    emailIntent.putExtra("android.intent.extra.TEXT", "\n \n \n Sent from App SatoshiMaker");
                    emailIntent.setType("message/rfc822");
                    startActivity(Intent.createChooser(emailIntent, getString(R.string.Emailsupport)));
                    return true;
            }
            return false;
        }
    };

    //Video remunerado
    @Override
    public void onRewardedVideoLoaded(LVDORewardedAd lvdoRewardedAd) {
        Log.i("video", "onRewardedVideoAdLoaded");
        textView8.setText(getString(R.string.videouploaded));

    }

    @Override
    public void onRewardedVideoFailed(LVDORewardedAd lvdoRewardedAd, LVDOConstants.LVDOErrorCode lvdoErrorCode) {
        Log.i("video", "onRewardedVideoAdFailedToLoad");
        requestNewInterstitial();
    }

    @Override
    public void onRewardedVideoShown(LVDORewardedAd lvdoRewardedAd) {
        Log.i("video", "onRewardedVideoAdOpened");
    }

    @Override
    public void onRewardedVideoShownError(LVDORewardedAd lvdoRewardedAd, LVDOConstants.LVDOErrorCode lvdoErrorCode) {
        Log.i("video", "onRewardedVideoShownError");
    }

    @Override
    public void onRewardedVideoDismissed(LVDORewardedAd lvdoRewardedAd) {
        Log.i("video", "onRewardedVideoDismissed");
    }

    @Override
    public void onRewardedVideoCompleted(LVDORewardedAd lvdoRewardedAd) {
        Log.i("video", "onRewardedVideoAdClosed");
        Anuncio = "Video Remunarado";
        ConsultCount = ConsultCV;

    }

    //Intersticial
    @Override
    public void onInterstitialLoaded(LVDOInterstitialAd lvdoInterstitialAd) {
        textView8.setText(getString(R.string.videouploaded));
        ErrorCarga = 1;

        Log.i("Interstitial", "onInterstitialLoaded");
    }

    @Override
    public void onInterstitialFailed(LVDOInterstitialAd lvdoInterstitialAd, LVDOConstants.LVDOErrorCode lvdoErrorCode) {
        textView8.setText(Welcome.this.getString(R.string.errorvideo) + " " + lvdoErrorCode);
        loadRewardedVideoAd();
        Log.i("Interstitial", "onInterstitialFailed");
    }

    @Override
    public void onInterstitialShown(LVDOInterstitialAd lvdoInterstitialAd) {
        Log.i("Interstitial", "onInterstitialShown");
        Anuncio = "Interstitial";
        ConsultCount = ConsultCI;

    }

    @Override
    public void onInterstitialClicked(LVDOInterstitialAd lvdoInterstitialAd) {
        Log.i("Interstitial", "onInterstitialClicked");

    }

    @Override
    public void onInterstitialDismissed(LVDOInterstitialAd lvdoInterstitialAd) {
        Log.i("Interstitial", "onInterstitialDismissed");

    }

    public static class BottomNavigationViewHelper {
        @SuppressLint("RestrictedApi")
        public static void disableShiftMode(BottomNavigationView view) {
            BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
            try {
                Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
                shiftingMode.setAccessible(true);
                shiftingMode.setBoolean(menuView, false);
                shiftingMode.setAccessible(false);
                for (int i = 0; i < menuView.getChildCount(); i++) {
                    BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                    //noinspection RestrictedApi
                    item.setShiftingMode(false);
                    // set once again checked value, so view will be updated
                    //noinspection RestrictedApi
                    item.setChecked(item.getItemData().isChecked());
                }
            } catch (NoSuchFieldException e) {
                Log.e("BNVHelper", "Unable to get shift mode field", e);
            } catch (IllegalAccessException e) {
                Log.e("BNVHelper", "Unable to change value of shift mode", e);
            }
        }
    }


    private void requestNewInterstitial() {
        mInterstitialAd = new LVDOInterstitialAd(this, API_KEY, this);
        LVDOAdRequest adRequest = new LVDOAdRequest(this);
        adRequest.setAppStoreUrl("play.google.com/store/apps/details?id=com.badwolf.satoshimakerV2&hl=es");
        adRequest.setRequester("Satoshi Maker - Free Bitcoin");
        adRequest.setAppDomain("BlogEntretenido.com");
        adRequest.setAppName("Satoshi Maker - Free Bitcoin");
        adRequest.setCategory("IAB1");
        adRequest.setPublisherDomain("BlogEntretenido.com");
        mInterstitialAd.loadAd(adRequest);

        textView8.setText(getString(R.string.loadingvideo));
        Log.i("anuncio", "interestacial cargando");
    }
    private void loadRewardedVideoAd() {
        mRewardedAd = new LVDORewardedAd(this, API_KEY, this);
        LVDOAdRequest adRequest = new LVDOAdRequest(this);
        adRequest.setAppStoreUrl("play.google.com/store/apps/details?id=com.badwolf.satoshimakerV2&hl=es");
        adRequest.setRequester("Satoshi Maker - Free Bitcoin");
        adRequest.setAppDomain("BlogEntretenido.com");
        adRequest.setAppName("Satoshi Maker - Free Bitcoin");
        adRequest.setCategory("IAB1");
        adRequest.setPublisherDomain("BlogEntretenido.com");
        mRewardedAd.loadAd(adRequest);

        textView8.setText(getString(R.string.loadingvideo));
        Log.i("anuncio", "remunerado cargando");
    }


}
