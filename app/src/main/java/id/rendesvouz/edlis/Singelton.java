package id.rendesvouz.edlis;

import android.content.Context;

import java.util.ArrayList;

import id.rendesvouz.edlis.Hawari.Question;

public class Singelton {
    private static Singelton instance;
    private String PassingUsername="";
    private String PassingEmail="";

    private String questions[];
    private int answer[];
    private int currentAnswer[];


    private Singelton(){

    }

    public static Singelton getInstance(){
        if(instance==null){
            instance = new Singelton();
        }
        return instance;
    }

    public String getPassingUsername() {
        return PassingUsername;
    }

    public void setPassingUsername(String passingUsername) {
        PassingUsername = passingUsername;
    }


    public String getPassingEmail() {
        return PassingEmail;
    }

    public void setPassingEmail(String passingEmail) {
        PassingEmail = passingEmail;
    }

}

