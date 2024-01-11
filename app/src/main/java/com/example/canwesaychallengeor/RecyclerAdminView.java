package com.example.canwesaychallengeor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
 import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.CDATASection;

import java.util.ArrayList;

public class RecyclerAdminView extends AppCompatActivity {
   RecyclerView recyclerView;
   FloatingActionButton addbtn;
   ImageView empty_Image;
    TextView no_data;
    ArrayList <String> filteredDataOnAdmin;
    ArrayList <String> id,title,author,location;
    DatabaseHandler databaseHandler;
    EditText search;
    CustomAdapter customAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        addbtn=findViewById(R.id.floatingActionButton);
        recyclerView=findViewById(R.id.recyclerview);

        no_data=findViewById(R.id.no_Data);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(RecyclerAdminView.this,AdminPage.class);
                startActivity(intent);
            }
       });
        databaseHandler =new DatabaseHandler(this);
        id=new ArrayList<> ();
        title=new ArrayList<> ();
        author=new ArrayList<> ();
        location=new ArrayList<> ();

        StoreDataInArray();
        customAdapter =new CustomAdapter(RecyclerAdminView.this,this,id,title,author,location);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(RecyclerAdminView.this));
        search.addTextChangedListener(new TextWatcher() {
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
                filteredDataOnAdmin.add(item);
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
          empty_Image.setVisibility(View.VISIBLE);
           no_data.setVisibility(View.VISIBLE);
       }
       else {
            while(cursor.moveToNext()){
                id.add(cursor.getString(0));
               title.add(cursor.getString(1));
               author.add(cursor.getString(2));
               location.add(cursor.getString(3));
            }
            empty_Image.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
   }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.delete_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.deleteAll){
            confirmDialog();
           Toast.makeText(this, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
        }

        return super.onContextItemSelected(item);
   }

    void confirmDialog(){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Delete All?" );
        builder.setMessage("Are you sure you want to Delete All Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
           @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseHandler databaseHandler =new DatabaseHandler(RecyclerAdminView.this);
                databaseHandler.DeleteAllData();

               Intent intent = new Intent(RecyclerAdminView.this,RecyclerAdminView.class);
               startActivity(intent);
               finish();

            }
        });
     builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
       builder.create().show();
   }
}
