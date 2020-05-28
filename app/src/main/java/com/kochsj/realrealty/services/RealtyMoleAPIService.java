package com.kochsj.realrealty.services;

import android.util.Log;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;



public class RealtyMoleAPIService {
    // https://square.github.io/okhttp/recipes/
    // https://rapidapi.com/realtymole/api/realty-mole-property-api
    private OkHttpClient client;
    private String endpointURL;

    public RealtyMoleAPIService() {
        this.client = new OkHttpClient();

//        Commented out to avoid payment
        this.endpointURL = "https://realty-mole-property-api.p.rapidapi.com/properties/";
    }

    public void getLocationData(String address) {
//         onResponse: {
//                "city":"Billings",
//                "state":"MT",
//                "addressLine1":"2439 Rancho Rd",
//                "bedrooms":4,
//                "bathrooms":3,
//                "squareFootage":3340,
//                "lotSize":12754,
//                "yearBuilt":1955,
//                "propertyType":"Single Family",
//                "zipCode":"59102",
//                "id":"2439-Rancho-Rd,-Billings,-MT-59102",
//                "longitude":-108.578379,
//                "latitude":45.797788
//         }

//        String testOnResponseString = {"city":"Billings","state":"MT","addressLine1":"2439 Rancho Rd","bedrooms":4,"bathrooms":3,"squareFootage":3340,"lotSize":12754,"yearBuilt":1955,"propertyType":"Single Family","zipCode":"59102","id":"2439-Rancho-Rd,-Billings,-MT-59102","longitude":-108.578379,"latitude":45.797788};
//        JsonParser parser = new JsonParser();
//        JSONObject json = (JSONObject) parser.parse(testOnResponseString);

        Request request = new Request.Builder()
                .url(endpointURL + address)
                .get()
                .addHeader("x-rapidapi-host", "realty-mole-property-api.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "aba3d9c1a3msh7e3893a822e73a1p1802b6jsn2cd6d397f4ef")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }

                    String jsonData = responseBody.string();
                    Log.d("TAG", "onResponse: "+ jsonData);

                    JSONObject jsonObject = new JSONObject(jsonData);

                    Log.d("TAG", "onResponse: json object: " +jsonObject);
                    Log.d("TAG", "onResponse: json object.state " + jsonObject.getString("state"));




                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
