CC=gcc
CFLAGS= -Wall -ansi -pedantic -std=c99 -lglpk
LDFLAGS=
EXEC=decoupe

all: clean $(EXEC) clear execute

push: gitreplace gitadd gitcommit gitpush	

decoupe: decoupe.c
	$(CC) -o decoupe decoupe.c $(CFLAGS)
	
clean:
	rm -rf $(EXEC)

clear:
	clear
	
execute:
	./decoupe
	
gitreplace:
	cd ../..
	
gitadd:
	git add .

gitcommit:
	git commit -m"modif : TP3 ARO"
	
gitpush:
	git push
