import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS

import groovy.json.JsonOutput

Path tmpDir = Paths.get("./tmp")
Files.createDirectories(tmpDir)

// Send the request and get the response
ResponseObject response = WS.sendRequest(
	findTestObject('Simple examples/api-2-issue/Get issue/Get an issue by Key'))
// Verify the response
WS.verifyResponseStatusCode(response, 200)
WS.verifyElementPropertyValue(response, 'fields.project.key', 'KD')

// obtain Response HEADER fields
Map<String, List<String>> headerFields = response.getHeaderFields()

// store the HTTP Response Headers into file as plain text
Path path1 = tmpDir.resolve("headers.txt")
path1.toFile().append(headerFields.toString(), 'utf-8')

// try to convert the Map into a string in Json format
def jsonStr = JsonOutput.toJson(headerFields)
Path path2 = tmpDir.resolve("headers.json")
path2.toFile().append(JsonOutput.prettyPrint(jsonStr), 'utf-8')



