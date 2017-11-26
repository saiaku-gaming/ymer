createuser -U postgres valhalla

dropdb --if-exists -U postgres person_service
dropdb --if-exists -U postgres character_service
dropdb --if-exists -U postgres party_service
dropdb --if-exists -U postgres friend_service

createdb -U postgres -O valhalla person_service
createdb -U postgres -O valhalla character_service
createdb -U postgres -O valhalla party_service
createdb -U postgres -O valhalla friend_service
