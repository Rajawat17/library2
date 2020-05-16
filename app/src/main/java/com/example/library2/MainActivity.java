package com.example.library2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<person> mExampleList;

    private RecyclerView mRecyclerView;
    private libraryadapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    TextView name;
    TextView branch;
    TextView component;
    Button add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loaddata();
        createExampleList();
        buildRecyclerView();

        name=(TextView)findViewById(R.id.etname);
        branch=(TextView)findViewById(R.id.etbranch);
        component=(TextView)findViewById(R.id.etcomponent);
        add=(Button)findViewById(R.id.btnadd);
        mRecyclerView.setHasFixedSize(true);
        // layoutManager=new LinearLayoutManager(this);
        mLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

       // mExampleList.add(new person("","rajawat","cse"));
        //mExampleList.add(new person("sarmesh","rajawat","cse"));
       add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1=name.getText().toString();
                String branch1=branch.getText().toString();
                String component1=component.getText().toString();
                mExampleList.add(new person(name1,branch1,component1));
                savedata();
                mAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this,"entry is added successfully",Toast.LENGTH_SHORT).show();


            }
        });

    }

    /*public void insertItem(int position) {
        mExampleList.add(position, new person("sarmesh","rajawat","sarmesh"));
        mAdapter.notifyItemInserted(position);
    }*/

    /*public void removeItem(int position) {
        mExampleList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }*/

    /*public void changeItem(int position, String text) {
        mExampleList.get(position).changeText1(text);
        mAdapter.notifyItemChanged(position);
    }*/
    public void savedata(){
        SharedPreferences sharedPreferences=getSharedPreferences("sharedpreferences",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(mExampleList);
        editor.putString("library",json);
        editor.apply();

    }
    public ArrayList<person> loaddata(){
        SharedPreferences sharedPreferences=getSharedPreferences("sharedpreferences",MODE_PRIVATE);
        Gson gson=new Gson();
        mExampleList=new ArrayList<person>();
        String json=sharedPreferences.getString("library",null);
        Type type=new TypeToken<ArrayList<person>>(){}.getType();
        mExampleList=gson.fromJson(json,type);
        if(mExampleList==null){
            mExampleList=new ArrayList<>();
        }
        return mExampleList;
    }
    public void removeitem(int position){
         final int position1=position;
        AlertDialog.Builder altdial = new AlertDialog.Builder(MainActivity.this);
        altdial.setMessage("Do you want to DELETE this entry ???").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //finish();
                        mExampleList.remove(position1);
                        mAdapter.notifyItemRemoved(position1);
                        savedata();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = altdial.create();
        alert.setTitle("Dialog Header");
        alert.show();

    }

    public void createExampleList() {

       // mExampleList.add(new person("sarmesh","rajawat","cse"));
       // mExampleList.add(new person("sarmesh","rajawat","cse"));

       // mExampleList.add(new person("sarmesh","rajawat","sarmesh"));
       //mExampleList.add(new person("sarmesh","rajawat","sarmesh"));
    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new libraryadapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new libraryadapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
               // changeItem(position, "Clicked");
            }

            @Override
            public void onDeleteClick(int position) {
                removeitem(position);
            }
        });
    }
}
