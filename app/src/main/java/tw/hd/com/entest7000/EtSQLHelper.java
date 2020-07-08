package tw.hd.com.entest7000;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class EtSQLHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "wordDB.db";
    private static final  int DATABASE_VERSION =1;
    private EnData enData;
    public EtSQLHelper(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    public Integer getDBSize(int levelNum){

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM LV7000  WHERE level = "+levelNum ,null);
        int returnNum = cursor.getCount();
        cursor.close();
        return returnNum;
    }
    public ArrayList<Integer> getDBidfromLevel(int level){
        ArrayList<Integer> getIdlist = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM LV7000  WHERE level = "+level,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            getIdlist.add(id);
        }
        return getIdlist;
    }
    public EnData getStringData(int idNumber){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM LV7000 WHERE id = "+idNumber,null);
        while (cursor.moveToNext()){
           int id = cursor.getInt(0);
           String enString = cursor.getString(1);
           String cnString = cursor.getString(2);
           String enSentence = cursor.getString(3);
           String cnSentence = cursor.getString(4);
           enData = new EnData(id,enString,cnString,enSentence,cnSentence);

        }
        return enData;

    }
    public ArrayList<Integer> getNullString(){
        SQLiteDatabase db =  getReadableDatabase();
        ArrayList<Integer> tempList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM LV7000 WHERE enSentences = "+"null",null);
        while (cursor.moveToNext()){
        tempList.add(cursor.getInt(0));
            Log.d("temp",cursor.getInt(0)+"");
    }
        return tempList;
    }
}
