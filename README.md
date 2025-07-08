1. WebClient – Reactive HTTP Client (Spring WebFlux)
WebClient is a non-blocking, reactive HTTP client introduced in Spring 5 as part of the WebFlux module. It replaces the older RestTemplate.

    Key Features:
      Supports asynchronous (non-blocking) communication
      Part of Spring WebFlux
      Suitable for high-throughput, reactive applications
      More flexible and powerful than RestTemplate.

2. FeignClient – Declarative REST Client
FeignClient is a declarative HTTP client built by Netflix and supported by Spring Cloud. It simplifies REST API calls by allowing you to just write an interface and annotate it.

    Key Features:
      Works with Eureka for service discover
      Automatically uses Ribbon or Spring Cloud LoadBalancer for load balancing
      Clean and simple syntax
      Supports fallbacks with Resilience4j or Hystrix
______________________________________________________________________________________________________________________________

🔷 API Gateway – Notes for Spring Boot / Microservices
An API Gateway is a single entry point for all client requests to a system of microservices.
It routes, secures, transforms, and monitors traffic between the client and backend services.
🧠 Why API Gateway is Important:
Microservices expose many APIs — API Gateway helps organize, protect, and manage them.

🧱 Architecture Overview:
  Client → API Gateway → Auth Service
                      → Product Service
                      → Order Service

______________________________________________________________________________________________________________________________

🔷 Eureka Server & Discovery Service – Notes for Spring Boot / Microservices
    Eureka is a Service Discovery tool from Netflix, integrated into Spring Cloud.
    It enables dynamic registration and discovery of microservices in a distributed architecture.

🔧 Key Components:
    Component	Description
    Eureka Server	Central registry where all microservices register themselves
    Eureka Client	A microservice that registers with the server and discovers other services

🔷 Eureka Server (Service Registry)
    Acts as a directory for microservices.
    Maintains a registry of all running service instances.
    Services register and periodically send heartbeats to indicate they are alive.

🧠 Benefits of Eureka Discovery:
    Dynamic discovery: No hardcoded URLs
    Load balancing ready: Integrates with client-side load balancer
    Failure resilience: Auto deregisters dead instances
    Centralized registry: View and manage all microservices in one dashboard

______________________________________________________________________________________________________________________________

🔷 Circuit Breaker – Notes for Spring Boot / Microservices
    A Circuit Breaker is a resilience pattern used in distributed systems (like microservices) to prevent cascading failures when a dependent service becomes slow, unresponsive, or fails.

🔧 Why Use It?
    In a microservices setup, if one service is down or slow:
    Continuous calls to it can cause timeouts
    Calling service may also crash or degrade
    This can lead to system-wide failures

🔁 Circuit Breaker States:
    🔵 Closed	Normal state – all requests are allowed
    🔴 Open	After repeated failures – requests are blocked and a fallback is triggered
    🟡 Half-Open	Allows limited test requests to see if the service has recovered

📦 Features of Circuit Breaker:
Failure monitoring
Timeout detection
Graceful degradation via fallback
Automatic recovery
Integrates with Resilience4j, Hystrix (legacy), Sentinel
    
