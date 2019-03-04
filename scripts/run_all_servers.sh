#!/bin/bash

#asumming we are in ./ymer/scripts
find ../../ -name .git -path "*server/*" -execdir ../ymer/scripts/run_all_x.sh "$@" \;

