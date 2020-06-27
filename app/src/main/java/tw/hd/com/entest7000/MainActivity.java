package tw.hd.com.entest7000;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mWord = findViewById(R.id.m_word);
        Button mWord1 = findViewById(R.id.m_word_bk);
        Button mSentence = findViewById(R.id.m_sentence);
        Button mSentence1 = findViewById(R.id.m_word_bk);


    }
    public void mword(View view){
        Intent intent = new Intent(this,WordActivity.class);
    }
    public void msentence(View view){
        Intent intent = new Intent(this,SentencesActivity.class);
    }
    public void mworkbk(View view){

    }
    public void msentencebk(View view){

    }
}
