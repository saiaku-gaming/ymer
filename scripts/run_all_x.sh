#!/bin/bash
if [ "$1" = "--autorun" ]
then
    AUTO_RUN=true
    shift
else
    AUTO_RUN=false
fi

echo "Now running \"$@\" in $(pwd)"
echo ""
eval "$@"
if [ "$AUTO_RUN" = false ] ; then
	echo " "
	echo "End of $(pwd)"
    read -p "Press enter to continue."
fi
echo " "
