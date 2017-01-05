
/*MELANGES*/

set M;

/* Ensemble */
/*MELANGES*/

param melanges{m in M};
var quantitees{m in M} >= 0;
/*MELANGE TOTALE*/
var mel;
/*NUTRITION*/
param mais{m in M};
param graines{m in M};
param mineraux{m in M};

/**/
param acheterMelange;
maximize defplan: mel;

/*CONTRAINTES*/
s.t. defmel: mel = sum{m in M} quantitees[m];
s.t. cquantitees: sum{m in M} quantitees[m] = acheterMelange;
s.t. cmais:sum{m in M} mais[m]*quantitees[m] >= 0.2*acheterMelange;
s.t. cgraines:sum{m in M} graines[m]*quantitees[m] >= 0.15*acheterMelange;
s.t. cmineraux:sum{m in M} mineraux[m]*quantitees[m] >= 0.25*acheterMelange;

/*SOLVING*/
solve;
display quantitees;



/*DONEES*/
/***************************************************************************/
data;
param acheterMelange := 100;

/*Ensemble des mélanges disponibles*/

set M := m1 m2 m3 m4;


param mais :=
  m1 0.3
  m2 0.2
  m3 0.05
  m4 0.1
;
param graines :=
  m1 0.1
  m2 0.3
  m3 0.15
  m4 0.1
;

param mineraux :=
  m1 0.2
  m2 0.15
  m3 0.20
  m4 0.30
;

end;

