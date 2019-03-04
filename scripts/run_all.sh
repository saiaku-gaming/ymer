#!/bin/bash

#asumming we are in ./ymer/scripts
find ../../ -name .git -execdir ../ymer/scripts/run_all_x.sh "$@" \;

