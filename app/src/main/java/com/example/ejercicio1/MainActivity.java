package com.example.ejercicio1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private AsynTaskClass asynTaskClass;
    private Button buttonBackUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonBackUp = (Button) findViewById(R.id.buttonBackup);
        buttonBackUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonBackup:
                asynTaskClass = new AsynTaskClass();
                asynTaskClass.execute();
                break;
        }
    }

    public class  AsynTaskClass extends AsyncTask<Void,Integer,Boolean>{

        ProgressDialog progressDialog;
        @Override
        protected Boolean doInBackground(Void... voids) {
            for (int i = 1; i <= 10 && !isCancelled(); i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progressDialog.setProgress(i*10);
            }
            return true;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Copia de Seguridad");
            progressDialog.setMessage("se estan copiando los datos");
            progressDialog.setProgress(0);
            progressDialog.setMax(100);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean) Toast.makeText(getApplicationContext(),"Tarea finalizada ",Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int progress = values[0].intValue();
            progressDialog.setProgressStyle(progress);
        }

    }
}