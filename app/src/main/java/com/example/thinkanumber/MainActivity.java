package com.example.thinkanumber;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.net.ipsec.ike.IkeIdentification;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView kocka1, kocka2;
    private Button egykocka,ketkocka,dobasGomb,ujraindit;
    private TextView kiirtEredmeny;
    private String szoveg;
    private boolean lathatoE;

    public MainActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        init();
        egykocka.setOnClickListener(view -> {
            kocka2.setVisibility(view.GONE);
            lathatoE = false;
        });

        ketkocka.setOnClickListener(view -> {
            kocka2.setVisibility(view.VISIBLE);
            lathatoE = true;
        });

        dobasGomb.setOnClickListener(view -> {
            // TODO: kepValtas();

            String sor = "";
            int eredmeny = 0;
            int szorzas = 1;

            if (lathatoE) {
                szorzas = 2;
            }
            for (int i = 0; i < szorzas; i++) {
                if(i == 0) {
                    int randomszam = (int) (Math.random()) * 6 + 1;
                    if(!lathatoE) {
                        szoveg += randomszam + "\n";
                    }
                    eredmeny += randomszam;
                    sor += "(" + randomszam + "+";
                    beallitas(randomszam,kocka1);

                }else {
                    int randomszam = (int) (Math.random()*6) + 1;
                    sor +=  randomszam + ")\n";
                    eredmeny += randomszam;
                    szoveg += eredmeny + sor;
                    beallitas(randomszam,kocka2);
                }
            }
            Toast.makeText(MainActivity.this, eredmeny + "", Toast.LENGTH_SHORT).show();
            kiirtEredmeny.setText(szoveg);
        });

        ujraindit.setOnClickListener(view -> {
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
            alertBuilder.setTitle("Reset");
            alertBuilder.setMessage("Biztos törölni szeretné az eddigi dobásait?");
            alertBuilder.setMessage("Szeretne új játékot kezdeni?");
            alertBuilder.setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    closeContextMenu();
                }
            });
            alertBuilder.setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ujrajatszas();
                    closeContextMenu();
                }
            });
            alertBuilder.create().show();
        });
    }


    private void init() {
        kocka1 = findViewById(R.id.kocka1);
        kocka2 = findViewById(R.id.kocka2);
        egykocka = findViewById(R.id.egykocka);
        ketkocka = findViewById(R.id.ketkocka);
        dobasGomb = findViewById(R.id.dobas);
        ujraindit = findViewById(R.id.reset);
        kiirtEredmeny = findViewById(R.id.iras);
        szoveg = "";
        lathatoE = true;

    }

    private void kepValtas() {
        int[] kepek = {
                R.drawable.kocka1,
                R.drawable.kocka2,
                R.drawable.kocka3,
                R.drawable.kocka4,
                R.drawable.kocka5,
                R.drawable.kocka6,
        };

        Handler kezeles = new Handler();
        Runnable futtathato = new Runnable() {
            int i = (int)Math.random() * 6 + 1;

            @Override
            public void run() {
                kocka1.setImageResource(kepek[i]);
                kocka2.setImageResource(kepek[i]);
                i++;
                if (i > kepek.length - 1){
                        i = 0;
                }
                kezeles.postDelayed(this,300);
            }
        };
        kezeles.postDelayed(futtathato,300);
    }

    private void ujrajatszas() {
        szoveg = "";
        kiirtEredmeny.setText("");
        kocka1.setImageResource(R.drawable.kocka1);
        kocka2.setImageResource(R.drawable.kocka1);
        kocka2.setVisibility(View.VISIBLE);
        lathatoE = true;
    }

    private void beallitas(int randszam, ImageView kep) {
        switch (randszam) {
            case 1:
                kep.setImageResource(R.drawable.kocka1);
                break;
            case 2:
                kep.setImageResource(R.drawable.kocka2);
                break;
            case 3:
                kep.setImageResource(R.drawable.kocka3);
                break;
            case 4:
                kep.setImageResource(R.drawable.kocka4);
                break;
            case 5:
                kep.setImageResource(R.drawable.kocka5);
                break;
            case 6:
                kep.setImageResource(R.drawable.kocka6);
                break;
        }
    }
}