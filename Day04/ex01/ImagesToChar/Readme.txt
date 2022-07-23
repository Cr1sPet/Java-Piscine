
#How to use

rm -rf target

mkdir target

javac -sourcepath ./src/java ./src/java/edu/school21/printer/app/Program.java -d ./target

cp -r src/resources target

jar cfm ./target/images-to-char.jar src/manifest.txt -C target .

java -jar tar

#java -jar target/images-to-char.jar {first_symbol} {second_symbol}

java -jar target/images-to-char.jar . o