all:
	make clean
	make compil
	make doc
	make tests
	make execute

compil:
	make compil_java
	make compil_tests

compil_java:
	javac -d classes src/mazegame/MazeMain.java

compil_tests:
	javac -classpath test4poo.jar test/*.java

doc:
	javadoc -d docs -subpackages src.mazegame

tests:
	java -jar test4poo.jar test.CellTest
	java -jar test4poo.jar test.ItemTest
	java -jar test4poo.jar test.PlayerTest

execute:
	java -classpath classes src/mazegame/MazeMain

clean:
	rm -f -r classes/
	rm -f -r docs/
	rm -f test/*.class