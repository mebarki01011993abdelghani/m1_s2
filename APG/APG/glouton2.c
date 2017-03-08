#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include "tas.h"
#include "tas.c"
#include "File_reader.h"
#include "File_reader.c"


typedef struct {
	int index;
	int opening_cost;
    int* connection;
} Fournisseur;
	

/* fonction utilisateur de comparaison fournie a qsort() */
static int compare (void const *a, void const *b)
{
	int const *pa = a;
    int const *pb = b;

   return *pa - *pb;
}



int betha(Data* data, int fournisseur, int S[], int **cout_client){
	int sol = 2*data->opening_cost[fournisseur];
	int cout = 0;
	for(int i = 1; i <= data->client_count; i++){
		if(S[i] == 0){
			/*On recupere le cout de connection*/
			for(int z = 1; z <= data->facility_count; z++){
				if(cout_client[i][z] > 0 ){
					cout = cout_client[i][z];
				}				
			}
			int x = cout - data->connection[fournisseur][i];
			if(x > 0)
				sol += x;
		}
	}
	
	
	/*finir les 3 autres points du calcul de betha et enregistrer les clients pris dans le spoint 2*/
	return sol;
	
}

void RetirerClient(Data *data, int fournisseur, int *S, int **cout_client, Tas *tas){
	for(int z = 1; z <= data->client_count; z++){
		if(cout_client[fournisseur][z] > 0 ){
			S[z] = 0; //On retire le client z
			InsererElement(tas, cout_client[fournisseur][z], CLIENT, -1);
		}				
	}
}


void glouton2(Data * data){
	int **cout_client = malloc((data->client_count + 1)*sizeof(int *));
	//Cout_Client clients[data->client_count + 1];
	
	
	Fournisseur **f = malloc((data->facility_count + 1)*sizeof(Fournisseur*));
	for(int i = 1; i <= data->facility_count; i++){
		f[i]->connection = malloc((data->client_count + 1)*sizeof(int));
	}
	
	
	
	int cout_connexion_triee[data->client_count + 1][data->facility_count + 1];
	printf("deb de glouton\n");
	/*Remplir le tableau facility*/
	for(int i = 1; i <= data->facility_count; i++){
		for(int j = 1; j <= data->client_count; i++){
			printf("i = %d et j = %d\n", i, j);
			(f[i]->connection)[j] = data->connection[i][j];
			printf("facility[%d][%d] = %d\n\n", i, j, (f[i])->connection[j]);
		}
	}
	printf("j'ai remplir\n");
	for(int i = 1; i <= data->client_count; i++){
		printf("non trie %d = \n", (f[1])->connection[i]);
	}
	/*On trie les cout client par fournisseur en ordre croissant*/
	for(int i = 1; i <= data->facility_count; i++){
		qsort (&f[i] , data->client_count, sizeof(cout_client), compare);
	}
	for(int i = 1; i <= data->client_count; i++){
		printf("trie %d = \n", (f[1])->connection[i]);
	}
	
	
	
	
	Tas *tas = createTas(10*data->facility_count);
	element x;
	int O[data->facility_count + 1]; 		//O[i] = 1 ssi le fournisseur i est dans O, et O[i] = 0 sinon
	int S[data->client_count + 1];			//S[i] = 1 ssi le client i est dans S, et S[i] = 0 sinon
	
	for(int i = 1; i <= data->client_count; i++){
		cout_client = malloc((data->facility_count+1)*sizeof(int));
	}
	
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
				RetirerClient(data, x.num, S, cout_client, tas);
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
    glouton2(data);
	printf("Aucun appel de fonction\n");	
}
