#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include "tas.h"





/*La fonction crée et renvoie un tas*/
Tas* createTas(int capacite)
{
    Tas *tas = malloc(sizeof(Tas));
    if(!tas) return NULL;
    tas->tab = malloc(capacite*sizeof(element));
    tas->capacity = capacite;
    tas->size = 0;
    return tas;
}

/*Retourne 1 si le tas est vide, 0 sinon*/
int EstVide(Tas * tas){
	if(tas->size <= 0){
		return 1;
	}
	else{
		return 0;
	}
}


/*Insere l'element 'value' dans le tas*/
void InsererElement(Tas *tas, int prio, int etat, int num)
{
    if(tas->size >= tas->capacity) ReallocTas(tas);
    tas->size++;
    tas->tab[tas->size].priorite = prio;
    tas->tab[tas->size].etat = etat;
    tas->tab[tas->size].num = num;
    reorganizeTas(tas);
}


/*Si nous n'avons plus de place dans le tas,
 * on realou un tableau de capacite double*/
static void ReallocTas(Tas *tas)
{
    int new_size = 2*tas->capacity;
    tas->tab = realloc(tas->tab, new_size*sizeof(int));
    tas->capacity = new_size;
}



/*Pour remettre l'element le plus grand à la racine 
 * apres avoir inserer un element dans le tas,
 * et pour que chaque fils soit inferieur à son pére*/
static void reorganizeTas(Tas *tas)
{
    int tmp;
    int size = tas->size;
    int index = size/2;
    while(tas->tab[index].priorite < tas->tab[size].priorite && size > 1)
    {
        tmp = tas->tab[size].priorite;
        tas->tab[size].priorite = tas->tab[index].priorite;
        tas->tab[index].priorite = tmp;

        index/=2;
        size/=2;
    }
}


/*La fonction retire le premier element du tas
 * et remet les autres élement dans l'ordre
 * en remettant l'element le plus grand à la racine*/
element supprimerElement(Tas *tas)
{
    int tmp;
    int indexUp = 1;
    int indexDn = 2;

    if(tas->size==0) 
		exit(1);

    element e = tas->tab[1];
    tas->tab[1].priorite = tas->tab[tas->size].priorite;
    tas->size--;

    while(indexDn <= tas->size)
    {
        if(indexDn+1 <= tas->size && tas->tab[indexDn].priorite < tas->tab[indexDn+1].priorite)
        {
            indexDn++;
        }
        if(tas->tab[indexDn].priorite > tas->tab[indexUp].priorite)
        {
            tmp = tas->tab[indexDn].priorite;
            tas->tab[indexDn].priorite = tas->tab[indexUp].priorite;
            tas->tab[indexUp].priorite = tmp;
        }
        indexUp = indexDn;
        indexDn *= 2;
    }
    return e;
}


/*Libere la memoire alloué pour le tas*/
void freeTas(Tas *tas)
{
    free(tas->tab);
    free(tas);
}





/*
int main( int argc, char *argv[])
{	
	Tas* tas = createTas(1000);
	InsererElement(tas, 12);
    InsererElement(tas, 11);
    InsererElement(tas, 14);
    InsererElement(tas, 8);
    printf("Valeur retiree : %d\n", supprimerElement(tas));
    printf("Valeur retiree : %d\n", supprimerElement(tas));
    printf("Valeur retiree : %d\n", supprimerElement(tas));
    printf("Valeur retiree : %d\n", supprimerElement(tas));
return 0;
}
*/
