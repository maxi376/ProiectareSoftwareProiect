package repositories;

import model.Administrator;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AdministratorRepository extends BaseRepository<Administrator>{
    Administrator save(Administrator administrator);

    //Administrator saveOrUpdate(Administrator administrator);

    Administrator findAdministratorByNumeUtilizatorAndParola(String numeUtilizator, String parola) ;

    List<Administrator> findAll() ;

    void delete(Integer id);

    Administrator removeAdministratorByNumeUtilizatorAndParola (String numeUtilizator, String parola) ;

    //ArrayList<Administrator> getAdministratorByNumeUtilizatorFalse;
}
