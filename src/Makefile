default: run

JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Book.java \
	HashTableMap.java \
	Library.java \
	LibSystem.java \
	MapADT.java \
	Node.java

run: LibSystem.class
	java LibSystem

LibSystem.class: $(CLASSES: .java=.class)

clean:
	$(RM) *.class

test: LibTest.class
	java -jar junit5.jar --class-path . --scan-classpath

LibTest.class: LibTest.java
	javac -cp .:junit5.jar Library.java *.java -Xlint



