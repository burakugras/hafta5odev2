package kodlama.io.hafta5odev2.business.abstracts;

import java.util.List;

import kodlama.io.hafta5odev2.business.requests.CreateTechnologyRequest;
import kodlama.io.hafta5odev2.business.requests.UpdateTechnologyRequest;
import kodlama.io.hafta5odev2.business.responses.GetAllTechnologyResponses;

public interface TechnologyService {
    void add(CreateTechnologyRequest createTechnologyRequest)throws Exception;
    void deleteTechnology(int technologyId);
    void updateTechnology(UpdateTechnologyRequest updateTechnologyRequest)throws Exception;
    List<GetAllTechnologyResponses> getAllTechnologies(); 
}
