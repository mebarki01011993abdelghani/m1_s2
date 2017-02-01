from sklearn.datasets import make_classification
X,Y=make_classification(n_samples=200,n_features=2,n_redundant=0,\
n_clusters_per_class=1,n_classes=3)
import pylab as pl
pl.scatter(X[:,0],X[:,1],c=Y)
pl.show()
