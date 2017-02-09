#!/bin/bash
ICI=`pwd`
MACHINE="MacbookPro" # Pas d'espaces ici, merci
VALEURCIBLE=10000000000
DONNEES="_mesures_sur_"$MACHINE
DATE=`date "+DATE: %Y-%m-%d HEURE: %H:%M:%S"`

echo "Performances de r�servoirs sur "$MACHINE" ("$DATE")"

# Je cr�e le r�pertoire dans lequel seront plac�s les r�sultats
if [ ! -e $DONNEES ]
then
    echo "Creation du repertoire "$DONNEES
    mkdir $DONNEES
fi
# Je sauvegarde les informations de la machine
if [ -e /proc/cpuinfo ]
then 
    cat /proc/cpuinfo > $DONNEES/info_materiel.txt
fi
if [ -e /usr/sbin/sysctl ]
then 
    sysctl hw.availcpu > $DONNEES/info_materiel.txt
fi

for MODE in 0_-_LockFree 1_-_ThreadPool 2_-_Futures 3_-_ExecutorCompletionService
do
    TEST="mesures_"$MODE".dat"
    echo "# Mesure de MonteCarlo.java dans "$MODE" sur "$MACHINE\
	 "("$DATE")" > $DONNEES/$TEST
    echo -n "MODE : "$MODE" - " 
    cd $MODE # Il y a un code diff�rent pour chaque mode
    # Je compile si c'est pas fait
    if [ ! -e MonteCarlo.class ]
    then 
	javac *.java
    fi
    for NBTHREADS in 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
    do
	echo -n " "$NBTHREADS
	java MonteCarlo $VALEURCIBLE $NBTHREADS  | grep "#_CHRONO_#" >> ../$DONNEES/$TEST
    done
    echo " (fini)"
    make clean
    cd $ICI
done

# On traite � part le cas s�quentiel
MODE="4_-_Sequentiel"
TEST="mesures_"$MODE".dat"
echo "# Mesure de MonteCarlo.java dans "$MODE" sur "$MACHINE\
     "("$DATE")" > $DONNEES/$TEST
echo -n "MODE : "$MODE" - " 
cd $MODE
# Je compile si c'est pas fait
if [ ! -e MonteCarlo.class ]
then 
    javac *.java
fi
java MonteCarlo $VALEURCIBLE | grep "#_CHRONO_#" >> ../$DONNEES/$TEST
echo " (fini)"
make clean
cd $ICI

echo "Fabrique des graphiques � partir des mesures obtenues..."
cp graphiques.gp $DONNEES
cd $DONNEES
gnuplot graphiques.gp
cd ..
echo "Fini!"
