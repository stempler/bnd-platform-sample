/**
 * Include Orient Graph database. Calls orientCore(...).
 */
def orientGraphDB(String orientDBVersion = '1.5.1') {
	orientCore(orientDBVersion)
	
	platform {
		bundle group: 'com.orientechnologies', name: 'orientdb-graphdb'
		
		bnd group: 'com.tinkerpop.gremlin', name: 'gremlin-groovy', {
			// adapt gremlin configuration
			optionalImport 'com.tinkerpop.blueprints.impls.sail'
		}
	}
}

/**
 * Orient DB core bundles and fixed versions for all OrientDB bundles.
 */
def orientCore(String orientDBVersion = '1.5.1') {
	def orientGroup = 'com.orientechnologies'
	
	repositories {
		maven {
			url 'https://oss.sonatype.org/content/groups/public/'
		}
	}
	
	configurations {
		platform {
			resolutionStrategy.eachDependency {
				if (it.requested.group == orientGroup) {
					it.useTarget(
						group: it.requested.group,
						name: it.requested.name,
						version: orientDBVersion // fixed version for all orient dependencies
					)
				}
			}
		}
	}
	
	platform {
		bnd group: orientGroup, {
			// import and export orient packages with version
			instruction 'Import-Package', "com.orientechnologies.*;version=${orientDBVersion},*"
			instruction 'Export-Package', "com.orientechnologies.*;version=${orientDBVersion},*"
		}
		
		bundle group: orientGroup, name: 'orientdb-core'
		
		/*
		 * Commons and nativeos must share a classloader.
		 */
		merge {
			bundle group: orientGroup, name: 'orient-commons'
			bundle group: orientGroup, name: 'orientdb-nativeos'
			bnd {
				version = orientDBVersion
				symbolicName = 'com.orientechnologies.common'
			}
		}
		
		bnd 'net.java.dev.jna:jna', {
			// ensure bundle is wrapped to fix bundle version
		}
	}
}