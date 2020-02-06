# coding-energy-consumption
The system that allows to receive and collect data about energy consumption from different villages. As a result, the system should, on demand, give out the consumption report per village for the last 24h.

##Stack:
Spring Boot
Maven
MySQL
Docker
Minikube for Development Environment


##Assumptions:
1. There has to be atleast one electrical counter in every village i.e. a village cannot exist without electrical counter.
2. A village can have any number of electrical counter.
3. The last 24h will be considered from current time when the report is generated.
4. All the electrical counter is operational all the time in every village.
5. GET https://europe-west2-zenhomes-development-project.cloudfunctions.net/counters/1 is always up and working and return non empty response.
6. By default the report will show energy consumption of last 24 hrs per village. 
7. Duration should always be in hours while generating reports.


##ToDo:
If possible do basic authentication on POST /counter_callback call.