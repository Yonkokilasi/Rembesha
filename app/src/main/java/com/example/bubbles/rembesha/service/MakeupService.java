package com.example.bubbles.rembesha.service;

import android.util.Log;

import com.example.bubbles.rembesha.Constants;
import com.example.bubbles.rembesha.MakeUp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Callback;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by bubbles on 6/20/17.
 */

public class MakeupService {
    public static void findMakeUp(String brand, String pType, String pCategory, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.BRAND_PARAMETER,brand);
        urlBuilder.addQueryParameter(Constants.CATEGORY_PARAMETER,pCategory);
        urlBuilder.addQueryParameter(Constants.TYPE_PARAMETER,pType);
        String url = urlBuilder.build().toString();
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }
    public ArrayList<MakeUp> processResults(Response response) {
        ArrayList<MakeUp> makeUps = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONArray makeupJSON = new JSONArray(jsonData);

                if (makeupJSON.length() == 0) {
                    Log.d("Service","No results found");
                } else {
                    for (int i = 0 ; i < makeupJSON.length(); i++) {
                        String name = makeupJSON.getJSONObject(i).getString("name");
                        String brand = makeupJSON.getJSONObject(i).getString("brand");
                        String imageUrl = makeupJSON.getJSONObject(i).getString("image_link");
                        String website = makeupJSON.getJSONObject(i).getString("product_link");
                        String productType = makeupJSON.getJSONObject(i).getString("product_type");
                        Double price = makeupJSON.getJSONObject(i).getDouble("price");
                        Double rating = makeupJSON.getJSONObject(i).getDouble("rating");
                        String description = makeupJSON.getJSONObject(i).getString("description");
                        String category = makeupJSON.getJSONObject(i).getString("category");
                        ArrayList<String> colors = new ArrayList<>();
                        JSONArray colorsJSON = makeupJSON.getJSONObject(i).getJSONArray("product_colors");
                        for (int y= 0; y < colorsJSON.length(); y++) {
                            colors.add(colorsJSON.getJSONObject(y).getString("colour_name"));
                        }
                        MakeUp makeUp = new MakeUp(name,price,imageUrl,website,brand,rating,colors,description,productType,category);
                        makeUps.add(makeUp);
                        Log.d(TAG,makeupJSON.getJSONObject(i).getString("name"));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return makeUps;
    }
}
