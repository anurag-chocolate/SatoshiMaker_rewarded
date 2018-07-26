package com.example.juan.pruebaaplicaciones;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class Top extends AppCompatActivity {
    ArrayList Count = new ArrayList();
    ArrayList ID = new ArrayList();
    ArrayList ID2 = new ArrayList();
    ArrayList Referido = new ArrayList();
    ArrayList SatoshisTotal = new ArrayList();
    ArrayList Username = new ArrayList();
    private ListView listview1;
    private ListView listview2;
    private ProgressDialog pDialogconsultardatos;
    private ProgressDialog pDialogconsultardatos2;
    TabHost th;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        th = (TabHost) findViewById(R.id.tabHosttop);
        th.setup();
        TabSpec ts1 = th.newTabSpec("Tab1");
        ts1.setIndicator(getString(R.string.TopSatoshi));
        ts1.setContent(R.id.tab1);
        th.addTab(ts1);
        th.setup();
        TabSpec ts2 = th.newTabSpec("Tab2");
        ts2.setIndicator(getString(R.string.TopRefered));
        ts2.setContent(R.id.tab2);
        th.addTab(ts2);
        ((AdView) findViewById(R.id.adViewTop)).loadAd(new Builder().build());
        listview2 = (ListView) findViewById(R.id.listView2);
        listview1 = (ListView) findViewById(R.id.listView1);
        ClassInt cHTTP = new ClassInt(Top.this);
        cHTTP.execute(getString(R.string.blog) + getString(R.string.progratool) + getString(R.string.superbit) + getString(R.string.topr), "");
        cHTTP.setOnTaskFinishedEvent(new ClassInt.OnTaskExecutionFinished() {
            @Override
            public void OnTaskFihishedEvent(String result) {
                class ImageAdapter extends BaseAdapter {
                    TextView count;
                    Context ctx;
                    TextView id;
                    LayoutInflater layoutinflater;
                    TextView referido;

                    public ImageAdapter(Context applicationContext) {
                        ctx = applicationContext;
                        layoutinflater = ((LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE));

                    }

                    public int getCount() {
                        return Count.size();
                    }

                    public Object getItem(int position) {
                        return position;
                    }

                    public long getItemId(int position) {
                        return (long) position;
                    }

                    public View getView(int position, View convertView, ViewGroup parent) {
                        ViewGroup viewgroup = (ViewGroup) layoutinflater.inflate(R.layout.activity_top_item, null);
                        String idplus = Integer.toString(Integer.parseInt(ID.get(position).toString()) + 1);
                        id = (TextView) viewgroup.findViewById(R.id.id);
                        referido = (TextView) viewgroup.findViewById(R.id.username);
                        count = (TextView) viewgroup.findViewById(R.id.numerrefer);
                        id.setText(idplus);
                        referido.setText(Referido.get(position).toString());
                        count.setText(Count.get(position).toString());
                        return viewgroup;
                    }
                }
                try {
                    JSONArray ja = new JSONArray(result);
                    for (int i = 0; i < ja.length(); i++) {
                        ID.add(ja.getJSONObject(i).getString("ID"));
                        Referido.add(ja.getJSONObject(i).getString("Referido"));
                        Count.add(ja.getJSONObject(i).getString("NumeroReferido"));
                    }
                    listview2.setAdapter(new ImageAdapter(getApplicationContext()));

                    ClassInt cHTTP = new ClassInt(Top.this);
                    cHTTP.execute(getString(R.string.blog) + getString(R.string.progratool) + getString(R.string.superbit) + getString(R.string.tops), "");
                    cHTTP.setOnTaskFinishedEvent(new ClassInt.OnTaskExecutionFinished() {
                        @Override
                        public void OnTaskFihishedEvent(String result) {
                            class ImageAdapter extends BaseAdapter {
                                Context ctx;
                                TextView id2;
                                LayoutInflater layoutinflater;
                                TextView satoshistotal;
                                TextView username;

                                public ImageAdapter(Context applicationContext) {
                                    ctx = applicationContext;
                                    layoutinflater = ((LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
                                }

                                public int getCount() {
                                    return SatoshisTotal.size();
                                }

                                public Object getItem(int position) {
                                    return position;
                                }

                                public long getItemId(int position) {
                                    return (long) position;
                                }

                                public View getView(int position, View convertView, ViewGroup parent) {
                                    ViewGroup viewgroup = (ViewGroup) layoutinflater.inflate(R.layout.activity_topsatoshis_item, null);
                                    String idplus = Integer.toString(Integer.parseInt(ID2.get(position).toString()) + 1);
                                    id2 = (TextView) viewgroup.findViewById(R.id.id);
                                    username = (TextView) viewgroup.findViewById(R.id.username);
                                    satoshistotal = (TextView) viewgroup.findViewById(R.id.numerrefer);
                                    id2.setText(idplus);
                                    username.setText(Username.get(position).toString());
                                    satoshistotal.setText(SatoshisTotal.get(position).toString());
                                    return viewgroup;
                                }
                            }
                            try {
                                JSONArray ja = new JSONArray(result);
                                for (int i = 0; i < ja.length(); i++) {
                                    ID2.add(ja.getJSONObject(i).getString("ID"));
                                    Username.add(ja.getJSONObject(i).getString("Username"));
                                    SatoshisTotal.add(ja.getJSONObject(i).getString("SatoshisTotal"));
                                }
                                listview1.setAdapter(new ImageAdapter(getApplicationContext()));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }});
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }});
    }
}
