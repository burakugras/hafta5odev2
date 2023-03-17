package kodlama.io.hafta5odev2.webApi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.hafta5odev2.business.abstracts.LanguageService;
import kodlama.io.hafta5odev2.business.requests.CreateLanguageRequest;
import kodlama.io.hafta5odev2.business.requests.UpdateLanguageRequest;
import kodlama.io.hafta5odev2.business.responses.GetAllLanguageResponses;

@RestController
@RequestMapping("/api/languages")
public class LanguagesController {
    private LanguageService languageService;

    public LanguagesController(LanguageService languageService) {
        this.languageService = languageService;
    }
    
    @PostMapping("/add")
    public void add(CreateLanguageRequest createLanguageRequest) throws Exception{
        languageService.add(createLanguageRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(int languageId){
        languageService.deleteLanguage(languageId);
    }

    @PutMapping("/update")
    public void update(UpdateLanguageRequest updateLanguageRequest) throws Exception{
        languageService.updateLanguage(updateLanguageRequest);
    }

    @GetMapping("/getall")
    public List<GetAllLanguageResponses> getAll(){
        return languageService.getAllLanguages();
    }
}
