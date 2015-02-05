bnd-platform-sample
===================

***Build is currently not working because of issues with the submodule, hope to fix this soon. In the meantime, for a running (if minimal) example check out [bnd-platform-minimal](https://github.com/stempler/bnd-platform-minimal).***

Sample project using [bnd-platform](https://github.com/stempler/bnd-platform) to build a set of OSGi bundles. It also makes use of the [gradle-include-plugin](https://github.com/stempler/gradle-include-plugin) to split the configuration in several modules.

Check out **build.gradle** and the script files in **modules/** to see what you can do with *bnd-platform* and run the build with `./gradlew` or `gradlew.bat` to create OSGi bundles and an Eclipse Update Site from the dependencies configured in the example. The created bundles will reside in **build/plugins**, the p2 repository in **build/updatesite**.

The sample includes a quite big set of dependencies and building the platform will download a lot of stuff and may take some time. So feel free to strip it down for your testing.

When cloning the repository make sure to also initialize and update the submodule, e.g. using `git submodule update --init`.
