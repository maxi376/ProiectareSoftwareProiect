package services;


import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.AdministratorRepository;
import repositories.CoordonatorRepository;
import repositories.PostasRepository;


@Service
public class LoginService {

    @Autowired
    private AdministratorRepository administratorRepository;
    @Autowired
    private CoordonatorRepository coordonatorRepository;
    @Autowired
    private PostasRepository postasRepository;

    public Administrator loginAdministrator (String numeUtilizator, String parola) {
        return administratorRepository.findAdministratorByNumeUtilizatorAndParola(numeUtilizator,parola);
    }

    public CoordonatorActivitate loginCoordonatorActivitate (String numeUtilizator, String parola) {
        return coordonatorRepository.findCoordonatorActivitateByNumeUtilizatorAndParola(numeUtilizator,parola);
    }

    public Postas loginPostas (String numeUtilizator, String parola) {
        return postasRepository.findPostasByNumeUtilizatorAndParola(numeUtilizator,parola);
    }
}
