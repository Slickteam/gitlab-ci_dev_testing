# gitlab-ci_dev_testing

Runnning tests on Gitlab-CI


# Purpose   

This project is an example of how we manage IT test on Gitlab-CI inside Slickteam.
It is also a support for these medium articles:
* [Integration testing with MongoDB on Gitlab-CI](https://medium.com/slickteam/integration-testing-with-mongodb-on-gitlab-ci-15eda2d293e1)
* article2


# Build

This project is written in Kotlin and uses Gradle as build tool.
There are IT tests within it, so you must start the MongoDB container first.

```script-shell
$ docker-compose -f docker-compose-dev.yml up
```

To have the IT test working on local configuration, you must also copy or rename *dev.example.properties* as *dev.properties*.

After you can build it with:

```script-shell
$ ./gradlew build
```


# Comments about this project

There are some incomplete parts, this project is meant only for the CI / testing part, so these parts will stay as they are.

If you want to use it as an example, please read the medium articles since some useful details are written in there.
There are also explanations about how to use it.

