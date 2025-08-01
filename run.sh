#!/bin/bash
# Compile all Java files into bin/
javac -d bin src/*.java

# If compilation succeeds, run the game
if [ $? -eq 0 ]; then
    java -cp bin PokemonGaOle
else
    echo "Compilation failed. Please check for errors."
fi 