Logging Response Headers and Bodies of Web Service --- Materials applied
========

## What is this?

This is a [Katalon Studio](https://www.katalon.com/) project for demonstration purpose.
You can clone it out to your PC and run with your Katalon Studio.

This project was developed using Katalon Studio version 5.8.0.

This project was developed to propose a solution to a discussion in Katalon Forum:
[*How to store response in a file*](https://forum.katalon.com/discussion/10209/how-to-store-response-in-a-file).

## Problem to solve

1. I want to test a Web Service API, like [katalon-studio-samples/jira-api-tests](https://github.com/katalon-studio-samples/jira-api-tests)
2. I want to save the Response Header and Response Body into files in JSON format.
3. I want to retain files chronologically for logging purpose. I do not like overwriting files. If I execute a single Test Suite 3 times, then I want to retain 3 sets of json files.
4. I will look back retained sets of json files frequently. Therefore I want a nice GUI that enables me to retrieve stored files quickly.

## Solution

I would introduce to you [Materials], a java/groovy library that resolves output file paths in a well-structured format. The  [`com.kazurayam.materials.MaterialRepository`] provides convinient methods with which I can solve the above-mensioned problems easily.

1. [`resolveMaterialPath(String testCaseId, String fileName)`] method returs `java.nio.file.Path` object for a File to be created.
2. [`makeIndex()`] method generates the `./Materials/index.html` file.

## How to run the demo

  
