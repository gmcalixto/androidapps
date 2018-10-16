package com.example.gmcalixto.exemplowebsocket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class MainActivity extends AppCompatActivity {


    private MyWebSocketListener listener;
    private TextView chatText;
    private OkHttpClient client;
    private WebSocket ws;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chatText = findViewById(R.id.chatText);
        client = new OkHttpClient();

        startWebSocket();
    }

    public void startWebSocket(){

        Request req = new Request.Builder().url("ws://echo.websocket.org").build();

        listener = new MyWebSocketListener(chatText,this);
        ws = client.newWebSocket(req,listener);

        client.dispatcher().executorService().shutdown();

    }

    public void start(View view){
        startWebSocket();
    }

    public void sendMessage(View view){
        TextView msg = findViewById(R.id.txtMessage);
        chatText.setMovementMethod(new ScrollingMovementMethod());
        chatText.setText(chatText.getText().toString() + "\n\n Enviei: " + msg.getText().toString());
        ws.send(msg.getText().toString());
    }

    @Override
    protected void onDestroy() {
        ws.close(1000, "Fechando");
        super.onDestroy();
    }
}
