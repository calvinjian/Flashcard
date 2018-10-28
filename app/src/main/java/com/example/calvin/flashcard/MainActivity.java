package com.example.calvin.flashcard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Set the Flashcard's answer to visible and the Flashcard's question to invisible
        findViewById(R.id.flashcard_question).setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
               findViewById(R.id.flashcard_question).setVisibility(View.INVISIBLE);
               findViewById(R.id.flashcard_answer).setVisibility(View.VISIBLE);
           }
        });
        //Set the Flashcard's question to visible and the Flashcard's answer to invisible
        findViewById(R.id.flashcard_answer).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);
                findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE);
            }
        });
        //Set Onclicklistener to + icon
        findViewById(R.id.addBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            String question = data.getExtras().getString("Question");
            String answer = data.getExtras().getString("Answer");
            ((TextView) findViewById(R.id.flashcard_question)).setText(question);
            ((TextView) findViewById(R.id.flashcard_answer)).setText(answer);
        }
    }
}
