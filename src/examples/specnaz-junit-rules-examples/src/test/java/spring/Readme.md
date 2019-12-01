Demonstrates using [Spring JUnit 4 Rules](https://docs.spring.io/spring/docs/current/spring-framework-reference/testing.html#testcontext-junit4-rules) in Specnaz.

The integration is straightforward, except for one detail:
you need to declare a `public` 'dummy' field, of type
[SpringMethodRule](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/test/context/junit4/rules/SpringMethodRule.html),
in your Spec class,
and annotate it with the [@Rule](http://junit.org/junit4/javadoc/4.12/org/junit/Rule.html) annotation.
The reason is that Spring's [ClassRule](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/test/context/junit4/rules/SpringClassRule.html)
has some built-in validation that checks for the presence of that field,
and fails the tests before they even start if it's not found.
The field will not be used at all, and so can be set to `null` safely.

See [this test](SpringIntegrationSpec.java) for an example.
