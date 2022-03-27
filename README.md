This example demonstrates how to run a single test in parallel on the different browsers using TestNG and Gradle.

# Usage

TIn order to launch this example install Gradle and run the following command from the root directory:

```
./gradlew build
```

You should see generated Allure Results files in **build/allure-results** directory.

In order to generate the allure report please run the following command:

```
allure generate build/allure-results
```

Then **allure-report** folder in the root directory will be created. In order to open the allure report please run:

```
allure open allure-report
```

<img width="1782" alt="Screenshot 2022-03-27 at 03 09 15" src="https://user-images.githubusercontent.com/102137047/160261194-7964ea33-495f-4695-b45c-24d6f8b43e28.png">
<img width="1783" alt="Screenshot 2022-03-27 at 03 09 39" src="https://user-images.githubusercontent.com/102137047/160261197-b5a87377-b521-4bd4-ad1b-00ee0aa13e2c.png">




