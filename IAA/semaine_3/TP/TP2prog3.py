from sklearn.datasets import load_iris
iris=load_iris()
from sklearn import tree
import random
from sklearn.datasets import make_classification
from sklearn.cross_validation import train_test_split
X,Y=make_classification(n_samples=100000,n_features=20,n_informative=15,n_classes=3)


X_train,X_test,Y_train,Y_test=\
train_test_split(X,Y,test_size=0.3,random_state=random.seed())

for i in range (1,40):
	clf=tree.DecisionTreeClassifier(max_depth=i)
	clf=clf.fit(X,Y)
	print ("Pour un i =", i)
	print clf.score(X,Y)
