package com.example.calvin.flashcard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashCards;
    int currentCardDisplayedIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize flashCardDatabase variable
        flashcardDatabase = new FlashcardDatabase(this);
        //Initialize allFlashCards
        allFlashCards = flashcardDatabase.getAllCards();

        currentCardDisplayedIndex = 0;

        if (allFlashCards != null && allFlashCards.size() > 0) {
            ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashCards.get(0).getQuestion());
            ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashCards.get(0).getAnswer());
        }

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
        //Set Onclicklistener to > icon
        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentCardDisplayedIndex++;
                if (currentCardDisplayedIndex > allFlashCards.size() - 1) {
                    currentCardDisplayedIndex = 0;
                }
                ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashCards.get(currentCardDisplayedIndex).getQuestion());
                ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashCards.get(currentCardDisplayedIndex).getAnswer());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            try {
                String question = data.getExtras().getString("Question");
                String answer = data.getExtras().getString("Answer");
                flashcardDatabase.insertCard(new Flashcard(question, answer));
                ((TextView) findViewById(R.id.flashcard_question)).setText(question);
                ((TextView) findViewById(R.id.flashcard_answer)).setText(answer);
            }
            catch (Exception e) {

            }
        }
    }
}
