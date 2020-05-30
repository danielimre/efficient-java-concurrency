# EJC - Efficient Java Concurrency

Experiments with different Java concurrency models.

## Running examples

### Monitoring
Start monitoring stack with:
```
docker-compose -f docker/monitoring/docker-compose.yml up
```                            

Following services should be available:
- InfluxDB http://localhost:8086
- Grafana http://localhost:3000
- Zipkin http://localhost:9411

First time, set up Grafana datasource: `http://localhost:8086`

### Example projects
Start project specific stack from subprojects (e.g. under `mirage`) with:

```
docker-compose up
```                                        

Start your application via IDE or whatnot.
