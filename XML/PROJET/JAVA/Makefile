CC=gcc
CFLAGS= -Wall -ansi -pedantic -std=c99
LDFLAGS=
EXEC=decoupe

all: java_compile java_exec

java_compile:
	javac JavaNomEnseignement.java

java_exec:
	 java JavaNomEnseignement > ../traceExecutions/nomUE.xml
