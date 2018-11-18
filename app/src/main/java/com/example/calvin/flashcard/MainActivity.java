package com.example.calvin.flashcard;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashCards;
    int currentCardDisplayedIndex;
    Flashcard cardToEdit;
    ArrayList<TextView> random;
    Flashcard check;
    Flashcard nullFix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cardToEdit = new Flashcard(null, null);
        //Initialize flashCardDatabase variable
        flashcardDatabase = new FlashcardDatabase(this);
        //Initialize allFlashCards
        allFlashCards = flashcardDatabase.getAllCards();

        random = new ArrayList<>();

        nullFix = new Flashcard("Sample Question", "Answer", "Wrong Answer", "Wrong Answer");

        check = new Flashcard(null, null, null, null);

        random.add((TextView) findViewById(R.id.answerOne));
        random.add((TextView) findViewById(R.id.answerTwo));
        random.add((TextView) findViewById(R.id.answerThree));
        Collections.shuffle(random);

        currentCardDisplayedIndex = 0;

        if (allFlashCards != null && allFlashCards.size() > 0) {
            ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashCards.get(0).getQuestion());
            ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashCards.get(0).getAnswer());
            random.get(0).setText(allFlashCards.get(0).getAnswer());
            random.get(1).setText(allFlashCards.get(0).getWrongAnswer1());
            random.get(2).setText(allFlashCards.get(0).getWrongAnswer2());
            check = allFlashCards.get(currentCardDisplayedIndex);
            flashcardDatabase.deleteCard("Sample Question");
        }

        flashcardDatabase.insertCard(nullFix);

//        //Set the Flashcard's answer to visible and the Flashcard's question to invisible
//        findViewById(R.id.flashcard_question).setOnClickListener(new View.OnClickListener(){
//           @Override
//           public void onClick(View v){
//               findViewById(R.id.flashcard_question).setVisibility(View.INVISIBLE);
//               findViewById(R.id.flashcard_answer).setVisibility(View.VISIBLE);
//           }
//        });
//        //Set the Flashcard's question to visible and the Flashcard's answer to invisible
//        findViewById(R.id.flashcard_answer).setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v){
//                findViewById(R.id.flashcard_answer).setVisibility(View.INVISIBLE);
//                findViewById(R.id.flashcard_question).setVisibility(View.VISIBLE);
//            }
//        });

        //Set OnClicklistener to + icon
        findViewById(R.id.addBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivityForResult(intent, 100);
            }
        });
        //Set OnClicklistener to > icon
        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentCardDisplayedIndex++;
                if (currentCardDisplayedIndex > allFlashCards.size() - 1) {
                    currentCardDisplayedIndex = 0;
                }
                check = allFlashCards.get(currentCardDisplayedIndex);
                ((TextView) findViewById(R.id.flashcard_question)).setText(allFlashCards.get(currentCardDisplayedIndex).getQuestion());
                ((TextView) findViewById(R.id.flashcard_answer)).setText(allFlashCards.get(currentCardDisplayedIndex).getAnswer());
                Collections.shuffle(random);
                random.get(0).setText(check.getAnswer());
                random.get(1).setText(check.getWrongAnswer1());
                random.get(2).setText(check.getWrongAnswer2());
                for (int i=0; i<random.size(); i++) {
                    random.get(i).setBackgroundResource(R.drawable.answer);
                }
            }
        });
        //Set OnClickListener to edit icon
        findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0; i < allFlashCards.size(); i++) {
                    if (((TextView) findViewById(R.id.flashcard_question)).getText().equals((allFlashCards.get(i).getQuestion()))) {
                        cardToEdit = allFlashCards.get(i);
                    }
                }
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                intent.putExtra("Question", ((TextView) findViewById(R.id.flashcard_question)).getText());
                intent.putExtra("Answer", ((TextView) findViewById(R.id.flashcard_answer)).getText());

                MainActivity.this.startActivityForResult(intent, 200);
            }
        });
        //Set OnClickListener to Multiple Choice
        findViewById(R.id.answerOne).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((TextView) findViewById(R.id.answerOne)).getText() == check.getAnswer()) {
                    findViewById(R.id.answerOne).setBackgroundResource(R.drawable.correct);
                }
                else {
                    findViewById(R.id.answerOne).setBackgroundResource(R.drawable.incorrect);
                }
            }
        });
        findViewById(R.id.answerTwo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((TextView) findViewById(R.id.answerTwo)).getText() == check.getAnswer()) {
                    findViewById(R.id.answerTwo).setBackgroundResource(R.drawable.correct);
                }
                else {
                    findViewById(R.id.answerTwo).setBackgroundResource(R.drawable.incorrect);
                }
            }
        });
        findViewById(R.id.answerThree).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((TextView) findViewById(R.id.answerThree)).getText() == check.getAnswer()) {
                    findViewById(R.id.answerThree).setBackgroundResource(R.drawable.correct);
                }
                else {
                    findViewById(R.id.answerThree).setBackgroundResource(R.drawable.incorrect);
                }
            }
        });
        //Set OnClickListener to Visible Icon
        findViewById(R.id.eye).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (findViewById(R.id.answerOne).getVisibility() == View.VISIBLE) {
                    findViewById(R.id.answerOne).setVisibility(View.INVISIBLE);
                    findViewById(R.id.answerTwo).setVisibility(View.INVISIBLE);
                    findViewById(R.id.answerThree).setVisibility(View.INVISIBLE);
                }
                else {
                    findViewById(R.id.answerOne).setVisibility(View.VISIBLE);
                    findViewById(R.id.answerTwo).setVisibility(View.VISIBLE);
                    findViewById(R.id.answerThree).setVisibility(View.VISIBLE);
                }
            }
        });
        //Set OnClickListener to Delete Icon
        findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100 && resultCode == RESULT_OK) {
            try {
                String question = data.getExtras().getString("Question");
                String answer = data.getExtras().getString("Answer");
                String wrongAnswer1 = data.getExtras().getString("WrongAnswer1");
                String wrongAnswer2 = data.getExtras().getString("WrongAnswer2");
                check = new Flashcard(question, answer, wrongAnswer1, wrongAnswer2);
                flashcardDatabase.insertCard(new Flashcard(question, answer, wrongAnswer1, wrongAnswer2));
                ((TextView) findViewById(R.id.flashcard_question)).setText(question);
                ((TextView) findViewById(R.id.flashcard_answer)).setText(answer);
                Collections.shuffle(random);
                random.get(0).setText(answer);
                random.get(1).setText(wrongAnswer1);
                random.get(2).setText(wrongAnswer2);
                for (int i=0; i<random.size(); i++) {
                    random.get(i).setBackgroundResource(R.drawable.answer);
                }
                flashcardDatabase.deleteCard("Sample Question");
                allFlashCards = flashcardDatabase.getAllCards();
                Snackbar.make(findViewById(R.id.flashcard_question),"Card Successfully Created", Snackbar.LENGTH_SHORT).show();
            }
            catch (Exception e) {

            }
        }

        else if (requestCode == 200 && resultCode == RESULT_OK) {
            try {
                String question = data.getExtras().getString("Question");
                String answer = data.getExtras().getString("Answer");
                String wrongAnswer1 = data.getExtras().getString("WrongAnswer1");
                String wrongAnswer2 = data.getExtras().getString("WrongAnswer2");
                check = new Flashcard(question, answer, wrongAnswer1, wrongAnswer2);
                cardToEdit.setQuestion(question);
                cardToEdit.setAnswer(answer);
                flashcardDatabase.updateCard(cardToEdit);
                ((TextView) findViewById(R.id.flashcard_question)).setText(question);
                ((TextView) findViewById(R.id.flashcard_answer)).setText(answer);
                Collections.shuffle(random);
                random.get(0).setText(answer);
                random.get(1).setText(wrongAnswer1);
                random.get(2).setText(wrongAnswer2);
                for (int i=0; i<random.size(); i++) {
                    random.get(i).setBackgroundResource(R.drawable.answer);
                }
                allFlashCards = flashcardDatabase.getAllCards();
                Snackbar.make(findViewById(R.id.flashcard_question),"Card Successfully Updated", Snackbar.LENGTH_SHORT).show();
            }
            catch (Exception e) {

            }
        }
    }
}
