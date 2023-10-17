import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SuiteDisplayName("Integration Test Suite for Register, ShoppingCart and Assortment")
@SelectClasses({RegisterTest.class, ShoppingCartTest.class, AssortmentTest.class})
public class IntegrationSuiteTests {
}
