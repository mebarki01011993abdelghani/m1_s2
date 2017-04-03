
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
from sklearn.preprocessing import StandardScaler
	



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
	fichier = open("parametres.txt", "w")

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
	print ("--             Avant normalisation des donnees         -")
	print ("--------------------------------------------------------")
	ACTIVATION_TYPES = ["identity", "logistic", "tanh", "relu"]
	for activation in ACTIVATION_TYPES:
		print X_train,"-----------------------------------------------------------------------------------------"
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

#question1222		
def ecrireDonnes(fichier,donnees,activation,i,temps,reseau,clsvm,predict,iterReseau,iterCl):
	donnees.append("---------------------------------------------------------")			
	donnees.append("-          iteration n "+str(i)+" pour l'activation :"+str(activation)+"    - ")
	donnees.append("---------------------------------------------------------")	
	donnees.append("Temps :" +str(temps)+"\n")		
	donnees.append("Nombre Iterations de Reseau:\n"+str(iterReseau)+"\n")
	donnees.append("Nombre Iterations de CLSVM:\n"+str(iterCl)+"\n")
	donnees.append("Performances:\n"+str(predict)+"\n")
	


def question122bis():

	donnees = []
	i = 0;
	fichier = open("parametresquestion2.txt", "w")

	#question122bis1
	iris=load_iris()
	X,Y=make_classification(n_samples=1000,n_features=20,n_informative=15,n_classes=3)
	X_train,X_test,Y_train,Y_test=\
	train_test_split(X,Y,test_size=0.3,random_state=random.seed())
	
	scaler = StandardScaler()
	scaler.fit(X_train)
	X_train = scaler.transform(X_train)
	X_test = scaler.transform(X_test)

	# Question 122bis2
	print ("--------------------------------------------------------")
	print ("--             Apres normalisation des donnees         -")
	print ("--------------------------------------------------------")
	ACTIVATION_TYPES = ["identity", "logistic", "tanh", "relu"]
	for activation in ACTIVATION_TYPES:
		print X_train,"-----------------------------------------------------------------------------------------"
		print ("activation :", activation)
		print ("nombre de couches : _1_")
		start_time = time.time()
		reseau = MLPClassifier(max_iter=200,hidden_layer_sizes=(10), activation=activation, solver='lbfgs', alpha=0.0001, tol=0.0001)
		reseau.fit(X_train,Y_train)
		print ("predict :",reseau.predict(X_test))
		print ("score :",reseau.score(X_train,Y_train))
		clsvm = svm.SVC(max_iter=200,kernel='poly')
		clsvm.fit(X_train,Y_train)
		print ("SVM predict: ",clsvm.predict(X_test))
		print ("SVM score : ",clsvm.score(X_train,Y_train))
		end_time = time.time()
		predict = clsvm.predict(X_test)

		#question 1222
		ecrireDonnes(fichier,donnees,activation,i,(str(end_time - start_time)),reseau,clsvm,predict,200,200)

		print ("Temps exec : ",str(end_time - start_time))
		print ("--------------------------------------------------------")
		print ("activation :", activation)
		print ("nombre de couches : _2_")
		start_time = time.time()
		reseau = MLPClassifier(max_iter=200,hidden_layer_sizes=(10,20), activation=activation, solver='lbfgs', alpha=0.0001, tol=0.0001)
		reseau.fit(X_train,Y_train)
		print ("predict :",reseau.predict(X_test))
		print ("score :",reseau.score(X_train,Y_train))
		clsvm = svm.SVC(max_iter=200,kernel='poly')
		clsvm.fit(X_train,Y_train)
		print ("SVM predict: ",clsvm.predict(X_test))
		print ("SVM score : ",clsvm.score(X_train,Y_train))
		end_time = time.time()
		#question 1222
		ecrireDonnes(fichier,donnees,activation,i,(str(end_time - start_time)),reseau,clsvm,predict,200,200)

		print ("Temps exec : ",str(end_time - start_time))
		print ("--------------------------------------------------------")
		print ("activation :", activation)
		print ("nombre de couches : _3_")
		start_time = time.time()
		reseau = MLPClassifier(max_iter=200,hidden_layer_sizes=(10,20,30), activation=activation, solver='lbfgs', alpha=0.0001, tol=0.0001)
		reseau.fit(X_train,Y_train)
		print ("predict :",reseau.predict(X_test))
		print ("score :",reseau.score(X_train,Y_train))
		clsvm = svm.SVC(max_iter=200,kernel='poly')
		clsvm.fit(X_train,Y_train)
		print ("SVM predict: ",clsvm.predict(X_test))
		print ("SVM score : ",clsvm.score(X_train,Y_train))
		end_time = time.time()
		#question 1222
		ecrireDonnes(fichier,donnees,activation,i,(str(end_time - start_time)),reseau,clsvm,predict,200,200)

		print ("Temps exec : ",str(end_time - start_time))
		print ("--------------------------------------------------------")
		print ("activation :", activation)
		print ("nombre de couches : _4_")
		start_time = time.time()
		reseau = MLPClassifier(max_iter=200,hidden_layer_sizes=(10,20,30,40), activation=activation, solver='lbfgs', alpha=0.0001, tol=0.0001)
		reseau.fit(X_train,Y_train)
		print ("predict :",reseau.predict(X_test))
		print ("score :",reseau.score(X_train,Y_train))
		clsvm = svm.SVC(max_iter=200,kernel='poly')
		clsvm.fit(X_train,Y_train)
		print ("SVM predict: ",clsvm.predict(X_test))
		print ("SVM score : ",clsvm.score(X_train,Y_train))
		end_time = time.time()
		#question 1222
		ecrireDonnes(fichier,donnees,activation,i,(str(end_time - start_time)),reseau,clsvm,predict,200,200)

		print ("Temps exec : ",str(end_time - start_time))
		print ("--------------------------------------------------------")
		print ("activation :", activation)
		print ("nombre de couches : _5_")
		start_time = time.time()
		reseau = MLPClassifier(max_iter=200,hidden_layer_sizes=(10,20,30,40,50), activation=activation, solver='lbfgs', alpha=0.0001, tol=0.0001)
		reseau.fit(X_train,Y_train)
		print ("predict :",reseau.predict(X_test))
		print ("score :",reseau.score(X_train,Y_train))
		clsvm = svm.SVC(max_iter=200,kernel='poly')
		clsvm.fit(X_train,Y_train)
		print ("SVM predict: ",clsvm.predict(X_test))
		print ("SVM score : ",clsvm.score(X_train,Y_train))
		end_time = time.time()
		#question 1222
		ecrireDonnes(fichier,donnees,activation,i,(str(end_time - start_time)),reseau,clsvm,predict,200,200)

		print ("Temps exec : ",str(end_time - start_time))
		print ("--------------------------------------------------------")


		for item in donnees:
	     		fichier.write("%s \n" % item)



#MAIN 

print("####################################################################################")
print("##		QUESTION 121                                                     ##")
print("####################################################################################")
print("creation fichier txt dans la racine")

question121()	

print("####################################################################################")
print("##		QUESTION 122                                                     ##")
print("####################################################################################")

question122()

print("####################################################################################")
print("##		QUESTION 12bis                                                   ##")
print("####################################################################################")

question122bis()
