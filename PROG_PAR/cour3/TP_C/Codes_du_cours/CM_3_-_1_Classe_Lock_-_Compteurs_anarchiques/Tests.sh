#!/bin/bash
# Script pour la mesure de temps d'ex�cution avec taille = 10000

MACHINE="macbookpro"   # Pas d'espaces ici, merci!
VALEURCIBLE=10000000   # Un nombre d'op�rations raisonnable
DONNEES="_mesures_sur_"$MACHINE
DATE=`date "+DATE: %Y-%m-%d HEURE: %H:%M:%S"`

echo "Comparaisons de verrous sur "$MACHINE" "$DATE

# Je cr�e le r�pertoire dans lequel seront plac�s les r�sultats
if [ ! -e $DONNEES ]
then
    echo "Creation du repertoire "$DONNEES
    mkdir $DONNEES
fi
# Je sauvegarde les informations de la machine s'il y en a
if [ -e /proc/cpuinfo ]
then 
    cat /proc/cpuinfo > $DONNEES/info.txt
fi
if [ -e /usr/sbin/sysctl ]
then 
    sysctl hw.availcpu > $DONNEES/info.txt
fi

# Je compile si c'est pas fait
if [ ! -e Acteur.class ]
then 
    javac Acteur.java
    javac Chronometre.java
fi


for MODE in S L F
do
    TEST="donnees_"$MODE".dat"
    echo "# Tests d'attentes sur "$MACHINE" "$DATE > $DONNEES/$TEST
    echo -n "MODE : "$MODE" - " 
    for NBACTEUR in 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 18 20 \
		    22 24 26 28 30 32
    do
	echo -n " "$NBACTEUR
	java Acteur $NBACTEUR $VALEURCIBLE $MODE | grep "#" >> $DONNEES/$TEST
    done
    echo " (fini)"
done
cp Graphique.gp $DONNEES
cd $DONNEES
gnuplot Graphique.gp
cd ..

