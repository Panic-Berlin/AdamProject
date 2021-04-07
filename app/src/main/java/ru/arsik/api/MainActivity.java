package ru.arsik.api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import ru.arsik.api.Model.PostManApi;
import ru.arsik.api.Model.ResponseYelp;
import ru.arsik.api.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private PostYelp postYelp;
    private GetYelp getYelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View root = findViewById(R.id.root);
        binding = ActivityMainBinding.bind(root);
        Intent intent = new Intent(this, MainUser.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
        if (getYelp != null) {
            getYelp.cancel(true);
        }
        if (postYelp != null) {
            postYelp.cancel(true);
        }
    }

    private class GetYelp extends AsyncTask<Void, Void, PostManApi> {

        @Override
        protected PostManApi doInBackground(Void... voids) {
            URL url;
            HttpURLConnection conn = null;
            try {
                url = new URL("https://postman-echo.com/get?foo1=bar1&foo2=bar2&foo3");
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                InputStreamReader in = new InputStreamReader(conn.getInputStream());
                Gson gson = new Gson();
                return gson.fromJson(in, PostManApi.class);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(PostManApi postManApi) {
            super.onPostExecute(postManApi);
            binding.label1.append(postManApi.args.foo1);
            binding.label1.append(postManApi.args.foo2);
            PostYelp postYelp = new PostYelp();
            postYelp.execute(postManApi);
        }


    }

    private class PostYelp extends AsyncTask<PostManApi, Void, PostManApi> {


        @Override
        protected PostManApi doInBackground(PostManApi... postManApis) {
            HttpURLConnection conn = null;
            URL url = null;

            try {
                Gson gson = new Gson();
                url = new URL("https://postman-echo.com/post");
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setReadTimeout(5000);
                conn.setDoOutput(true);
                conn.setDoInput(true);
                OutputStream os = conn.getOutputStream();
                String json = gson.toJson(postManApis);

                os.write(json.getBytes());
                os.close();

                InputStreamReader is = new InputStreamReader(conn.getInputStream());
                return gson.fromJson(is, PostManApi.class);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {

                    conn.disconnect();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(PostManApi postManApi) {
            super.onPostExecute(postManApi);

            binding.label1.append(postManApi.args.foo1);

        }
    }
}
