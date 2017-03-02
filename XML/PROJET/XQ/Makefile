CC=gcc
CFLAGS= -Wall -ansi -pedantic -std=c99
LDFLAGS=
EXEC=decoupe

all: alias exec_xq copy

alias:
	alias saxon="java -jar saxon9/saxon9he.jar" 
exec_xq:
	java -cp saxon9/saxon9he.jar net.sf.saxon.Query "-q:requete.xq" > ../www/xq.xhtml

copy:
	cp ../www/xq.xhtml ../traceExecutions/
	
	

	
