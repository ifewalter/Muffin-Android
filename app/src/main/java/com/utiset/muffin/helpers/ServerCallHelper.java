package com.utiset.muffin.helpers;

import android.content.Context;

import com.utiset.muffin.data.AuthPrefs;
import com.utiset.muffin.logger.AppLogger;
import com.utiset.muffin.models.ResponseModel;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by ife on 03/06/16.
 */

public class ServerCallHelper {

    public OkHttpClient httpClient = new OkHttpClient();
    private Request request;
    private Response response;
    private RequestBody requestBody;
    private String token = "";

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public ServerCallHelper(Context ctx)
    {
        setToken(new AuthPrefs(ctx).getToken());
    }

    //Parameterless constructor
    public ServerCallHelper()
    {

    }

    public ResponseModel getData(String url) throws IOException
    {

        request = new Request.Builder()
                .url(url)
                .addHeader("token", token)
                .build();
        response = httpClient.newCall(request).execute();
        return new ResponseModel(response.code(), response.body().string());

    }

    public ResponseModel postData(String url, String data) throws IOException {

        requestBody = RequestBody.create(JSON, data);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("token", token)
                .post(requestBody)
                .build();

        response = httpClient.newCall(request).execute();

        return new ResponseModel(response.code(), response.body().string());

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
