package com.example.mouna.wouldyourather;

/**
 * Created by Mouna on 09/07/2017.
 */

public class Question {
    private String quetionString, answer1, answer2;
    private int compteur1, compteur2;

    public Question()
    {}

    public Question(String quetionString, String answer1, String answer2, int compteur2, int compteur1) {
        this.quetionString = quetionString;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.compteur2 = compteur2;
        this.compteur1 = compteur1;
    }


    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getQuetionString() {
        return quetionString;
    }

    public void setQuetionString(String quetionString) {
        this.quetionString = quetionString;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public int getCompteur1() {
        return compteur1;
    }

    public void setCompteur1(int compteur1) {
        this.compteur1 = compteur1;
    }

    public int getCompteur2() {
        return compteur2;
    }

    public void setCompteur2(int compteur2) {
        this.compteur2 = compteur2;
    }
}
