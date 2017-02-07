#include <stdio.h>
#include <stdlib.h>
#include <glpk.h>
#include <stdbool.h>


bool check_contrainte_taille_rouleau(int *motifs,int *tailles, int taille_rouleau){
	int somme = 0;
	int tailleMotifs = sizeof(motifs)/sizeof(int);
	for(int i = 0; i < tailleMotifs;i++){
		somme = somme + motifs[i] * tailles[i];
	}
	return (somme > taille_rouleau) ? false : true;
};

bool check_contrainte_dual(int *opti_dual,int *tailles){
	int somme = 0;
	int tailleOpti = sizeof(opti_dual)/sizeof(int);
	for(int i = 0; i < tailleOpti;i++){
		somme = somme + opti_dual[i] * tailles[i];
	}
	return (somme > 1) ? true : false;
};

int main(int argc, char *argv[])
{
	int taille_rouleau = 100; // cm : largeur des rouleaux initiaux
	int motifs[4];
	int tailles[] = {45,36,31,14}; 
	int quantitees[] = {97,610,395,211};
	

exit(0);

};
