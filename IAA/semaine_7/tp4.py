from pylab import rand
from pylab import *
import numpy as np
import pylab as pl
from sklearn import linear_model
from sklearn.linear_model import Ridge
from sklearn.linear_model import Lasso
from sklearn import datasets
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D
from sklearn.metrics import mean_squared_error
from sklearn.grid_search import GridSearchCV

fig = plt.figure()
ax = fig.add_subplot(111, projection='3d')

def contruireGraph(data):
	
	for i in range(len(data)):
		for c, m, zlow, zhigh in [('r', 'o', -50, -25), ('b', '^', -30, -5)]:
		    xs = data[i][0]
		    ys = data[i][1]
		    zs = data[i][2]
		    ax.scatter(xs, ys, zs, c=c, marker=m)



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

def question2(data):
	plt = linear_model.LinearRegression()
	(longeur,largeur) = shape(data)
	Xc = np.empty((longeur,largeur))
	Y = np.empty((longeur,1))
	for i in range(len(data)):
		couple = (data[i][0],data[i][1])
		Xc[i] = np.concatenate((couple, [1]), axis = 0)
		Y[i] =  data[i][2]
	plt.fit(Xc,Y,)
	return plt.coef_

def question3(w,z):
	w=w.T
	rep = mean_squared_error(w, w)
	print "difference : ",rep

def question4(a,b):
	rep = mean_squared_error(a, b)
	print "difference : ",rep
	
def question4Boston(boston):
	boston_X = boston.data[:, np.newaxis, 2]
	boston_X_train = boston_X[:-20]
	boston_X_test = boston_X[-20:]
	boston_y_train = boston.target[:-20]
	boston_y_test = boston.target[-20:]
	regr = linear_model.LinearRegression()
	regr.fit(boston_X_train, boston_y_train)
	return regr.coef_

def question4Diabete(diabetes):
	diabetes_X = diabetes.data[:, np.newaxis, 2]
	diabetes_X_train = diabetes_X[:-20]
	diabetes_X_test = diabetes_X[-20:]
	diabetes_y_train = diabetes.target[:-20]
	diabetes_y_test = diabetes.target[-20:]
	regr = linear_model.LinearRegression()
	regr.fit(diabetes_X_train, diabetes_y_train)
	return regr.coef_
	
def question5Ridge(boston):
	x = boston.data
	y = boston.target
	clf = Ridge(alpha=1.0)
	clf.fit(x,y) 
	return clf
	
def question5Lasso(boston):
	x = boston.data
	y = boston.target
	clf = Lasso(alpha=1.0)
	clf.fit(x,y) 
	return clf

def question5(c,d):
	print "Ridge:"
	print "coef_",c.coef_
	print "intercept_",c.intercept_
	print "Lasso:"
	print "coef_",d.coef_
	print "intercept_",d.intercept_
	
def question6(boston):
	X = boston.data
	y = boston.target
	alphas = np.logspace(-3, -1, 20)
	for Model in [Ridge, Lasso]:
		gscv = GridSearchCV(Model(), dict(alpha=alphas), cv=5).fit(X, y)
		print(Model.__name__, gscv.best_params_)
	
boston = datasets.load_boston()
diabetes = datasets.load_diabetes()
data = np.loadtxt("dataRegLin2D.txt")
w = question1(data)
z = question2(data)

question3(w,z)

a = question4Diabete(diabetes)
b = question4Boston(boston)

question4(a,b)
c = question5Ridge(boston)
d = question5Lasso(boston)
question5(c,d)
question6(boston)
contruireGraph(data)
plt.show()
