package kodlama.io.hafta5odev2.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kodlama.io.hafta5odev2.business.abstracts.TechnologyService;
import kodlama.io.hafta5odev2.business.requests.CreateTechnologyRequest;
import kodlama.io.hafta5odev2.business.requests.UpdateTechnologyRequest;
import kodlama.io.hafta5odev2.business.responses.GetAllTechnologyResponses;
import kodlama.io.hafta5odev2.business.responses.GetByIdTechnologyResponse;
import kodlama.io.hafta5odev2.core.utilities.mappers.ModelMapperService;
import kodlama.io.hafta5odev2.dataAccess.abstracts.LanguageRepository;
import kodlama.io.hafta5odev2.dataAccess.abstracts.TechnologyRepository;
import kodlama.io.hafta5odev2.entities.concretes.Language;
import kodlama.io.hafta5odev2.entities.concretes.Technology;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TechnologyManager implements TechnologyService {
    private TechnologyRepository technologyRepository;
    private LanguageRepository languageRepository;
    private ModelMapperService modelMapperService;

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

            /*
            Technology technology=new Technology();
            technology.setTechnologyName(createTechnologyRequest.getTechnologyName());//teknoloji adını set ediyoruz.
            technology.setLanguage(language);//teknolojinin bağlı olduğu programalama diline atıyoruz.
            technologyRepository.save(technology);
            */

            Technology technology=this.modelMapperService.forRequest().map(createTechnologyRequest,Technology.class);
            
            this.technologyRepository.save(technology);
        }
        
    }

    @Override
    public void deleteTechnology(int technologyId) {
        this.technologyRepository.deleteById(technologyId);
    }
    
    @Override
    public List<GetAllTechnologyResponses> getAllTechnologies() {
        List<Technology> technologies =technologyRepository.findAll();
        
        List<GetAllTechnologyResponses> technologyResponses=technologies.stream()
        .map(technology->this.modelMapperService.forResponse()
        .map(technology, GetAllTechnologyResponses.class)).collect(Collectors.toList());
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
            Technology technology=this.modelMapperService.forRequest().map(updateTechnologyRequest, Technology.class);
            this.technologyRepository.save(technology);
        }

    }

    @Override
    public GetByIdTechnologyResponse getByIdTechnologyResponse(int technologyId) {
        Technology technology=this.technologyRepository.findById(technologyId).orElseThrow();

        GetByIdTechnologyResponse technologyResponse=this.modelMapperService.forResponse().map(technology, GetByIdTechnologyResponse.class);
        return technologyResponse;

    }
}
