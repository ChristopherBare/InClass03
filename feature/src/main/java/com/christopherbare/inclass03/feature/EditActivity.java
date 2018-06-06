package com.christopherbare.inclass03.feature;

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

        //Check to see if information was passed from Second Activity
        if(getIntent() != null && !getIntent().getExtras().isEmpty()) {

            ///Create the student from the student object passed through the intent from the Second Activity
            student = (Student) getIntent().getExtras().getSerializable(STUDENT_KEY);

            //Edit name handler
            if (getIntent().getExtras().getBoolean(DisplayActivity.NAME_KEY) == true) {

                //Set the layout to edit the name
                setContentView(R.layout.edit_name);

                //Fill name field with current name
                final EditText name = (EditText) findViewById(R.id.edit_name_field);
                name.setText(student.getName());

                //Send information back when save button is clicked
                findViewById(R.id.save_name_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Get the new name
                        String new_name = name.getText().toString();

                        //Return the result
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

            //Edit email handler
            if (getIntent().getExtras().getBoolean(DisplayActivity.EMAIL_KEY) == true) {

                //Set the layout to edit the email
                setContentView(R.layout.edit_email);

                //Fill name field with current email
                final EditText email = (EditText) findViewById(R.id.edit_email_field);
                email.setText(student.getEmail());

                //Send information back when save button is clicked
                findViewById(R.id.save_email_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Get the new email
                        String new_email = email.getText().toString();

                        //Return the result
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

            //Edit department handler
            if (getIntent().getExtras().getBoolean(DisplayActivity.RADIO_KEY) == true) {

                //Set the layout to edit the department
                setContentView(R.layout.edit_dept);

                //Get the current department
                String currentDept = student.getDepartment();

                //Get the radio group
                final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.edit_dept_radio_group);

                //Check the radio button that corresponds to the student's current department
                int count = radioGroup.getChildCount();
                for (int i=0;i<count;i++) {
                    RadioButton rb = (RadioButton) radioGroup.getChildAt(i);
                    if (rb.getText().toString().equals(currentDept))
                        radioGroup.check(radioGroup.getChildAt(i).getId());
                }

                //Send information back when save button is clicked
                findViewById(R.id.save_dept_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Get the new department
                        RadioButton radioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());
                        String new_dept = radioButton.getText().toString();

                        //Return the result
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

            //Edit mood handler
            if (getIntent().getExtras().getBoolean(DisplayActivity.MOOD_KEY) == true) {

                //Set the layout to edit the department
                setContentView(R.layout.edit_mood);

                //Set the slider to the current mood
                int currentMood = student.getMood();
                final SeekBar seekBar = (SeekBar) findViewById(R.id.edit_mood_seekBar);
                seekBar.setProgress(currentMood);

                //Send information back when save button is clicked
                findViewById(R.id.save_mood_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Get the new mood
                        int new_mood = seekBar.getProgress();

                        //Return the result
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
