/*PROBLEME SAC A DOS*/
set I; /*OBJETS DISPONIBLES*/

/* Ensemble */
param poids{i in I};
param distances{r in R, m in M};
param capacite{m in M};


/*RECHERCHE DES QUANTITEES POUR L APPROVISIONEMENT*/
var quantitees{r in R, m in M}>= 0;


/*Calcul du cout*/
param cost{r in R, m in M} := 0.1 *distances[r,m];


minimize prix: sum{r in R, m in M} cost[r,m]*quantitees[r,m];

/*CONTRAINTES*/
s.t. sommesTotalTrajets:sum{r in R, m in M} quantitees[r,m] = sum{r in R} production[r];
s.t. sommesRegions {r in R} : sum{m in M} quantitees[r,m] <= production[r];
s.t. limitesMinotories {r in R,m in M} : quantitees[r,m] <= capacite[m];

/*DECLARATIONS*/
solve;
display quantitees;
/*DONEES*/

data;
/*Ensemble des régions*/
set R := r1 r2 r3;
/*Ensemble des minotories*/
set M := m1 m2 m3;


/* Distances des  région agricoles  et les minoteries*/
param distances : m1  m2  m3 :=
r1 		210 500 400
r2 		350 300 220
r3 		550 200 250
;

/*Capacité des minoteries*/
param capacite :=
  m1 200
  m2 550
  m3 225
;
/*Production par région*/
param production :=
  r1 275
  r2 400
  r3 300
;


end;
















