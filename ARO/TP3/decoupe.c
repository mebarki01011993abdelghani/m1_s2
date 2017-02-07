#include <stdio.h>
#include <stdlib.h>
#include <glpk.h>
#include <stdbool.h>


bool check_contrainte_taille_rouleau(int *motif,int *tailles, int taille_rouleau){
	int somme = 0;
	int tailleMotif = sizeof(motif)/sizeof(int);
	for(int i = 0; i < tailleMotif;i++){
		somme = somme + motif[i] * tailles[i];
	}
	return (somme > taille_rouleau) ? false : true;
};

bool check_contrainte_dual(int *opti_dual,int *tailles){
	int somme = 0;
	int taille_opti = sizeof(opti_dual)/sizeof(int);
	for(int i = 0; i < taille_opti;i++){
		somme = somme + opti_dual[i] * tailles[i];
	}
	return (somme > 1) ? true : false;
};

int ** add_colonne(int * new_colonne,int** matrice){	
	int taille_matrice_lignes = sizeof(matrice)/sizeof(int);
	int taille_matrice_colones = sizeof(matrice[0])/sizeof(int);

	int new_matrice[taille_matrice_lignes+1][taille_matrice_colones];

	for(int i = 0; i < taille_matrice_lignes;i++){
		for(int k = 0; k < taille_matrice_colones;k++){
			new_matrice[i][k] = matrice[i][k];
		}
	}

	for(int i = 0; i < taille_matrice_colones;i++){
		new_matrice[taille_matrice_lignes+1][i] = new_colonne[i];
	}
	
	return new_matrice;
};

int main(int argc, char *argv[])
{
	int taille_rouleau = 100; // cm : largeur des rouleaux initiaux
	int motifs[4][4] = {{2,0,0,0},{0,2,0,0},{0,0,3,0},{0,0,0,7}};
	int tailles[] = {45,36,31,14}; 
	int quantitees[] = {97,610,395,211};
	

exit(0);

};
