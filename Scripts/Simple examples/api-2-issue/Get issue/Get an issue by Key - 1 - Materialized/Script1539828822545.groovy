import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.nio.file.Path

import com.kazurayam.materials.MaterialRepository
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonOutput
import internal.GlobalVariable as GlobalVariable

// Send the request and get the response
response = WS.sendRequest(findTestObject('Simple examples/api-2-issue/Get issue/Get an issue by Key'))

// Verify the response
WS.verifyResponseStatusCode(response, 200)

WS.verifyElementPropertyValue(response, 'fields.project.key', 'KD')

WS.verifyElementPropertyValue(response, 'fields.issuetype.name', 'Task')

WS.verifyElementPropertyValue(response, 'fields.priority.name', 'Medium')

WS.verifyElementPropertyValue(response, 'fields.summary', 'REST - Create new RESTful test using Katalon Studio')

WS.verifyElementPropertyValue(response, 'fields.description', 'As a Katalon user, I want to create a new RESTful test, so that I can ensure that my APIs work correctly.\r\n\r\nAC1 - Ability to create RESTful test using single end-point\r\nAC2 - Ability to import RESTful end-points from Swagger')



// The following lines are inserted by kazurayam
// make MaterialRepository accessible
MaterialRepository mr = (MaterialRepository)GlobalVariable.MATERIAL_REPOSITORY

// store the HTTP Response Headers into file
Path path1 = mr.resolveMaterialPath(GlobalVariable.CURRENT_TESTCASE_ID, "headers.txt")
String headers = "Hello: World"
path1.toFile().append(headers, 'utf-8')

// store the HTTP Response Body into file
Path path2 = mr.resolveMaterialPath(GlobalVariable.CURRENT_TESTCASE_ID, "body.json")
def json = JsonOutput.toJson([name: 'John Doe', age: 42])
def jsonPP = JsonOutput.prettyPrint(json)
path2.toFile().append(jsonPP, 'utf-8')
