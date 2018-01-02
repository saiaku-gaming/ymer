
createuser -U postgres valhalla

dropdb --if-exists -U postgres person_service
dropdb --if-exists -U postgres character_service
dropdb --if-exists -U postgres party_service
dropdb --if-exists -U postgres friend_service
dropdb --if-exists -U postgres wardrobe_service
dropdb --if-exists -U postgres instance_service
dropdb --if-exists -U postgres instance_container_service
dropdb --if-exists -U postgres notification_service
dropdb --if-exists -U postgres feat_service

createdb -U postgres -O valhalla person_service
createdb -U postgres -O valhalla character_service
createdb -U postgres -O valhalla party_service
createdb -U postgres -O valhalla friend_service
createdb -U postgres -O valhalla wardrobe_service
createdb -U postgres -O valhalla instance_service
createdb -U postgres -O valhalla instance_container_service
createdb -U postgres -O valhalla notification_service
createdb -U postgres -O valhalla feat_service
