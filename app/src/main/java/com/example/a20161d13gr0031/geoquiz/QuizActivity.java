package com.example.a20161d13gr0031.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class QuizActivity extends AppCompatActivity {
    private ImageButton mTrueButton;
    private ImageButton mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mVoltButton;
    private TextView mostraPontuacao;
    private String pontuacao = "";
    private int contPontuacao = 0;
    private TextView mQuestionTextView;
    private int mCurrentIndex;
    private Button mCheatButton;
    private ArrayList<Question> mQuestionBank = new ArrayList<Question>();
    private boolean usuarioTrapaceou;

    private static final int REQUEST_CODE_CHEAT = 0;
    public static final int RESULT_CANCELED    = 0;
    public static final int RESULT_OK           = -1;
    public static final int RESULT_FIRST_USER   = 1;

    private void listaDeQuestoes(){

                Question questao = new Question(R.string.question1, true, 1);
                    mQuestionBank.add(questao);
                questao = new Question(R.string.question1, true, 1);
                    mQuestionBank.add(questao);
                questao = new Question(R.string.question2, false, 3);
                    mQuestionBank.add(questao);
                questao = new Question(R.string.question3, true, 4);
                    mQuestionBank.add(questao);
                questao = new Question(R.string.question4, false, 3);
                    mQuestionBank.add(questao);
                questao = new Question(R.string.question5, true,5);
                    mQuestionBank.add(questao);
                questao = new Question(R.string.question6, false,6);
                    mQuestionBank.add(questao);

    }
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;
            }
            usuarioTrapaceou =
                    CheatActivity.wasAnswerShown(data);
        }
    }



    private void proximaQuestion(){
        mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.size();
        int question = mQuestionBank.get(mCurrentIndex).getTextResID();
        mQuestionTextView.setText(question);
    }
    private void anteriorQuestion(){
        mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.size();
        int question = mQuestionBank.get(mCurrentIndex).getTextResID();
        mQuestionTextView.setText(question);
    }
    private void mostrarPontuação(boolean a,boolean b){
        if(a == b) {
            contPontuacao++;
            pontuacao = "Pontuação: " + contPontuacao;
            mostraPontuacao.setText(pontuacao);
        }else{
            contPontuacao = contPontuacao - 1;
            pontuacao = "Pontuação: " + contPontuacao;
            mostraPontuacao.setText(pontuacao);
        }
    }
    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank.get(mCurrentIndex).isAnswerTrue();
        int messageResId = (answerIsTrue == userPressedTrue)?
                R.string.correto_toast:
                R.string.incorreto_toast;

        if(userPressedTrue == answerIsTrue){
            proximaQuestion();
        }
        mostrarPontuação(userPressedTrue, answerIsTrue);
        Toast.makeText(this, messageResId,
                Toast.LENGTH_SHORT).show();
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        listaDeQuestoes();
        Collections.sort(mQuestionBank);
        mQuestionTextView = (TextView)findViewById(R.id.question);
        final int question = mQuestionBank.get(mCurrentIndex).getTextResID();
        mQuestionTextView.setText(question);
        mTrueButton = (ImageButton) findViewById(R.id.true_button);
        mFalseButton = (ImageButton) findViewById(R.id.false_button);
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mVoltButton = (ImageButton) findViewById(R.id.volt_button);
        mostraPontuacao = findViewById(R.id.status);
        mCheatButton = (Button)findViewById(R.id.cheat_button);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proximaQuestion();
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                proximaQuestion();
            }

        });

        mVoltButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anteriorQuestion();
            }
        });

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent(QuizActivity.this,
                        CheatActivity.class);
                intent.putExtra(CheatActivity.getExtraAnswerIsTrue(),
                        mQuestionBank.get(mCurrentIndex).isAnswerTrue());

                startActivity(intent);

            }
        });
    }

    public ImageButton getTrueButton() {
        return mTrueButton;
    }

    public void setTrueButton(ImageButton ttrueButton) {
        mTrueButton = ttrueButton;
    }

    public ImageButton getFalseButton() {
        return mFalseButton;
    }

    public void setFalseButton(ImageButton falseButton) {
        mFalseButton = falseButton;
    }

    public int getContPontuacao() {
        return contPontuacao;
    }

    public ImageButton getNextButton() {
        return mNextButton;
    }

    public void setNextButton(ImageButton nextButton) {
        mNextButton = nextButton;
    }
}
