package tw.hd.com.entest7000;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WordActivity extends AppCompatActivity {

    private TextView wStringNum;
    private TextView wLevel;
    private TextView wenString;
    private TextView wcnString;
    private Button wKnow;
    private Button wUnknow;
    private LogicLayer logicLayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);
        findview();
    }
    public void wknow(View view){

    }
    public void wunknow(View view){

    }

    private void findview() {
        wStringNum = findViewById(R.id.w_stringNum);
        wLevel = findViewById(R.id.w_level);
        wenString = findViewById(R.id.w_enSentence);
        wcnString = findViewById(R.id.w_cnSentence);
        wKnow = findViewById(R.id.w_button_know);
        wUnknow = findViewById(R.id.w_botton_unknow);
    }
}