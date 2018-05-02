# Cognitive-Measures

[![Travis Build Status](https://travis-ci.org/dutta14/Cognitive-Measures.svg?branch=master)](https://travis-ci.org/dutta14/Cognitive-Measures/)
[![codebeat badge](https://codebeat.co/badges/8aeb3b3b-b4b8-40b9-a64d-6475f46973c9)](https://codebeat.co/projects/github-com-dutta14-cognitive-measures-master)

Android application for Cognitive measures - a part of Project Talent, American Institutes for Research.

## Building the application
To build the application locally on your machine:

Windows: `gradle build`

Mac: `./gradlew build`

## Uploading the application

If you are an authorized developer, you will have access to Dropbox where you can upload your apk.

## Documentation
Code documentation are available [here](https://dutta14.github.io/Cognitive-Measures/docs/).

## Useful tools

### Updating build tools
As of when this is written, Cognitive measures using build-tools 27.0.3. If you are submitting with newer build tools, please update `.travis.yml` to reflect the same.

### Updating documentation
If you add a new API to a class, please generate updated Javadoc and commit that as well.

Steps to update Javadoc: `Android Studio > Tools > Generate JavaDoc...`
