package id.rendesvouz.edlis;

public class Singelton {
    private static Singelton instance;
    private String PassingUsername;
    private String PassingEmail;


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

