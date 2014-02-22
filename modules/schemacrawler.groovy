def schemacrawlerVersion = '8.16'

platform {
	bundle "net.sourceforge.schemacrawler:schemacrawler:$schemacrawlerVersion", {
		bnd {
			// optional imports
			optionalImport 'freemarker.*', 'org.springframework.context', 'org.springframework.context.*',
				'org.apache.velocity', 'org.apache.velocity.*' 
			// export schemacrawler stuff only
			instruction 'Export-Package', """
					schemacrawler;version=$version,
					schemacrawler.*;version=$version"""
			instruction 'Private-Package', '*' // other packages private
		}
	}
	bundle 'com.thoughtworks.xstream:xstream:1.4.5', { // dependency of schemacrawler not listed in pom!
		bnd {
			optionalImport 'sun.misc'
		}
	}
}
