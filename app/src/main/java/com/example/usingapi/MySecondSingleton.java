package com.example.usingapi;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySecondSingleton {
    private static MySecondSingleton second_instance;
    private RequestQueue requestQueue;
    private static Context ctx2;

    private MySecondSingleton(Context context) {
        ctx2 = context;
        requestQueue = getRequestQueue();

    }

    public static synchronized MySecondSingleton getSecond_instance(Context context) {
        if (second_instance == null) {
            second_instance = new MySecondSingleton(context);
        }
        return second_instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(ctx2.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }


}
