package com.example.downloadingimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ProcessamentoDeDownloadDeImagem extends AsyncTask<String, Void, Bitmap> {

    private EditText srcTextLink;
    private  ImageButton imgButtonClick;
    private ImageView imgView;
    private ProgressBar pgbProgresso;
    private TextView txtNaoEncontrada;

    public ProcessamentoDeDownloadDeImagem(EditText srcTextLink, ImageButton imgButtonClick,
             ImageView imgView, ProgressBar pgbProgresso, TextView txtNaoEncontrada) {
        this.srcTextLink=srcTextLink;
        this.imgButtonClick=imgButtonClick;
        this.imgView=imgView;
        this.pgbProgresso=pgbProgresso;
        this.txtNaoEncontrada=txtNaoEncontrada;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(strings[0]);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            Bitmap temp = BitmapFactory.decodeStream(inputStream);
            return temp;
        } catch(MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(httpURLConnection != null)
                 httpURLConnection.disconnect();
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        txtNaoEncontrada.setVisibility(View.INVISIBLE);
        imgView.setVisibility(View.INVISIBLE);
        pgbProgresso.setVisibility(View.VISIBLE);
        imgButtonClick.setEnabled(false);
        srcTextLink.setEnabled(false);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(bitmap == null){
            pgbProgresso.setVisibility(View.INVISIBLE);
            txtNaoEncontrada.setVisibility(View.VISIBLE);
            txtNaoEncontrada.setTextColor(Color.parseColor("#ff0000"));

        } else {
            txtNaoEncontrada.setVisibility(View.INVISIBLE);
            pgbProgresso.setVisibility(View.INVISIBLE);
            imgView.setVisibility(View.VISIBLE);
            imgView.setImageBitmap(bitmap);
        }

        imgButtonClick.setEnabled(true);
        srcTextLink.setEnabled(true);
    }
}
