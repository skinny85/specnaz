Specnaz support for JUnit 4 Rules
---------------------------------

This sub-project shows examples of tests leveraging Specnaz's support for the
[JUnit 4 Rules API](https://github.com/junit-team/junit4/wiki/rules).
The project contains different Java packages, demonstrating using various pre-existing Rules:

* [built_in_rules](src/test/java/built_in_rules) shows examples of using Rules that are distributed with JUnit 4,
   like [ExpectedException](http://junit.org/junit4/javadoc/4.12/org/junit/rules/ExpectedException.html)
   and [TestName](http://junit.org/junit4/javadoc/4.12/org/junit/rules/TestName.html)
* [mockito](src/test/java/mockito) shows how to use
   [Mockito's JUnit 4 Rule](https://static.javadoc.io/org.mockito/mockito-core/1.10.19/org/mockito/junit/MockitoRule.html)
   in Specnaz
* [spring](src/test/java/spring) shows how to use the
   [Spring JUnit 4 Rules](https://docs.spring.io/spring/docs/current/spring-framework-reference/testing.html#testcontext-junit4-rules)
   in Specnaz
* [dropwizard](src/test/java/dropwizard) shows how to use
   [Dropwizard's ResourceTestRule](http://www.dropwizard.io/en/stable/manual/testing.html#testing-resources)
   in Specnaz

Check out the [main documentation on JUnit 4 Rules](../../../docs/reference-manual.md#junit-4-rules)
for more details on how to use JUnit 4 Rules in Specnaz.
