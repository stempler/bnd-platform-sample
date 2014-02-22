/**
 * Common bnd configurations, e.g. to make package imports optional.
 */
def bnd() {
	platform {
		bnd 'jdom:jdom', {
			optionalImport(
				'oracle.xml.parser',
				'oracle.xml.parser.v2',
				'org.jaxen;resolution',
				'org.jaxen.jdom'
			)
		}
		
		bnd(group: 'net.sf.json-lib', name: 'json-lib') {
			// optional imports the `manual way`
			instruction 'Import-Package',
				'nu.xom;resolution:=optional,org.apache.oro.text.regex;resolution:=optional,' +
				(properties['Import-Package']?:'*')
		}
		
		bnd group: 'org.apache.ant', name: 'ant', {
			optionalImport 'gnu.gcj', 'kaffe.util', 'org.apache.harmony.luni.util', 'weblogic'
		}
		
		bnd group: 'org.codehaus.groovy.modules.http-builder', name: 'http-builder', {
			optionalImport 'com.google.appengine.api.urlfetch', 'oauth.signpost', 'oauth.signpost.*'
		}
	}
}