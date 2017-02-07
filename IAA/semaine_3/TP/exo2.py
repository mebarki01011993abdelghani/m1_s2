from sklearn.datasets import load_iris
iris=load_iris()
from sklearn import tree
clf=tree.DecisionTreeClassifier(criterion='entropy',max_depth=3)
clf=clf.fit(iris.data,iris.target)
print clf.predict(iris.data[50,:])
print clf.score(iris.data,iris.target)
