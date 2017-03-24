set path=%path%;C:\Program Files\Java\jdk1.8.0_92\bin
javac -d . projlab\*.java
jar -cmf manifest.mf Vonatos.jar projlab\*.class 
@echo Compiled successfully.
java -jar Vonatos.jar
@echo Running Vonatos.jar