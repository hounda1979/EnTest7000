package tw.hd.com.entest7000;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {

    private SeekBar seekbarLevel;
    private SeekBar seekbarNumber;
    private Switch ttlswitch;
    private TextView level;
    private TextView number;
    private SharedPreferences spf;
    private Context mcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mcon = SettingActivity.this;
        level = findViewById(R.id.set_level_show);
        number = findViewById(R.id.set_number);
        seekbarLevel = findViewById(R.id.set_seekBar_level);
        seekbarLevel.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                level.setText("Level : LV"+progress+1);
                spf = getSharedPreferences("en7000",MODE_PRIVATE);
                spf.edit()
                        .putInt("Level",progress+1)
                        .commit();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int temp = seekBar.getProgress();
                Toast.makeText(mcon,"選擇了單字Level  :"+temp,Toast.LENGTH_SHORT).show();
            }
        });
        seekbarNumber = findViewById(R.id.set_seekBar_num);
        seekbarNumber.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                number.setText("單字數量 : "+progress);
                spf = getSharedPreferences("en7000",MODE_PRIVATE);
                spf.edit()
                        .putInt("Wordnumber",progress)
                        .commit();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int temp = seekBar.getProgress();
                Toast.makeText(mcon,"選擇了單字數量 :" +temp,Toast.LENGTH_SHORT);

            }
        });
        ttlswitch = findViewById(R.id.stting_switch);
        ttlswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {



            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    spf = getSharedPreferences("en7000",MODE_PRIVATE);
                    spf.edit()
                            .putInt("TTS",0)
                            .commit();
                    Toast.makeText(mcon,"TTS OFF" ,Toast.LENGTH_SHORT);
                }else {
                    spf = getSharedPreferences("en7000",MODE_PRIVATE);
                    spf.edit()
                            .putInt("TTS",1)
                            .commit();
                    Toast.makeText(mcon,"TTS ON" ,Toast.LENGTH_SHORT);
                }
            }
        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mcon,MainActivity.class);
                startActivity(intent);
                finish();
           /*     Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            */
            }
        });
    }
}