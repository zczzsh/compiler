###
# A simple Makefile for Lab #1
###

# define the java compiler to be used and the flags
JC = javac
FLAGS = -g -cp $(CP)
CP = .

# define the rules
Main.class: WordScanner.class WordCount.class Info.class Word.class Main.java
	javac Main.java
	
Word.class: Word.java
	javac Word.java

Info.class: Info.java
	javac Info.java

WordCount.class: WordCount.java
	javac WordCount.java

WordScanner.class: Word.class WordScanner.java
	javac WordScanner.java


# testing 
test:
	java -cp $(CP) Main

# clean up
clean:
	rm -f *~ *.class
