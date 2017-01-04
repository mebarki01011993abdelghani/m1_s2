/*AGRICOLE*/
set R;
set M;
set T;

/* Ensemble */
var trajets{t in T};
param distances{r in R, m in M};
param capacite{m in M};
param regionUne{t in T};
param regionDeux{t in T};
param regionTrois{t in T};

maximize obj: r1 sum{t in T} 0.1 / (trajets[t] * );

/*CONTRAINTES*/
s.t. sommesTotalTrajets:sum{t in T} trajets[t] = sum{r in R} capacite[R];
s.t. sommesRegionUne: sum{t in T} trajets[t] * regionUne[t] = capacite[0];
s.t. sommesRegionDeux: sum{t in T} trajets[t] * regionDeux[t] = capacite[1];
s.t. sommesRegionTrois: sum{t in T} trajets[t] * regionTrois[t] = capacite[2];


/*DECLARATIONS*/
solve;

/*DONEES*/

data;
/*Ensemble des régions*/
set R := r1 r2 r3;
/*Ensemble des minotories*/
set M := m1 m2 m3;
/*Ensembles des trajets*/
set T := t1 t2 t3 t4 t5 t6 t7 t8 t9;



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

param regionUne :=
 a = 1
 b = 1
 c = 1
 d = 0
 e = 0
 f = 0
 g = 0
 h = 0
 i =0
;

param regionDeux :=
 a = 0
 b = 0
 c = 0
 d = 1
 e = 1
 f = 1
 g = 0
 h = 0
 i =0
;

param regionTrois :=
 a = 0
 b = 0
 c = 0
 d = 0
 e = 0
 f = 0
 g = 1
 h = 1
 i = 1
;

end;


