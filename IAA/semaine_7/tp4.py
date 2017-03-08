from pylab import rand
from pylab import *
import numpy as np
import pylab as pl
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D

fig = plt.figure()
ax = fig.add_subplot(111, projection='3d')

def contruireGraph(data):
	
	for i in range(len(data)):
		for c, m, zlow, zhigh in [('r', 'o', -50, -25), ('b', '^', -30, -5)]:
		    xs = data[i][0]
		    ys = data[i][1]
		    zs = data[i][2]
		    ax.scatter(xs, ys, zs, c=c, marker=m)

def contruireNouveauGraph(data,w):
	####
	## TODO
	## calculer un nouveau graph tel que :
	## xs = un point dans une grille prédéfinie avec un pas de 0.5 ( changeable )
	## ys = le même point que ys
	## ws = le produit scalaire entre xs et ys
	####

#Produit scalaire
def produitScalaire(u,v):

	p=0
	for i in range(len(u)):
		p=p+u[i]*v[i]
        return p
        
       
def question1(data):

	(longeur,largeur) = shape(data)
	Xc = np.empty((longeur,largeur))
	Y = np.empty((longeur,1))
	Xt = np.empty((largeur,longeur))
	for i in range(len(data)):
		couple = (data[i][0],data[i][1])
		Xc[i] = np.concatenate((couple, [1]), axis = 0)
		Y[i] =  data[i][2]
	#on calcule la transpose
	Xt = Xc.T
	#on calcule l'inverse du produit Xbarre * Xtranspose
	Xi = np.linalg.inv(Xt.dot(Xc))
	w = (Xi.dot(Xt)).dot(Y)
	return w


data = np.loadtxt("dataRegLin2D.txt")
w = question1(data)
contruireGraph(data)
plt.show()
