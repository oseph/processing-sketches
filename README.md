## Processing Sketches written in Kotlin

This is a personal exploration of the Kotlin language using the [Processing](https://www.processing.org) core library. The project is Gradle friendly, but beware: Processing isn't JDK9 friendly, so JDK8 is required to run these. Each sketch can be run with its own Gradle task. So far they are:

`./gradlew runSuperellipse` - Explore the superellipse in modulating grid form.

`./gradlew runTreeGenerator` - A tree generator using the space colonization algorithm.

`./gradlew runSuperShape2D` - Super shapes in two dimensions! Sort of like an extension of the superellipse.

*This project is not JDK9 friendly.*
