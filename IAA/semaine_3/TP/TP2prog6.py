from sklearn.datasets import load_digits
digits=load_digits()
from sklearn import tree
import random
import math
from sklearn.datasets import make_classification
from sklearn.cross_validation import train_test_split
from sklearn.metrics import *
from sklearn.metrics import confusion_matrix
X = digits.data
Y = digits.target



X_test,X_app,Y_test,Y_app=\
train_test_split(X,Y,test_size=0.3,random_state=random.seed())


clf=tree.DecisionTreeClassifier(criterion='entropy')
clf.fit(X_app,Y_app)
e = 1 - clf.score(X_test,Y_test)
n = len(X)

minimum =  e - 1.96 * math.sqrt( (e * (1-e)) / n )
maximum =  e + 1.96 * math.sqrt( (e * (1-e)) / n )
Y_pred =clf.predict(X_test)
cm = confusion_matrix(Y_test, Y_pred)


print "---------------------------------"
print "-           ENTROPY             -"
print "---------------------------------"
print "Intervalle : ", [ minimum , maximum ]

print "entropy :", e
print "matrice :"
print cm


clf=tree.DecisionTreeClassifier(criterion='gini')
clf.fit(X_app,Y_app)
e = 1 - clf.score(X_test,Y_test)
n = len(X)

minimum =  e - 1.96 * math.sqrt( (e * (1-e)) / n )
maximum =  e + 1.96 * math.sqrt( (e * (1-e)) / n )
Y_pred =clf.predict(X_test)
cm = confusion_matrix(Y_test, Y_pred)

n00 = 0
n01 = 0
n10 = 0
n11 = 0
for i in range(len(cm)):
    for j in range(len(cm)):
        if ( i!=j ):
            n00 = n00+cm[i][j]
            n01 = n01+cm[i][j]
        else:
            n11 = n11+cm[i][j]
            n10 = n10+cm[i][j]
resultat = (((abs(n01 - n10)) - 1)*((abs(n01 - n10)) - 1)) / ( n01 + n10 )

print resultat 

print "---------------------------------"
print "-           GINI                -"
print "---------------------------------"
print "Intervalle : ", [ minimum , maximum ]

print "gini :", e
print "matrice :"
print cm

n00 = 0
n01 = 0
n10 = 0
n11 = 0
for i in range(len(cm)):
    for j in range(len(cm)):
        if ( i!=j ):
            n00 = n00+cm[i][j]
            n10 = n10+cm[i][j]
        else: 
            n11 = n11+cm[i][j]
            n01 = n01+cm[i][j]
            
resultat = (abs((n01 - n10) - 1)*abs((n01 - n10) - 1))/(n01+n10)

print resultat 

if (resultat>3.841459) :
	print "sup a 95 %"
else :
	print "inf a 95 %"

			



