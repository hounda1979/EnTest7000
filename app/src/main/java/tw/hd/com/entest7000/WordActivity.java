package tw.hd.com.entest7000;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class WordActivity extends AppCompatActivity {

    private TextView wStringNum;
    private TextView wLevel;
    private TextView wenString;
    private TextView wcnString;
    private Button wKnow;
    private Button wUnknow;
    private LogicLayer logicLayer ;
    private int getLevel;
    private int wordNumber;
    private ArrayList<EnData> enList;
    private ArrayList<EnData> unKnowList;
    private Intent intent;
    private TextToSpeech tts;
    private int getTTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);
        logicLayer = new LogicLayer(this);
        tts = logicLayer.getTTS();
        findview();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mainpage:
                SharedPreferences preferences = getSharedPreferences("en7000",MODE_PRIVATE);
                preferences.edit()
                        .putInt("Type", 0)
                        .commit();
                intent = new Intent(this,MainActivity.class);
                startActivity(intent);

                finish();
                break;
            case R.id.settingpage:
                intent = new Intent(this,SettingActivity.class);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void wknow(View view){

        try{
            enList.remove(0);
            if(getTTS == 0){
                tts.speak(enList.get(0).getEnString(),TextToSpeech.QUEUE_FLUSH,null,"1");
            }
            wenString.setText(enList.get(0).getEnString());
            wcnString.setText(enList.get(0).getCnString());
            wStringNum.setText( (wordNumber-enList.size())+1+" / "+wordNumber);
        }catch (Exception e){
            Toast.makeText(this,"完成這次的復習",Toast.LENGTH_LONG).show();
        //    wStringNum.setText( (50-enList.size())+" / 50");
            finish();

        }



    }
    public void wunknow(View view){

        enList.add(new EnData(enList.get(0).get_id(),enList.get(0).getEnString(),enList.get(0).getCnString(),enList.get(0).getEnSentence(),enList.get(0).getCnSentence()));

        enList.remove(0);
        //Toast.makeText(this,"enList.size = "+enList.size(),Toast.LENGTH_SHORT).show();
        if(getTTS == 0){
            tts.speak(enList.get(0).getEnString(),TextToSpeech.QUEUE_FLUSH,null,"1");
        }
        wenString.setText(enList.get(0).getEnString());
        wcnString.setText(enList.get(0).getCnString());
        wStringNum.setText( (wordNumber-enList.size())+1+" / "+wordNumber);


    }

    private void findview() {
        getTTS = getSharedPreferences("en7000",MODE_PRIVATE).getInt("TTS",0);
        wStringNum = findViewById(R.id.w_stringNum);
        wLevel = findViewById(R.id.w_level);
        wenString = findViewById(R.id.w_enSentence);
        wcnString = findViewById(R.id.w_cnSentence);
        wKnow = findViewById(R.id.w_button_know);
        wUnknow = findViewById(R.id.w_botton_unknow);
        ImageView imageplay = findViewById(R.id.w_imageplay);
        imageplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tts.speak(enList.get(0).getEnString(),TextToSpeech.QUEUE_FLUSH,null,"1");
            }
        });
        getLevel = getSharedPreferences("en7000",MODE_PRIVATE).getInt("Level",1);
        wordNumber = getSharedPreferences("en7000",MODE_PRIVATE).getInt("Wordnumber",50);
        wLevel.setText("LV"+getLevel);
        enList = logicLayer.getFiftySentences(getLevel,wordNumber);
        if(getTTS == 0) {
            tts.speak(enList.get(0).getEnString(), TextToSpeech.QUEUE_FLUSH, null, "1");
        }
        wenString.setText(enList.get(0).getEnString());
        wcnString.setText(enList.get(0).getCnString());
        wStringNum.setText("1 / "+wordNumber);
         }


}