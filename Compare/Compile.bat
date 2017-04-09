call CP.bat
javac -d . compare\*.java
jar -cmf manifest.mf teszter.jar compare\*.class 
@echo Compiled successfully.