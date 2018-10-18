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

I would introduce to you [Materials](https://github.com/kazurayam/Materials), a java/groovy library that resolves output file paths in a well-structured format. The  `com.kazurayam.materials.MaterialRepository` class provides convenient methods with which I can solve the above-mensioned problems easily.

1. `resolveMaterialPath(String testCaseId, String fileName)` method returns `java.nio.file.Path` object for a File to be created.
2. `makeIndex()` method generates the `./Materials/index.html` file.

## How to run the demo

1. download the zip of this project from [releases](https://github.com/katalon-studio-samples/jira-api-tests) page.
2. unzip it. You will obtain a folder named `KatalonDiscussion10209`.
3. start your Katalon studio, and open the project
4. open a Test Suite named `Test Suites/TS_Materials_applied` and run it.
5. you should run it multiple times. e.g., try 3 times.

## What the test does

This project is based on the [katalon-studio-samples/jira-api-tests](https://github.com/katalon-studio-samples/jira-api-tests) project.

The `Test Suites/TS_Materials_applied` executes [`Test Cases/Simple examples/api-2-issue/Get issue/Get an issue by Key - 1 - Materialized`](/Scripts/Simple%20examples/api-2-issue/Get%20issue/Get%20an%20issue%20by%20Key%20-%201%20-%20Materialized/Script1539828822545.groovy). The test case code looks like this:

```
// Send the request and get the response
response = WS.sendRequest(findTestObject('Simple examples/api-2-issue/Get issue/Get an issue by Key'))

// Verify the response
WS.verifyResponseStatusCode(response, 200)
// make MaterialRepository accessible
MaterialRepository mr = (MaterialRepository)GlobalVariable.MATERIAL_REPOSITORY

// store HTTP status
Path path1 = mr.resolveMaterialPath(GlobalVariable.CURRENT_TESTCASE_ID, "status.txt")
int status = response.getStatusCode()
path1.toFile().append("${status}", 'utf-8')

// store the HTTP Response Headers into file
Path path2 = mr.resolveMaterialPath(GlobalVariable.CURRENT_TESTCASE_ID, "headers.json")
Map<String, List<String>> headerFields = response.getHeaderFields()
String headersJson = CustomKeywords.'com.kazurayam.ksbackyard.WebServiceTestSupport.convertHeaderFieldsToJsonString'(headerFields)
path2.toFile().append(headersJson, 'utf-8')

// store the HTTP Response Body into file
Path path3 = mr.resolveMaterialPath(GlobalVariable.CURRENT_TESTCASE_ID, "body.json")
String body = response.getResponseBodyContent()
path3.toFile().append(body, 'utf-8')
```

This test case send a HTTP GET request to a RESTFul URL  https://katalon.atlassian.net/rest/api/2/issue/KD-1000?expand=names&fields=summary,status,issuetype,assignee,project,priority,description&= . The test case makes a bit of assertions over the response. And it writes the HTTP Response Headers and Body into files on local disk in JSON format.

## output

By running `Test Suites/TS_Materials_applied`, a new folder `<projectDir>/Materials` will be created. In there you can find a folder tree and a HTML file named `index.html`.

![Materials_index](docs/images/Materials_index.png)

You can browse the index.html. It will look like this:

![index_html](docs/images/index_html.png)

In the index you will find as many records of test suite runs as you executed. In the above screenshot, you can find 3 times of test suite run recorded.

Each test suite run contains a record of test case run, which contains links to the files created.

By clicking the links, you can view the files in modal widdow. Here is `headers.json`:

![headers_json](docs/images/headers_json.PNG)

Here is `body.json`:

![body_json](docs/images/body_json.PNG)

## Path format

The created files has a path like this:
```
${projectDir}/Materials/${testSuiteName}/${testSuiteTimestamp}/${testCaseName}/${subdirs}/${fileName}
```

A concrete path example:

```
./Materials/TS_Materials_applied/20181018_140249/Simple examples.api-2-issue.Get issue.Get an issue by Key - 1 - Materialized/header.json
```

Please note that this folder tree contains a timestamp layer `yyyyMMdd_hhmmss`. *Having a timestamp layer enables you to retain outputs as long as you like until you intensionally delete them. This chronological folder structure is best fit for logging purpose.*
