all:
	javac  -cp hamcrest-core-1.3.jar:junit-4.12.jar:. *.java

clean:
	rm -f *.class

calc: all
	java Calculator

test: all
	java -cp .:hamcrest-core-1.3.jar:junit-4.12.jar org.junit.runner.JUnitCore CalcTest
