#/!bin/bash

nom=""

for d in ../www/*.html; do
	exec tidy ${d##*/}
done
exit 0
