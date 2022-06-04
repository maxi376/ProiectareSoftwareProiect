package controller;

import model.Administrator;
import model.CoordonatorActivitate;
import model.Postas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.LoginService;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/administrator")
    public ResponseEntity<Administrator> loginAdministrator(@RequestBody Administrator administrator ){
        return new ResponseEntity<>(loginService.loginAdministrator(administrator.getNumeUtilizator(),administrator.getParola()),null, HttpStatus.OK);
    }

    @PostMapping(value = "/coordonatoractivitate")
    public ResponseEntity<CoordonatorActivitate> loginCoordonatorActivitate(@RequestBody CoordonatorActivitate coordonatorActivitate ){
        return new ResponseEntity<>(loginService.loginCoordonatorActivitate(coordonatorActivitate.getNumeUtilizator(),coordonatorActivitate.getParola()),null, HttpStatus.OK);
    }

    @PostMapping(value = "/postas")
    public ResponseEntity<Postas> loginPostas(@RequestBody Postas postas ){
        return new ResponseEntity<>(loginService.loginPostas(postas.getNumeUtilizator(),postas.getParola()),null, HttpStatus.OK);
    }
}
