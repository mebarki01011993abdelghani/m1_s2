#/!bin/bash
for FILE in `find ../www/ -name "*html" `;
do
	execPath=$PWD/${FILE}
	executionTidy="tidy -q --show-warnings false -utf8 $execPath"
	$executionTidy
	 
done
