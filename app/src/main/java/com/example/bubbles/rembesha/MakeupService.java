package com.example.bubbles.rembesha;

import okhttp3.Callback;;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

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
}
