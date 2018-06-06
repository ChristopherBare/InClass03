package com.christopherbare.inclass03.feature;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        setTitle("Display Activity");

        if (getIntent() != null && getIntent().getExtras() != null) {
            Student student = (Student) getIntent().getExtras().getSerializable(MainActivity.STUDENT_KEY);

            final TextView name = (TextView) findViewById(R.id.textView);
            final TextView email = (TextView) findViewById(R.id.textView4);
            final TextView department = (TextView) findViewById(R.id.textView5);
            final TextView mood = (TextView) findViewById(R.id.textView6);

            String nameString = student.getName();
            String emailString = student.getEmail();
            String departmentString = student.getDepartment();
            String moodString = student.getMoodString();

            if (nameString != null && emailString != null && departmentString != null) {
                name.setText("Name: " + nameString);
                email.setText("Email: " + emailString);
                department.setText("Department: " + departmentString);
                mood.setText("Mood: " + moodString);
            }

        }

    }
}
