import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.sainsburys.screenscraper.pojo.CategoryTest;
import com.sainsburys.screenscraper.pojo.ProductTest;
import com.sainsburys.screenscraper.utils.ProductFromURLScraperTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	
	CategoryTest.class,
	ProductTest.class,
	ProductFromURLScraperTest.class
})

public class ScreenScraperUnitTestSuite {

}
