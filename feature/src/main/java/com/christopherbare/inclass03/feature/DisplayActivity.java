package com.christopherbare.inclass03.feature;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class DisplayActivity extends AppCompatActivity {
    final static String STUDENT_KEY = "STUDENT";
    Student passStudent;

    final static String NAME_KEY = "NAME";
    final static String EMAIL_KEY = "EMAIL";
    final static String RADIO_KEY = "RADIO";
    final static String MOOD_KEY = "MOOD";

    final static int NAME_CODE = 0;
    final static int EMAIL_CODE = 1;
    final static int RADIO_CODE = 2;
    final static int MOOD_CODE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        setTitle("Display Activity");

        if (getIntent() != null && getIntent().getExtras() != null) {
            Student student = (Student) getIntent().getExtras().getSerializable(MainActivity.STUDENT_KEY);
            passStudent = student;

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

        ImageButton editName = (ImageButton) findViewById(R.id.imageButton3);
        ImageButton editEmail = (ImageButton) findViewById(R.id.imageButton4);
        ImageButton editDepartment = (ImageButton) findViewById(R.id.imageButton5);
        ImageButton editMood = (ImageButton) findViewById(R.id.imageButton6);

        editName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayActivity.this, EditActivity.class);
                intent.putExtra(MainActivity.STUDENT_KEY, passStudent);
                intent.putExtra(NAME_KEY, true);
                startActivityForResult(intent, NAME_CODE);
            }
        });

        editEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayActivity.this, EditActivity.class);
                intent.putExtra(MainActivity.STUDENT_KEY, passStudent);
                intent.putExtra(EMAIL_KEY, true);
                startActivityForResult(intent, EMAIL_CODE);
            }
        });

        editDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayActivity.this, EditActivity.class);
                intent.putExtra(MainActivity.STUDENT_KEY, passStudent);
                intent.putExtra(RADIO_KEY, true);
                startActivityForResult(intent, RADIO_CODE);
            }
        });

        editMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayActivity.this, EditActivity.class);
                intent.putExtra(MainActivity.STUDENT_KEY, passStudent);
                intent.putExtra(MOOD_KEY, true);
                startActivityForResult(intent, MOOD_CODE);
            }
        });

    }


    //Handle the information that comes back when the student edits his/her information
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {

            //if name changed
            if (requestCode == NAME_CODE) {
                passStudent.setName(data.getExtras().getString(NAME_KEY));
                TextView name_view = (TextView) findViewById(R.id.textView);
                name_view.setText("Name: " + passStudent.getName());
            }

            //if email changed
            if (requestCode == EMAIL_CODE) {
                passStudent.setEmail(data.getExtras().getString(EMAIL_KEY));
                TextView email_view = (TextView) findViewById(R.id.textView4);
                email_view.setText("Email: " + passStudent.getEmail());
            }

            //if department changed
            if (requestCode == RADIO_CODE) {
                passStudent.setDepartment(data.getExtras().getString(RADIO_KEY));
                TextView radio_view = (TextView) findViewById(R.id.textView5);
                radio_view.setText("Department: " + passStudent.getDepartment());
            }

            //if mood changed
            if (requestCode == MOOD_CODE) {
                passStudent.setMood(data.getExtras().getInt(MOOD_KEY));
                TextView mood_view = (TextView) findViewById(R.id.textView6);
                mood_view.setText("Mood: " + Integer.toString(passStudent.getMood()));
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "Update denied.", Toast.LENGTH_LONG).show();
        }
    }

}
