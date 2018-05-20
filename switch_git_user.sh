#/bin/bash

if [[ "$1" == "rwfazul" ]]; then 
	set -x;
	git config --global user.name rwfazul
	git config --global user.email rwfazul@inf.ufsm
	git config user.name rwfazul
	git config user.email rwfazul@inf.ufsm	
elif [[ "$1" == "mapframe" ]]; then
	set -x;
	git config --global user.name mapframe
	git config --global user.email mapframe@gmail.com
	git config user.name mapframe
	git config user.email mapframe@gmail.com
else
	echo "valid user required"
fi
