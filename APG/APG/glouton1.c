#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

typedef struct {
    int client_count;
    int facility_count;
    int* opening_cost;
    int** connection;
} Data;

typedef struct {
    int num_fournisseur;
    int evaluation;
} fournisseur;

void skipLine(FILE *fp) {
    while (fgetc(fp) != '\n');
}

Data* load_instance(char* filename) {
    int fac, client;
    FILE *fp;
    fp = fopen(filename, "r");
    skipLine(fp);
    Data* data = malloc(sizeof (data));
    int nothing;
    fscanf(fp, "%d %d %d", &data->facility_count, &data->client_count, &nothing);
    data->opening_cost = malloc((1 + data->facility_count) * sizeof (int));
    data->connection = malloc((1 + data->facility_count) * sizeof (int*));
    for (fac = 1; fac <= data->facility_count; fac++) {
        fscanf(fp, "%d %d", &nothing, &data->opening_cost[fac]);
        data->connection[fac] = malloc((1 + data->client_count) * sizeof (int));
        for (client = 1; client <= data->client_count; client++) {
            fscanf(fp, "%d", &data->connection[fac][client]);
        }
    }
    fclose(fp);
    return data;
}

void free_data(Data* data) {
    int fac;
    for (fac = 1; fac <= data->facility_count; fac++)
        free(data->connection[fac]);
    free(data->connection);
    free(data->opening_cost);
    free(data);

    return;
}

/*return 1 si valeur appartient à tab
 * et 0 sinon*/
int In(int *tab, int taille_tab, int valeur) {
    int i;
    for (i = 1; i < taille_tab+1; i++) {
        if (tab[i] == valeur) {
            return 1;
        }
    }
    return 0;
}

/*Cette fonction prend en argument un numero de client et la liste
 * des fournisseurs ouverts pour renvoyer le cout minimum pour attacher 
 * ce client à l'un des fournisseurs ouverts*/
int Cout_Min(Data *data, int numClient, int* open_facility, int open_facility_count) {
    int min = INT_MAX;

    for (int ouvert = 1; ouvert <= open_facility_count; ouvert++) {
        printf("connexion entre four %d et cli %d = %d et min = %d\n", ouvert, numClient, data->connection[open_facility[ouvert]][numClient], min);
        if (data->connection[open_facility[ouvert]][numClient] < min) {
            min = data->connection[open_facility[ouvert]][numClient];
            printf("* - MAJ min  = %d \n", min);
        }
    }
    return min;

}

/*Cette fonction prend en argument la structure de notre programme
 * et le sous ensemble des fournisseurs que nous avons decidé d'ouvrir
 * qui sera donné sous forme d'un tableau contenant le numéro des fournisseurs ouverts
 * et retourne le cout total de cette installation*/
int evaluation_function(Data* data, int* open_facility, int open_facility_count) {
    int fac;
    int valeur = 0;
    /*Utile pour l'algorithme glouton*/
    if (open_facility_count == 0) {
        return INT_MAX;
    }

    for (int i = 1; i <= open_facility_count; i++) {

        fac = open_facility[i];
        valeur += data->opening_cost[fac];

    }
    printf("cout d'ouverture des fournisseur = %d\n", valeur);
    
    int valeur2 = 0;
    for (int i = 1; i <= data->client_count; i++) {
        valeur2 += Cout_Min(data, i, open_facility, open_facility_count);
        printf("valeur2 = %d\n", valeur2);
    }
    printf("cout de connexion = %d\n", valeur2);
    return valeur + valeur2;
}

/*retourn le fournisseur non encore pris qui minimise eval*/
fournisseur* Minimise_eval(Data* data, int *open_facility, int open_facility_count) {
 
    fournisseur *f = malloc(sizeof (fournisseur));
    int i_min = -1;
    int eval_min = INT_MAX, eval_temp;

    for (int i = 1; i <= data->facility_count; i++) {
       
        /*si le fournisseur i n'est pas dejà ouvert*/
        if (In(open_facility, open_facility_count, i) == 0) {
            //printf("**On entre dans le premier if de Minimise **\n");
            open_facility[open_facility_count + 1] = i;
            eval_temp = evaluation_function(data, open_facility, open_facility_count + 1);
            printf("eval_temp = %d", eval_temp);
            if (eval_temp <= eval_min) {

                eval_min = eval_temp;
                i_min = i;
            }
        }
    }
    f->num_fournisseur = i_min;
    f->evaluation = eval_min;
    printf("**pour fournisseur %d je trouve f->evaluation = %d**\n", f->num_fournisseur, f->evaluation);
    return f;
    

}

void glouton(Data *data) {
    int i;
    fournisseur *f = malloc(sizeof (fournisseur));
    int *open_facility = malloc((1 + data->facility_count) * sizeof (int));
    int open_facility_count = 0;
    int eval_ouvert = INT_MAX; /*Initialement on n'a pas de fournisseur ouvert*/

    f = Minimise_eval(data, open_facility, open_facility_count);
    while (open_facility_count < data->facility_count && f->evaluation <= eval_ouvert) {
        /*On ouvert un nouveau fournisseur*/
        
        open_facility[open_facility_count + 1] = f->num_fournisseur;
        printf("++++++++++++++++++++++++++++Je rajoute dans mon tab[%d] le fournisseur %d\n", open_facility_count + 1, open_facility[open_facility_count + 1]);
        open_facility_count++;
        eval_ouvert = f->evaluation;
        /*On cherche encore une fois un autre fournisseur*/
        f = Minimise_eval(data, open_facility, open_facility_count);
    }

    /*Affichage des fournisseurs ouvert*/
    printf("Nombre de fournisseurs ouverts dans la fin du glouton %d\n", open_facility_count);
    for (i = 1; i <= open_facility_count; i++) {

        printf("fournisseurs %d ouvert\n", open_facility[i] - 1);
    }
}

int main(int argc, char *argv[]) {
    if (argc != 2) {
        printf("Usage: ./executable nom_du_fichier.txt\n");
        return 0;
    }
    int i;


    Data* data = load_instance(argv[1]);
    glouton(data);

    free_data(data);
    return 0;
}
