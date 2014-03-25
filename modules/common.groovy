/**
 * Common bnd configurations, e.g. to make package imports optional.
 */
platform {
	// extend/override the default bnd configuration
	// the default bnd configuration does not apply to Jars that are already bundles
	// (to protect the existing imports and exports)
	bnd {
		// unit test only dependencies should be optional
		optionalImport 'junit.framework', 'org.junit'
	}
	
	// individual bundle configurations
	
	bnd(group: 'net.sf.json-lib', name: 'json-lib') {
		// optional imports the `manual way`
		instruction 'Import-Package',
			'nu.xom;resolution:=optional,org.apache.oro.text.regex;resolution:=optional,' +
			(properties['Import-Package']?:'*')
	}
	
	bnd group: 'commons-configuration', name: 'commons-configuration', {
		optionalImport 'org.apache.commons.jxpath', 'org.apache.commons.jxpath.*'
	}
}