import junit.framework.TestCase;
import org.junit.Test;

import java.util.Optional;

public class HelloServiceTest extends TestCase {
    private final static String WELCOME = "Hello";
    private final static String FALLBACK_ID_WELCOME = "Hola";

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
        var mockRepository = alwaysReturningLangRepository();
        var SUT = new HelloService(mockRepository);
        var name = "Test";
        //when
        var result = SUT.prepareGreeting(name, "-1");
        //then
        assertEquals(WELCOME + " " + name + "!", result);
    }

    @Test
    public void testPrepareGreeting_nonExistingLang_ReturnsGreetingWithFallbackIDLang() {
        //given
        var mockRepository = new LangRepository() {
            @Override
            Optional<Lang> findById(Integer id) {
                return Optional.empty();
            }
        };
        var SUT = new HelloService(mockRepository);
        //when
        var result = SUT.prepareGreeting(null, "-1");
        //then
        assertEquals(HelloService.FALLBACK_LANG.getWelcomeMsg() + " " + HelloService.FALLBACK_NAME + "!", result);
    }

    @Test
    public void testPrepareGreeting_NullLang_ReturnsGreetingWithFallbackIDLang() {
        //given
        var mockRepository = FallbackLangIdRepository();
        var SUT = new HelloService(mockRepository);
        //when
        var result = SUT.prepareGreeting(null, null);
        //then
        assertEquals(FALLBACK_ID_WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }


    @Test
    public void testPrepareGreeting_texyLang_ReturnsGreetingWithFallbackIDLang() {
        //given
        var mockRepository = FallbackLangIdRepository();
        var SUT = new HelloService(mockRepository);
        //when
        var result = SUT.prepareGreeting(null, "abc");
        //then
        assertEquals(FALLBACK_ID_WELCOME + " " + HelloService.FALLBACK_NAME + "!", result);
    }


    private LangRepository FallbackLangIdRepository() {
        return new LangRepository() {
            @Override
            Optional<Lang> findById(Integer id) {
                if (id.equals(HelloService.FALLBACK_LANG.getId())) {
                    return Optional.of(new Lang(null, FALLBACK_ID_WELCOME, null));
                }
                return Optional.empty();
            }
        };
    }

    private LangRepository alwaysReturningLangRepository() {
        return new LangRepository() {
            @Override
            Optional<Lang> findById(Integer id) {
                return Optional.of(new Lang(null, WELCOME, null));
            }
        };
    }



    public HibernateUtil getHibernateUtil() {
        return hibernateUtil;
    }
}