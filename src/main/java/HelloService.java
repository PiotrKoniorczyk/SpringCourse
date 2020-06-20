import java.util.Optional;

public class HelloService {

    static final String FALLBACK_NAME = "world";
    static final Lang FALLBACK_LANG = new Lang(1L, "Hello", "en");

    private LangRepository repository;

    HelloService() {
        this(new LangRepository());
    }

    HelloService(LangRepository repository) {
        this.repository = repository;
    }

    String prepareGreeting(String name, String lang) {
        var langID = Optional.ofNullable(lang).map(Long::valueOf).orElse(FALLBACK_LANG.getId());
        var welcomeMessage = repository.findById(langID).orElse(FALLBACK_LANG).getWelcomeMsg();
        var nameToWelcome = Optional.ofNullable(name).orElse(FALLBACK_NAME);
        return welcomeMessage + " " + nameToWelcome + "!";
    }
}
