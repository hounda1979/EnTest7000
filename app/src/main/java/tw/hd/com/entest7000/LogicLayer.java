package tw.hd.com.entest7000;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class LogicLayer {
    private EtSQLHelper etSQLHelper;
    private Context mcontext;
    private  TextToSpeech tts;

    public LogicLayer(Context mcontext) {
        this.mcontext = mcontext;
        etSQLHelper = new EtSQLHelper(mcontext);
    }

    private List<Integer> getFiftyNumber(int level,int wordNumber){

        List firstlist = etSQLHelper.getDBidfromLevel(1);
        List endlist = new ArrayList();
        int tmep;

        while (endlist.size()< wordNumber){
        tmep = (int) (Math.random()*firstlist.size());
        endlist.add(firstlist.get(tmep));
        firstlist.remove(tmep);
       }
        return endlist;
    }
    //取得50個隨機數字後,依這些數字調出資料,如例句為NULL則原ID數+1,並判斷是否有和這隨機取得的數是否有重覆,如果有則在加1並判斷,重取資料
public ArrayList<EnData> getFiftySentences(int level, int wordNumber){
        ArrayList<EnData> fiftyString = new ArrayList<>();
        ArrayList<Integer> fiftynumber = (ArrayList)getFiftyNumber(level,wordNumber);
    for (int i = 0; i < fiftynumber.size(); i++) {
        if(etSQLHelper.getStringData(fiftynumber.get(i)).getEnString().equals("null")){
            fiftyString.add(checkID(fiftynumber,fiftynumber.get(i)));
         }else {
            fiftyString.add(etSQLHelper.getStringData(fiftynumber.get(i)));
        }
    }
    return fiftyString;
}

    private EnData checkID(ArrayList<Integer> fiftynumber, int i) {
        int temp = i;
        boolean b = true;
        do{
            for (int j = 0; j < fiftynumber.size(); j++) {
                if(fiftynumber.get(j) == temp){
                    temp = temp+1;
            }else{

                    b = false;
                }

                }
        }while (b);
        return  etSQLHelper.getStringData(temp);
    }

    public String setTestEnsenten(EnData enData) {
        String temp = enData.getEnSentence().toString();
        int stringSize = enData.getEnString().length();
        String t1 = "";
        for (int i = 0; i < stringSize; i++) {
            t1 = t1+" _";
        }
        temp = temp.replace(enData.getEnString().toString(),t1);
        return temp;
    }
    public String setTestEnString(EnData enData){
        String temp = enData.getEnString();
        String t1="";
        char[] chartemp = temp.toCharArray();
        for (int i = 0; i < chartemp.length; i++) {
         if(i == 0){
             t1 = t1+chartemp[0];
         }else if(i == chartemp.length-1){
             t1 = t1+chartemp[chartemp.length-1];
         }else {
             t1 = t1+" _";
         }

        }
        return t1;
    }
public TextToSpeech getTTS(){
    tts = new TextToSpeech(mcontext, new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(int status) {
            if(status == TextToSpeech.SUCCESS){
                Locale us = Locale.US;
                if(tts.isLanguageAvailable(us) == TextToSpeech.LANG_COUNTRY_AVAILABLE){
                    tts.setLanguage(us);
                }
            }
        }
    });
    return tts;
}


}
