package tw.hd.com.entest7000;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.KeyEvent;
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

public class WordBkActivity extends AppCompatActivity {

    private TextView wbkwordNum;
    private TextView wbkenString;
    private TextView wbkcnString;
    private TextInputLayout textInputLayout;
    private EditText wbkedit;
    private LogicLayer logicLayer;
    private ArrayList<EnData> getAllist;
    private Button okbutton;
    private Intent intent;
    private int getNumber;
    private TextToSpeech tts;
    private int getTTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_bk);
        logicLayer = new LogicLayer(this);
        tts = logicLayer.getTTS();
        getAllist = new ArrayList<>();
        findview();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mainpage:
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    private void findview() {
        getTTS = getSharedPreferences("en7000",MODE_PRIVATE).getInt("TTS",0);
        int getLevel = getSharedPreferences("en7000",MODE_PRIVATE).getInt("Level",1);
        getNumber = getSharedPreferences("en7000",MODE_PRIVATE).getInt("Wordnumber",50);
        okbutton = findViewById(R.id.wbk_button_ok);
        getAllist = logicLayer.getFiftySentences(getLevel,getNumber);
        wbkwordNum = findViewById(R.id.wbk_word_num);
        TextView wbklevel = findViewById(R.id.wbk_level);
        wbkenString = findViewById(R.id.wbk_enSen);
        wbkcnString = findViewById(R.id.wbk_cnSen);
        textInputLayout = findViewById(R.id.wbk_textlayout);
        wbkedit = findViewById(R.id.wbk_edittext);
        wbkedit.setImeOptions(EditorInfo.IME_ACTION_SEND);
        wbkwordNum.setText("1 / "+ getNumber);
        wbklevel.setText("LV"+getLevel);
        ImageView wbkimageplay = findViewById(R.id.sbk_image_play);
        wbkimageplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tts.speak(getAllist.get(0).getEnString(),TextToSpeech.QUEUE_FLUSH,null,"1");

            }
        });
        wbkenString.setText(logicLayer.setTestEnString(getAllist.get(0)));
        wbkcnString.setText(getAllist.get(0).getCnString());
    }
    public void wbkOk(View view){
        checkString();
    }

    private void checkString() {

        String inputString = wbkedit.getText().toString();
        if(inputString.equals(getAllist.get(0).getEnString())){
            wbkedit.setText(null);
            textInputLayout.setError(null);

            getAllist.remove(0);
            wbkenString.setText(logicLayer.setTestEnString(getAllist.get(0)));
            wbkcnString.setText(getAllist.get(0).getCnString());
            wbkwordNum.setText(getNumber-getAllist.size()+" / "+getNumber);
        }else{
            if(getTTS == 0){
                tts.speak(getAllist.get(0).getEnString(),TextToSpeech.QUEUE_FLUSH,null,"1");
            }

            textInputLayout.setError(getAllist.get(0).getEnString());

        }
    }
}