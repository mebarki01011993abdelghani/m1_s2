/*ACTIONS BOURSIERES*/
set O;

/* Ensemble */
var revenu;
var invest{o in O} >= 0;

param yield{o in O};
param short{o in O};
param long{o in O};
param exon{o in O};
param budget;

maximize obj: revenu;

/*CONTRAINTES*/

s.t. defrevenu: revenu = sum{o in O} yield[o] * invest[o];
s.t. c1: sum{o in O} invest[o] = budget;
s.t. c2: sum{o in O} long[o] * invest[o]  >= 0.5 * budget;
s.t. c3: sum{o in O} short[o] * invest[o] >= 0.5 * budget;
s.t. c4: sum{o in O} exon[o] * invest[o] >= 0.4 * budget;
s.t. c5:  0.3 * revenu  <= sum{o in O} yield[o] * exon[o] * invest[o];

/*DECLARATIONS*/
solve;
display invest;

data;

set O := A B C D E;

/* Exonération fiscale true */
param budget := 100000;

param exon :=
  A 0
  B 1
  C 0
  D 1
  E 0
;
/* Revenue annuel */
param yield :=
  A 0.1
  B 0.04
  C 0.07
  D 0.06
  E 0.08
  ;
/* Maturité courte  */
param short :=
  A 1
  B 0
  C 1
  D 1
  E 0
  ;
/* Maturité longue */
param long :=
  A 0
  B 1
  C 0
  D 0
  E 1
  ;


end;


