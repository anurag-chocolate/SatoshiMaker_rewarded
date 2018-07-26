package com.example.juan.pruebaaplicaciones;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.github.snowdream.android.widget.SmartImageView;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

//import com.vungle.mediation.BuildConfig;

public class History extends AppCompatActivity {
    ArrayList BTCRetirado = new ArrayList();
    ArrayList Descripcion = new ArrayList();
    ArrayList Descripcion2 = new ArrayList();
    ArrayList Estado = new ArrayList();
    ArrayList Estado2 = new ArrayList();
    ArrayList Fecha = new ArrayList();
    ArrayList FechaEnviado = new ArrayList();
    ArrayList FechaEnviado2 = new ArrayList();
    ArrayList FechaRetirada = new ArrayList();
    ArrayList Satoshi = new ArrayList();
    ArrayList Username = new ArrayList();
    private Bitmap bmp;
    private Bitmap bmp2;
    Dialog dialog;
    Dialog dialog2;
    private ListView listview;
    private ListView listview2;
    private ProgressDialog pDialogconsultardatos;
    private ProgressDialog pDialogconsultardatos2;
    String sSubCadena;
    String sSubCadena2;
    String sSubCadena3;
    TabHost th;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        th = (TabHost) findViewById(R.id.tabHostHistory);
        th.setup();
        TabSpec ts1 = th.newTabSpec("Tab1");
        ts1.setIndicator(getString(R.string.History) + " " + Welcome.struser);
        ts1.setContent(R.id.tab1);
        th.addTab(ts1);
        th.setup();
        TabSpec ts2 = th.newTabSpec("Tab2");
        ts2.setIndicator(getString(R.string.HistoryTotal));
        ts2.setContent(R.id.tab2);
        th.addTab(ts2);
        ((AdView) findViewById(R.id.adViewHistory)).loadAd(new Builder().build());
        listview = (ListView) findViewById(R.id.listView1);
        listview2 = (ListView) findViewById(R.id.listView2);
        ClassInt cHTTP = new ClassInt(History.this);
        cHTTP.execute(getString(R.string.blog) + getString(R.string.progratool) + getString(R.string.superbit) + getString(R.string.consultar), Welcome.struser);
        cHTTP.setOnTaskFinishedEvent(new ClassInt.OnTaskExecutionFinished() {
            @Override
            public void OnTaskFihishedEvent(String result) {
                class ImageAdapter extends BaseAdapter {
                    Context ctx;
                    TextView descripcion;
                    TextView fecha;
                    TextView fecha2;
                    LayoutInflater layoutinflater;
                    SmartImageView smartimageview;
                    TextView titulo;

                    public ImageAdapter(Context applicationContext) {
                        ctx = applicationContext;
                        layoutinflater = ((LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
                    }

                    public int getCount() {
                        return Estado.size();
                    }

                    public Object getItem(int position) {
                        return position;
                    }

                    public long getItemId(int position) {
                        return position;
                    }

                    public View getView(int position, View convertView, ViewGroup parent) {
                        ViewGroup viewgroup = (ViewGroup)layoutinflater.inflate(R.layout.activity_main_item, null);
                        smartimageview = (SmartImageView) viewgroup.findViewById(R.id.imagen1);
                        titulo = (TextView) viewgroup.findViewById(R.id.titulo);
                        descripcion = (TextView) viewgroup.findViewById(R.id.descripcion);
                        fecha = (TextView) viewgroup.findViewById(R.id.date);
                        fecha2 = (TextView) viewgroup.findViewById(R.id.fecha2);
                        String url = getString(R.string.blog) + getString(R.string.progratool) + getString(R.string.superbit) + Estado.get(position).toString();
                        Rect rect=new Rect(smartimageview.getLeft(), smartimageview.getTop(), smartimageview.getRight(), smartimageview.getBottom());
                        smartimageview.setImageUrl(url, rect);
                        if (Satoshi.get(position).toString().equals(Welcome.ConsultSRef))
                        {
                            titulo.setText(getString(R.string.titulo) + " " + Satoshi.get(position).toString() + " SatoBerry");
                        }
                        else
                        {
                            titulo.setText(getString(R.string.titulo) + " " + Satoshi.get(position).toString() + " Satoshis");
                        }
                        Log.i("Error: ", "" + Satoshi.get(position).toString());
                        Log.i("Error: ", "" + Fecha.get(position).toString());
                        fecha.setText(getString(R.string.fecha) + " " + Fecha.get(position).toString());
                        fecha2.setText(FechaEnviado.get(position).toString());
                        descripcion.setText(getString(R.string.descripcion) + " " + Descripcion.get(position).toString());
                        return viewgroup;
                    }
                }
                try
                {
                    JSONArray ja = new JSONArray(result);
                    for (int i = 0; i < ja.length(); i++) {
                        Satoshi.add(ja.getJSONObject(i).getString("BTCRetirado"));
                        Fecha.add(ja.getJSONObject(i).getString("FechaRetirada"));
                        FechaEnviado.add(ja.getJSONObject(i).getString("FechaEnvio"));
                        Descripcion.add(ja.getJSONObject(i).getString("Descripcion"));
                        Estado.add(ja.getJSONObject(i).getString("Estado"));
                    }
                    listview.setAdapter(new ImageAdapter(getApplicationContext()));

                    ClassInt cHTTP = new ClassInt(History.this);
                    cHTTP.execute(getString(R.string.blog) + getString(R.string.progratool) + getString(R.string.superbit) + getString(R.string.consultartodos), "");
                    cHTTP.setOnTaskFinishedEvent(new ClassInt.OnTaskExecutionFinished() {
                        @Override
                        public void OnTaskFihishedEvent(String result) {
                            class ImageAdapter extends BaseAdapter {
                                TextView amount;
                                Context ctx;
                                TextView date;
                                TextView date2;
                                TextView descripcion;
                                LayoutInflater layoutinflater;
                                SmartImageView smartimageview2;
                                TextView user;

                                public ImageAdapter(Context applicationContext) {
                                    ctx = applicationContext;
                                    layoutinflater = ((LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
                                }

                                public int getCount() {
                                    return Estado2.size();
                                }

                                public Object getItem(int position) {
                                    return position;
                                }

                                public long getItemId(int position) {
                                    return (long) position;
                                }

                                public View getView(int position, View convertView, ViewGroup parent) {
                                    ViewGroup viewgroup = (ViewGroup) layoutinflater.inflate(R.layout.activity_main_item_total, null);
                                    smartimageview2 = (SmartImageView) viewgroup.findViewById(R.id.imagen1);
                                    user = (TextView) viewgroup.findViewById(R.id.user);
                                    amount = (TextView) viewgroup.findViewById(R.id.amount);
                                    date = (TextView) viewgroup.findViewById(R.id.fecha);
                                    date2 = (TextView) viewgroup.findViewById(R.id.fecha2);
                                    //descripcion = (TextView) viewgroup.findViewById(R.id.descripcion);
                                    String url = getString(R.string.blog) + getString(R.string.progratool) + getString(R.string.superbit) + Estado2.get(position).toString();
                                    Rect rect=new Rect(smartimageview2.getLeft(), smartimageview2.getTop(), smartimageview2.getRight(), smartimageview2.getBottom());
                                    smartimageview2.setImageUrl(url, rect);
                                    user.setText(getString(R.string.username) + " " + Username.get(position).toString());
                                    amount.setText(getString(R.string.titulo) + " " + BTCRetirado.get(position).toString() + " Satoshis");
                                    date.setText(getString(R.string.fecha) + " " + FechaRetirada.get(position).toString());
                                    Log.i("URL", "" + FechaEnviado2.get(position).toString());
                                    date2.setText(FechaEnviado2.get(position).toString());
                                    //descripcion.setText(getString(R.string.descripcion) + " " + Descripcion2.get(position).toString());
                                    return viewgroup;
                                }
                            }
                            try {
                                JSONArray ja = new JSONArray(result);
                                for (int i = 0; i < ja.length(); i++) {
                                    Username.add(ja.getJSONObject(i).getString("Username"));
                                    BTCRetirado.add(ja.getJSONObject(i).getString("BTCRetirado"));
                                    FechaRetirada.add(ja.getJSONObject(i).getString("FechaRetirada"));
                                    FechaEnviado2.add(ja.getJSONObject(i).getString("FechaEnvio"));
                                    Estado2.add(ja.getJSONObject(i).getString("Estado"));
                                    Descripcion2.add(ja.getJSONObject(i).getString("Descripcion"));
                                }
                                listview2.setAdapter(new ImageAdapter(getApplicationContext()));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }});
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }

            }});
        listview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String part2 = ((TextView) view.findViewById(R.id.titulo)).getText().toString().split(": ")[1];
                String fecha = ((TextView) view.findViewById(R.id.date)).getText().toString();
                String fechaenviado = ((TextView) view.findViewById(R.id.fecha2)).getText().toString();
                sSubCadena = fecha.substring(6, 25);
                Log.i("fecha string", sSubCadena);
                dialog = new Dialog(History.this);
                dialog.requestWindowFeature(1);
                dialog.setContentView(R.layout.layout_info_payment);
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(true);
                TextView ammount = (TextView) dialog.findViewById(R.id.textView14);
                TextView request = (TextView) dialog.findViewById(R.id.textView15);
                TextView sent = (TextView) dialog.findViewById(R.id.textView16);
                Log.i("ammount", part2);
                Log.i("request", sSubCadena);
                Log.i("sent", fechaenviado);
                ammount.setText(part2);
                request.setText(sSubCadena);
                if (fechaenviado.equals("0000-00-00 00:00:00")) {
                    sent.setText("Not payed");
                } else {
                    sent.setText(fechaenviado);
                }
                dialog.show();
            }
        });
        listview2.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String part2 = ((TextView) view.findViewById(R.id.amount)).getText().toString().split(": ")[1];
                String fecha = ((TextView) view.findViewById(R.id.fecha)).getText().toString();
                String fechaenviado = ((TextView) view.findViewById(R.id.fecha2)).getText().toString();
                sSubCadena = fecha.substring(6, 25);
                Log.i("fecha string", sSubCadena);
                dialog2 = new Dialog(History.this);
                dialog2.requestWindowFeature(1);
                dialog2.setContentView(R.layout.layout_info_payment);
                dialog2.setCancelable(true);
                dialog2.setCanceledOnTouchOutside(true);
                TextView ammount = (TextView) dialog2.findViewById(R.id.textView14);
                TextView request = (TextView) dialog2.findViewById(R.id.textView15);
                TextView sent = (TextView) dialog2.findViewById(R.id.textView16);
                Log.i("ammount", part2);
                Log.i("request", sSubCadena);
                Log.i("sent", fechaenviado);
                ammount.setText(part2);
                request.setText(sSubCadena);
                if (fechaenviado.equals("0000-00-00 00:00:00")) {
                    sent.setText("Not payed");
                } else {
                    sent.setText(fechaenviado);
                }
                dialog2.show();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_history, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_info) {
            dialog = new Dialog(this);
            dialog.setTitle(getString(R.string.dialogname));
            dialog.requestWindowFeature(3);
            dialog.setContentView(R.layout.layout_dialog);
            dialog.setCancelable(true);
            dialog.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
