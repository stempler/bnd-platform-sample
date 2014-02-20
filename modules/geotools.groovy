def defaultModules = [
	'gt-api',
	'gt-coverage',
	'gt-cql',
	'gt-data',
	'gt-epsg-hsql',
	'gt-feature-pregeneralized',
	'gt-geojson',
	'gt-main',
	'gt-metadata',
	'gt-process',
	'gt-property',
	'gt-referencing',
	'gt-referencing3D',
	'gt-render',
	'gt-shapefile',
	'gt-validation',
	'gt-wfs',
	'gt-wms',
	'gt-xml',
	'gt-xsd-core',
	'gt-xsd-fes',
	'gt-xsd-filter',
	'gt-xsd-gml2',
	'gt-xsd-gml3',
	'gt-xsd-kml',
	'gt-xsd-ows',
	'gt-xsd-sld',
	'gt-xsd-wcs',
	'gt-xsd-wfs',
	'gt-xsd-wms',
	'gt-xsd-wps'
]

/**
 * Adds slf4j and logback to the platform
 */
def geotools(String geotoolsVersion = '10.4', String bundleVersion = '10.4.0.combined',
	def modules = defaultModules) {
	
	repositories {
		maven {
			url 'http://download.osgeo.org/webdav/geotools/'
		}
		maven {
			url 'http://repo.opengeo.org'
		}
	}
	
	platform {
		// opengis
		bundle "org.geotools:gt-opengis:${geotoolsVersion}" {
			bnd {
				symbolicName = 'org.opengis' //XXX not yet supported
			}
		}
		
		// geotools
		merge { //XXX not yet supported
			modules.each {
				bundle "org.geotools:${it}:${geotoolsVersion}"
			}
			
			bnd {
				symbolicName = 'org.geotools'
				bundleName = 'Geotools'
				version = bundleVersion
			}
		}
	}
}