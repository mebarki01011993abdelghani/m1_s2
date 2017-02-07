from sklearn.datasets import load_iris
iris=load_iris()
from sklearn import tree
import random
import math
from sklearn.datasets import make_classification
from sklearn.cross_validation import train_test_split
X,Y=make_classification(n_samples=100000,n_features=20,n_informative=15,n_classes=3)

X_2,X_1,Y_2,Y_1=\
train_test_split(X,Y,test_size=0.95,random_state=random.seed())

X_app,X_test,Y_app,Y_test=\
train_test_split(X_1,Y_1,test_size=0.2,random_state=random.seed())

clf=tree.DecisionTreeClassifier()
clf.fit(X_app,Y_app)

e = 1 - clf.score(X_test,Y_test)
n = 1000

minimum =  e - 1.96 * math.sqrt( (e * (1-e)) / n )
maximum =  e + 1.96 * math.sqrt( (e * (1-e)) / n )

print [ minimum , maximum ]

