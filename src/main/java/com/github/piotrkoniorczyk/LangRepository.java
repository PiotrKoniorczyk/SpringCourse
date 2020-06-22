import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class LangRepository {
    private List<Lang> language;

    LangRepository(){
        language = new ArrayList<>();
        language.add(new Lang(1, "Hello", "en"));
        language.add(new Lang(2, "Witam", "pl"));
    }

    Optional<Lang> findById(Integer id){
        return language.stream()
                .filter(l -> l.getId().equals(id))
                .findFirst();

    }

}
