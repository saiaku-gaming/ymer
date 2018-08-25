#!/bin/sh

set -e
PWD=$(pwd)

if [ ! -e backend ]; then
	mkdir backend
fi
cd backend

for i in "feat" "character" "person" "statistics" "wardrobe" "notification" "friend" "chat" "party" "instance" "instance-container" "trait" "actionbar"; do
	if [ ! -e $i-service-server ]; then
		git clone "git@github.com:saiaku-gaming/$i-service-server.git"
	fi
	if [ ! -e $i-service-client ]; then
		git clone "git@github.com:saiaku-gaming/$i-service-client.git"
	fi
done

if [ ! -e ymer ]; then
	git clone git@github.com:saiaku-gaming/ymer.git
fi

if [ ! -e valhalla-common ]; then
	git clone git@github.com:saiaku-gaming/valhalla-common.git
fi

cd ..
if [ ! -e misc ]; then
	mkdir misc
fi
cd misc

if [ ! -e jenkins-common ]; then
	git clone git@github.com:saiaku-gaming/jenkins-common.git
fi

if [ ! -e valhalla-game.se ]; then
	git clone git@github.com:saiaku-gaming/valhalla-game.se.git
fi

if [ ! -e valhalla-intellij-root ]; then
	git clone git@github.com:saiaku-gaming/valhalla-intellij-root.git
fi

