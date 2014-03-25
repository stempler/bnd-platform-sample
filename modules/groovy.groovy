def groovyAll(String groovyVersion = '2.1.5', boolean addDependency = true, String customName = null) {
	configurations {
		platform {
			// groovy
			resolutionStrategy.eachDependency {
				if (it.requested.group == 'org.codehaus.groovy') {
					// always use groovy-all instead of (groovy-*)
					it.useTarget "org.codehaus.groovy:groovy-all:${groovyVersion}"
				}
			}
		}
	}
	
	platform {
		if (customName) {
			// custom symbolic name
			bnd('org.codehaus.groovy:groovy-all') {
				symbolicName = customName
			}
		}
		
		if (addDependency) {
			bundle "org.codehaus.groovy:groovy-all:${groovyVersion}"
		}
		
		// configure how bundles having groovy as dependency are configured regarding package imports
		imports(group: 'org.codehaus.groovy') {
			versionStrategy = MINIMUM // assume that groovy is backwards compatible
		}
	}
}