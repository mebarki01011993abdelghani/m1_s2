CC=gcc
CFLAGS= -Wall -ansi -pedantic -std=c99
LDFLAGS=
EXEC=decoupe

all: clean mkdir_web mkdir_traces dtd noEnt xsd web tidy xq  javaNom

clean:
	rm -R traceExecutions www
	@echo "-----[ CLEAN TRACE OK ]-----"
mkdir_traces:
	mkdir -p traceExecutions
	@echo "-----[ MKDIR TRACE OK ]-----"

mkdir_web:
	mkdir -p www
	@echo "-----[ MKDIR WWW OK ]-----"
	
dtd:
	@echo "-----[ DTD OK ]-----"
	xmllint --valid XML/master.xml > traceExecutions/traceDTD.txt
	
noEnt:
	@echo "-----[ noENT OK ]-----"
	xmllint --valid --noent XML/master.xml > XML/masterNoEnt.xml 

xsd:
	xmllint -valid -schema XSD/master.xsd XML/masterNoEnt.xml > traceExecutions/traceXSD.txt
	@echo "-----[ XSD OK ]-----"

web:
	xsltproc XSL/master.xsl XML/master.xml > www/index.html
	@echo "-----[ WEB OK ]-----"

tidy:
	cd TIDY && ./verif.sh > ../traceExecutions/traceTidy.txt
	@echo "-----[ TIDY OK ]-----"

xq:
	cd XQ && make all
	@echo "-----[ XQ OK ]-----"

javaNom:
	cd JAVA && make all
	@echo "-----[ JAVA OK ]-----"
