package com.example.canwesaychallengeor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.CDATASection;

import java.util.ArrayList;

public class RecyclerUserView extends AppCompatActivity {
   RecyclerView recyclerView;
    EditText searchuser;
    ArrayList <String> filteredDataOnUser;
   ArrayList <String> id,title,author,location;
    DatabaseHandler databaseHandler;
   CustomAdapter customAdapter;
    @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_user_view);

       recyclerView=findViewById(R.id.userRecyclerView);
       searchuser=findViewById(R.id.searchuser);



       databaseHandler =new DatabaseHandler(this);
       id=new ArrayList<> ();
       title=new ArrayList<> ();
       author=new ArrayList<> ();
       location=new ArrayList<> ();
       StoreDataInArray();
     customAdapter =new CustomAdapter(RecyclerUserView.this,this,id,title,author,location);
     recyclerView.setLayoutManager(new LinearLayoutManager(RecyclerUserView.this));
     recyclerView.setAdapter(customAdapter);
     searchuser.addTextChangedListener(new TextWatcher() {
         @Override
         public void beforeTextChanged(CharSequence s, int start, int count, int after) {

         }

         @Override
         public void onTextChanged(CharSequence s, int start, int before, int count) {

         }

         @Override
         public void afterTextChanged(Editable s) {
            filter(s.toString());
         }
     });

   }

    @SuppressLint("NotifyDataSetChanged")
    private void filter(String string_text) {
    ArrayList<String> stringArrayList=new ArrayList<>();
    stringArrayList=customAdapter.filteredData;

        for(String item:stringArrayList){
            if(item.toLowerCase().contains(string_text.toLowerCase())){
                filteredDataOnUser.add(item);
            }
        }
        customAdapter.notifyDataSetChanged();
    }



   @Override
   protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
       super.onActivityResult(requestCode, resultCode, data);
       if(requestCode==1){
            recreate();
       }
   }

    public void StoreDataInArray(){
        Cursor cursor= databaseHandler.FetchData();
       if(cursor.getCount()==0){
            Toast.makeText(this, "There is no data in DataBase!", Toast.LENGTH_SHORT).show();
        }
        else {
           while(cursor.moveToNext()){
               id.add(cursor.getString(0));
               title.add(cursor.getString(1));
               author.add(cursor.getString(2));
               location.add(cursor.getString(3));
            }
       }
   }
}
