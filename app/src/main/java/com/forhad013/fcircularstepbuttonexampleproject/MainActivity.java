package com.forhad013.fcircularstepbuttonexampleproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.forhad013.FCircularStepButton.ArcAngleAnimation;
import com.forhad013.FCircularStepButton.FCircularStepButton;

public class MainActivity extends AppCompatActivity {
    ArcAngleAnimation animation;
    FCircularStepButton fCircularStepButton;
    TextView textview;
    LottieAnimationView animationView;
    int currentPosition = 0;
    int[] screenColor = {R.color.teal_400, R.color.light_blue_200, R.color.deep_orange_200, R.color.light_green_300};
    int[] stringList = {R.string.text1,R.string.text2,R.string.text3,R.string.text4};
    int numberOfStep = 4;
    ConstraintLayout mainContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainContent = findViewById(R.id.mainContent);
        textview = findViewById(R.id.textview);
        fCircularStepButton = findViewById(R.id.stepButton);


        fCircularStepButton.setNumberOfSteps(numberOfStep);
        setViewForCurrentStep(currentPosition);


        fCircularStepButton.setOnClickListener(view -> {

            if (fCircularStepButton.getCurrentStep() < fCircularStepButton.getNumberOfSteps()) {
                startAnimation();
            }
        });

        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textview.getText().toString().equals(getString(R.string.restart))) {
                    currentPosition = 0;
                    fCircularStepButton.setCurrentStep(currentPosition);
                    fCircularStepButton.setShouldAnimate(false);
                    fCircularStepButton.invalidate();
                    fCircularStepButton.setEnabled(true);
                    setViewForCurrentStep(currentPosition);
                }
            }
        });

    }


    private void startAnimation() {

        animation = new ArcAngleAnimation(fCircularStepButton);
        animation.setDuration(1000);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                fCircularStepButton.setEnabled(false);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                if(fCircularStepButton.getCurrentStep() < fCircularStepButton.getNumberOfSteps() -1) {
                    fCircularStepButton.setEnabled(true);
                    currentPosition++;
                    fCircularStepButton.setCurrentStep(currentPosition);
                    fCircularStepButton.setShouldAnimate(false);
                    fCircularStepButton.invalidate();
                    setViewForCurrentStep(currentPosition);
                }else{
                    textview.setText( getString(R.string.restart));
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        fCircularStepButton.setShouldAnimate(true);
        fCircularStepButton.startAnimation(animation);
    }


    private void setViewForCurrentStep(int currentStep){

        textview.setText(getString(stringList[currentStep]));
        mainContent.setBackgroundColor(getResources().getColor(screenColor[currentStep]));

    }

}