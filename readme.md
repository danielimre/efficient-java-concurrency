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
- Prometheus http://localhost:9090
- Prometheus Pushgateway http://localhost:9091
- Grafana http://localhost:3000
- Zipkin http://localhost:9411

First time, setup Grafana:
- add Prometheus datasource: `http://prometheus:9090`
- add InfluxDB datasource: `http://influxdb:8086` with `ejc_database` database and `user/user` credentials

Please note that Prometheus will scrape http://localhost:8080/actuator/prometheus by default. Adjust [prometheus.yml](docker/monitoring/config/prometheus/prometheus.yml) if necessary.

### Example projects
Start project specific stack from subprojects (e.g. under `mirage`) with:

```
docker-compose up
```                                        

Start your application via IDE or whatnot.
