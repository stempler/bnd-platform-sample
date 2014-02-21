def orientDB(String orientDBVersion = '1.5.1') {
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
			// import orient packages with version
			instruction 'Import-Package', "com.orientechnologies.*;version=${orientDBVersion},*"
		}
		
		bundle group: orientGroup, name: 'orientdb-core', {
			bnd {
				symbolicName = 'com.orientechnologies.orient.core'
			}
		}
		
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
		
		bundle group: orientGroup, name: 'orientdb-graphdb', {
			bnd {
				symbolicName = 'com.orientechnologies.orient.graphdb'
			}
		}
		
		bundle group: orientGroup, name: 'orientdb-tools', {
			bnd {
				symbolicName = 'com.orientechnologies.orient.tools'
			}
		}
		bundle group: orientGroup, name: 'orientdb-client', {
			bnd {
				symbolicName = 'com.orientechnologies.orient.client'
			}
		}
		bundle group: orientGroup, name: 'orientdb-server', {
			bnd {
				symbolicName = 'com.orientechnologies.orient.server'
			}
		}
		bundle group: orientGroup, name: 'orientdb-enterprise', {
			bnd {
				symbolicName = 'com.orientechnologies.orient.enterprise'
			}
		}
	}
}