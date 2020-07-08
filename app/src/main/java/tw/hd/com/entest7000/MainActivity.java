package tw.hd.com.entest7000;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity  {


    private Intent intent;
    private int type;
    private SharedPreferences pref;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mainpage:
                break;
            case R.id.settingpage:
                intent = new Intent(this,SettingActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int type = getSharedPreferences("en7000",MODE_PRIVATE).getInt("Type",0);
        Button mWord = findViewById(R.id.m_word);
        Button mWord1 = findViewById(R.id.m_word_bk);
        Button mSentence = findViewById(R.id.m_sentence);
        Button mSentence1 = findViewById(R.id.m_word_bk);
        switch (type){
            case 1:
                intent = new Intent(this,WordActivity.class);
                startActivity(intent);
                finish();
                break;
            case 2:
                intent = new Intent(this,SentencesActivity.class);
                startActivity(intent);
                finish();
                break;
            case 3:
                intent = new Intent(this,WordBkActivity.class);
                startActivity(intent);
                finish();
                break;
            case 4:
                intent = new Intent(this,SenBkActivity.class);
                startActivity(intent);
                finish();
                break;
        }


    }
    public void mword(View view){

        pref = getSharedPreferences("en7000", MODE_PRIVATE);
        pref.edit()
                .putInt("Type", 1)
                .commit();
        intent = new Intent(this,WordActivity.class);
        startActivity(intent);
        finish();

    }
    public void msentence(View view){

        pref = getSharedPreferences("en7000", MODE_PRIVATE);
        pref.edit()
                .putInt("Type",2)
                .commit();
        intent = new Intent(this,SentencesActivity.class);
        startActivity(intent);
        finish();
    }
    public void mwordbk(View view){
        type = 3;
        pref = getSharedPreferences("en7000",MODE_PRIVATE);
        pref.edit()
                .putInt("Type",3)
                .commit();
        intent = new Intent(this,WordBkActivity.class);
        startActivity(intent);
        finish();

    }
    public void msentencebk(View view){
        pref = getSharedPreferences("en7000",MODE_PRIVATE);
        pref.edit()
                .putInt("Type",4)
                .commit();
      Intent  intent = new Intent(this,SenBkActivity.class);
        startActivity(intent);
        finish();

    }
}
