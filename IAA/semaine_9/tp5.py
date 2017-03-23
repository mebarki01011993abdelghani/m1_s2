
import numpy as np
import matplotlib.pyplot as plt
from sklearn.datasets import fetch_mldata
import time
from sklearn.datasets import load_iris
from sklearn.neural_network import MLPClassifier
import random
from sklearn.datasets import make_classification
from sklearn.cross_validation import train_test_split
from sklearn import svm



def question121() :

	## Question 1.2.1.1
	X_train = [[0,0],[0,1],[1,0],[1,1]]
	Y_train = [0,1,0,1]
	X_test = [[-1,-2],[2,2]]

	##Question 1.2.1.2
	reseau = MLPClassifier(hidden_layer_sizes=(4), activation='relu', solver='lbfgs', alpha=0.0001, max_iter=200, tol=0.0001)
	reseau.fit(X_train,Y_train)
	resultat = reseau.predict(X_test)

	
	## Question 1.2.1.4
	X_train = [[0,0],[0,1],[1,1],[1,1]]
	Y_train = [0,1,0,1]
	X_test = [[-1,-2],[2,6]]
	reseau.fit(X_train,Y_train)
	resultat = reseau.predict(X_test)
	
	## Question 1.2.1.5
	reseau = MLPClassifier(hidden_layer_sizes=(4), activation='relu', solver='lbfgs', alpha=0.0001, max_iter=200, tol=0.0001)

	X_train = [[0,0],[0,1],[1,1],[1,1]]
	Y_train = [0,1,0,1]
	X_test = [[-1,-2],[2,6]]
	reseau.fit(X_train,Y_train)
	resultat = reseau.predict(X_test)
	parametres = reseau.get_params()
	fichier = open("/amuhome/g11538437/Bureau/m1_s2/IAA/semaine_9/parametres.txt", "w")

	for item in parametres:
	     fichier.write("%s \n" % item)
	fichier.close()
	
def question122():

	# Question 12221
	iris=load_iris()
	X,Y=make_classification(n_samples=1000,n_features=20,n_informative=15,n_classes=3)
	X_train,X_test,Y_train,Y_test=\
	train_test_split(X,Y,test_size=0.3,random_state=random.seed())
	
	# Question 12222
	print ("--------------------------------------------------------")
	ACTIVATION_TYPES = ["identity", "logistic", "tanh", "relu"]
	for activation in ACTIVATION_TYPES:
		print ("activation :", activation)
		print ("nombre de couches : _1_")
		start_time = time.time()
		reseau = MLPClassifier(hidden_layer_sizes=(10), activation=activation, solver='lbfgs', alpha=0.0001, tol=0.0001)
		reseau.fit(X_train,Y_train)
		print ("predict :",reseau.predict(X_test))
		print ("score :",reseau.score(X_train,Y_train))
		# Question 12223
		clsvm = svm.SVC(kernel='poly')
		clsvm.fit(X_train,Y_train)
		print ("SVM predict: ",clsvm.predict(X_test))
		print ("SVM score : ",clsvm.score(X_train,Y_train))
		end_time = time.time()
		print ("Temps exec : ",str(end_time - start_time))
		print ("--------------------------------------------------------")
		print ("activation :", activation)
		print ("nombre de couches : _2_")
		start_time = time.time()
		reseau = MLPClassifier(hidden_layer_sizes=(10,20), activation=activation, solver='lbfgs', alpha=0.0001, tol=0.0001)
		reseau.fit(X_train,Y_train)
		print ("predict :",reseau.predict(X_test))
		print ("score :",reseau.score(X_train,Y_train))
		# Question 12223
		clsvm = svm.SVC(kernel='poly')
		clsvm.fit(X_train,Y_train)
		print ("SVM predict: ",clsvm.predict(X_test))
		print ("SVM score : ",clsvm.score(X_train,Y_train))
		end_time = time.time()
		print ("Temps exec : ",str(end_time - start_time))
		print ("--------------------------------------------------------")
		print ("activation :", activation)
		print ("nombre de couches : _3_")
		start_time = time.time()
		reseau = MLPClassifier(hidden_layer_sizes=(10,20,30), activation=activation, solver='lbfgs', alpha=0.0001, tol=0.0001)
		reseau.fit(X_train,Y_train)
		print ("predict :",reseau.predict(X_test))
		print ("score :",reseau.score(X_train,Y_train))
		# Question 12223
		clsvm = svm.SVC(kernel='poly')
		clsvm.fit(X_train,Y_train)
		print ("SVM predict: ",clsvm.predict(X_test))
		print ("SVM score : ",clsvm.score(X_train,Y_train))
		end_time = time.time()
		print ("Temps exec : ",str(end_time - start_time))
		print ("--------------------------------------------------------")
		print ("activation :", activation)
		print ("nombre de couches : _4_")
		start_time = time.time()
		reseau = MLPClassifier(hidden_layer_sizes=(10,20,30,40), activation=activation, solver='lbfgs', alpha=0.0001, tol=0.0001)
		reseau.fit(X_train,Y_train)
		print ("predict :",reseau.predict(X_test))
		print ("score :",reseau.score(X_train,Y_train))
		# Question 12223
		clsvm = svm.SVC(kernel='poly')
		clsvm.fit(X_train,Y_train)
		print ("SVM predict: ",clsvm.predict(X_test))
		print ("SVM score : ",clsvm.score(X_train,Y_train))
		end_time = time.time()
		print ("Temps exec : ",str(end_time - start_time))
		print ("--------------------------------------------------------")
		print ("activation :", activation)
		print ("nombre de couches : _5_")
		start_time = time.time()
		reseau = MLPClassifier(hidden_layer_sizes=(10,20,30,40,50), activation=activation, solver='lbfgs', alpha=0.0001, tol=0.0001)
		reseau.fit(X_train,Y_train)
		print ("predict :",reseau.predict(X_test))
		print ("score :",reseau.score(X_train,Y_train))
		# Question 12223
		clsvm = svm.SVC(kernel='poly')
		clsvm.fit(X_train,Y_train)
		print ("SVM predict: ",clsvm.predict(X_test))
		print ("SVM score : ",clsvm.score(X_train,Y_train))
		end_time = time.time()
		print ("Temps exec : ",str(end_time - start_time))
		print ("--------------------------------------------------------")
		
	
	
question122()
