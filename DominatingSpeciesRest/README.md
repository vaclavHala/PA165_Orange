# PA165_Orange Syntax for REST API Animals

#Get list of animals
curl -i -X GET http://localhost:8080/pa165/rest/animals

#Get Detail of Animal by id 1
curl -i -X GET http://localhost:8080/pa165/rest/animals/1

#Get brief Detail of Animal by id 1
curl -i -X GET http://localhost:8080/pa165/rest/animals/1/brief

#Delete a Animal with id 1
curl -i -X DELETE http://localhost:8080/pa165/rest/animals/1

#Create a Animal
 curl -X POST -i -H "Content-Type: application/json" --data '{"name":"jmeno","species":"druh"}' http://localhost:8080/pa165/rest/animals/create
 
#Update a Animal 
curl -X PUT -i -H "Content-Type: application/json" --data '{"id": "1", "name":"jmeno","species":"druh","foodNeeded":"100","repreductionRate":"2.2"}' http://localhost:8080/pa165/rest/animals/update


