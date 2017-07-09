package com.example.mouna.wouldyourather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static android.R.attr.max;

public class MainActivity extends AppCompatActivity {

    Question question;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button next_question = (Button)findViewById(R.id.next_question);
        final TextView question_string = (TextView) findViewById(R.id.question_text);
        final Button answer1 = (Button)findViewById(R.id.answer1);
        final Button answer2= (Button)findViewById(R.id.answer2);
        final TextView percentage1 = (TextView)findViewById(R.id.pourcentage1);
        final TextView percentage2 = (TextView)findViewById(R.id.pourcentage2);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("questions");

        // add data to database; code hétha 4alet, tawa just lina,
        // ba3ad lazem yetbadal
        createQuestion("4neya sam3ouhélék?", "hmemea taret", "mark mark", myRef);
        createQuestion("4neya t7ebha?", "hmemea taret", "mark mark", myRef);
        createQuestion("4neya tekraha?", "hmemea taret", "mark mark", myRef);


        next_question.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    //read from the database
                        myRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot != null) {
                                    Random random = new Random();
                                    int index = random.nextInt((int) dataSnapshot.getChildrenCount());
                                    int count = 0;
                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                        if (count == index) {
                                            question = snapshot.getValue(Question.class);

                                            //display the question
                                            question_string.setText(question.getQuetionString());
                                            answer1.setText(question.getAnswer1());
                                            answer2.setText(question.getAnswer2());
                                            return;
                                        }
                                        count++;
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


                    }
                }
        );

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // update the database
                myRef.child("questions").child(String.valueOf(index)).child("compteur1").setValue(question.getCompteur1()+1);
                // show percentage
                percentage1.setText(question.getCompteur1()/(question.getCompteur1()+question.getCompteur2())*100);
                percentage2.setText(question.getCompteur2()/(question.getCompteur1()+question.getCompteur2())*100);


            }
        });


    }

    public void createQuestion(String question_string, String answer1 ,String answer2, DatabaseReference reference) {
        String questionId = reference.push().getKey();
        Question question = new Question(question_string, answer1, answer2, 0, 0);
        reference.child(questionId).setValue(question);
    }
}
