# sc-gateway-test

## Reproduce

Run gateway and tomcat application.

```bash
cd gateway/
mvn spring-boot:run

cd ../tomcat
mvn spring-boot:run
```

```
curl http://localhost:9000/foo -v # it works as expected
curl http://localhost:9000/empty-response -v
curl http://localhost:9000/redirect -v
```

And then, see gateway application's logs.

> The logging level of the gateway application is set to `error` to see filters'
> logs. You can change it from `gateway/src/main/resources/application.yml`.

## License

[MIT. Copyright (C)](LICENSE) [Halil İbrahim Şener](http://halilsener.com).
