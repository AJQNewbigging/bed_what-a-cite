Jira: [WC board](https://anewbigging.atlassian.net/jira/software/projects/WC/boards/4)
# What a Cite: Citation management system

A simple RESTful api to be used by the [FED project](https://github.com/AJQNewbigging/fed_what-a-cite)

## Getting Started

Database schemas are kept in src/main/resources/schema-mysql.sql, this will need to be run before the application can be executed.

A compiled version of the application exists within the base proejct directory. This can be run in your terminal with:

```bash
java -jar bed-0.0.1-SNAPSHOT.jar
```

### Prerequisites

If you do not have an installation of MySQL, a comprehensive installation guide for your device can be found [here](https://dev.mysql.com/doc/mysql-installation-excerpt/5.7/en/).

* MySQL version 14.14; any version between this and the latest will work equally well.

* Java JDK version 16.0.1. You can find a comprehensive installation guide for this [here](https://www.java.com/en/download/help/download_options.html)

* Project Lombok (latest); this is used to generate bioler-plate code through class annotations. An installation guide is available [here](https://projectlombok.org/setup/eclipse).

* Maven (latest); Maven is our build management tool, used to manage dependencies and build our application. Find an installation guide [here](https://maven.apache.org/install.html).

* Springboot (latest); Springboot is what we use to create a RESTful API service, it can be added as a Maven dependency.

### Installing

1. Import SQL schema:

```bash
mysql -u user -p < src/main/resources/schema-mysql.sql
```

2. Build the application in your IDE with maven (optional). Note: a pre-built jar may already exist in the target/ directory, check this if you don't want to overwrite it.

```bash
mvn clean install
```

3. Run the application in your terminal

```bash
java -jar target/bed-0.0.1-SNAPSHOT.jar
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Ash Newbigging** - *BED and FED* - [AJQNewbigging](https://github.com/AJQNewbigging)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Morgan Walsh - providing guidance and example from which to work
* [Baeldung](https://baeldung.com) - providing example code
* [Class-Visualizer](http://www.class-visualizer.net/faq.html) - generating UML
* [Project Lombok](https://projectlombok.org)