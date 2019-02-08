#!/bin/sh

if [ "scripts" != "${PWD##*/}" ]; then
    exit 1;
fi

for i in "feat" "character" "person" "statistics" "wardrobe" "notification" "friend" "chat" "party" "instance" "instance-container" "trait" "actionbar" "currency" "recipe" "bank"; do
	if [ ! -e ../../$i-service-server ]; then
		exit 1;
	fi
    cp -f json-logback.xml ../../$i-service-server/src/main/resources/json-logback.xml
    cp -f dev-logback.xml ../../$i-service-server/src/main/resources/dev-logback.xml
done