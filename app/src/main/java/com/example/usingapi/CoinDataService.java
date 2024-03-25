package com.example.usingapi;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CoinDataService {
    Context context ;


     public CoinDataService(Context context) {
        this.context = context;
    }
     ArrayList<coinData> coinDataArrayList = new ArrayList<>();

     public final static String COIN_DATA_QUERY = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=100&page=1&sparkline=false";
     //public final static String COIN_CHART_DATA_QUERY = "https://api.coingecko.com/api/v3/coins/bitcoin/market_chart?vs_currency=usd&days=30";

     //interface fo coin details
    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(ArrayList<coinData> coinList);
    }
     //method to get coin details
     public void getCoinData (VolleyResponseListener volleyResponseListener) {

         JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, COIN_DATA_QUERY, null, response -> {
             for (int i = 0; i < response.length(); i++) {
                 coinData Coindata = new coinData();
                 try {

                     JSONObject coindata = response.getJSONObject(i);

                     Coindata.setId(coindata.getString("id"));
                     Coindata.setName(coindata.getString("name"));
                     Coindata.setImage(coindata.getString("image"));
                     Coindata.setLast_updated(coindata.getString("last_updated"));
                     Coindata.setCurrent_price(coindata.getDouble("current_price"));
                     Coindata.setMarket_cap(coindata.getLong("market_cap"));
                     Coindata.setPrice_change_percentage_24h(coindata.getDouble("price_change_percentage_24h"));

                 } catch (JSONException e) {
                     e.printStackTrace();
                 }
                 coinDataArrayList.add(Coindata);

             }
             volleyResponseListener.onResponse(coinDataArrayList);
         }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {
                 volleyResponseListener.onError(" CDS, Something is Wrong");
             }
         });

         MySingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);
     }
      //interface to get deatils of Particular coin
    public interface CoinDetailsResponseListener {
        void onError(String message);

        void onResponse(coinData coin);
    }
    public void getCoinDetails(String coin_id, CoinDetailsResponseListener coinDetailsResponseListener){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, COIN_DATA_QUERY, null, response -> {
            coinData Coindata = new coinData();
            for (int i = 0; i < response.length(); i++) {

                try {
                    JSONObject coindata = response.getJSONObject(i);

                    if(coindata.getString("id").equals(coin_id)) {

                        Coindata.setRank(coindata.getLong("market_cap_rank"));
                        Coindata.setName(coindata.getString("name"));
                        Coindata.setImage(coindata.getString("image"));
                        Coindata.setLast_updated(coindata.getString("last_updated"));
                        Coindata.setCurrent_price(coindata.getDouble("current_price"));
                        Coindata.setMarket_cap(coindata.getLong("market_cap"));
                        Coindata.setPrice_change_percentage_24h(coindata.getDouble("price_change_percentage_24h"));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
            coinDetailsResponseListener.onResponse(Coindata);
        }, error -> coinDetailsResponseListener.onError(" CDS, Something is Wrong"));

        MySingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);
    }


}
