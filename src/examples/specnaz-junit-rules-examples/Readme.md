Specnaz support for JUnit Rules
-------------------------------

This sub-project shows examples of tests leveraging Specnaz's support for the [JUnit Rules API](https://github.com/junit-team/junit4/wiki/rules).
The project contains different packages, demonstrating using various pre-existing Rules:

* [built_in_rules](src/test/java/built_in_rules) shows examples of using Rules that are distributed with JUnit,
   like [ExpectedException](http://junit.org/junit4/javadoc/4.12/org/junit/rules/ExpectedException.html)
   and [TestName](http://junit.org/junit4/javadoc/4.12/org/junit/rules/TestName.html)
* [mockito](src/test/java/mockito) shows how to use [Mockito's JUnit Rule](https://static.javadoc.io/org.mockito/mockito-core/1.10.19/org/mockito/junit/MockitoRule.html)
