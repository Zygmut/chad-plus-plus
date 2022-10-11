#!/usr/bin/env sh
java -jar antlr-4.9.3-complete.jar ../grammar/AritmeticLexer.g4 -o ../src/antlr
java -jar antlr-4.9.3-complete.jar -visitor ../grammar/AritmeticParser.g4 -o ../src/antlr
