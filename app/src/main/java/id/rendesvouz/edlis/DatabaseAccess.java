package id.rendesvouz.edlis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    public Cursor ViewResult(String Email){
        SQLiteDatabase db3 = openHelper.getReadableDatabase();
        String query = "select ResultID,Email,LevelID,TypeID,Score from Result where Email='"+Email+"'";
        Cursor cursor2 = db3.rawQuery(query,null);

        return cursor2;
    }



}
