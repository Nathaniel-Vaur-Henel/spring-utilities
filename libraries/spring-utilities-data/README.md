# Spring Utilities - Data

## Auto Specification

It's a convenient util to easily build [Specification](https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/domain/Specification.html) from HTTP request parameters.

Full example available here [spring-utilities-data-example](../../examples/spring-utilities-data-example).
Especially to [PersonRequestParamType](../../examples/spring-utilities-data-example/src/main/java/fr/nvh/spring/utilities/fellowship/person/PersonRequestParamType.java) and [PersonSpecification](../../examples/spring-utilities-data-example/src/main/java/fr/nvh/spring/utilities/fellowship/person/PersonSpecification.java).

### Summary

- Create a [Controller](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Controller.html) with `Map` parameters,
- Create an `enum` to describe your parameters,
- Call a method to convert the `Map` parameters,
- Create an [AutoSpecification](src/main/java/fr/nvh/spring/utilities/auto/specification/AutoSpecification.java) to link your parameters to database search,
- Call your preferred [Repository](https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/Repository.html) method.
- And get the result!

And if you need to add a new parameter, just add a line in your enum. 👍

Instead of `enum`, you can create a `class` with instanced objects, but it's not recommended.

### Detailed

#### Write your own RequestParamType

First, you need to create an `enum` implementing [RequestParamType](src/main/java/fr/nvh/spring/utilities/auto/specification/param/RequestParamType.java).
You can find a complete example in [PersonRequestParamType](../../examples/spring-utilities-data-example/src/main/java/fr/nvh/spring/utilities/fellowship/person/PersonRequestParamType.java).

- `filedName()` must return the entity field name, not the SQL field name. It can be null only for `OVER_SEARCH` [SpecificationParamType](src/main/java/fr/nvh/spring/utilities/auto/specification/param/SpecificationParamType.java),
- `paramType()` must return a [SpecificationParamType](src/main/java/fr/nvh/spring/utilities/auto/specification/param/SpecificationParamType.java) to describe the behaviour of your param, can not be null,
- `operator()` must return a [SpecificationOperator](src/main/java/fr/nvh/spring/utilities/auto/specification/param/SpecificationOperator.java) to link your param to a SQL operator, can not be null,
- `isArgumentName()` return `true` if the param is link to the enum value, `false` otherwise. You can use a simple `String`, or a `Set` if you want handle several writings for a parameter,
- `isOverSearch()` and `isOverSearchIncluded()` are default methods used to build specification. It is highly recommended not to implement these methods.

#### Write your own AutoSpecification

Create a class extending [AutoSpecification](src/main/java/fr/nvh/spring/utilities/auto/specification/AutoSpecification.java).  
Choose your entity and use your own [RequestParamType](src/main/java/fr/nvh/spring/utilities/auto/specification/param/RequestParamType.java).  
It's done.  
Example available to [PersonSpecification](../../examples/spring-utilities-data-example/src/main/java/fr/nvh/spring/utilities/fellowship/person/PersonSpecification.java)

#### Link all

To finish, you have just to :

- If you have an input, like an HTTP request, get parameters as `Map<String, String>`, convert it to a `Map<YourRequestParamType, String>` using the method `convert` of [MapStringToMapEnumConverter](src/main/java/fr/nvh/spring/utilities/auto/specification/MapStringToMapRequestParamTypeConverter.java).
- Instantiate an [AutoSpecification](src/main/java/fr/nvh/spring/utilities/auto/specification/AutoSpecification.java) with this converted `Map`.
- Use this [AutoSpecification](src/main/java/fr/nvh/spring/utilities/auto/specification/AutoSpecification.java) in your [repository](https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/Repository.html) method like any [Specification](https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/domain/Specification.html).
- And get the result ! 👍
- If you want to add some new params, just add lines in your [RequestParamType](src/main/java/fr/nvh/spring/utilities/auto/specification/param/RequestParamType.java) enum.

### To do

It's just some ideas. Maybe implemented sometime. Feel free to contribute.

- [ ] handle `.` in fieldName
- [ ] add a `Get Started`
- [ ] add a param type for params in over search not accessible by params name
- [ ] add contributing guideline and code of conduct.
- [ ] add RequestParamTypeValidator to test and validate implemented RequestParamType
- [ ] enhance documentation :D
  - [ ] full explanation of SpecificationOperator
  - [ ] full explanation of SpecificationParamType
  - [ ] table with data type/operator compatibility
- [ ] use Object instead of String in return params map values.
- [ ] handle date value
- [ ] add boolean (isTrue / isFalse) search
- [ ] handle empty has null.
- [ ] add null/not null operator
- [ ] add all mathematics searches
- [ ] handle case
- [ ] handle IN collection
- [ ] add a multiple SpecificationParamType like OVER_SEARCH, but without over behavior
- [ ] add throw exception if no params (configurable)
- [ ] handle join
- [ ] add an over search with additional params (the new over search type will search in email, firstname and lastname, but you can add a param to search on age)

## H2 database utilities

### Summary

- Add `spring-utilities-data` dependency to your project,
- Add `spring.utilities.database.log.enabled=true` to your `application.properties` file,
- Start your application,
- And get the result!

Simple example available at [spring-utilities-data-example](../../examples/spring-utilities-data-example).
Especially to [application.properties](../../examples/spring-utilities-data-example/src/main/resources/application.properties).

### Detailed

#### H2 database

It's a convenient util to easily enhance startup log of H2 database.
If you activate H2 console and spring-utilities database logs in your to your `application.properties` file with :

```properties
# default value 'false', mandatory to activate H2 console
spring.h2.console.enabled=true
# default value 'false', mandatory to activate this H2 console log
spring.utilities.database.log.enabled=true
# default value 'localhost', optional to change H2 console url in log
spring.utilities.database.log.domain=127.0.0.1
```

You will get a startup log like this :

```text
Path to local H2 console: http://127.0.0.1:8080/h2-console ; with JDBC URL= 'jdbc:h2:mem:fellowship_of_the_ring' ; with user= 'JRRT'
```

Notes :
- This example is based on [spring-utilities-data-example](../../examples/spring-utilities-data-example) project. If you run it, you will see the log.
- Using H2 database, the console port is relative to the server port. So, if you change the server port, the H2 console port will change too.
- 🏆 _The configuration is loaded only if your have load the H2 database driver
with `spring.datasource.driver-class-name=org.h2.Driver` in your `application.properties` or `.yaml` file._

### To do

- [X] nothing for now :)

## TestUtils

### Summary

The class [TestUtils.java](src/test/java/fr/nvh/spring/utilities/TestUtils.java) contains some methods to help you to test your code.

### Detailed

#### `assertThatIsUtilityClass`

This method help you to assert that a class is a utility class. A utility class is a class with only static methods and a private constructor. This constructor is usually empty, but it's not mandatory, it can be used to throw an exception for example.

```java
final class MyUtilityClass {

  private MyUtilityClass() {
    //   throw new UnsupportedOperationException("Utility class");// optional
  }

  static void someUtilMethod() {
    // ...
  }

}
```

### To do

- [ ] create a module for other test utils to be used in other projects.
- [ ] add some methods for tests. 