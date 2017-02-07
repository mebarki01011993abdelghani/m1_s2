from sklearn.datasets import load_digits
digits=load_digits()
from sklearn import tree
import random
import math
from sklearn.datasets import make_classification
from sklearn.cross_validation import train_test_split
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


print "---------------------------------"
print "-           GINI                -"
print "---------------------------------"
print "Intervalle : ", [ minimum , maximum ]

print "gini :", e
print "matrice :"
print cm

print len(X_test)

n0 = 0
n1 = 0
for i in range(len(cm)) :
	count = 0
	countn1 = 0
	for j in range(len(cm)) :
		if i != j :
			count = cm[i,j] + count
		else :
			countn1 = cm[i,j] + countn1
	n0 = count + n0	
	n1 = n1 + countn1
	
print i,n0,n1
		

			



