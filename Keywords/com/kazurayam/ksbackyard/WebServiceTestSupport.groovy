package com.kazurayam.ksbackyard

import com.kms.katalon.core.annotation.Keyword

import groovy.json.JsonOutput

class WebServiceTestSupport {

	/**
	 * ResponseObject#getHeaderFields() has a problem:
	 * it returns a Map<String, List<String>> which contains an entry of "null:[HTTP/1.0 200]"
	 * the null value as key causes RuntimeException.
	 * see https://forum.katalon.com/discussion/10237/responseobject-getheaderfields-returns-map-with-key-of-null-value#latest
	 * 
	 * This method replaces key of null to a string 'null'
	 */
	@Keyword
	String convertHeaderFieldsToJsonString(Map<String, List<String>> headerFields) {
		Map<String, List<String>> filtered = new HashMap<String, List<String>>()
		for (String key : headerFields.keySet()) {
			List<String> value = headerFields.get(key)
			if (key != null) {
				filtered.put(key, value)
			} else {
				filtered.put('null', value)
			}
		}
		return JsonOutput.toJson(filtered)
	}
}