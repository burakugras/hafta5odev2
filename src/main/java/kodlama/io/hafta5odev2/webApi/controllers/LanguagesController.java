package kodlama.io.hafta5odev2.webApi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.hafta5odev2.business.abstracts.LanguageService;
import kodlama.io.hafta5odev2.business.requests.CreateLanguageRequest;
import kodlama.io.hafta5odev2.business.requests.UpdateLanguageRequest;
import kodlama.io.hafta5odev2.business.responses.GetAllLanguageResponses;
import kodlama.io.hafta5odev2.business.responses.GetByIdLanguageResponse;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/languages")
@AllArgsConstructor
public class LanguagesController {
    private LanguageService languageService;
  
    
    @PostMapping()
    @ResponseStatus(code=HttpStatus.CREATED)
    public void add(@RequestBody CreateLanguageRequest createLanguageRequest) throws Exception{
        languageService.add(createLanguageRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int languageId){
        languageService.deleteLanguage(languageId);
    }

    @PutMapping
    public void update(@RequestBody UpdateLanguageRequest updateLanguageRequest) throws Exception{
        languageService.updateLanguage(updateLanguageRequest);
    }

    @GetMapping()
    public List<GetAllLanguageResponses> getAll(){
        return languageService.getAllLanguages();
    }

    @GetMapping("/{id}")
    public GetByIdLanguageResponse getByIdLanguageResponse(@PathVariable int id){
        return languageService.getByIdLanguageResponse(id);
    }
}
