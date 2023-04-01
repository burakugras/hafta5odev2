package kodlama.io.hafta5odev2.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kodlama.io.hafta5odev2.business.abstracts.LanguageService;
import kodlama.io.hafta5odev2.business.requests.CreateLanguageRequest;
import kodlama.io.hafta5odev2.business.requests.UpdateLanguageRequest;
import kodlama.io.hafta5odev2.business.responses.GetAllLanguageResponses;
import kodlama.io.hafta5odev2.business.responses.GetByIdLanguageResponse;
import kodlama.io.hafta5odev2.core.utilities.mappers.ModelMapperService;
import kodlama.io.hafta5odev2.dataAccess.abstracts.LanguageRepository;
import kodlama.io.hafta5odev2.entities.concretes.Language;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LanguageManager implements LanguageService{
    private LanguageRepository languageRepository;
    private ModelMapperService modelMapperService;    

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
            Language language=this.modelMapperService.forRequest().map(createLanguageRequest, Language.class);
            this.languageRepository.save(language);
        }
    }

    @Override
    public void deleteLanguage(int languageId) {
        languageRepository.deleteById(languageId);
    }
    
    @Override
    public List<GetAllLanguageResponses> getAllLanguages() {
        List<Language> languages=languageRepository.findAll();
        
        List<GetAllLanguageResponses> languageResponses=languages.stream()
        .map(language->this.modelMapperService.forResponse()
        .map(languages, GetAllLanguageResponses.class)).collect(Collectors.toList());
        
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
            Language language=this.modelMapperService.forRequest().map(updateLanguageRequest, Language.class);
            this.languageRepository.save(language);
        }
    }

    @Override
    public GetByIdLanguageResponse getByIdLanguageResponse(int languageId) {
        Language language=this.languageRepository.findById(languageId).orElseThrow();

        GetByIdLanguageResponse response=this.modelMapperService.forResponse().map(language, GetByIdLanguageResponse.class);

        return response;
    }
}
