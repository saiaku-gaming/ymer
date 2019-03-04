#!/bin/bash

#assumming we are in ./ymer/scripts
INPUT="$@"
find ../../ -name .git -not -path "../../valhalla-intellij-root/.git" -print0 | sed 's/.git//g' | xargs -0L1 bash -c "cd \$1 && pwd && ../ymer/scripts/run_all_x.sh $INPUT" dummy

