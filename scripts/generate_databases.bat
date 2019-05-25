
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
dropdb --if-exists -U postgres statistics_service
dropdb --if-exists -U postgres trait_service
dropdb --if-exists -U postgres actionbar_service
dropdb --if-exists -U postgres recipe_service
dropdb --if-exists -U postgres currency_service
dropdb --if-exists -U postgres bank_service
dropdb --if-exists -U postgres inventory_service


createdb -U postgres -O valhalla person_service
createdb -U postgres -O valhalla character_service
createdb -U postgres -O valhalla party_service
createdb -U postgres -O valhalla friend_service
createdb -U postgres -O valhalla wardrobe_service
createdb -U postgres -O valhalla instance_service
createdb -U postgres -O valhalla instance_container_service
createdb -U postgres -O valhalla notification_service
createdb -U postgres -O valhalla feat_service
createdb -U postgres -O valhalla statistics_service
createdb -U postgres -O valhalla trait_service
createdb -U postgres -O valhalla actionbar_service
createdb -U postgres -O valhalla recipe_service
createdb -U postgres -O valhalla currency_service
createdb -U postgres -O valhalla bank_service
createdb -U postgres -O valhalla inventory_service
