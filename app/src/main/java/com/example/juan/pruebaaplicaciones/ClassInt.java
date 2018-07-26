package com.example.juan.pruebaaplicaciones;


import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ClassInt extends AsyncTask<String, Void, String> {
    public static final int ESPERA_CONEXION=10000;
    public static final int ESPERA_LECTURA=15000;
    private Context ContextoClase;
    HttpURLConnection ConexionHTTP;
    URL DireccionURL = null;
    int nTotDatEnvio;
    public ClassInt(Context ContextoConst) {
        this.ContextoClase = ContextoConst;
    }
    private OnTaskExecutionFinished _task_finished_event;

    public interface OnTaskExecutionFinished    {
        public void OnTaskFihishedEvent(String Result);
    }

    public void setOnTaskFinishedEvent(OnTaskExecutionFinished _event)   {
        if(_event != null)    {
            this._task_finished_event = _event;
        }
    }

    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... arg0) {
        nTotDatEnvio = arg0.length;
        /*Intentar crear URL de servidor*/
        try {
            DireccionURL = new URL(arg0[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "ERROR-URL";
        }
        /*Intentar establecer conexión y enviar datos al servidor web*/
        try {
            // Configurar conexión mediante HttpURLConnection
            ConexionHTTP = (HttpURLConnection)DireccionURL.openConnection();
            ConexionHTTP.setReadTimeout(ESPERA_LECTURA);
            ConexionHTTP.setConnectTimeout(ESPERA_CONEXION);
            ConexionHTTP.setRequestMethod("POST");

            // activar envio (setDoInput) y recepción (setDoOutput)
            ConexionHTTP.setDoInput(true);
            ConexionHTTP.setDoOutput(true);
            // Añadir parámetros a URL
            Uri.Builder builder = new Uri.Builder();
            for (int nDatEnvio=1; nDatEnvio<nTotDatEnvio;nDatEnvio++){
                builder.appendQueryParameter("DATO" + String.valueOf(nDatEnvio), arg0[nDatEnvio]);
                Log.i("DATO", "DATO" + String.valueOf(nDatEnvio) + " = " + arg0[nDatEnvio]);
            }
            String Peticion = builder.build().getEncodedQuery();
            // abrir conexion para enviar datos
            OutputStream Salida = ConexionHTTP.getOutputStream();
            BufferedWriter Escribir = new BufferedWriter(new OutputStreamWriter(Salida, "UTF-8"));
            Escribir.write(Peticion);
            Log.i("DATO", DireccionURL + "  " + Peticion);
            Escribir.flush();
            Escribir.close();
            Salida.close();
            ConexionHTTP.connect();
        } catch (IOException e1) {
            e1.printStackTrace();
            return "ERROR-CON";
        }
        /*Intentar leer datos del servidor*/

        try {
            int Respuesta = ConexionHTTP.getResponseCode();
            Log.i("option2 3.2", ""+Respuesta + ConexionHTTP.getErrorStream ());

            // Si la conexión ha sido establecida correctamente
            if (Respuesta == HttpURLConnection.HTTP_OK) {
                // Leer datos del servidor
                Log.i("option2 3.3", ""+Respuesta);

                InputStream Entrada = ConexionHTTP.getInputStream();
                BufferedReader Leer = new BufferedReader(new InputStreamReader(Entrada));
                StringBuilder Resultado = new StringBuilder();
                String Linea;
                while ((Linea = Leer.readLine()) != null) {
                    Resultado.append(Linea);
                }
                // Pasar datos al método onPostExecute

                return(Resultado.toString());

            }else{
                return ("ERROR-RESP");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "ERROR-RESP2";
        } finally {
            ConexionHTTP.disconnect();
        }
    }
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if(this._task_finished_event != null)        {
            this._task_finished_event.OnTaskFihishedEvent(result);
        }  else     {
            //
        }

    }
}