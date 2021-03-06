package ues.fia.eisi.reservalocalfia;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ControladorServicio {

    public static String obtenerRespuestaPeticion(String url, Context ctx) {
        String respuesta = " ";
        HttpParams parametros = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(parametros, 3000);
        HttpConnectionParams.setSoTimeout(parametros, 5000);
        HttpClient cliente = new DefaultHttpClient(parametros);
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse httpRespuesta = cliente.execute(httpGet);
            StatusLine estado = httpRespuesta.getStatusLine();
            int codigoEstado = estado.getStatusCode();
            if (codigoEstado == 200) {
                HttpEntity entidad = httpRespuesta.getEntity();
                respuesta = EntityUtils.toString(entidad);
            }
        } catch (Exception e) {
            Toast.makeText(ctx, "Error en la conexion", Toast.LENGTH_LONG).show();
            Log.v("Error de Conexion", e.toString());
        }
        return respuesta;
    }

    public static String obtenerRespuestaPost(String url, JSONObject obj, Context ctx) {
        String respuesta = " ";
        try {
            HttpParams parametros = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(parametros, 3000);
            HttpConnectionParams.setSoTimeout(parametros, 5000);
            HttpClient cliente = new DefaultHttpClient(parametros);
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("content-type", "application/json");
            StringEntity nuevaEntidad = new StringEntity(obj.toString());
            httpPost.setEntity(nuevaEntidad);
            Log.v("Peticion", url);
            Log.v("POST", httpPost.toString());
            HttpResponse httpRespuesta = cliente.execute(httpPost);
            StatusLine estado = httpRespuesta.getStatusLine();
            int codigoEstado = estado.getStatusCode();
            if (codigoEstado == 200) {
                respuesta = Integer.toString(codigoEstado);
                Log.v("respuesta", respuesta);
            } else {
                Log.v("respuesta", Integer.toString(codigoEstado));
            }
        } catch (Exception e) {
            Toast.makeText(ctx, "Error en la conexion", Toast.LENGTH_LONG).show();
            Log.v("Error de Conexion", e.toString());
        }
        return respuesta;
    }

    public static List<CargaAcademica> obtenerCargaAcademicaExterno(String json, Context ctx) {
        List<CargaAcademica> listaCargaAcademicas = new ArrayList<CargaAcademica>();
        try {
            JSONArray cargaAJSON = new JSONArray(json);
            for (int i = 0; i < cargaAJSON.length(); i++) {
                JSONObject obj = cargaAJSON.getJSONObject(i);
                CargaAcademica cargaAcademica = new CargaAcademica();
                cargaAcademica.setidRolDocente(obj.getInt("idRolDocente"));
                cargaAcademica.setcodigoAsignatura(obj.getString("codigoAsignatura"));
                cargaAcademica.setcodigoCiclo(obj.getString("codigoCiclo"));
                cargaAcademica.setcarnetDocente(obj.getString("carnetDocente"));

                listaCargaAcademicas.add(cargaAcademica);
            }
            return listaCargaAcademicas;
        } catch (Exception e) {
            Toast.makeText(ctx, "Error en parseOO de JSON", Toast.LENGTH_LONG).show();
            return null;
        }
    }
    public static void insertarDiaExterno(String peticion, Context ctx) {
        String json = obtenerRespuestaPeticion(peticion, ctx);
        try {
            JSONObject resultado = new JSONObject(json);
            Toast.makeText(ctx, "Registro ingresado" + resultado.getJSONArray("resultado").toString(), Toast.LENGTH_LONG).show();
            int respuesta = resultado.getInt("resultado");
            if (respuesta == 1) Toast.makeText(ctx, "Registro ingresado", Toast.LENGTH_LONG).show();
            else Toast.makeText(ctx, "Error registro duplicado", Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void insertarEscuelaExterno(String peticion, Context ctx) {
        String json = obtenerRespuestaPeticion(peticion, ctx);
        try {
            JSONObject resultado = new JSONObject(json);

            Toast.makeText(ctx, "Registro ingresado" + resultado.getJSONArray("resultado").toString(), Toast.LENGTH_LONG).show();
            int respuesta = resultado.getInt("resultado");
            if (respuesta == 1) Toast.makeText(ctx, "Registro ingresado", Toast.LENGTH_LONG).show();
            else Toast.makeText(ctx, "Error registro duplicado", Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static void insertarReservaExterno(String peticion, Context ctx) {
        String json = obtenerRespuestaPeticion(peticion, ctx);
        try {
            JSONObject resultado = new JSONObject(json);

            Toast.makeText(ctx, "Registro ingresado" + resultado.getJSONArray("resultado").toString(), Toast.LENGTH_LONG).show();
            int respuesta = resultado.getInt("resultado");
            if (respuesta == 1) Toast.makeText(ctx, "Registro ingresado", Toast.LENGTH_LONG).show();
            else Toast.makeText(ctx, "Error registro duplicado", Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static List<ReservaEvento> obtenerReservasExterno(String json, Context ctx) {
        List<ReservaEvento> listaReservasEventos = new ArrayList<ReservaEvento>();
        try {
            JSONArray reservaJSON = new JSONArray(json);
            for (int i = 0; i < reservaJSON.length(); i++) {
                JSONObject obj = reservaJSON.getJSONObject(i);
                ReservaEvento reservaEvento = new ReservaEvento();
                reservaEvento.setIdReservaEvento(obj.getInt("idReservaEvento"));
                reservaEvento.setCodigoEscuela(obj.getString("codigoEscuela"));
                reservaEvento.setNombreEvento(obj.getString("nombreEvento"));
                reservaEvento.setCapacidadTotalEvento(obj.getInt("capacidadEvento"));
                reservaEvento.setFechaReservaEvento(obj.getString("fechaEvento"));

                listaReservasEventos.add(reservaEvento);
            }
            return listaReservasEventos;
        } catch (Exception e) {
            Toast.makeText(ctx, "Error en parseOO de JSON", Toast.LENGTH_LONG).show();
            return null;
        }
    }
    public static void insertarCicloExterno(String peticion, Context ctx) {
        String json = obtenerRespuestaPeticion(peticion, ctx);
        try {
            JSONObject resultado = new JSONObject(json);
            Toast.makeText(ctx, "Registro ingresado" + resultado.getJSONArray("resultado").toString(), Toast.LENGTH_LONG).show();
            int respuesta = resultado.getInt("resultado");
            if (respuesta == 1) Toast.makeText(ctx, "Registro ingresado", Toast.LENGTH_LONG).show();
            else Toast.makeText(ctx, "Error registro duplicado", Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static void insertarLocalExterno(String peticion, Context ctx) {
        String json = obtenerRespuestaPeticion(peticion, ctx);
        try {
            JSONObject resultado = new JSONObject(json);
            Toast.makeText(ctx, "Registro ingresado" + resultado.getJSONArray("resultado").toString(), Toast.LENGTH_LONG).show();
            int respuesta = resultado.getInt("resultado");
            if (respuesta == 1) Toast.makeText(ctx, "Registro ingresado", Toast.LENGTH_LONG).show();
            else Toast.makeText(ctx, "Error registro duplicado", Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static List<Encargado> obtenerEncargadoExterno(String json, Context ctx) {
        List<Encargado> listaEncargado = new ArrayList<Encargado>();
        try {
            JSONArray encargadoJSON = new JSONArray(json);
            for (int i = 0; i < encargadoJSON.length(); i++) {
                JSONObject obj = encargadoJSON.getJSONObject(i);
                Encargado encargado = new Encargado();
                encargado.setIdEncargadoLocal(obj.getString("idEncargadoLocal"));
                encargado.setNomEncargadoLocal(obj.getString("nomEncargadoLocal"));
                encargado.setApeEncargadoLocal(obj.getString("apeEncargadoLocal"));


                listaEncargado.add(encargado);
            }
            return listaEncargado;

        } catch (Exception e) {
            Toast.makeText(ctx, "Error en parseOO de JSON", Toast.LENGTH_LONG).show();
            return null;
        }
    }
        public static void insertarEncargadoExterno(String peticion, Context ctx) {
            String json = obtenerRespuestaPeticion(peticion, ctx);
            try {
                JSONObject resultado = new JSONObject(json);
                Toast.makeText(ctx, "Registro ingresado" + resultado.getJSONArray("resultado").toString(), Toast.LENGTH_LONG).show();
                int respuesta = resultado.getInt("resultado");
                if (respuesta == 1) Toast.makeText(ctx, "Registro ingresado", Toast.LENGTH_LONG).show();
                else Toast.makeText(ctx, "Error registro duplicado", Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        public static List<Local> obtenerLocalesExterno(String json, Context ctx) {
            List<Local> listaLocal = new ArrayList<Local>();
            try {
                JSONArray localJSON = new JSONArray(json);
                for (int i = 0; i < localJSON.length(); i++) {
                    JSONObject obj = localJSON.getJSONObject(i);
                    Local local = new Local();
                    local.setCodigoLocal(obj.getString("codigoLocal"));
                    local.setIdEncargadoLocal(obj.getString("idEncargadoLocal"));
                    local.setIdTipoLocal(obj.getString("idtipolocal"));
                    local.setCapacidadLocal(obj.getInt("capacidadLocal"));
                    local.setUbicacionLocal(obj.getString("ubicacionLocal"));

                    listaLocal.add(local);
                }
                return listaLocal;

            } catch (Exception e) {
                Toast.makeText(ctx, "Error en parseOO de JSON", Toast.LENGTH_LONG).show();
                return null;
            }
    }


}
        

