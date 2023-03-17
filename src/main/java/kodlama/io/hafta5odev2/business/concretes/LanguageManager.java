package kodlama.io.hafta5odev2.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import kodlama.io.hafta5odev2.business.abstracts.LanguageService;
import kodlama.io.hafta5odev2.business.requests.CreateLanguageRequest;
import kodlama.io.hafta5odev2.business.requests.UpdateLanguageRequest;
import kodlama.io.hafta5odev2.business.responses.GetAllLanguageResponses;
import kodlama.io.hafta5odev2.dataAccess.abstracts.LanguageRepository;
import kodlama.io.hafta5odev2.entities.concretes.Language;

@Service
public class LanguageManager implements LanguageService{
    private LanguageRepository languageRepository;

    public LanguageManager(LanguageRepository languageRepository){
        this.languageRepository=languageRepository;
    }

    @Override
    public void add(CreateLanguageRequest createLanguageRequest)throws Exception {
        if(createLanguageRequest.getLanguageName().isEmpty()){
            throw new Exception("Programlama dili boş bırakılamaz.");
        }
        else if(languageRepository.existsByLanguageName(createLanguageRequest.getLanguageName())){
            throw new Exception("Bu isimde bir programlama dili zaten bulunuyur.");
            //burada getAllLanguages metodunda yaptığımız gibi bütün isimleri başka bir ArrayList'e alıp oradan karşılaştırma yapabiliriz. 
        }
        else{
            Language language=new Language();
            language.setLanguageName(createLanguageRequest.getLanguageName());
            languageRepository.save(language);
        }
    }

    @Override
    public void deleteLanguage(int languageId) {
        languageRepository.deleteById(languageId);
    }
    
    @Override
    public List<GetAllLanguageResponses> getAllLanguages() {
        List<Language> languages=languageRepository.findAll();
        List<GetAllLanguageResponses> languageResponses=new ArrayList<>();        

        for(Language languageElement : languages){
            GetAllLanguageResponses responseItem=new GetAllLanguageResponses();
            responseItem.setLanguageId(languageElement.getLanguageId());
            responseItem.setLanguageName(languageElement.getLanguageName());

            languageResponses.add(responseItem);

        }
        
        return languageResponses;
    }

    @Override
    public void updateLanguage(UpdateLanguageRequest updateLanguageRequest)throws Exception{
        if(updateLanguageRequest.getUpdatedLanguageName().isEmpty()){
            throw new Exception("Programlama dili boş bırakılamaz");
        }else if(languageRepository.existsByLanguageName(updateLanguageRequest.getUpdatedLanguageName())){
            throw new Exception("Bu isimde bir programlama dili zaten bulunuyor.");
        }
        else{
            Language language=languageRepository.findById(updateLanguageRequest.getLanguageId()).orElseThrow(()-> new Exception("Id bulunmuyor"));
            language.setLanguageName(updateLanguageRequest.getUpdatedLanguageName());
            languageRepository.save(language);
        }
    }
}
