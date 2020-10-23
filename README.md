# FCircularStepButton


![Alt text](sample.gif) / ![](sample.gif)



#Adding to project

To get a Git project into your build:

 
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.forhad013:FCircularStepButton:1.0.0'
	}


# Usage

*setCurrentStep(int number)
*setDistance(int number) (Distance between each arc)
*setImageViewSize(int imageViewSize) (Square size of imageview)
*setButtonImageSrc(int buttonImageSrc)
*setNumberOfSteps(int numberOfSteps)
*setSubCircleColor(int subCircleColor) (secondary circle color)
*setCircleColor(int circleColor) (main circle color)



