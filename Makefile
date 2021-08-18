# Assignment 1 program makefile
# Comfort Twala

### VARIABLES ###
JAVA=/usr/bin/java
JAVAC=/usr/bin/javac
JAVADOC=/usr/bin/javadoc
.SUFFIXES: .java .class
SRCDIR=src
BINDIR=bin
DOCDIR=doc
OUTDIR=sampleOutput

### PART 1: PROGRAM ###
$(BINDIR)/%.class:$(SRCDIR)/%.java
		$(JAVAC) -d $(BINDIR)/ $(SRCDIR)/*.java

CLASSES=fileHandler.class arrayHandler.class SequentialFiltering.class ParallelFiltering.class FilteringMethods.class Program.class

CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

clean:
		$(RM) $(BINDIR)/*.class
		$(RM) $(OUTDIR)/parallel/*
		$(RM) $(OUTDIR)/sequential/*

run: $(CLASS_FILES)
		$(JAVA) -cp $(BINDIR) Program $(data_file) $(filter_size) $(output_file)


### Documentation
docs:
		$(JAVADOC) -d $(DOCDIR) $(SRCDIR)/*

clean-doc:
		rm -r $(DOCDIR)/*