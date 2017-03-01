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
noEnt:
	xmllint --valid --noent XML/master.xml > XML/masterNoEnt.xml 

xsd:
	xmllint -valid -schema XSD/master.xsd XML/masterNoEnt.xml > traceExecutions/traceXSD.txt

web:
	xsltproc XSL/master.xsl XML/master.xml > www/index.html

tidy:

path_xq:
	wget http://tinyurl.com/jlmassat/ens/xml/ress/SaxonHE9-7-0-15J.zip &
	mkdir $HOME/saxon9 &
	unzip -d $HOME/saxon9 SaxonHE9-7-0-15J.zip &
	alias saxon="java -jar $HOME/saxon9/saxon9he.jar"

xq:
	java -cp $HOME/saxon9/saxon9he.jar net.sf.saxon.Query "-q:XQ/requete.xq" > www/xq.xhtml

javaNom:
	cd JAVA && make all
