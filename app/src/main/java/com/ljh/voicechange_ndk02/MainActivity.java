package com.ljh.voicechange_ndk02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ljh.voicechange_ndk02.utils.EffectUtils;

import org.fmod.FMOD;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FMOD.init(this);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        Button button1 = findViewById(R.id.bt_main_1);
        Button button2 = findViewById(R.id.bt_main_2);
        Button button3 = findViewById(R.id.bt_main_3);
        Button button4 = findViewById(R.id.bt_main_4);
        Button button5 = findViewById(R.id.bt_main_5);
        Button button6 = findViewById(R.id.bt_main_6);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Log.d("TAG", "onRequestPermissionsResult: 权限被禁止...");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FMOD.close();
    }

    private void voiceChange(final String path, final int i) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                EffectUtils.fix(path, i);
            }
        }).start();
    }

    @Override
    public void onClick(View view) {
        final String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separatorChar + "singing.wav";
        switch (view.getId()) {
            case R.id.bt_main_1:
                //原声
                Toast.makeText(MainActivity.this, "原声", Toast.LENGTH_SHORT).show();
                voiceChange(path, EffectUtils.MODE_NORMAL);
                break;
            case R.id.bt_main_2:
                //萝莉
                Toast.makeText(MainActivity.this, "萝莉", Toast.LENGTH_SHORT).show();
                voiceChange(path, EffectUtils.MODE_LUOLI);
                break;
            case R.id.bt_main_3:
                //大叔
                Toast.makeText(MainActivity.this, "大叔", Toast.LENGTH_SHORT).show();
                voiceChange(path, EffectUtils.MODE_DASHU);
                break;
            case R.id.bt_main_4:
                //惊悚
                Toast.makeText(MainActivity.this, "惊悚", Toast.LENGTH_SHORT).show();
                voiceChange(path, EffectUtils.MODE_JINGSONG);
                break;
            case R.id.bt_main_5:
                //搞怪
                Toast.makeText(MainActivity.this, "搞怪", Toast.LENGTH_SHORT).show();
                voiceChange(path, EffectUtils.MODE_GAOGUAI);
                break;
            case R.id.bt_main_6:
                //空灵
                Toast.makeText(MainActivity.this, "空灵", Toast.LENGTH_SHORT).show();
                voiceChange(path, EffectUtils.MODE_KONGLING);
                break;
        }
    }
}
