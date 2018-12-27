package com.gbeilaaliuwahab.saveghanaapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gbeilaaliuwahab.saveghanaapp.Helpers.LocalStore;
import com.gbeilaaliuwahab.saveghanaapp.Helpers.ServerCallClass;
import com.google.gson.JsonObject;

public class MessageActivity extends AppCompatActivity {
    EditText sendMessageTitle, messageBody;
    String title, message;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        sendMessageTitle = (EditText) findViewById(R.id.send_message_title_edit_text);
        messageBody = (EditText) findViewById(R.id.message_body_edit_text);

        send = (Button) findViewById(R.id.send_message_button);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendMessage();
            }
        });
    }

    private void sendMessage() {
        title = sendMessageTitle.getText().toString();
        message = messageBody.getText().toString();


        JsonObject localStoreObject = new LocalStore(MessageActivity.this).readUserObjectAsJson();
        new ServerCallClass(MessageActivity.this).sendMessage(localStoreObject,
                title, message);


    }
}
