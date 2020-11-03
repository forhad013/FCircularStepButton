package com.forhad013.FCircularStepButton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.forhad013.R;


public class FCircularStepButton extends RelativeLayout {


    public void setShouldAnimate(boolean shouldAnimate) {
        this.shouldAnimate = shouldAnimate;
    }

    public float getArcAngle() {
        return arcAngle;
    }

    public void setArcAngle(float arcAngle) {
        this.arcAngle = arcAngle;
    }

    private boolean shouldAnimate = false;

    //image resource for the button
    private int buttonImageSrc;

    // total number of steps
    private int numberOfSteps;

    //current step position
    private int currentStep;

    public int getButtonImageSrc() {
        return buttonImageSrc;
    }

    public void setButtonImageSrc(int buttonImageSrc) {
        this.buttonImageSrc = buttonImageSrc;
    }

    public int getNumberOfSteps() {
        return numberOfSteps;
    }

    public void setNumberOfSteps(int numberOfSteps) {
        this.numberOfSteps = numberOfSteps;
    }

    public int getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(int currentStep) {
        this.currentStep = currentStep;
        this.shouldAnimate = false;
    }

    public int getCircleColor() {
        return circleColor;
    }

    public void setCircleColor(int circleColor) {
        this.circleColor = circleColor;
    }

    public int getSubCircleColor() {
        return subCircleColor;
    }

    public void setSubCircleColor(int subCircleColor) {
        this.subCircleColor = subCircleColor;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getImageViewSize() {
        return imageViewSize;
    }

    public void setImageViewSize(int imageViewSize) {
        this.imageViewSize = imageViewSize;
    }


    //progress circle coolor
    private int circleColor;

    //secondary circle color
    private int subCircleColor;


    private int nextArcWillStartFrom;

    private int strokeWidth;

    private float arcAngle = 0 ;

    public int getStepAngle() {
        return stepAngle;
    }

    public void setStepAngle(int stepAngle) {
        this.stepAngle = stepAngle;
    }

    private int stepAngle;

    private int startingAngle;

    private int distance;

    private int imageViewSize;

    private Paint mPaintForSubCircle, mPaintForCircle;

    private RectF mRect;

    private Context context;

    private ImageView imageView;



    public FCircularStepButton(Context context) {
        super(context);
        // TODO Auto-generated constructor stub

        init();
    }


    public FCircularStepButton(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);

        this.context = context;
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.FCircularStepButton, 0, 0);

        buttonImageSrc = a.getResourceId(R.styleable.FCircularStepButton_src, 0);
        circleColor = a.getResourceId(R.styleable.FCircularStepButton_circleColor, Color.BLACK);
        subCircleColor = a.getResourceId(R.styleable.FCircularStepButton_subCircleColor, Color.BLACK);
        numberOfSteps = a.getInt(R.styleable.FCircularStepButton_numberOfSteps, 4);
        currentStep = a.getInt(R.styleable.FCircularStepButton_currentStep, 0);
        startingAngle = a.getInt(R.styleable.FCircularStepButton_currentStep, 1);
        strokeWidth = a.getInt(R.styleable.FCircularStepButton_circleWidth, 2);
        distance = a.getInt(R.styleable.FCircularStepButton_distance, 8);
        imageViewSize = a.getInt(R.styleable.FCircularStepButton_imageViewSize, 8);


        setWillNotDraw(false);


        init();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void init() {

        stepAngle = (360 / numberOfSteps);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        FCircularStepButton view = (FCircularStepButton) inflater.inflate(R.layout.circular_step_button, this);

        imageView = view.findViewById(R.id.image);
        imageView.setImageResource(buttonImageSrc);

        LayoutParams pm = new LayoutParams(imageViewSize, imageViewSize);
        pm.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        imageView.setLayoutParams(pm);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaintForCircle = new Paint();
        mPaintForCircle.setAntiAlias(true);
        mPaintForCircle.setStyle(Paint.Style.STROKE);
        mPaintForCircle.setStrokeWidth(strokeWidth);
        mPaintForCircle.setColor(getResources().getColor(circleColor));

        mPaintForSubCircle = new Paint();
        mPaintForSubCircle.setAntiAlias(true);
        mPaintForSubCircle.setStyle(Paint.Style.STROKE);
        mPaintForSubCircle.setStrokeWidth(strokeWidth);
        mPaintForSubCircle.setColor(getResources().getColor(subCircleColor));
        mRect = new RectF(10, 10, this.getWidth(), this.getHeight());


        int arcsStartAngle = -90;
        nextArcWillStartFrom = arcsStartAngle;

        if(currentStep > 0) {
            for (int i = 0; i < currentStep; i++) {
                canvas.drawArc(mRect, arcsStartAngle + distance, stepAngle - 2 * distance, false, mPaintForCircle);
                arcsStartAngle = arcsStartAngle + stepAngle ;
                nextArcWillStartFrom = arcsStartAngle;
            }
        }


        for (int i = currentStep; i < numberOfSteps; i++) {

            canvas.drawArc(mRect, arcsStartAngle + distance, stepAngle - 2 * distance, false, mPaintForSubCircle);
            arcsStartAngle = arcsStartAngle + stepAngle;

        }


        if (shouldAnimate) {
            canvas.drawArc(mRect, nextArcWillStartFrom + distance, arcAngle, false, mPaintForCircle);
        }

    }


}


