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

    ArrayList<User> users = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        Log.d("pttt", "A - Number of users: " + users.size());

        MyFirebase.getUsers(new CallBack_UsersReady() {
            @Override
            public void usersReady(ArrayList<User> users) {
                refreshList(users);
                Log.d("pttt", "C - Number of users: " + users.size());
            }

            @Override
            public void error() {
                // TODO: 2020-01-08 handle errors
            }

        });

        Log.d("pttt", "B - Number of users: " + users.size());

        /*

//        User temp = new User();
//        User user = new User("Gadi", 111111666, false);
//        myRef.child("Users").child("Yeman").child("Petah-Tikva").child("" + user.getId()).setValue(user);
//
//
//        User user2 = new User("Tomer", 111111777, false);
//        myRef.child("Users").child("Russia").child("Ramat-Gan").child("" + user2.getId()).setValue(user2);





//        Message message = new Message();
//
//        message = new Message()
//                .setTimeStamp(System.currentTimeMillis())
//                .setStatus(0)
//                .setSender(user.getId())
//                .setReceiver(user2.getId())
//                .setContent("Hi Tomer4!");
//
//
//        myRef.child("Chats").child(user.getId() + "-" + user2.getId()).push().setValue(message);


        myRef.child("Users").child("Israel").child("Tel-Aviv").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Log.d("pttt", dataSnapshot.getValue().toString());

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Log.d("pttt", "ds: " + ds.getKey().toString() + " -- " + ds.getValue().toString());
                    User user = ds.getValue(User.class);
                    users.add(user);
                    Log.d("pttt", "ds in: " + user.getName());
                }

                Log.d("pttt", "B - Number of users: " + users.size());

//                //for one item:
//                User user2 = dataSnapshot.getValue(User.class);
//                Log.d("pttt", "2: " + user2.getName());
//
//                // for multi items:
//                for (DataSnapshot child: dataSnapshot.getChildren()) {
//                    Log.d("pttt", child.getValue().toString());
//                    User user = child.getValue(User.class);
//                    Log.d("pttt", user.getName());
//                }
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

         */
    }

    private void refreshList(ArrayList<User> users) {
        // TODO: 2020-01-08 refresh list
    }

}
