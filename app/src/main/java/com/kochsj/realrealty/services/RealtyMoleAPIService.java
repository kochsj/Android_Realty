package com.kochsj.realrealty.services;

import com.squareup.okhttp.OkHttpClient;

import org.json.JSONException;
import org.json.JSONObject;



public class RealtyMoleAPIService {
    // https://square.github.io/okhttp/recipes/
    // https://rapidapi.com/realtymole/api/realty-mole-property-api
    private OkHttpClient client;
    private String endpointURL;
    private JSONObject jsonObject;

    private JSONObject dummyJSONObject;


    public RealtyMoleAPIService() {
        this.client = new OkHttpClient();
        this.jsonObject = new JSONObject();

//        Commented out to avoid payment ////////////////////////////////////////////////////////////////////////////////////
//        this.endpointURL = "https://realty-mole-property-api.p.rapidapi.com/properties/";
    }

    public JSONObject getLocationData(String address) throws JSONException {
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

        dummyJSONObject = new JSONObject();
        dummyJSONObject.put("city","Billings");
        dummyJSONObject.put("state","MT");
        dummyJSONObject.put("addressLine1", "2439 Rancho Rd");
        dummyJSONObject.put("bedrooms", 4);
        dummyJSONObject.put("bathrooms", 3);
        dummyJSONObject.put("squareFootage",3340);
        dummyJSONObject.put("lotSize", 12754);
        dummyJSONObject.put("yearBuilt", 1955);
        dummyJSONObject.put("propertyType", "Single Family");
        dummyJSONObject.put("zipCode", "59102");
        dummyJSONObject.put("id", "2439-Rancho-Rd,-Billings,-MT-59102");
        dummyJSONObject.put("longitude", -108.578379);
        dummyJSONObject.put("latitude", 45.797788);

        return dummyJSONObject;

//        Commented out to avoid payment ///////////////////////////////////////////////////////////////////////////////////
//        Request request = new Request.Builder()
//                .url(endpointURL + address)
//                .get()
//                .addHeader("x-rapidapi-host", "realty-mole-property-api.p.rapidapi.com")
//                .addHeader("x-rapidapi-key", "aba3d9c1a3msh7e3893a822e73a1p1802b6jsn2cd6d397f4ef")
//                .build();
//
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Request request, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Response response) throws IOException {
//                try (ResponseBody responseBody = response.body()) {
//                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//
//                    Headers responseHeaders = response.headers();
//                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
//                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
//                    }
//
//                    String jsonData = responseBody.string();
//                    Log.d("TAG", "onResponse: "+ jsonData);
//
//                    jsonObject = new JSONObject(jsonData);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        return jsonObject;
    }
}
