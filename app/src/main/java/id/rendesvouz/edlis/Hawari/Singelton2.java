package id.rendesvouz.edlis.Hawari;

import android.content.Context;

import java.util.ArrayList;

import id.rendesvouz.edlis.DatabaseAccess;

public class Singelton2 {
    private static Singelton2 instance;

    private String questions[];
    private int answer[];
    private int currentAnswer[];
    private String sources[];

    private Singelton2(){

    }

    public static Singelton2 getInstance(){
        if(instance==null){
            instance = new Singelton2();
        }
        return instance;
    }

    public void getExamData(int type, int level, Context context){
        final DatabaseAccess dbAccess = DatabaseAccess.getInstance(context);
        dbAccess.open();
        ArrayList<Question> data = dbAccess.getExamData(type,level);
        int index = 0;
        questions = new String[10];
        answer = new int[10];
        currentAnswer = new int[10];
        sources = new String[10];
        for (Question a:data) {
            questions[index] = a.Question;
            answer[index] = a.Answer;
            sources[index] = a.Sources;
            index++;
        }
        dbAccess.close();
    }

    public void setAnswer (int index,int ans){
        if(ans == answer[index]){
            currentAnswer[index] = 1;
        } else {
            currentAnswer[index] = 0;
        }

    }

    public String getSources (int index) { return sources[index]; }

    public String getQuestion (int index){ return questions[index]; }

    public int[] countScore(){
        int scores[] = {0,0,0};
        for (int a : currentAnswer) {
            if(a==1){
                scores[0] +=10;
                scores[1]++;
            }
            else{
                scores[2]++;
            }
        }
        return scores;
    }
}