from sklearn.datasets import load_iris
iris=load_iris()
from sklearn import tree
clf1=tree.DecisionTreeClassifier(criterion='entropy',max_depth=3)
clf1=clf1.fit(iris.data,iris.target)
clf2=tree.DecisionTreeClassifier(criterion='gini',max_depth=3)
clf2=clf2.fit(iris.data,iris.target)
tree.export_graphviz(clf1 ,out_file="tree_entropy.dot")
tree.export_graphviz(clf2 ,out_file="tree_gini.dot")

