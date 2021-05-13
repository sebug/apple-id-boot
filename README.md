# Apple ID Boot
Spring Boot application that uses Apple ID for login.

## Obtaining client ID and client secret
In the application.properties file I have put the parameters I obtained from
https://github.com/spring-projects/spring-security/issues/9047 , referencing two
environment variables APPLE_CLIENT_ID and APPLE_CLIENT_SECRET .

To find those out, I'll use the steps on https://auth0.com/docs/connections/apple-siwa/set-up-apple 

Ok, that needs a real https URL, let me set that up first.

