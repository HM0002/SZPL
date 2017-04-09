call CP.bat
javac -d . projlab\*.java
jar -cmf manifest.mf Vonatos.jar projlab\*.class 
@echo Compiled successfully.
