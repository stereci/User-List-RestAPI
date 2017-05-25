package com.example.mia.userlistrestapi;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<String> mDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPost = (Button) findViewById(R.id.btnPost);
        Button horizontal=(Button) findViewById(R.id.ver);

        horizontal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.example.mia.userlistrestapi.horizontal.class);
                startActivity(intent);
            }
        });
        mDataset=new ArrayList<>();
        for(int i=0;i<30;i++)
        {
            mDataset.add("User "+i+"\n");
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MainAdapter(mDataset);
        mRecyclerView.setAdapter(mAdapter);

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Credential cr = new Credential();
                cr.setUserName("semih");
                cr.setPassword("1234");
                Gson jsonSerialize = new Gson();
                String jsonData = jsonSerialize.toJson(cr);

                try {
                    new AsyncTask<String, String, String>() {
                        @Override
                        protected String doInBackground(String... params) {
                            String result = HttpRest.Post("http://192.168.2.174/LoginRestAPI/api/login/GetAllCredentials", params[0], 10000);

                            for(int i=0;i<10;i++)
                            {
                                Gson deserialize = new Gson();
                                Type listType = new TypeToken<ArrayList<Credential>>(){}.getType();
                                List<Credential> us = deserialize.fromJson(result, listType);
                                mDataset.add(i,"Username: "+us.get(i).getUserName()+"\nPassword:"+us.get(i).getPassword());
                            }

                            return result;
                        }
                        @Override
                        protected void onPostExecute(String result) {
                            Gson deserialize = new Gson();
                            AuthenticationDetail validation = deserialize.fromJson(result, AuthenticationDetail.class);
                        }
                    }.execute(jsonData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
