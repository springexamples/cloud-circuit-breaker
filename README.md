Service Discovery + Ribbon + Hystrix

one instance of [weather-app](https://github.com/springexamples/cloud-circuit-breaker/tree/master/weather-app) runs over 8080 calls two instances of [weather-service](https://github.com/springexamples/cloud-circuit-breaker/tree/master/weather-service) (runs on ports 8081 and 8082) load balanced via Ribbon (using RestTemplate @LoadBalanced) and discovered via [Eureka discovery service](https://github.com/springexamples/cloud-circuit-breaker/tree/master/discovery-server).

When one weather-service instance of the two is down, then Hystrix call the callback method for number of requests (3 I think).
Then using because of service discovery, the weather-service will be removed from the discovery service after 30 seconds, and at this time all requests to the weather-service will be redirect to the single up instance of the wather-service.

