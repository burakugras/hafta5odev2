package kodlama.io.hafta5odev2.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

import kodlama.io.hafta5odev2.business.abstracts.TechnologyService;
import kodlama.io.hafta5odev2.business.requests.CreateTechnologyRequest;
import kodlama.io.hafta5odev2.business.requests.UpdateTechnologyRequest;
import kodlama.io.hafta5odev2.business.responses.GetAllTechnologyResponses;
import kodlama.io.hafta5odev2.dataAccess.abstracts.LanguageRepository;
import kodlama.io.hafta5odev2.dataAccess.abstracts.TechnologyRepository;
import kodlama.io.hafta5odev2.entities.concretes.Language;
import kodlama.io.hafta5odev2.entities.concretes.Technology;

@Service
public class TechnologyManager implements TechnologyService {
    private TechnologyRepository technologyRepository;
    private LanguageRepository languageRepository;

    public TechnologyManager(TechnologyRepository technologyRepository,LanguageRepository languageRepository) {
        this.technologyRepository = technologyRepository;
        this.languageRepository=languageRepository;
    }

    @Override
    public void add(CreateTechnologyRequest createTechnologyRequest)throws Exception {
        Language language=languageRepository.findById(createTechnologyRequest.getLanguageId()).orElseThrow(()->new Exception("Programlama dili id'si bulunmuyor"));//burada teknolojinin bağlı olduğu programlama dilini id ile getirip language'ye atıyoruz.
        
        if(createTechnologyRequest.getTechnologyName().isEmpty()){
            throw new Exception("Teknoloji ismi boş bırakılamaz.");
        }
        else if(technologyRepository.existsByTechnologyName(createTechnologyRequest.getTechnologyName())){
            throw new Exception("Bu isimde bir teknoloji zaten mevcut");
        }
        else{
            Technology technology=new Technology();
            technology.setTechnologyName(createTechnologyRequest.getTechnologyName());//teknoloji adını set ediyoruz.
            technology.setLanguage(language);//teknolojinin bağlı olduğu programalama diline atıyoruz.
            technologyRepository.save(technology);
        }
        
    }

    @Override
    public void deleteTechnology(int technologyId) {
        technologyRepository.deleteById(technologyId);
    }
    
    @Override
    public List<GetAllTechnologyResponses> getAllTechnologies() {
        List<Technology> technologies =technologyRepository.findAll();
        List<GetAllTechnologyResponses> technologyResponses=new ArrayList<>();


        for(Technology technologyElement:technologies){
            GetAllTechnologyResponses responseItem=new GetAllTechnologyResponses();

            responseItem.setTechnologyId(technologyElement.getTechnologyId());
            responseItem.setTechnologyName(technologyElement.getTechnologyName());
            responseItem.setTechnologyName(responseItem.getTechnologyName());
            
            technologyResponses.add(responseItem);
        }
;
        return technologyResponses;
    }

    @Override
    public void updateTechnology(UpdateTechnologyRequest updateTechnologyRequest) throws Exception {
        if(updateTechnologyRequest.getUpdatedTechnologyName().isEmpty()){
            throw new Exception("Teknoloji ismi boş bırakılamaz.");
        }
        else if(technologyRepository.existsByTechnologyName(updateTechnologyRequest.getUpdatedTechnologyName())){
            throw new Exception("Bu isimde bir teknoloji zaten mevcut");
        }
        else{
            Technology technology=technologyRepository.findById(updateTechnologyRequest.getTechnologyId()).orElseThrow(()->new Exception("Programlama dili id'si bulunmuyor"));
            technology.setTechnologyName(updateTechnologyRequest.getUpdatedTechnologyName());
            technologyRepository.save(technology);
        }

    }
}
