import org.gradle.api.artifacts.DependencyResolveDetails

/**
 * Adds slf4j and logback to the platform
 */
def slf4jAndLogback(String slf4jVersion = '1.7.5', String logbackVersion = '1.0.13') {
	configurations {
		platform {
			// resolution configuration for logging bridges (... over slf4j)
			
			resolutionStrategy.eachDependency { DependencyResolveDetails details ->
				if (details.requested.name == 'commons-logging') {
					// prefer 'jcl-over-slf4j' over 'commons-logging'
					details.useTarget "org.slf4j:jcl-over-slf4j:${slf4jVersion}"
				}
			}
			
			resolutionStrategy.eachDependency { DependencyResolveDetails details ->
				if (details.requested.name == 'log4j' && details.requested.group == 'log4j') {
					// prefer 'log4j-over-slf4j' over 'log4j'
					details.useTarget "org.slf4j:log4j-over-slf4j:${slf4jVersion}"
				}
			}
		}
	}
	
	platform {
		// slf4j
		bundle "org.slf4j:slf4j-api:${slf4jVersion}"
		bundle "org.slf4j:slf4j-ext:${slf4jVersion}"
		
		// jul adapter
		bundle "org.slf4j:jul-to-slf4j:${slf4jVersion}"
		
		// logback
		bundle "ch.qos.logback:logback-classic:${logbackVersion}"
		bundle "ch.qos.logback:logback-core:${logbackVersion}"
	}
}