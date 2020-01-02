package com.guy.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        User user = new User("Gadi", 111111666, false);
        myRef.child("Users").child("Yeman").child("Petah-Tikva").child("" + user.getId()).setValue(user);


        User user2 = new User("Tomer", 111111777, false);
        myRef.child("Users").child("Russia").child("Ramat-Gan").child("" + user2.getId()).setValue(user2);





        Message message = new Message();

        message = new Message()
                .setTimeStamp(System.currentTimeMillis())
                .setStatus(0)
                .setSender(user.getId())
                .setReceiver(user2.getId())
                .setContent("Hi Tomer4!");


        myRef.child("Chats").child(user.getId() + "-" + user2.getId()).push().setValue(message);


        myRef.child("Chats").child(user.getId() + "-" + user2.getId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Log.d("pttt", dataSnapshot.getValue().toString());
//                String value = dataSnapshot.getValue(String.class);
//                Log.d("pttt", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("pttt", "Failed to read value.", error.toException());
            }
        });



        HashMap<String, ArrayList<Message>> data = new HashMap<>();

        data.put("190120", new ArrayList<Message>());
        data.get("190120").add(new Message());

        long now = System.currentTimeMillis();
        String date = new SimpleDateFormat("ddMMyy").format(now);


        ArrayList<Message> todayEvents = data.get(date);
    }
}
