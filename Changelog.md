Version 1.5 (2019-11-30)
------------------------
- added JUnit 5 support

Version 1.4.1 (2019-09-01)
--------------------------
- fixed a bug where test suites using TestNG XML files with `<package>` elements failed
  ([Issue #10](https://github.com/skinny85/specnaz/issues/10))

Version 1.4 (2018-07-29)
------------------------
- added TestNG support

Version 1.3.1 (2018-05-06)
--------------------------
- corrected a bug where Specnaz worked only with JUnit version 4.12
- corrected a bug where test method annotations were not being passed correctly to JUnit Test Rules

Version 1.3 (2018-02-27)
------------------------
- added support for JUnit Rules ([Issue #1](https://github.com/skinny85/specnaz/issues/1))
- added support for writing parametrized tests

Version 1.2 (2017-12-25)
------------------------
- added the capability to focus tests with `fshould`, `fshouldThrow` and `fdescribes`
- added the capability to ignore tests with `xshould`, `xshouldThrow` and `xdescribes`
- changed `shouldThrow` to allow specifying further assertions on the thrown Exception
  ([Pull Request #4](https://github.com/skinny85/specnaz/pull/4))

Version 1.1 (2017-07-26)
------------------------
- added `shouldThrow` for Java and Kotlin - [Issue #2](https://github.com/skinny85/specnaz/issues/2)

Version 1.0 (2016-09-09)
------------------------
- first public release
- `should`, `beginsAll/Each`, `endsAll/Each`, `describes`
- Kotlin support
