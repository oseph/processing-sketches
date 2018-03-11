## Processing Sketches written in Kotlin

This is a personal exploration of the Kotlin language using [Processing](https://www.processing.org). The project is Gradle friendly, but Processing isn't JDK9 friendly, so that means that JDK8 is required to run these. Each sketch can be run with its own Gradle task. So far they are:

`./gradlew runSuperellipse` - Explore the Superellipse

`./gradlew runTreeGenerator` - a tree generator using the Space Colonization algorithm

`./gradlew runSuperShape2D` - Super shapes in two dimensions! Sort of like an extension of the superellipse.

*This project is not JDK9 friendly.*
