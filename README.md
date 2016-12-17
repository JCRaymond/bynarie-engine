# bynarie-engine

## Setup instructions for IntelliJ IDEA

1. Clone the project
1. Yes, create project from sources
1. Select *import project from external model>maven*
1. Enable *search for projects recursively*, *import maven files automatically*
1. Next (should just be *lwjgl-natives-windows&gt;*)
1. Next (should be one project)
1. Next (*use jdk 1.8*)
1. Next (*should be com.bynarie.engine*)

At some point, IDEA will ask to add a bunch of new files to git - say no.

Right click on *engine>src>main>java>HelloWorld* and run

Console should say "Hello LWJGL 3.1.0 build 40!" with a small red window in the middle of the screen.

If it doesn't just work, go to *View>Tool Windows>Maven Projects*
In the took window, double click on *com.bynarie.engine>Lifecycle>compile*