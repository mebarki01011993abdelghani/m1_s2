#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include "tas.h"
#include "tas.c"
#include "File_reader.h"
#include "File_reader.c"



int betha(Data* data, int fournisseur, int S[], int cout_client[][]){
	int sol = 2*data->opening_cost[fournisseur];
	int cout = 0;
	for(int i = 1; i <= data->client_count; i++){
		if(S[i] == 0){
			/*On recupere le cout de connection*/
			for(int z = 1; z <= data->facility_count; z++){
				if(cout_client[i][j] > 0 ){
					cout = cout_client[i][j];
				}				
			}
			x = cout - data->connection[fournisseur][i];
			if(x > 0)
				sol += x;
		}
	}
	
	return sol;
	
}

void RetirerClient(int fournisseur, int *S, int cout_client[][], Tas tas){
	for(int z = 1; z <= data->client_count; z++){
		if(cout_client[fournisseur][z] > 0 ){
			S[z] = 0; //On retire le client z
			InsererElement(tas, cout_client[fournisseur][z], CLIENT, -1);
		}				
	}
}


void glouton2(Data * data){
	int cout_client[data->client_count + 1][data->facility_count + 1];
	Tas *tas = createTas(10*data->facility_count);
	element x;
	int O[data->facility_count + 1]; 		//O[i] = 1 ssi le fournisseur i est dans O, et O[i] = 0 sinon
	int S[data->client_count + 1];			//S[i] = 1 ssi le client i est dans S, et S[i] = 0 sinon
	
	/*Initialement tous les clients sont dans S*/
	for(int i = 1; i <= data->client_count; i++){
		S[i] = 1;
	}
	
	for(int i = 1; i <= data->client_count; i++){
		for(int j = 1; j <= data->facility_count; j++){
			cout_client[i][j] = 0;
		}
	}
	/*Initialement aucun fournisseur n'est ouvert*/
	for(int i = 1; i <= data->facility_count; i++){
		O[i] = 0;
	}
	
	for(int i = 1; i <= data->facility_count; i++){
		InsererElement(tas, -2*data->opening_cost[i], FOURNISSEUR, i); // etat positif pour les fournisseurs et neg pour les clients
	}
	
	while( EstVide(tas) != 1){
		x = supprimerElement(tas);
		if(x.etat == FOURNISSEUR){ // Si x est un fournisseur
			if(betha(data, x.num, S, cout_client) == x.priorite){
				O[x.num] = 1; 
				retirerClient(x.num, S, cout_client);
			}
		}
	}
	
}


int main(int argc, char *argv[]){
	
	if (argc != 2) {
        printf("Usage: ./executable nom_du_fichier.txt\n");
        return 0;
    }

    Data* data = load_instance(argv[1]);
	printf("Aucun appel de fonction\n");	
}
