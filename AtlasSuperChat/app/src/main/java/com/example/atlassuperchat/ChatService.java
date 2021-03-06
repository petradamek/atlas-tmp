package com.example.atlassuperchat;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ChatService extends Service {

    private DatabaseReference mDatabase;

    private final IBinder mBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        ChatService getService() {
            return ChatService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("globalChatMessages");
        return mBinder;
    }

}
