package com.brogrammers.jonosokti.views;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.brogrammers.jonosokti.R;
import com.brogrammers.jonosokti.adapters.SliderAdapter;
import com.brogrammers.jonosokti.helpers.AppPreferences;

public class IntroSliderActivity extends AppCompatActivity{

    SliderAdapter sliderAdapter;
    ViewPager viewPager;
    LinearLayout dotsLayout;
    TextView[] dots;
    Button letsGetStarted,buttonNext;
    Animation animation;
    int currentPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.activity_intro_slider );

        viewPager = findViewById( R.id.slider );

        dotsLayout = findViewById( R.id.dots );
        letsGetStarted = findViewById( R.id.getStartBtn );
        buttonNext = findViewById(R.id.nextBtn);

        letsGetStarted.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( IntroSliderActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                AppPreferences.Login.setIsFirstTimeLogin(IntroSliderActivity.this,true);
                startActivity(intent);
            }
        });
        findViewById(R.id.skipBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( IntroSliderActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                AppPreferences.Login.setIsFirstTimeLogin(IntroSliderActivity.this,true);
                startActivity(intent);
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(currentPosition+1);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        sliderAdapter = new SliderAdapter(  this);
        viewPager.setAdapter( sliderAdapter );
        viewPager.addOnPageChangeListener(changeListener);

        addDots(0);

    }

    private void addDots(int position){
        dots = new TextView [3];
        dotsLayout.removeAllViews();
        for (int i=0; i<dots.length ; i++){
            dots[i] = new TextView( this );
            if (i == position){
                dots[i].setTextColor( getResources().getColor( R.color.colorPrimary ) );
                dots[i].setText( Html.fromHtml("&#8226;") );
            }else{
                dots[i].setText( Html.fromHtml("&#8226;") );
            }
            dots[i].setTextSize( 35 );

            dotsLayout.addView( dots[i] );
        }
    }
    ViewPager.OnPageChangeListener changeListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentPosition = position;
            if (position==0){
                addDots(position);
                findViewById(R.id.skipBtn).setVisibility(View.VISIBLE);
                letsGetStarted.setVisibility( View.INVISIBLE );
                buttonNext.setVisibility(View.VISIBLE);
            }
            else if (position==1){
                addDots(position);
                findViewById(R.id.skipBtn).setVisibility(View.VISIBLE);
                letsGetStarted.setVisibility( View.INVISIBLE );
                buttonNext.setVisibility(View.VISIBLE);
            }
            else {
                addDots(position);
                findViewById(R.id.skipBtn).setVisibility(View.GONE);
                buttonNext.setVisibility(View.GONE);
                letsGetStarted.setVisibility( View.VISIBLE );
                animation= AnimationUtils.loadAnimation( IntroSliderActivity.this,R.anim.botom_animation );
                letsGetStarted.setAnimation( animation );
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };





}