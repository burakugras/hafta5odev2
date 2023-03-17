package kodlama.io.hafta5odev2.business.abstracts;

import java.util.List;

import kodlama.io.hafta5odev2.business.requests.CreateLanguageRequest;
import kodlama.io.hafta5odev2.business.requests.UpdateLanguageRequest;
import kodlama.io.hafta5odev2.business.responses.GetAllLanguageResponses;

public interface LanguageService {
    void add(CreateLanguageRequest createLanguageRequest)throws Exception;
    void deleteLanguage(int languageId);
    void updateLanguage(UpdateLanguageRequest updateLanguageRequest)throws Exception;
    List<GetAllLanguageResponses> getAllLanguages();
}
