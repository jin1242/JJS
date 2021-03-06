package com.example.closet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

public class ClosetActivity extends AppCompatActivity{

    //ClothesRegistrationActivity에서 버튼이 눌리면 true로 바뀌는 flag
    public static boolean isRegistration;

    Toolbar toolbar;

    FragmentTop fragmentTop;
    FragmentBottom fragmentBottom;
    FragmentOuter fragment3;
    FragmentShoes fragment4;
    FragmentAccessory fragment5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closet);

        isRegistration = false;

        //액션바 + 프래그먼트로 탭바 구현
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        fragmentTop = new FragmentTop();
        fragmentBottom = new FragmentBottom();
        fragment3 = new FragmentOuter();
        fragment4 = new FragmentShoes();
        fragment5 = new FragmentAccessory();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentTop).commit();
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("상의"));
        tabs.addTab(tabs.newTab().setText("하의"));
        tabs.addTab(tabs.newTab().setText("아우터"));
        tabs.addTab(tabs.newTab().setText("신발"));
        tabs.addTab(tabs.newTab().setText("액세서리"));
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.d("MainActivity", "선택된 탭 : " + position);
                Fragment selected = null;
                if (position == 0) {
                    selected = fragmentTop;
                } else if (position == 1) {
                    selected = fragmentBottom;
                } else if (position == 2) {
                    selected = fragment3;
                } else if (position == 3) {
                    selected = fragment4;
                } else if (position == 4) {
                    selected = fragment5;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //옷 등록 화면으로 가는 버튼
        Button btnGoClothesRegistration = (Button) findViewById(R.id.btnGoRegistration);
        btnGoClothesRegistration.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), ClothesRegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
}