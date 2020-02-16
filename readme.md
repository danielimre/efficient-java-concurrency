# EJC - Efficient Java Concurrency

Experiments with different Java concurrency models.

## Running examples

### Monitoring
Start monitoring stack with:
```
docker-compose -f docker-compose-monitoring.yml up
```                            

Following services should be available:
- Graphite http://localhost:80
- Grafana http://localhost:3000
- Zipkin http://localhost:9411

First time, set up grafana datasource: `http://graphite:80`

### Example projects
Start project specific stack from subprojects (e.g. under `mirage`) with:

```
docker-compose up
```                                        

Start your application via IDE or whatnot.
