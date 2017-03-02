#/!bin/bash
compt=0
for FILE in `find ../www/ -name "*.html" `;
do
	execPath=$PWD/${FILE}
	executionTidy="tidy -q --show-warnings false -utf8 $execPath"
	$executionTidy
	compt=$((compt+1))	 
done

compt=$((compt+1))
echo 'Fichiers tidy check : ' $compt > ../traceExecutions/nbFichiersTidy.txt
