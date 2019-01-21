package id.rendesvouz.edlis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import id.rendesvouz.edlis.Hawari.Question;
import id.rendesvouz.edlis.Victor.DatabaseData;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor cursor;

    private DatabaseAccess(Context context){
        this.openHelper = new DatabaseHelper(context);
    }

    public static DatabaseAccess getInstance(Context context){
        if(instance==null){
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open(){
        this.db = openHelper.getWritableDatabase();
    }

    public void close(){
        if(db!=null){
            this.db.close();
        }
    }


    public String getUsername(String Email,String Password){
       Cursor cursor1 = db.rawQuery("select UserName from MsUser" +
               " where Email='"+Email+"' and Password='"+Password+"'",new String[]{});
       String username =  "";
        if (cursor1.moveToFirst()) {
            username = cursor1.getString(cursor1.getColumnIndex("UserName"));
        }
        cursor1.close();
       return username;
    }

    public String getEmail(String Email,String Password){
        Cursor cursor1 = db.rawQuery("select Email from MsUser" +
                " where Email='"+Email+"' and Password='"+Password+"'",new String[]{});
        String email =  "";
        if (cursor1.moveToFirst()) {
            email = cursor1.getString(cursor1.getColumnIndex("Email"));
        }
        cursor1.close();
        return email;
    }


    public Boolean LoginValidation(String Email, String Password){
        cursor = db.rawQuery("select UserName,Email,Password,NoHp,Hint from MsUser where Email='"+Email+"' and Password='"+Password+"'",new String[]{});
        if(cursor.getCount()>0) {
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean InsertUser(String UserName,String Password,String Email,String NoHp,String Hint){
        ContentValues contentValues = new ContentValues();
        contentValues.put("UserName",UserName);
        contentValues.put("Password",Password);
        contentValues.put("Email",Email);
        contentValues.put("NoHp",NoHp);
        contentValues.put("Hint",Hint);
        long ins = db.insert("MsUser",null,contentValues);
        if(ins==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public Boolean UpdateUser(String Email,String Password){
        ContentValues contentValues = new ContentValues();
        contentValues.put("Email",Email);
        contentValues.put("Password",Password);
        long ins = db.update("MsUser",contentValues,"Email=?",new String[]{Email});
        if(ins==-1){
            return false;
        }
        else{
            return true;
        }
    }


    public Boolean CheckEmail(String Email){
        SQLiteDatabase db1 = openHelper.getReadableDatabase();
        Cursor c = db1.rawQuery("select UserName,Email,Password,NoHp,Hint from MsUser where Email='"+Email+"'",new String[]{});
        if (c.getCount()>0) return false;
        else return true;
    }

    public Boolean CheckHint(String Email,String Hint){
        SQLiteDatabase db2 = openHelper.getReadableDatabase();
        Cursor c1 = db2.rawQuery("select UserName,Email,Password,NoHp,Hint from MsUser where Email='"+Email+"' and Hint='"+Hint+"'",new String[]{});
        if(c1.getCount()>0) return  true;
        else return false;
    }

    /*------------------------------------------------------------------------------------------------------------------------------*/

    public DatabaseData getData(int topicID, int pageNumber) {
        String Query2 = String.format("SELECT * FROM DetailTopic WHERE TypeID = '%d' AND PageNumber = '%d'", topicID, pageNumber);
        DatabaseData D = new DatabaseData();
        Cursor cursor2 = db.rawQuery(Query2, new String[]{});
        if(cursor2.moveToFirst()) {
            D.judul = cursor2.getString(2);
            D.desc = cursor2.getString(3);
            D.resources = cursor2.getString(5);
        }
        cursor2.close();
        return D;
    }

    public Cursor ViewResult(String Email){
        SQLiteDatabase db3 = openHelper.getReadableDatabase();
        String query = "select ResultID,Email,LevelID,TypeID,Score,Date,Time from Result where Email='"+Email+"'";
        Cursor cursor2 = db3.rawQuery(query,null);

        return cursor2;
    }

    /*------------------------------------------------------------------------------------------------------------------------------*/

    public ArrayList<Question> getExamData(int type, int level){
        ArrayList<Question> data = new ArrayList<Question>();
        Cursor c = db.rawQuery("SELECT * FROM DetailExam WHERE TypeID = '"+type+"' AND LevelID ='"+level+"'",new String[]{});
        if(c.moveToFirst()){
            do{
                Question q = new Question();
                q.Question = c.getString(3);
                q.Answer = c.getInt(5);
                q.Sources = c.getString(6);
                data.add(q);
            }while(c.moveToNext());
        }
        return data;
    }
    
    public void storeExamResult(int score){

    }

    public void InsertScoreData(String Email, Integer LevelID, Integer TypeID, Integer Score, String Date, String Time){
        ContentValues contentValues1 = new ContentValues();
        contentValues1.put("Email", Email);
        contentValues1.put("LevelID", LevelID);
        contentValues1.put("TypeID", TypeID);
        contentValues1.put("Score", Score);
        contentValues1.put("Date", Date);
        contentValues1.put("Time", Time);
        db.insert("Result", null, contentValues1);
    }

    public String getHighScore(int type, int level, String email){

//        Cursor cab = db.rawQuery("SELECT Score FROM Result WHERE TypeID = "+type+" AND LevelID ="+level+" AND Email = '"+email+"'",new String[]{});
//        HighScore = cab.getInt(cab.getColumnIndex("Score"));
        String Query2 = String.format("SELECT Score FROM Result WHERE TypeID = '"+type+"' AND LevelID ='"+level+"' AND Email = '"+email+"'");
        Cursor cursor20 = db.rawQuery(Query2, new String[]{});
        int score = 0;
        String email1 = "";
        if (cursor20.moveToLast()) {
            email1 = cursor20.getString(cursor20.getColumnIndex("Score"));
        }
        cursor20.close();
        return email1;
    }

    public void InsertLogin(String UserName, String Email) {
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put("UserName", UserName);
        contentValues2.put("Email", Email);
        db.insert("Login", null, contentValues2);
    }

    public String getUsernameSession(){
        Cursor cursor1 = db.rawQuery("select Username from Login",null);
        String username =  "";
        if (cursor1.moveToFirst()) {
            username = cursor1.getString(cursor1.getColumnIndex("Username"));
        }
//        if(cursor1.getCount()==0){
//            username = "";
//        }
//        else{
//            username = cursor1.getString(cursor1.getColumnIndex("Username"));
//        }
        cursor1.close();
        return username;
    }

    public String getEmailSession(){
        Cursor cursor1 = db.rawQuery("select Email from Login",null);
        String email =  "";
        if (cursor1.moveToFirst()) {
            email = cursor1.getString(cursor1.getColumnIndex("Email"));
        }
//        if(cursor1.getCount()==0){
//            email = "";
//        }
//        else{
//            email = cursor1.getString(cursor1.getColumnIndex("Email"));
//        }
        cursor1.close();
        return email;
    }

    public void DeleteAllDataInSessionLogin(){
        SQLiteDatabase db99 = openHelper.getWritableDatabase();
        db99.execSQL("delete from Login");
    }
}
