package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import repositories.PostasRepository;

public class RegisterController {

    @Autowired
    private PostasRepository postasRepository;


    /*@PostMapping
    public ResponseEntity<Postas> save(@RequestBody Postas postas){
        Postas postas2= postasRepository.save(postas);
        return new ResponseEntity<>(postas2,null, HttpStatus.OK);
    }*/
}
