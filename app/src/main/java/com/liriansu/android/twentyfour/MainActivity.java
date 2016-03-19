package com.liriansu.android.twentyfour;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void beginClassicGame (View view) {
        Intent intent = new Intent(this, ClassicActivity.class);
        startActivity(intent);
    }

    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.closing_title)
                .setMessage(R.string.closing_prompt)
                .setPositiveButton(R.string.closing_confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton(R.string.closing_decline, null)
                .show();
    }
}
