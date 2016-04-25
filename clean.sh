#!/usr/bin/bash

if [ $# -eq 0 ]
then
	echo "usage: clean Folder1";
	exit 1;
fi

cd $1
rm *.class
rm *.ctxt
cd ..