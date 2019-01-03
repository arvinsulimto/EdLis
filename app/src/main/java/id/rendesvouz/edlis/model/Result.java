package id.rendesvouz.edlis.model;

public class Result {
    private String ResultID;
    private String Type;
    private String Level;
    private String Score;



    public String getType() {
        return Type;
    }

    public void setType(String type) {
        if(type.equals("1")){
            Type="Reading";
        }
        else if(type.equals("2")){
            Type="Writing";
        }
        else if(type.equals("3")){
            Type="Listening";
        }
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        if(level.equals("1")){
            Level = "Easy";
        }
        else if(level.equals("2")){
            Level = "Medium";
        }
        else if(level.equals("3")){
            Level= "Hard";
        }
    }

    public String getResultID() {
        return ResultID;
    }

    public void setResultID(String resultID) {
        ResultID = resultID;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        Score = score;
    }

}
