cd `dirname $0`

export CLASSPATH=$CLASSPATH:./../../JPlanner.jar
mkdir ./classes
javac -d ./classes ./*.java
cd classes
mkdir minApp/Resources
cp ../Resources/*.png minApp/Resources/
java -cp ./:./../../../JPlanner.jar minApp.MainWindow
cd ..
echo “Done”