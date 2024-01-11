package com.example.canwesaychallengeor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    /*
     * Declaration of objects of the pallets
     * and other objects
     * */

   private Context context;
Activity activity;
ArrayList<String> filteredData;
 ArrayList book_id,book_title,book_author,book_location;

/*
* Context -Interface to global information about an application environment.
*  This is an abstract class whose implementation is provided by the Android system.
* It allows access to application-specific resources and classes, as well as up-calls
* for application-level operations such as launching activities,
* broadcasting and receiving intents, etc
* */
int position;
    CustomAdapter(Activity activity,Context context, ArrayList book_id, ArrayList book_title, ArrayList book_author, ArrayList book_location){
       this.activity=activity;
       this.book_id=book_id;
        this.book_title=book_title;
       this.book_author=book_author;
       this.book_location=book_location;

    }
// End of CustomAdapter Constructor


    /*
    * OnCreate Method Of the class
    * */
    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.activity_custom_adapter,parent,false);
        return new MyViewHolder(view);
   }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.book_id_txt.setText(String.valueOf(book_id.get(position)));
        holder.book_title_txt.setText(String.valueOf(book_title.get(position)));
        holder.book_author_txt.setText(String.valueOf(book_author.get(position)));
         holder.book_location_txt.setText(String.valueOf(book_location.get(position)));

        /*
         * Add extended data to the intent. The name must include a package prefix,
         * for example the app com.android.contacts would use names
         * like "com.android.contacts.ShowAll".
         *
         * */
         holder.mainlayout.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
               Intent intent =new Intent(context, UpdatePage.class);
                intent.putExtra("id",String.valueOf(book_id.get(position)));
                intent.putExtra("title",String.valueOf(book_title.get(position)));
                intent.putExtra("author",String.valueOf(book_author.get(position)));
               intent.putExtra("location",String.valueOf(book_location.get(position)));

               activity.startActivityForResult(intent,1);
            }
        });
    }

   @Override
    public int getItemCount() {
        return book_id.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView book_id_txt,book_title_txt,book_author_txt,book_location_txt;
    LinearLayout mainlayout;
        public MyViewHolder(@NonNull View itemView) {
           super(itemView);
            book_id_txt=itemView.findViewById(R.id.book_id_txt);
            book_title_txt=itemView.findViewById(R.id.book_title_txt);
            book_author_txt=itemView.findViewById(R.id.book_author_txt);
            book_location_txt=itemView.findViewById(R.id.book_location_txt);
            mainlayout=itemView.findViewById(R.id.mainlayout);

       }
    }

}

