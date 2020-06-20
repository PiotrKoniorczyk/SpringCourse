import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class LangRepository {
    private List<Lang> language;

    LangRepository(){
        language = new ArrayList<>();
        language.add(new Lang(1L, "Hello", "en"));
        language.add(new Lang(2L, "Witam", "pl"));
    }

    Optional<Lang> findById(Long id){
        return language.stream()
                .filter(l -> l.getId().equals(id))
                .findFirst();

    }

}
