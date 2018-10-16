package com.example.gmcalixto.exemplowebsocket;

import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import okhttp3.Response;
import okhttp3.WebSocket;
import okio.ByteString;

public final class MyWebSocketListener extends okhttp3.WebSocketListener {


    private TextView output;
    private AppCompatActivity activity;

    public MyWebSocketListener(TextView output, AppCompatActivity act) {
        this.output = output;
        this.activity = act;
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        webSocket.send("Iniciando Websocket");
        //webSocket.close(1000,"Fechando");
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        updateTextView(text);
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        updateTextView(bytes.hex());
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        webSocket.close(1000, null);
        updateTextView("Encerrado : " + code + " / " + reason);


    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        updateTextView("Erro: " + t.getMessage());
    }


    public void updateTextView(final String text){
        this.activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                output.setMovementMethod(new ScrollingMovementMethod());
                output.setText(output.getText().toString() + "\n\n Recebi: " + text);
            }
        });
    }
}
