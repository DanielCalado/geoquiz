package com.example.a20161d13gr0031.geoquiz;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {
    private TextView txtQuestao;
    private String api;
    private TextView apiText;
    private int apiInt = Build.VERSION.SDK_INT;
    private Button btMostrarResposta;
    private boolean respostaQuestao;
    private static final String EXTRA_ANSWER_IS_TRUE =
            "com.bignerdranch.android.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN =
            "Com.bignerdranch.android.geoquiz.answer_shown";

    public static String getExtraAnswerIsTrue() {
        return EXTRA_ANSWER_IS_TRUE;
    }
    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    private void mostrarResposta(boolean respostaQuestao) {
        txtQuestao.setText(respostaQuestao == true ?
                R.string.true_button :
                R.string.false_button);
        setAnswerShownResult(true);
        int cx = btMostrarResposta.getWidth() / 2;
        int cy = btMostrarResposta.getHeight() / 2;
        float radius = btMostrarResposta.getWidth();
        Animator anim = ViewAnimationUtils.createCircularReveal(btMostrarResposta, cx, cy, radius, 0);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                btMostrarResposta.setVisibility(View.INVISIBLE);
            }
        });
        anim.start();

    }

    private void setAnswerShownResult(boolean respostaApareceu) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN,respostaApareceu);
        setResult(RESULT_OK, data);
    }


    public static Intent newIntent(Context packageContext,
                                   boolean answerIsTrue) {
        Intent intent = new Intent(packageContext,
                CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        api = "Versão API " + apiInt;

        apiText = (TextView)findViewById(R.id.api);
        apiText.setText(api);
        respostaQuestao =
                getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        txtQuestao = (TextView) findViewById(R.id.answer_text_view);
        btMostrarResposta = (Button) findViewById(R.id.show_answer_button);
        btMostrarResposta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarResposta(respostaQuestao);
            }
        });
    }

}


