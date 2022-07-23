

#How to use


# Clean
rm -rf target
rm -rf lib


mkdir target
mkdir lib

# Download libs

curl  https://repo1.maven.org/maven2/com/beust/jcommander/1.82/jcommander-1.82.jar -o lib/jcommander-1.82.jar
curl  https://repo1.maven.org/maven2/com/diogonunes/JCDP/4.0.2/JCDP-4.0.2.jar -o lib/JCDP-4.0.2.jar

#Compile app files with Library jars

javac -d target -sourcepath src/java -cp lib/JCDP-4.0.2.jar:lib/jcommander-1.82.jar:. src/java/edu/school21/printer/app/Program.java

# Collect target forlder

cp -r src/resources target

jar -xf lib/jcommander-1.82.jar com
jar -xf lib/JCDP-4.0.2.jar com
mv com ./target

#Create java archive

jar cfm ./target/images-to-chars-printer.jar src/manifest.txt -C target/ .

#Running program

#java -jar target/images-to-chars-printer.jar  --white={COLOR1} --black={COLOR2}

java -jar target/images-to-chars-printer.jar --white=RED --black=GREEN
