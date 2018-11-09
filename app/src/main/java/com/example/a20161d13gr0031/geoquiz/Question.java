package com.example.a20161d13gr0031.geoquiz;

import android.support.annotation.NonNull;

public class Question implements Comparable<Question>{
    private int mTextResID;
    private boolean mAnswerTrue;
    private int dificuldade;


    public Question(int textResID, boolean answerTrue, int dificuldade) {
        mTextResID = textResID;
        mAnswerTrue = answerTrue;
        dificuldade = dificuldade;
    }

    public int getTextResID() {
        return mTextResID;
    }

    public void setTextResID(int textResID) {
        mTextResID = textResID;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public int getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }


    @Override
    public int compareTo(@NonNull Question o) {
        return this.getDificuldade() - getDificuldade();
    }
}
