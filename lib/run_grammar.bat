java -jar lib/jflex-full-1.8.2.jar grammar/Scanner.flex
java -jar lib/java-cup-11b.jar grammar/Parser.cup
move Parser.java grammar/
move ParserSym.java grammar/
