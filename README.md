# FCircularStepButton






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


