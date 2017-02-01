from sklearn.datasets import load_iris
iris=load_iris()
from sklearn import tree
clf1=tree.DecisionTreeClassifier(criterion='entropy',max_depth=2)
clf1=clf1.fit(iris.data,iris.target)
tree.export_graphviz(clf ,out_file="essai.dot")

