package com.example.canwesaychallengeor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AdminPage extends AppCompatActivity {
   /*
    * Declaration of objects of the pallets
     * and other objects
     * */

    EditText title, author, location;
   ImageView image;
   Button btnadd;
   //Spinner category ,chooseimgbtn;
   DatabaseHandler databaseHandler;


   @SuppressLint("MissingInflatedId")
   @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_admin_page);
       /*
       * Get ids of the layout fields
       */
       title=findViewById(R.id.ubooktitle);
        author =findViewById(R.id.ubook_author);
       location=findViewById(R.id.ubook_location);
       btnadd=findViewById(R.id.Button_Add);




/*
* On Button click validate the fields that we receive inputs
* and Instantiating Database object
*/
       btnadd.setOnClickListener(new View.OnClickListener() {
        @Override
       public void onClick(View v) {
            if(title.getText().toString().equals("")
                    || author.getText().toString().equals("")
                    || location.getText().toString().equals("") )
            {
               title.setError("This field Is required");
                author.setError("This filed Is required");
               location.setError("This filed Is required");
           }
            else{
                databaseHandler=new DatabaseHandler(AdminPage.this);
                databaseHandler.AddBook(title.getText().toString().trim(), author.getText().toString().trim(), location.getText().toString().trim());
                Toast.makeText(AdminPage.this, "Book Added Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(AdminPage.this,UpdatePage.class);
                startActivity(intent);
            }





        }
   });




    }
}



