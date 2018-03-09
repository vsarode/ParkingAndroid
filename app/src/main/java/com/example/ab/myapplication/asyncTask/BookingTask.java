package com.example.ab.myapplication.asyncTask;

import android.content.Context;
import android.os.AsyncTask;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ab.myapplication.User;
import com.example.ab.myapplication.constants.Constants;

import org.json.JSONObject;

import java.util.HashMap;

public class BookingTask extends AsyncTask<Void, Void, Boolean> {

    Context contex;
    String blockId;
    private Response.Listener successListener;
    boolean result = false;

    public BookingTask(String blockId, Context contex, Response.Listener successListener) {
        this.blockId = blockId;
        this.contex = contex;
        this.successListener = successListener;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(contex);
            String url = Constants.SERVER_URL + "/login/";
            HashMap<String, String> paramsBody = new HashMap<>();
//            paramsBody.put("email", email);
//            paramsBody.put("password", password);

            User currentUser = User.getCurrentUser();

            JsonObjectRequest request = new JsonObjectRequest(url, new JSONObject(paramsBody), successListener, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("Error while logging in");
                }
            });
            requestQueue.add(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}