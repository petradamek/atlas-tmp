package com.example.atlassuperchat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
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

    private final ChildEventListener messagesListener = new ChildEventListener() {

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

    };
    private DatabaseReference globalChatMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        globalChatMessages = FirebaseDatabase.getInstance().getReference().child("globalChatMessages");

        final TextView textViewMessages = findViewById(R.id.textViewMessages);
        textViewMessages.setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                actionId == EditorInfo.IME_ACTION_DONE ||
                                event != null &&
                                        event.getAction() == KeyEvent.ACTION_DOWN &&
                                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            if (event == null || !event.isShiftPressed()) {
                                // the user is done typing.
                                sendMessage(textViewMessages);
                                return true; // consume.
                            }
                        }
                        return false; // pass on to other listeners.
                    }
                }
        );
        textViewMessages.setMovementMethod(new ScrollingMovementMethod());

    }

    @Override
    protected void onStart() {
        super.onStart();
        TextView textViewMessages = findViewById(R.id.textViewMessages);
        textViewMessages.setText("");
        globalChatMessages.addChildEventListener(messagesListener);
    }

    @Override
    protected void onStop() {
        globalChatMessages.removeEventListener(messagesListener);
        super.onStop();
    }

    public void sendMessage(View view) {

        String from = "Petr Adamek";
        EditText editTextNewMessage = findViewById(R.id.editTextNewMessage);
        String  text = editTextNewMessage.getText().toString();
        editTextNewMessage.setText("");

        Message message = new Message(text, from);

        DatabaseReference newMessageReference = globalChatMessages.push();
        newMessageReference.setValue(message);

    }
}
