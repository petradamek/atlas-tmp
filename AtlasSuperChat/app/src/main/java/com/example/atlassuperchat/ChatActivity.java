package com.example.atlassuperchat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ChatActivity extends AppCompatActivity {

    private DatabaseReference globalChatMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        //FirebaseApp.initializeApp(this);
        globalChatMessages = FirebaseDatabase.getInstance().getReference().child("globalChatMessages");

    }

    @Override
    protected void onStart() {
        super.onStart();
        initUpdateListener();
    }

    private void initUpdateListener() {
        globalChatMessages.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //Do something with the individual node here`enter code here
                TextView textViewMessages = findViewById(R.id.textViewMessages);
                Message message = dataSnapshot.getValue(Message.class);

                textViewMessages.append(message.getFrom() + ": " + message.getText() + "\n");
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }

    @Override
    protected void onStop() {

        super.onStop();
    }

    public void sendMessage(View view) {

        String from = "Petr";
        EditText editTextNewMessage = findViewById(R.id.editTextNewMessage);
        String  text = editTextNewMessage.getText().toString();

        Message message = new Message(text, from);

        DatabaseReference newMessageReference = globalChatMessages.push();
        newMessageReference.setValue(message);

    }
}
