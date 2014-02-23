platform {
	// add custom groovy-sandbox jar as bundle to the platform
	// `thisDir` is the location of this script, provided by the include plugin
	bundle new File(thisDir, 'groovy-sandbox-1.6-SNAPSHOT.jar'), {
		bnd {
			version = '1.6.0.hale'
			symbolicName = 'org.kohsuke.groovy.sandbox'
			bundleName = 'Groovy Sandbox (adapted)'
		}
	}
	
	// depends on groovy
	bundle 'org.codehaus.groovy:groovy:1.8.5'
} 