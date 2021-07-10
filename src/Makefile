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
	MapADT.java


run: LibSystem.class
	java LibSystem

LibSystem.class: $(CLASSES: .java=.class)

clean:
	$(RM) *.class




