java -jar lib/antlr-4.9.3-complete.jar grammar/ChadppLexer.g4 -o src/antlr
java -jar lib/antlr-4.9.3-complete.jar -visitor grammar/ChadppParser.g4 -o src/antlr
