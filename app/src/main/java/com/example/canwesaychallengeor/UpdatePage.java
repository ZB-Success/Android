 package com.example.canwesaychallengeor;

 import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdatePage extends AppCompatActivity {
   EditText title,author,location;
   Button updatebtn,delete;
   String id,l_title,l_author,l_location;
   DatabaseHandler databaseHandler;
   @SuppressLint("MissingInflatedId")
   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_update_page);
       title=findViewById(R.id.ubooktitle);
       author=findViewById(R.id.ubook_author);
        location=findViewById(R.id.ubook_location);
       updatebtn=findViewById(R.id.uadd_btn);
       delete=findViewById(R.id.delete_btn);



       getandsetdata();
        ActionBar ab=getSupportActionBar();
        if(ab!=null){
            ab.setTitle(l_title);
        }


       updatebtn.setOnClickListener(new View.OnClickListener() {
           @Override
          public void onClick(View v) {
               databaseHandler=new DatabaseHandler(UpdatePage.this);
                databaseHandler.UpdateData(id,l_title,l_author,l_location);
           }
      });
       delete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               databaseHandler.deletedata(id);
            }
        });

   }
   public void getandsetdata(){
       if(getIntent().hasExtra("id") &&
               getIntent().hasExtra("title") &&
               getIntent().hasExtra("author") &&
                getIntent().hasExtra("location") ){
            //getting data
             id= getIntent().getStringExtra("id");
           l_title= getIntent().getStringExtra("title");
           l_author= getIntent().getStringExtra("author");
           l_location= getIntent().getStringExtra("location");
          // setting data
           title.setText(l_title);
           author.setText(l_author);
           location.setText(l_location);
       }
       else{
           Toast.makeText(this, "No Data!", Toast.LENGTH_SHORT).show();
        }
   }
   void confirmDialog(){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
       builder.setTitle("Delete?");
       builder.setMessage("Are you sure you want to Delete"+title);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
           public void onClick(DialogInterface dialog, int which) {
               DatabaseHandler databaseHandler =new DatabaseHandler(UpdatePage.this);
               databaseHandler.deletedata(id);
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
