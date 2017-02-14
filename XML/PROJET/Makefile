CC=gcc
CFLAGS= -Wall -ansi -pedantic -std=c99
LDFLAGS=
EXEC=decoupe

all: mkdir_web mkdir_traces

mkdir_traces:
	mkdir -p traceExecutions

mkdir_web:
	mkdir -p www
dtd:
	xmllint --valid XML/master.xml > traceExecutions/traceDTD.txt

xsd:
	xmllint -valid -schema XSD/master.xsd XML/noEnt.xml > traceExecutions/traceXSD.txt

web:
	xsltproc XSL/master.xsl XML/master.xml > www/index.html

tidy:

xq:
	
java:
	cd JAVA && make all
