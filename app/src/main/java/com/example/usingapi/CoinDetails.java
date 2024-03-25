package com.example.usingapi;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;

import java.util.ArrayList;
import java.util.List;

public class CoinDetails extends AppCompatActivity  {
    private LineChart lineChart;
    private TextView coin_rank, name, price, price_percent24h;
    private ImageView coin_image, price_arrow;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coin_details_layout);
        // List<Float> priceChartDataListUtil = new ArrayList<>();
        lineChart = findViewById(R.id.LineChart);
        Intent intent = getIntent();
        String coinId = "bitcoin";
        if (null != intent) {
            coinId = intent.getStringExtra("id");
        }
        intitView();

        PlotData plotData = new PlotData(this);
        plotData.getCoinChartData(coinId, new PlotData.ChartDataQueryListener() {
            @Override
            public void onError(String message) {
                Log.i("Plot data", " something is wrong");
            }

            @Override
            public void onResponse(ArrayList<Float> priceChartDataList) {

                List<Entry> entries = new ArrayList<Entry>();


                for (float f : priceChartDataList) {

                    entries.add(new Entry((float) entries.size(), f));
                }
                LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
                dataSet.setColor(Color.RED);
                dataSet.setValueTextColor(Color.RED);
                dataSet.notifyDataSetChanged();// styling, ...
                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                dataSets.add(dataSet);
                LineData lineData = new LineData(dataSets);
                lineChart.setData(lineData);
                lineChart.invalidate(); // refresh
            }
        });

        CoinDataService cds = new CoinDataService(this);
        cds.getCoinDetails(coinId, new CoinDataService.CoinDetailsResponseListener() {
            @Override
            public void onError(String message) {
               Log.i("Tag", " Something Wrong ");
            }

            @Override
            public void onResponse(coinData coin) {
                Log.i("PLot Data : ", " Getting Response ");
                Glide.with(CoinDetails.this)
                        .asBitmap()
                        .load(coin.getImage())
                        .into(coin_image);
                coin_rank.setText(" #" + Long.toString(coin.getRank())+" ");

                name.setText(coin.getName());
                price.setText("$"+Double.toString(coin.getCurrent_price()));
                double a = coin.getPrice_change_percentage_24h();
                String percentchnge =  String.format("%.2f", a) + "%";
                price_percent24h.setText(percentchnge);
                if(a<0){
                    price_percent24h.setTextColor(Color.RED);
                    price_arrow.setImageResource(R.mipmap.percent_down_icon);
                }
                else{
                    price_percent24h.setTextColor(Color.GREEN);
                    price_arrow.setImageResource(R.mipmap.percent_up_icon);
                }
            }

        });
    }
    private void intitView() {
        coin_image=findViewById(R.id.coin_image);
        price_arrow=findViewById(R.id.price_arrow_image);
        coin_rank=findViewById(R.id.rank);
        name=findViewById(R.id.coin_name);
        price=findViewById(R.id.price_num);
        price_percent24h=findViewById(R.id.price_change_24h_percent);
    }


}
