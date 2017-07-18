package com.javaandroid.main;


import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    ConstraintLayout constraintLayout;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constraintLayout= (ConstraintLayout) findViewById(R.id.constraintLayout);
        btn1= (Button) findViewById(R.id.fab1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "点击了", Toast.LENGTH_SHORT).show();
                SnackbarUtil.LongSnackbar(constraintLayout,"你好",1).show();
            }
        });

    }

}
