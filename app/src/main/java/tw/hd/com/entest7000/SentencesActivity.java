package tw.hd.com.entest7000;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SentencesActivity extends AppCompatActivity {

    private TextView senSen;
    private TextView scnSen;
    private Button sknowbutton;
    private Button sunknowbutton;
    private TextView slevel;
    private TextView swordnum;
    private LogicLayer logicLayer ;
    private int getlevel;
    private int getwordNumber;
    private ArrayList<EnData> getSenList;
    private ArrayList<EnData> unknowList;
    private Intent intent;
    private TextToSpeech tts;
    private int getTTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentences);
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
                break;
        }
        return true;
    }

    private void findview() {
        getTTS = getSharedPreferences("en7000",MODE_PRIVATE).getInt("TTS",0);
        getlevel = getSharedPreferences("en7000",MODE_PRIVATE).getInt("Level",1);
        getwordNumber = getSharedPreferences("en7000",MODE_PRIVATE).getInt("Wordnumber",50);
        senSen = findViewById(R.id.s_enSentence);
        scnSen = findViewById(R.id.s_cnSentence);
        sknowbutton = findViewById(R.id.s_button_know);
        sunknowbutton = findViewById(R.id.s_button_unknow);
        slevel = findViewById(R.id.s_level);
        swordnum = findViewById(R.id.s_wordnum);
        ImageView senimageplay = findViewById(R.id.s_image_play);
        senimageplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getSenList.get(0).getEnSentence().equals("null")){
                    tts.speak(getSenList.get(0).getEnString(),TextToSpeech.QUEUE_FLUSH,null,"1");
                }else {
                    tts.speak(getSenList.get(0).getEnSentence(),TextToSpeech.QUEUE_FLUSH,null,"1");
                }
            }
        });

        getSenList = logicLayer.getFiftySentences(getlevel,getwordNumber);
        if(getTTS == 0){
            tts.speak(getSenList.get(0).getEnSentence(),TextToSpeech.QUEUE_FLUSH,null,"1");
        }
        senSen.setText(getSenList.get(0).getEnSentence().toString());
        scnSen.setText(getSenList.get(0).getCnSentence().toString());
        swordnum.setText("1 / "+getwordNumber);
        slevel.setText("LV"+getlevel);
    }
    public void sKnow(View view){

        try{
            getSenList.remove(0);
            if(getSenList.get(0).getEnSentence().equals("null")){
                if(getTTS == 0){
                    tts.speak(getSenList.get(0).getEnString(),TextToSpeech.QUEUE_FLUSH,null,"1");
                }
                senSen.setText(getSenList.get(0).getEnString());
                scnSen.setText(getSenList.get(0).getCnString());

            }else {
                if(getTTS == 0){
                    tts.speak(getSenList.get(0).getEnSentence(),TextToSpeech.QUEUE_FLUSH,null,"1");
                }
                senSen.setText(getSenList.get(0).getEnSentence().toString());
                scnSen.setText(getSenList.get(0).getCnSentence().toString());
            }
            swordnum.setText((getwordNumber-getSenList.size())+1+" / "+getwordNumber);
        }catch (Exception e){
            Toast.makeText(this,"完成這次的復習",Toast.LENGTH_LONG).show();
            finish();
        }



    }
    public void sUnknow(View view){

        getSenList.add(new EnData(getSenList.get(0).get_id(),getSenList.get(0).getEnString(),getSenList.get(0).getCnString(),getSenList.get(0).getEnSentence(),getSenList.get(0).getCnSentence()));
        getSenList.remove(0);
        if(getSenList.get(0).getEnSentence().equals("null")){
            if(getTTS == 0){
                tts.speak(getSenList.get(0).getEnString(),TextToSpeech.QUEUE_FLUSH,null,"1");
            }
            senSen.setText(getSenList.get(0).getEnString());
            scnSen.setText(getSenList.get(0).getCnString());

        }else {
            if(getTTS == 0){
                tts.speak(getSenList.get(0).getEnSentence(),TextToSpeech.QUEUE_FLUSH,null,"1");
            }
            senSen.setText(getSenList.get(0).getEnSentence().toString());
            scnSen.setText(getSenList.get(0).getCnSentence().toString());
        }
        swordnum.setText((getwordNumber-getSenList.size())+1+" / "+getwordNumber);
    }
}