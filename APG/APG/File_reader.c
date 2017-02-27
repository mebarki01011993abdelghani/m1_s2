#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include "File_reader.h"





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

/*
int main(void){
	printf("vide\n");	
}
*/
