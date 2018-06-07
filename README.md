# screenscraper
Java app to screenscrape a Sainsbury's product page and return JSON formatted data.

#Overview

JSoup to identify links in the category page to product details pages. follows these links, 
creates a Document of the Product page for the Product constructor, which extracts the 
required properties to populate an object.  
It then uses jackson to serialize the objects into a JSON structure.

POJOs are Category and Product, which loosely represent the taxonomy of the Websphere Commerce platform.

To run the test harnesses run ScreenScraperUnitTestSuite as JUnit test.

To run the app:
 mvn compile exec:java -Dexec.args="https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html"

NOTE - the structure of the JSON output is correct, but the formatting is slightly wrong, ran out of time before rectified this.

#Technologies

junit 
jsoup
jackson
wiremock

Requires local installation of Java 8 and Maven.
It's a Maven project created through Spring Boot for ease of auto configuration and covenience of dependency management through pom.xml

Have little experience of screen scraping, after some reading into other alternatives such as HTMLUnit, JSoup seemed more intuitive as no need for Tree traversa*l  

As was developing locally, didn't use Logging library, relied on console output

#Stubs

Worked off stub HTML files, taken from the source code for the category and products pages.
Had to make minor adjustments to the category markup to give full localhost URL to overcome MalformedURLException.    


