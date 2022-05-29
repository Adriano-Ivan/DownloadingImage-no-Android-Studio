package com.example.downloadingimage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText srcTextLink;
    private ImageButton imgButtonClick;
    private ImageView imgView;
    private ProgressBar pgbProgresso;
    private TextView txtNaoEncontrada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        srcTextLink = findViewById(R.id.srcTextLink);
        imgButtonClick = findViewById(R.id.imgButtonClick);
        imgView = findViewById(R.id.imgView);
        pgbProgresso = findViewById(R.id.pgbProgresso);
        txtNaoEncontrada = findViewById(R.id.txtNaoEncontrada);

        imgButtonClick.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ProcessamentoDeDownloadDeImagem task = new ProcessamentoDeDownloadDeImagem(
                srcTextLink,imgButtonClick,imgView,pgbProgresso,txtNaoEncontrada
        );
        task.execute(srcTextLink.getText().toString());
    }
}