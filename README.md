# Getting Started Unit Testing With JUnit 5

This repository contains source code examples for the [Pluralsight](http://pluralsight.com) course [Getting Started Unit Testing With JUnit5](http://www.pluralsight.com/courses/junit-5-unit-testing-getting-started). It contains a Java patient registration/intake system that unit tests are created for as the course progresses.

## Prerequisites / Requirements

  * Java 8 or higher is required to compile and run the code.  
  * git, of course

## Branches

This fork's branches deviate from the upstream project by the instructor in the following ways:

1. The `master` branch represents the system state at the **end** of the course
2. There are branches that correspond to the beginning and end state for each course module.
  
It is possible to see the difference in `git`, and export it,  with the following command:

`git diff module/2/begin..module/2/end > module.patch` 

These `patch` files can be directly applied to your fork with the following:

```shell
git checkout -b my/learnin/branch
git apply path/to/patch
git add --all
```

I hope this fork aids you in your learning journey with JUnit5

