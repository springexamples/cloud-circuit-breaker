Service Discovery + Ribbon + Hystrix

one weather app calls two instances of weather-service load balanced via Ribbon, all done via Eureka service registery

When one weather-service instance of the two is down, then Hystrix call the callback method for number of requests (3 I think).
Then using Hystrix and Eureka all requests are fully redirect to the single up instance of the wather-service. (amazing!)

