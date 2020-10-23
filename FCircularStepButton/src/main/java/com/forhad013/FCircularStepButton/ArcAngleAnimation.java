package com.forhad013.FCircularStepButton;

import android.view.animation.Animation;
import android.view.animation.Transformation;

public class ArcAngleAnimation extends Animation {

    private FCircularStepButton arcView;
    private float newAngle;


    public ArcAngleAnimation(FCircularStepButton arcView) {

        this.newAngle =  arcView.getStepAngle() - 2 * arcView.getDistance();
        this.arcView = arcView;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation transformation) {
            arcView.setArcAngle( newAngle * interpolatedTime);
            arcView.requestLayout();
    }


}


