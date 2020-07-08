package tw.hd.com.entest7000;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class SenBkActivity extends AppCompatActivity {

    private TextView sbkWordNum;
    private TextView sbkLevel;
    private TextView sbkenSen;
    private TextView sbkcnSen;
    private TextInputLayout sbkTextlayout;
    private EditText sbkEdit;
    private Button sbkButton;
    private LogicLayer logicLayer;
    private ArrayList<EnData> getAllist;
    private Intent intent;
    private int getNumber;
    private int getTTS;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sen_bk);
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
                SharedPreferences prd = getSharedPreferences("en7000",MODE_PRIVATE);
                prd.edit()
                        .putInt("Type",0)
                        .commit();
                intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.settingpage:
                intent = new Intent(this,SettingActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void findview() {
        getTTS = getSharedPreferences("en7000",MODE_PRIVATE).getInt("TTS",0);
        sbkWordNum = findViewById(R.id.sbk_wordnum);
        sbkLevel = findViewById(R.id.sbk_level);
        sbkenSen = findViewById(R.id.sbk_enSen);
        sbkcnSen = findViewById(R.id.sbk_cnSen);
        sbkTextlayout = findViewById(R.id.sbk_textlayout);
        sbkEdit = findViewById(R.id.sbk_edittext);
        sbkButton = findViewById(R.id.sbk_button_ok);
        int getlevel = getSharedPreferences("en70000",MODE_PRIVATE).getInt("Level",1);
        getNumber = getSharedPreferences("en7000",MODE_PRIVATE).getInt("Wordnumber",50);
        getAllist = logicLayer.getFiftySentences(getlevel,getNumber);
        sbkWordNum.setText(" 1 /  "+ getNumber);
        sbkLevel.setText("LV"+getlevel);
        if(getAllist.get(0).getEnSentence().equals("null")){
            sbkenSen.setText(logicLayer.setTestEnString(getAllist.get(0)));
            sbkcnSen.setText(getAllist.get(0).getCnString());
        }else{
            sbkenSen.setText(logicLayer.setTestEnsenten(getAllist.get(0)));
            sbkcnSen.setText(getAllist.get(0).getCnSentence());
        }
        ImageView sbkimageplay = findViewById(R.id.sbk_image_play);
        sbkimageplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getAllist.get(0).getEnSentence().equals("null")){
                    tts.speak(getAllist.get(0).getEnString(),TextToSpeech.QUEUE_FLUSH,null,"1");
                }else{
                    tts.speak(getAllist.get(0).getEnSentence(),TextToSpeech.QUEUE_FLUSH,null,"1");
                }

            }
        });
        sbkTextlayout.setErrorEnabled(true);
        sbkEdit.setImeOptions(EditorInfo.IME_ACTION_SEND);


    }

    public void sbkOk(View view){
        checkString();
    }

    private void checkString() {
        String input = sbkEdit.getText().toString();
        if(input.equals(getAllist.get(0).getEnString())){
            sbkEdit.setText(null);
            sbkTextlayout.setError(null);
            getAllist.remove(0);
            sbkenSen.setText(logicLayer.setTestEnsenten(getAllist.get(0)));
            sbkcnSen.setText(getAllist.get(0).getCnSentence());
            sbkWordNum.setText(getNumber-getAllist.size() +" / "+getNumber);

        }else{
            sbkTextlayout.setError(getAllist.get(0).getEnString());
        }
    }
}