package com.bingkong.shadewindow;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.bingkong.shadewindow.R;
import com.hzn.easypickerview.EasyPickerView;

import java.util.ArrayList;

import cn.bluemobi.dylan.searchview.SearchView;

public class MainActivity extends AppCompatActivity {

    private EasyPickerView epvH;
    private EasyPickerView epvM;
    private TextView mTextMessage;
    private Dialog dialog;

    SearchView sv=null;
    private void InitEPVh() {
        /*
        epvH = (EasyPickerView) findViewById(R.id.epv_h);
        final ArrayList<String> hDataList = new ArrayList<>();
        for (int i = 0; i < 24; i++)
            hDataList.add("" + i);
        epvH.setDataList(hDataList);
        epvH.setOnScrollChangedListener(new EasyPickerView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(int curIndex) {
                Log.e("MainActivity", "onScrollChanged: curIndex="+curIndex );
            }
            @Override
            public void onScrollFinished(int curIndex) {
                Log.e("MainActivity", "onScrollFinished: curIndex="+curIndex );
            }
        });
       */
    }

    private void InitEPVm(EasyPickerView epvM) {

        final ArrayList<String> hDataList = new ArrayList<>();
        for (int i = 0; i < 12; i++)
            hDataList.add("" + i);

        epvM.setDataList(hDataList);
        epvM.setOnScrollChangedListener(new EasyPickerView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(int curIndex) {
                Log.e("MainActivity", "onScrollChanged: curIndex="+curIndex );
            }

            @Override
            public void onScrollFinished(int curIndex) {
                Log.e("MainActivity", "onScrollFinished: curIndex="+curIndex );
            }
        });
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    mengcengDialog();
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;

                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextMessage = (TextView) findViewById(R.id.message);
        sv=(SearchView) findViewById(R.id.mSearchView);
        sv.setOnChangeListener(new SearchView.OnChangeListener() {
            @Override
            public void onChange(int type,String content) {
                    if(type==SearchView.SEARCH_INPUT_TEXT) {
                        Toast.makeText(MainActivity.this,
                                "Get input text["+content+"]",
                                Toast.LENGTH_LONG).show();
                    }
            }
        });
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }
    public void mengcengDialog() {
        dialog= new Dialog(this, R.style.custom_dialog);
        dialog.setContentView(R.layout.dialoglayout);
        epvM = (EasyPickerView) dialog.findViewById(R.id.epv_m);
        InitEPVm(epvM);
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        lp.width= dm.widthPixels;
        lp.height= dm.heightPixels;

        lp.x = 0; // 新位置X坐标
        lp.y = dm.heightPixels/2; // 新位置Y坐标
        lp.width = dm.widthPixels; // 宽度
        lp.height = dm.heightPixels/2; // 高度
        dialog.getWindow().setAttributes(lp);
        dialog.show();
    }


}
