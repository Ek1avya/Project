package com.example.usingapi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Locale;

import static java.lang.String.valueOf;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
     List<coinData> coinDataList;
     private Context context;

    public RecyclerViewAdapter(List<coinData> coinDataList, Context context) {
        this.coinDataList = coinDataList;
        this.context = context;
        notifyDataSetChanged();
    }

    public RecyclerViewAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.coins_rec_view_layout,parent,false);
           return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            holder.name.setText(coinDataList.get(position).getName());
           //holder.image.setText(coinDataList.get(position).getName());
            String res =  Long.toString(coinDataList.get(position).getMarket_cap());
            String res2 ="$"+ coinDataList.get(position).getCurrent_price();
            holder.market_cap.setText(res);
            holder.price.setText(res2);
            double a=coinDataList.get(position).getPrice_change_percentage_24h();

           String percentchnge =  String.format("%.2f", a) + "%";

          holder.pricechange24.setText(percentchnge);

           if(a<0) {
               //Log.i("TAG : ", Double.toString(a));
               holder.pricechange24.setTextColor(Color.RED);
           }
           else{
               holder.pricechange24.setTextColor(Color.GREEN);
           }
//        holder.parentLinearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, ParitcularBook.class);
//                intent.putExtra(BOOK_ID_KEY, books.get(position).getId());
//                context.startActivity(intent);
//            }
//        });
        holder.parentLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, CoinDetails.class);
                intent.putExtra("id",coinDataList.get(position).getId());
                context.startActivity(intent);
            }
        });
           Glide.with(context)
                .asBitmap()
                .load(coinDataList.get(position).getImage())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return coinDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
       private final  TextView name,market_cap,price, pricechange24;
       private  final ImageView image;
       private final LinearLayout parentLinearLayout;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);

         name = itemView.findViewById(R.id.coinName);
         image = itemView.findViewById(R.id.coinPic);
         market_cap = itemView.findViewById(R.id.marketcap);
         price= itemView.findViewById(R.id.price);
         pricechange24= itemView.findViewById(R.id.pricechangepercent24);
         parentLinearLayout= itemView.findViewById(R.id.parentLinearLayout);

    }
}
}
