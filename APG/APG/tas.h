#ifndef TAS_H
#define TAS_H
	 
#define CLIENT 0
#define FOURNISSEUR 1

typedef struct
{
    int priorite;
    int etat; // client ou fournisseur
    int num; // leur num
} element;

typedef struct
{
    int capacity;
    int size;
    element *tab;
} Tas;






Tas* createTas(int capacite);
void InsererElement(Tas *tas, int prio, int etat, int num);
static void ReallocTas(Tas *tas);
static void reorganizeTas(Tas *tas);
element supprimerElement(Tas *tas);
void freeTas(Tas *tas);








#endif
