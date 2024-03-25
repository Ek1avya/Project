package com.example.usingapi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Pair;
import android.widget.TextView;
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

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    static String NAME = "name" , MARKET_CAP= "market_cap", CURR_PRICE = "current_price";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CoinDataService coinDataService = new CoinDataService(this);
        recyclerView =findViewById(R.id.coinListRelativeLayout);

        coinDataService.getCoinData(new CoinDataService.VolleyResponseListener() {
             @Override
             public void onError(String message) {
                 Toast.makeText(MainActivity.this," NOT RETURNING TO MAIN ACTIVITY "+ message,Toast.LENGTH_SHORT).show();
             }

             @Override
             public void onResponse(ArrayList<coinData> coinList) {

                  RecyclerViewAdapter adapter= new RecyclerViewAdapter(coinList,MainActivity.this);
                  recyclerView.setAdapter(adapter);
                  recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
             }
         });


    }
}