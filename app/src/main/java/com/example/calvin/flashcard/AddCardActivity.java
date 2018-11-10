package com.example.calvin.flashcard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class AddCardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        //Set a cancel/close button to close current view
        findViewById(R.id.closeBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //Set a save button to save input text (Question and Answer)
        findViewById(R.id.saveBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCardActivity.this, MainActivity.class);
                intent.putExtra("Question", ((EditText) findViewById(R.id.addQuestion)).getText().toString());
                intent.putExtra( "Answer", ((EditText) findViewById(R.id.addAnswer)).getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
