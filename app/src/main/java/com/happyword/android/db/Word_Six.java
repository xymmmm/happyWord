package com.happyword.android.db;

/**
 * Created by Administrator on 2018/3/3.
 */
public class Word_Six extends DataSupport{

    private int id;
    private String word;
    private String interpret;
    private int learned;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLearned() {
        return learned;
    }

    public void setLearned(int learned) {
        this.learned = learned;
    }

    public String getInterpret() {
        return interpret;
    }

    public void setInterpret(String interpret) {
        this.interpret = interpret;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
