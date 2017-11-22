all:
	javac *.java

clean:
	rm -f *.class

calc: all
	java Calculator
