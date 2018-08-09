package com.example.gmcalixto.pesquisahttp;

import android.graphics.drawable.*;

import java.io.InputStream;
import java.net.URL;


public class ImageToolkit {

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }
}
