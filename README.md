# Savings account management application

## Purpose
Simple application that exposes an endpoint allowing an existing user to open a savings account.

**Restrictions:**

1: Working hours - account may only be opened:
- during all weekdays, except Saturday and Sunday
- between working hours

2: A user may only have one savings account at a time

## Configuration

Working hours parameterization is available through **application.properties** file:
```
working-hours.timezone=GMT+3
working-hours.start=8
working-hours.end=18
```

## Calling the endpoint

Sample valid request:
```
POST http://localhost:8081/account-management/savings-account
Connection: keep-alive
Cache-Control: max-age=0
Authorization: Basic dGVzdF91c2VyOnRlc3RfcGFzcw==
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (iPhone; CPU iPhone OS 13_2_3 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.3 Mobile/15E148 Safari/604.1
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9
Sec-Fetch-Site: cross-site
Sec-Fetch-Mode: navigate
Sec-Fetch-User: ?1
Sec-Fetch-Dest: document
Content-Type: application/json
Accept-Language: en-US,en;q=0.9

{"name":"My savings account","currency":"RON"}
```