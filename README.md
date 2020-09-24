# HalloweenKit
A Little Halloween Kit for Halloween 2020

## Getting Started
### Preperation
- Eclipse IDE (For Java) [https://www.eclipse.org/downloads/packages/]
- A JRE ([8](https://www.oracle.com/java/technologies/javase-jre8-downloads.html) or [above](https://jdk.java.net/)) 
- A copy of the current version of Spigot downloaded or built locally with [BuildTools](https://www.spigotmc.org/wiki/buildtools/).

### Setup
- Clone and Checkout the project (dev branch)
- Copy your `spigot-1.16.3.jar` to the `/run/Spigot` directory
- Import the project into Eclipse and select `Spigot Server` from the launch menu to verify everything works.

## The Process
The process employed by this project place emphasis on local feature development, and collective integration and testing. This means;
- When working on a new feature or bugfix create a local feature branch for development.
- Once completed merge the local feature branch into the `dev` for review and collective testing. 
- When a set of updates are considered 'stable' the `dev` branch will be merged into the `build` branch and a copy of the plugin jar file will be made available for download. 
- The `main` branch will catch up to the build branch once builds are successuly installed onto the production server.

## Versioning
To Keep it simple we'll be using a flavour of [semver](https://semver.org/); MAJOR.MINOR.PATCH-BUILD where;
- The BUILD will be the serialised date of the jar was built (i.e. 24/09/2020 would be build 24092020).
- PATCH will be set to 0 when MINOR is incremented, and will be incremented when bugs are fixed.
- MINOR will be set to 0 when MAJOR is incremented, and will be incremented when a new feature is added.
- MAJOR will be incremented when a command available in game changes in a non-backward compatible way or a programming api the plugin exposes changes in a non-backward compatible way.
