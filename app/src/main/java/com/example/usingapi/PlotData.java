package com.example.usingapi;

import android.content.Context;
import android.util.Log;
import android.util.Pair;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PlotData {
    public final static String COIN_CHART_DATA_QUERY = "https://api.coingecko.com/api/v3/coins/", COIN_DATA_QUERY_2ND_HALF="/market_chart?vs_currency=usd&days=100";

    Context context;

    public PlotData(Context context) {
        this.context = context;
    }

    // Interface for linechart data
    public interface ChartDataQueryListener {
        void onError(String message);

        void onResponse( ArrayList<Float> priceChartDataList);
    }
    // method to get chart data
    public void getCoinChartData(String coin_Id ,ChartDataQueryListener chartDataQueryListener){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, COIN_CHART_DATA_QUERY+coin_Id+COIN_DATA_QUERY_2ND_HALF, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                ArrayList<Float>  priceChartDataList = new ArrayList <> ();
                try {
                    JSONArray priceChartData = response.getJSONArray("prices");

                    for(int i=0;i< priceChartData.length();i++){
                        JSONArray jsonArray = (JSONArray) priceChartData.get(i);
                       // Log.i("TAG : Plot data ", Double.toString(jsonArray.getDouble(1)));
                        priceChartDataList.add((float) jsonArray.getDouble(1));


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("PLOTDATA" , "SOMETHING WRONG");
                }


                chartDataQueryListener.onResponse(priceChartDataList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                chartDataQueryListener.onError(" CDS, Something is Wrong while getting chart data ");
            }
        });
        MySecondSingleton.getSecond_instance(context).addToRequestQueue(jsonObjectRequest);
    }
}
