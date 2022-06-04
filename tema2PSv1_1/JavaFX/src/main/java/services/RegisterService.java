package services;

import model.Administrator;
import model.Angajat;
import model.CoordonatorActivitate;
import model.Postas;

public class RegisterService {



    public Angajat registerUser (String numeUtilizator, String parola, int i){
        Angajat angajat;
        if (i==1) {
            angajat = new Administrator (numeUtilizator, parola);
        }else if (i==2){
            angajat = new CoordonatorActivitate (numeUtilizator, parola);
        }else{//i==3
            angajat = new Postas (numeUtilizator, parola);
        }
        //Main.addAngajat (angajat);
        return angajat;
    }

    //angajat->persistenta cu add din
}
