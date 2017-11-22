all:
	javac *.java

parser: all
	java ParserDriver

clean:
	rm -f *.class
