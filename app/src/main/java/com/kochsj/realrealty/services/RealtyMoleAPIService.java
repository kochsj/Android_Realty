package com.kochsj.realrealty.services;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import javax.security.auth.callback.Callback;


public class RealtyMoleAPIService {
    private OkHttpClient client;
    private String endpointURL;

    public RealtyMoleAPIService() {
        this.client = new OkHttpClient();
        this.endpointURL = "https://realty-mole-property-api.p.rapidapi.com/properties/";
    }

    public void getLocationData(String address) throws Exception {
        Request request = new Request.Builder()
                .url(endpointURL + address)
                .get()
                .addHeader("x-rapidapi-host", "realty-mole-property-api.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "aba3d9c1a3msh7e3893a822e73a1p1802b6jsn2cd6d397f4ef")
                .build();

//        try {
//            Response response = client.newCall(request).execute();
//            Log.d("TAG", "getLocationData: Success! " );
//            return response.body().string();
//        } catch (Exception e) {
//            Log.d("TAG", "getLocationData: " + e.toString());
//            return "";
//        }
        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }

                    System.out.println(responseBody.string());
                }
            }
        });
    }

}
