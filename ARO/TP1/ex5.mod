
/*FIGORA*/

/*ENSEMBLE PRODCTION*/
set P;

/*PRODUCTIONS / MOIS*/
var productions{p in P} >= 0;

var coutP;
/*QUANTITEES MOIS*/

param cout{p in P};
param demande{p in P};
param capacite{p in P};

param minStock;
param stockActuel;
param maxStock;
/**/

maximize defplan: sum{p in P} productions[p];


/*CONTRAINTES*/
s.t. cminStock{p in P}: (productions[p] - demande[p] + stockActuel) >= minStock;
s.t. cmaxStock{p in P}: (productions[p] - demande[p] + stockActuel) <= maxStock;
s.t. ccout{p in P} : productions[p] - demande[p] + stockActuel* 1.5 * cout[p+1];


/*SOLVING*/
solve;
display productions;



/*DONEES*/
/***************************************************************************/
data;
param minStock := 1500;
param maxStock := 6000;
param stockActuel := 2750;
/*Ensemble des mélanges disponibles*/

set P := p1 p2 p3 p4 p5 p6;


param cout :=
  p1 240
  p2 250
  p3 265
  p4 285
  p5 280
  p6 258
;
param demande :=
  p1 1000
  p2 4500
  p3 6000
  p4 5500
  p5 3500
  p6 4000
;
param capacite :=
  p1 4000
  p2 3500
  p3 4000
  p4 4500
  p5 4000
  p6 3500
;

end;

