package ru.arsik.api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import ru.arsik.api.Model.UserClass;
import ru.arsik.api.databinding.ActivityMainBinding;
import ru.arsik.api.databinding.ActivityMainUserBinding;

public class MainUser extends AppCompatActivity {
    private ActivityMainUserBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);
        View view = findViewById(R.id.userRoot);
        binding =ActivityMainUserBinding.bind(view);
        GetUser getUser =new GetUser();
        getUser.execute();
    }
    private class  GetUser extends AsyncTask<Void, Integer, List<UserClass>>{
        @Override
        protected List<UserClass> doInBackground(Void... voids) {
            URL url;
            HttpURLConnection conn = null;
            try {
                url = new URL("https://ikapi.azurewebsites.net/api/user/users");
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(5000);
                conn.setRequestMethod("GET");
                InputStreamReader in = new InputStreamReader(conn.getInputStream());
                Gson gson = new Gson();
                return  gson.fromJson(in, new TypeToken<List<UserClass>>(){}.getType());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {

            }
            return  null;
        }

        @Override
        protected void onPostExecute(List<UserClass> userClasses) {
            super.onPostExecute(userClasses);
            userClasses.forEach(res->{binding.label2.setText(res.users.size());});
        }
    }
}