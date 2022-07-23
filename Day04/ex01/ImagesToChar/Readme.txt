
#How to use

rm -rf target

mkdir target

javac -sourcepath ./src/java ./src/java/edu/school21/printer/app/Program.java -d ./target

cp -r src/resources target

jar cfm ./target/images-to-chars-printer.jar src/manifest.txt -C target .

#java -jar target/images-to-chars-printer.jar {first_symbol} {second_symbol}

java -jar target/images-to-chars-printer.jar . o