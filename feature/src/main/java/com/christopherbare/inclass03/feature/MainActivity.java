package com.christopherbare.inclass03.feature;
/**
 * Christopher Bare
 * Assignment 03
 * 800789199
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    final static String STUDENT_KEY = "STUDENT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText name = (EditText) findViewById(R.id.editText2);
        final EditText email = (EditText) findViewById(R.id.editText);
        final RadioGroup departmentGroup = (RadioGroup) findViewById(R.id.radioGroup);
        final SeekBar mood = (SeekBar) findViewById(R.id.seekBar2);


        Button submit = (Button) findViewById(R.id.button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int currentMood = mood.getProgress();
                RadioButton department = (RadioButton) findViewById(departmentGroup.getCheckedRadioButtonId());
                Student student = new Student(name.getText().toString(), email.getText().toString(), department.getText().toString(), currentMood);
                Log.d("info", student.toString());

                if (!student.getName().equals("") || !student.getEmail().equals("")) {
                    Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                    intent.putExtra(STUDENT_KEY, student);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter all the required fields!",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
