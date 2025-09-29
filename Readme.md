The order of starting the applications are like:

️config-server 
discovery-server  
kafka and zipkin as docker 
	docker-compose up -d zipkin //start zipkin 	
	doker-compose up -d //start zookeeper kafka and kafkaUI
gateway  
user-service  
product-service  
order-service


docker-compose up -d
docker-compose down -v
docker logs kafka

--kafka commands --
docker ps

--enter into kafka container --
docker exec -it kafka bash
--list topics --
kafka-topics --list --bootstrap-server localhost:9092
--create a topic --
kafka-topics --create --topic test-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
--create producer--
kafka-console-producer --topic test-topic --bootstrap-server localhost:9092

in another console
--enter into kafka container --
docker exec -it kafka bash
--create a consumer--
kafka-console-consumer --topic test-topic --from-beginning --bootstrap-server localhost:9092



