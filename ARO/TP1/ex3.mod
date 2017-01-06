
/*POLICIERS*/

set H;


/* Ensemble */
/*TRANCHE HORAIRES DES POLICIERS*/
param horaires{h in H};
var plan;
var planning{h in H} >= 0;

param ha{h in H};
param hb{h in H};
param hc{h in H};
param hd{h in H};
param he{h in H};
param hf{h in H};
param hall{h in H};

minimize defplan: plan;

/*CONTRAINTES*/
s.t. defPlan:plan = sum{h in H} hall[h]*planning[h];
s.t. horaireUne:sum{h in H} ha[h]*planning[h] >= 21;
s.t. horaireDeux:sum{h in H} hb[h]*planning[h] >= 25;
s.t. horaireTrois:sum{h in H} hc[h]*planning[h] >= 25;
s.t. horaireQuatre:sum{h in H} hd[h]*planning[h] >= 30;
s.t. horaireCinq:sum{h in H} he[h]*planning[h] >= 30;
s.t. horaireSix:sum{h in H} hf[h]*planning[h] >= 12;

solve;
display planning;

/*DONEES*/
/***************************************************************************/
data;

/*Ensemble des horaires possible du planning*/

set H := t1 t2 t3 t4 t5 t6;


param hall :=
  t1 1
  t2 1
  t3 1
  t4 1
  t5 1
  t6 1
;
	

param ha :=
  t1 1
  t2 1
  t3 0
  t4 0
  t5 0
  t6 0
;
param hb :=
  t1 0
  t2 1
  t3 1
  t4 0
  t5 0
  t6 0
;
param hc :=
  t1 0
  t2 0
  t3 1
  t4 1
  t5 0
  t6 0
;
param hd :=
  t1 0
  t2 0
  t3 0
  t4 1
  t5 1
  t6 0
;
param he :=
  t1 0
  t2 0
  t3 0
  t4 0
  t5 1
  t6 1
;
param hf :=
  t1 1
  t2 0
  t3 0
  t4 0
  t5 0
  t6 1
;

end;

