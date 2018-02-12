#!/bin/bash

# Environment Variable
STARTPORT=8399

# Prepare combile director
rm -rf ./compile
mkdir -p compile
cp -R libs/jarContents/* compile

# Get jdk information
jdkVersion=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}')

# Modify MANIFEST.MF
UserName=`whoami`
sed -i 's/{username}/'"$UserName"'/g' compile/META-INF/MANIFEST.MF
sed -i 's/{jdkversion}/'"$jdkVersion"'/g' compile/META-INF/MANIFEST.MF

# Prepare libraries
LINENUM=`ls -alF libs/lib | awk '{print $9}' | wc -l`
FILENAMES=`ls -alF libs/lib | awk -v endLineNo="$LINENUM" 'NR==4,NR==endLineNo { if (NR==endLineNo) {printf "libs/lib/%s",$9} else {printf "libs/lib/%s:",$9}}'`
CLASSPATH=".:"$FILENAMES
COMPILEOUTDIR="compile/BOOT-INF/classes"
cp libs/lib/*.jar compile/BOOT-INF/lib/

#Compile
javac -d $COMPILEOUTDIR -sourcepath src/main/java/ -cp $CLASSPATH src/main/java/bis332/objects/CalculatorObject.java
javac -d $COMPILEOUTDIR -sourcepath src/main/java/ -cp $CLASSPATH src/main/java/bis332/objects/GeneInfoObject.java
javac -d $COMPILEOUTDIR -sourcepath src/main/java/ -cp $CLASSPATH src/main/java/bis332/Controller.java
javac -d $COMPILEOUTDIR -sourcepath src/main/java/ -cp $CLASSPATH src/main/java/bis332/Application.java

#JAR Creation
cd compile; jar -cvf0m bis332-0.0.1-SNAPSHOT.jar META-INF/MANIFEST.MF .
java -Dserver.port=$STARTPORT -jar bis332-0.0.1-SNAPSHOT.jar
