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

### Sample output

```
For /foo it works as expected

[ctor-http-nio-2] GatewayConfiguration  : webFilter doOnRequest
[ctor-http-nio-2] GatewayConfiguration  : globalFilter doOnRequest
[ctor-http-nio-3] GatewayConfiguration  : gatewayFilter doOnRequest
[ctor-http-nio-3] GatewayConfiguration  : gatewayFilter doOnSuccessOrError
[ctor-http-nio-3] GatewayConfiguration  : globalFilter doOnSuccessOrError
[ctor-http-nio-3] GatewayConfiguration  : globalFilter doAfterSuccessOrError
[ctor-http-nio-3] GatewayConfiguration  : gatewayFilter doAfterSuccessOrError
[ctor-http-nio-2] GatewayConfiguration  : webFilter doOnSuccessOrError
[ctor-http-nio-2] GatewayConfiguration  : webFilter doAfterSuccessOrError


For /empty-response

[ctor-http-nio-4] GatewayConfiguration  : webFilter doOnRequest
[ctor-http-nio-4] GatewayConfiguration  : globalFilter doOnRequest
[ctor-http-nio-3] GatewayConfiguration  : gatewayFilter doOnRequest
[ctor-http-nio-3] GatewayConfiguration  : gatewayFilter doOnSuccessOrError
[ctor-http-nio-3] GatewayConfiguration  : globalFilter doOnSuccessOrError
[ctor-http-nio-3] GatewayConfiguration  : globalFilter doAfterSuccessOrError
[ctor-http-nio-3] GatewayConfiguration  : gatewayFilter doAfterSuccessOrError


For /redirect

[ctor-http-nio-5] GatewayConfiguration  : webFilter doOnRequest
[ctor-http-nio-5] GatewayConfiguration  : globalFilter doOnRequest
[ctor-http-nio-3] GatewayConfiguration  : gatewayFilter doOnRequest
[ctor-http-nio-3] GatewayConfiguration  : gatewayFilter doOnSuccessOrError
[ctor-http-nio-3] GatewayConfiguration  : globalFilter doOnSuccessOrError
[ctor-http-nio-3] GatewayConfiguration  : globalFilter doAfterSuccessOrError
[ctor-http-nio-3] GatewayConfiguration  : gatewayFilter doAfterSuccessOrError
```

As you see, `webFilter doOnSuccessOrError` and `webFilter doAfterSuccessOrError`
are missing for `/empty-response` and `/redirect`. `content-length` header
is 0 for both.

## License

[MIT. Copyright (C)](LICENSE) [Halil İbrahim Şener](http://halilsener.com).
