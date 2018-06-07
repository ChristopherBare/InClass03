package com.christopherbare.inclass03.feature;
/**
 * Christopher Bare
 * Assignment 03
 * 800789199
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import static com.christopherbare.inclass03.feature.MainActivity.STUDENT_KEY;

public class EditActivity extends AppCompatActivity {

    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        setTitle("Third Activity");

        if(getIntent() != null && !getIntent().getExtras().isEmpty()) {

            student = (Student) getIntent().getExtras().getSerializable(STUDENT_KEY);

            if (getIntent().getExtras().getBoolean(DisplayActivity.NAME_KEY) == true) {

                setContentView(R.layout.edit_name);

                final EditText name = (EditText) findViewById(R.id.edit_name_field);
                name.setText(student.getName());

                findViewById(R.id.save_name_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String new_name = name.getText().toString();

                        if (new_name == null || new_name.isEmpty()) setResult(RESULT_CANCELED);
                        else {
                            Intent intent = new Intent();
                            intent.putExtra(DisplayActivity.NAME_KEY, new_name);
                            setResult(RESULT_OK, intent);
                        }
                        finish();
                    }
                });
            }

            if (getIntent().getExtras().getBoolean(DisplayActivity.EMAIL_KEY) == true) {

                setContentView(R.layout.edit_email);

                final EditText email = (EditText) findViewById(R.id.edit_email_field);
                email.setText(student.getEmail());

                findViewById(R.id.save_email_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String new_email = email.getText().toString();

                        if (new_email == null || new_email.isEmpty()) setResult(RESULT_CANCELED);
                        else {
                            Intent intent = new Intent();
                            intent.putExtra(DisplayActivity.EMAIL_KEY, new_email);
                            setResult(RESULT_OK, intent);
                        }
                        finish();
                    }
                });
            }

            if (getIntent().getExtras().getBoolean(DisplayActivity.RADIO_KEY) == true) {

                setContentView(R.layout.edit_dept);

                String currentDept = student.getDepartment();

                final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.edit_dept_radio_group);

                int count = radioGroup.getChildCount();
                for (int i=0;i<count;i++) {
                    RadioButton rb = (RadioButton) radioGroup.getChildAt(i);
                    if (rb.getText().toString().equals(currentDept))
                        radioGroup.check(radioGroup.getChildAt(i).getId());
                }

                findViewById(R.id.save_dept_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        RadioButton radioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
                        String new_dept = radioButton.getText().toString();

                        if (new_dept == null || new_dept.isEmpty()) setResult(RESULT_CANCELED);
                        else {
                            Intent intent = new Intent();
                            intent.putExtra(DisplayActivity.RADIO_KEY, new_dept);
                            setResult(RESULT_OK, intent);
                        }
                        finish();
                    }
                });
            }

            if (getIntent().getExtras().getBoolean(DisplayActivity.MOOD_KEY) == true) {


                setContentView(R.layout.edit_mood);

                int currentMood = student.getMood();
                final SeekBar seekBar = (SeekBar) findViewById(R.id.edit_mood_seekBar);
                seekBar.setProgress(currentMood);

                findViewById(R.id.save_mood_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int new_mood = seekBar.getProgress();

                        Intent intent = new Intent();
                        intent.putExtra(DisplayActivity.MOOD_KEY, new_mood);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
            }

       }

    }
}
