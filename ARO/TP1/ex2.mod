/*AGRICOLE*/
set R;
set M;

/* Ensemble */
var t{t in T};
param distances{r in R, m in M};
param capacite{r in R};


maximize obj: 

/*CONTRAINTES*/
s.t. c1:sum{t in T} t[t]

/*DECLARATIONS*/
solve;

data;

set R := r1 r2 r3;
set M := m1 m2 m3;
set T := t1 t2 t3 t4 t5 t6 t7 t8 t9;


/* Region agricoles */
param distances : m1  m2  m3 :=
r1 		210 500 400
r2 		350 300 220
r3 		550 200 250
;

param capacite :=
  m1 200
  m2 550
  m3 225
;

param production :=
  r1 275
  r2 400
  r3 300
;




end;


