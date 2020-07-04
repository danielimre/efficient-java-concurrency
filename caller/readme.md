# EJC - Caller

A Gatling based traffic generator for demonstration purposes.

# Usage

Mirage should be up and running. Execute simulation with

```
  ./mvnw clean gatling:test
```
 You can also specify the length of the execution and the number of users in each second
 
```
  ./mvnw clean gatling:test -DUSERS_PER_SEC=3 -DDURATION=5
```