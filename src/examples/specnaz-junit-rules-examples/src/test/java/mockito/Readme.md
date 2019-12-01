Demonstrates using [Mockito's JUnit 4 Rule](https://static.javadoc.io/org.mockito/mockito-core/1.10.19/org/mockito/junit/MockitoRule.html) in Specnaz.

The integration is straightforward, except for one detail:
if you use the [@InjectMocks](https://static.javadoc.io/org.mockito/mockito-core/2.13.0/org/mockito/InjectMocks.html) annotation,
you need to make sure to re-set
(that is, assign to `null`)
all fields annotated with that after each test -
otherwise, they won't be injected with fresh mock instances again.
See [this test](InjectMocksAnnotationMockitoRuleSpec.java) for details.
