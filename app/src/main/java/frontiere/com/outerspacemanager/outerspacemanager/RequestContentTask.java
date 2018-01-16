package frontiere.com.outerspacemanager.outerspacemanager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

 class OKHttp extends AsyncTask {

       public static OkHttpClient getClient(OkHttpClient client) {
        if (client == null) {
                client = new OkHttpClient.Builder()
                .build();
                }
          return client;
       }


public static String createUser(OkHttpClient client) throws IOException {
        MediaType JSON= MediaType.parse("application/json; charset=utf-8");
        String json="data: {\n" +"    email: 'remi@hotmail.fr',\n" +"    username: 'remi',\n" +"    password: 'hello'\n" +"  }";
        String urlPost="https://outer-space-manager.herokuapp.com";
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
        .url(urlPost+"/api/vXXX/auth/create")
        .post(body)
        .build();
        Call postCall=getClient(client).newCall(request);
        Response response = postCall.execute(); //you have your response code
        int httpStatusCode=response.code();
        String ret=response.body().string();
        response.body().close();
        return ret;
        }

     @Override
     protected Object doInBackground(Object[] objects) {
         return null;
     }
 }
