from pylab import rand
import pylab as pl


################################################################################
##                                 Fonctions                                  ##
################################################################################


#Generer des donnees aleatoires :
def generateData(n):

	xb = (rand(n) * 2 - 1) / 2 - 0.5
	yb = (rand(n) * 2 - 1) / 2 + 0.5
	xr = (rand(n) * 2 - 1) / 2 + 0.5
	yr = (rand(n) * 2 - 1) / 2 - 0.5

	inputs = []
	for i in range(n):
		inputs.append(((xb[i],yb[i]),-1))
		inputs.append(((xr[i],yr[i]),1))
	return inputs
		

def generateData2(n):

	xb = (rand(n) * 2 - 1) / 2 + 0.5
	yb = (rand(n) * 2 - 1) / 2
	xr = (rand(n) * 2 - 1) / 2 + 1.5
	yr = (rand(n) * 2 - 1) / 2 - 0.5
	
def complete(sample):
	inputs = []
	for i in range(len(sample)):
		x = list(sample[i][0])
		x.append(1)
		classe = sample[i][1]
		inputs.append((x,classe))
	return inputs
	
#Produit scalaire
def produitScalaire(u,v):

	p=0
	for i in range(len(u)):
		p=p+u[i]*v[i]
        return p
        
#Additionne deux vecteurs
def ajuster(couple1,couple2):

	(abscisse1,ordonnee1) = couple1
	(abscisse2,ordonnee2) = couple2
	return(abscisse1+abscisse2,ordonnee1+ordonnee2)

def perceptron(N,data):

	w = tuple([0]*len(data[0][0]))
	#w=(0,0)
	print w
	for j in range(N):
		taille = len(data)
		for i in range (taille):
			yi = data[i][1]
			xi = data[i][0]
			if(yi*produitScalaire(w,xi) <= 0):
				couple = data[i][0]
				w = ajuster(w,couple)
	pl.plot([0,0],w)
	return w
	##########
	# TODO FAIRE AJUSTER POUR QUELLE PRENNE UN TRUC A N DIMENSIONS
	##########

################################################################################
##                                 Exercices                                  ##
################################################################################
#Dessiner un Graphe
def question1(data):

	taille = len(data)
	
	for i in range (taille):
		(abscisse,ordonnee) = data[i][0]
		classe = data[i][1]
	
		if(classe == 1):
			pl.plot(abscisse,ordonnee,'ob')
		else:
			pl.plot(abscisse,ordonnee,'or')

def question2(data):

	w = perceptron(50,data)
	return w
	
def question3(test,w):

	err = 0.0
	taille = len(test)
	for i in range (taille):
		yi = test[i][1]
		xi = test[i][0]
		if(yi*produitScalaire(w,xi) <= 0):
			err = err +1
	return err/taille
	


################################################################################
##                                 Execution                                 ##
################################################################################
data = generateData(30)
question1(data)
w= question2(data)
test = generateData(1000)
err = question3(test,w)
print "err : ", err

complete(data)
pl.show()
