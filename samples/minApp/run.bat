set CLASSPATH=%CLASSPATH%;..\..\JPlanner.jar
md classes
javac -d classes *.java
cd classes
md minApp\Resources
copy ..\Resources\*.* minApp\Resources\*.*
java -cp ./;..\..\..\JPlanner.jar minApp.MainWindow
cd..
pause..