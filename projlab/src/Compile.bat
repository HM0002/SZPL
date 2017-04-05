set path=%path%;C:\Program Files (x86)\Java\jdk1.8.0_66\bin
javac -d . projlab\*.java
jar -cmf manifest.mf Vonatos.jar projlab\*.class 
@echo Compiled successfully.

