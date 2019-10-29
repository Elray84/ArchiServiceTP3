curl -X POST "http://localhost:9001/data?nom=Ouioui&date=123456789"
curl http://localhost:9001/data?ID=0
curl -X POST "http://localhost:9001/data?nom=PetitOursBrun&date=987654321"
curl -X PUT "http://localhost:9001/data?ID=1&nom=PetitOursBrun&date=197382654"
curl http://localhost:9001/data?ID=1
curl -X DELETE http://localhost:9001/data?ID=1
