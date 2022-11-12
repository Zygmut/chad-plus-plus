rm grammar/Parser.java
rm grammar/ParserSym.java
rm grammar/Scanner.java

java -jar lib/jflex-full-1.8.2.jar grammar/Scanner.flex
java -jar lib/java-cup-11b.jar grammar/Parser.cup

mv Parser.java grammar/
mv ParserSym.java grammar/