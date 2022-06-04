package controllers;

import com.sun.security.ntlm.Client;
import dataAcces.DTOAdministrator;
import dataAcces.DTOAngajat;
import dataAcces.DTOCoordonatorActivitate;
import dataAcces.DTOPostas;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.springframework.http.HttpEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static utils.Utils.buildStage;


public class LoginViewController {
    private Integer functia=1;

    public Button butonAutentificare;
    public PasswordField parolaTextField;
    public TextField textFieldNumeUtilizator;
    public Button butonInregistrare;
    public MenuButton menuButton;

    // create menuitems
    public MenuItem m1 = new MenuItem("Administrator");
    public MenuItem m2 = new MenuItem("CoordonatorActivitate");
    public MenuItem m3 = new MenuItem("Postas");

    @FXML
    public void initialize() {
        VBox root = new VBox();
        menuButton.setText("Functia");

        // add menu items to menu
        menuButton.getItems().add(m1);
        menuButton.getItems().add(m2);
        menuButton.getItems().add(m3);

        menuButton = new MenuButton("Alege functia: ", null, m1, m2, m3);
        Label functie = new Label("Functie: neselectata");

        m1.setOnAction (e->{ functia=1; functie.setText("Functie: Administrator");});
        m2.setOnAction (e->{ functia=2; functie.setText("Functie: CoordonatorActivitate");});
        m3.setOnAction (e->{ functia=3; functie.setText("Functie: Postas"); });
    }

    public void onClickButonAutentificare(ActionEvent actionEvent) throws Exception {
        String parola=parolaTextField.getText();
        String numeUtilizator=textFieldNumeUtilizator.getText();
        DTOAdministrator dtoAdministrator = new DTOAdministrator(numeUtilizator,parola);
        DTOCoordonatorActivitate dtoCoordonatorActivitate = new DTOCoordonatorActivitate(numeUtilizator,parola);
        DTOPostas dtoPostas = new DTOPostas(numeUtilizator,parola);

        RestTemplate restTemplate=new RestTemplate();
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(converters);

        DTOAngajat dto;
        if(functia==1) {
            menuButton.setText("administrator");
            HttpEntity<DTOAdministrator> dtoAdministratorHttpEntity=new HttpEntity<DTOAdministrator>(dtoAdministrator);
            dto = restTemplate.postForObject("http://localhost:8080/login/administrator",dtoAdministratorHttpEntity,DTOAdministrator.class);
            if(dto!=null)
                buildStage("administrator");
        }else if(functia==2) {
            HttpEntity<DTOCoordonatorActivitate> dtoCoordonatorActivitateHttpEntity=new HttpEntity<DTOCoordonatorActivitate>(dtoCoordonatorActivitate);
            dto = restTemplate.postForObject("http://localhost:8080/login/coordonatoractivitate",dtoCoordonatorActivitateHttpEntity,DTOCoordonatorActivitate.class);
            if(dto!=null)
                buildStage("coordonatorActivitate");
        }else {
            HttpEntity<DTOPostas> dtoPostasHttpEntity = new HttpEntity<DTOPostas>(dtoPostas);
            dto = restTemplate.postForObject("http://localhost:8080/login/postas", dtoPostasHttpEntity, DTOPostas.class);
            if(dto!=null)
                buildStage("postas");
        }
    }

    public void onClickButonInregistrare(ActionEvent actionEvent) throws  Exception{
        buildStage("register");
    }

    public void onClickMenuButton(ActionEvent actionEvent) {
    }
}
