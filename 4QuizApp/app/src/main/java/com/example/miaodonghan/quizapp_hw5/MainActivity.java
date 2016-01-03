package com.example.miaodonghan.quizapp_hw5;

import android.app.Activity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;



public class MainActivity extends Activity {

    TextView textMsg, textPrompt;
    final String textSource = "http://www.papademas.net/sample.txt";

    List<String> stringList = new ArrayList<String>();


    static int questionNum=0;

    private RadioGroup radioQuestions;
    private RadioButton radioButton;
    private Button btnDisplay;
    RatingBar rb;
    ImageView image;
    TextView question;



    static int correctNumber =0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // processing asynchronous
        // because SDK I installed is Android 5.0
        RetrieveTextTask asyncTask = new RetrieveTextTask();
        asyncTask.execute(textSource); // run in backgroud

        startQuiz();

        try {
            stringList = asyncTask.get();
            question = (TextView)findViewById(R.id.textView1);
            question.setText("1. "+stringList.get(0));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }//end onCreate

    public void startQuiz() {
        buttonListener();
    }

    public void buttonListener() {

        radioQuestions = (RadioGroup) findViewById(R.id.radioQuestions);
        btnDisplay = (Button) findViewById(R.id.btnDisplay);
        imageListener();

        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = radioQuestions.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);

                switch (questionNum) {
                    case 0:
                        //verify if result matches the right button selection (True or false!)

                        if (radioButton.getText().equals("True"))
                            Toast.makeText(MainActivity.this,
                                    " Wrong! Android's current OS should be Marshmallow.", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this,
                                    " Right!", Toast.LENGTH_SHORT).show();
                        buttonListener();

                        break;
                    case 1:
                        //verify if result matches the right button selection (True or false!)

                        if (radioButton.getText().equals("False"))
                            Toast.makeText(MainActivity.this,
                                    " Right!", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this,
                                    " Wrong! SDK stands for Software Development Kit", Toast.LENGTH_SHORT).show();
                        buttonListener();

                        break;

                    //finish switch cases 2-4
                    case 2:

                        //verify if result matches the right button selection (True or false!)
                        if (radioButton.getText().equals("True"))
                            Toast.makeText(MainActivity.this,
                                    " Right!", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this,
                                    " Wrong!", Toast.LENGTH_SHORT).show();

                        buttonListener();
                        break;
                    case 3:

                        //verify if result matches the right button selection (True or
                        //false!)
                        if (radioButton.getText().equals("False"))
                            Toast.makeText(MainActivity.this,
                                    " Right!", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this,
                                    " Wrong! AVD stands for Andriod Virtual Device.", Toast.LENGTH_SHORT).show();

                        buttonListener();
                        break;
                    case 4:

                        //verify if result matches the right button selection (True or
                        //false!)
                        if (radioButton.getText().equals("False")) {
                            correctNumber++;
                            Toast.makeText(MainActivity.this,
                                    " Right!", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(MainActivity.this,
                                    " Wrong! Andriod's current SDK version is 6.0", Toast.LENGTH_SHORT).show();

                        //rb =(RatingBar)findViewById(R.id.ratingBar);


                        rb =(RatingBar)findViewById(R.id.ratingBar);
                        rb.setRating((float) correctNumber);
                        rb.setVisibility(View.VISIBLE);
                        //String rating = String.valueOf(rb.getRating());
                        //TextView test = (TextView) findViewById(R.id.test);
                        //test.setText(rating);

                        buttonListener();
                        break;
                }//end switch


            }
        });


    }

    public void imageListener() {
        radioQuestions = (RadioGroup) findViewById(R.id.radioQuestions);
        image = (ImageView)findViewById(R.id.imageView1);


        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // get selected radio button from radioGroup
                int selectedId = radioQuestions.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);

                switch (questionNum) {
                    case 0:

                        if (radioButton.getText().equals("False"))
                            correctNumber++;
                        imageListener();
                        break;
                    case 1:

                        if (radioButton.getText().equals("False"))
                            correctNumber++;
                        imageListener();
                        break;
                    case 2:
                        if (radioButton.getText().equals("True"))
                            correctNumber++;
                        imageListener();
                        break;
                    case 3:
                        if (radioButton.getText().equals("False"))
                            correctNumber++;
                        Toast.makeText(MainActivity.this,
                                " This is the last question.", Toast.LENGTH_LONG).show();
                        imageListener();
                        break;
                    case 4:

                        if (radioButton.getText().equals("False"))
                            correctNumber++;
                        Toast.makeText(MainActivity.this,
                                "Do it again", Toast.LENGTH_SHORT).show();
                        imageListener();
                        break;

                }

                // get new question for viewing
                TextView textView = (TextView) findViewById(R.id.textView1);
                if (questionNum == 4) {


                    //reset count to -1 to start first question again
                    questionNum = -1;
                    rb.setVisibility(View.INVISIBLE);
                    correctNumber = 0;

                }
                int num = questionNum+2;
                textView.setText(num + ". " + stringList.get(++questionNum));

                //reset radio button (radioTrue) to default
                //radioQuestions.check(R.id.radioTrue);

            }
        });

    }



}//end activity
