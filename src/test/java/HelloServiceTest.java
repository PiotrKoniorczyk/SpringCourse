import junit.framework.TestCase;
import org.junit.Test;

import java.util.Optional;

public class HelloServiceTest extends TestCase {
    private final static String WELCOME = "Hello";

    @Test
    public void testPrepareGreeting_NullName_ReturnsGreetingWithFallback() {
        //given
        var mockRepository = alwaysReturningLangRepository();
        var SUT = new HelloService(mockRepository);
        // when
        var result = SUT.prepareGreeting(null, "-1");
        //then
        assertEquals(WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }


    @Test
    public void testPrepareGreeting_Name_ReturnsGreetingWithName() {


        //given
        var SUT = new HelloService();
        var name = "Test";
        //when
        var result = SUT.prepareGreeting(name, "-1");
        //then
        assertEquals(WELCOME + " " + name + "!", result);
    }

    private LangRepository alwaysReturningLangRepository() {
        return new LangRepository() {
            @Override
            Optional<Lang> findById(Long id) {
                return Optional.of(new Lang(null, WELCOME, null));
            }
        };
    }

}