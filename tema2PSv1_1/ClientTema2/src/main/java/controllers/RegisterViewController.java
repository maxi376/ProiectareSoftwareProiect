package controllers;

import dataAcces.DTOAdministrator;
import dataAcces.DTOAngajat;
import dataAcces.DTOCoordonatorActivitate;
import dataAcces.DTOPostas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static utils.Utils.buildStage;

public class RegisterViewController {
    public Button butonInregistrareUtilizator;
    public PasswordField parolaTextField;
    public TextField numeUtilizatorTextField;
    public MenuButton menuButton;
    public MenuItem item1;
    public MenuItem item2;
    public MenuItem item3;
    public MenuItem item4;
    public static int stat = 3;
    public TextArea textArea;

    // create menuitems
    public MenuItem m1 = new MenuItem("Administrator");
    public MenuItem m2 = new MenuItem("CoordonatorActivitate");
    public MenuItem m3 = new MenuItem("Postas");
    public MenuItem m4;
    private Integer functia = 1;

    @FXML
    public void initialize(){
        VBox root = new VBox();
        menuButton.setText("Functia");

        // add menu items to menu
        menuButton.getItems().add(m1);
        menuButton.getItems().add(m2);
        menuButton.getItems().add(m3);

        menuButton = new MenuButton("Alege functia: ", null, m1, m2, m3);
        Label functie = new Label("Functie: neselectata");

        m1.setOnAction (e->{ functia=1; functie.setText("Functie: Administrator");
            System.out.println(Integer.toString(functia));});
        m2.setOnAction (e->{ functia=2; functie.setText("Functie: CoordonatorActivitate");
            System.out.println(Integer.toString(functia));});
        m3.setOnAction (e->{ functia=3; functie.setText("Functie: Postas");
            System.out.println(Integer.toString(functia));});

        root.getChildren().addAll(menuButton, functie);
        System.out.println(functie);;
    }

    public void onClickButonInregistrareUtilizator(ActionEvent actionEvent) throws Exception {
        String parola=parolaTextField.getText();
        String numeUtilizator=numeUtilizatorTextField.getText();
        DTOAdministrator dtoAdministrator = new DTOAdministrator(numeUtilizator,parola);
        DTOCoordonatorActivitate dtoCoordonatorActivitate = new DTOCoordonatorActivitate(numeUtilizator,parola);
        DTOPostas dtoPostas = new DTOPostas(numeUtilizator,parola);

        RestTemplate restTemplate=new RestTemplate();
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(converters);

        DTOAngajat dto;
        if(functia==1) {
            HttpEntity<DTOAdministrator> dtoAdministratorHttpEntity=new HttpEntity<DTOAdministrator>(dtoAdministrator);
            //ParameterizedTypeReference<DTOAdministrator> typeRef = new ParameterizedTypeReference<DTOAdministrator>() {};
            dto = restTemplate.postForObject("http://localhost:8080/administrator",dtoAdministratorHttpEntity,DTOAdministrator.class);
            if(dto!=null)
                buildStage("administrator");
        }else if(functia==2) {
            HttpEntity<DTOCoordonatorActivitate> dtoCoordonatorActivitateHttpEntity=new HttpEntity<DTOCoordonatorActivitate>(dtoCoordonatorActivitate);
            //ParameterizedTypeReference<DTOCoordonatorActivitate> typeRef = new ParameterizedTypeReference<DTOCoordonatorActivitate>() {};
            restTemplate.put("http://localhost:8080/coordonatoractivitate",dtoCoordonatorActivitateHttpEntity,DTOCoordonatorActivitate.class);

        }else {
            HttpEntity<DTOPostas> dtoPostasHttpEntity = new HttpEntity<DTOPostas>(dtoPostas);
            //ParameterizedTypeReference<DTOPostas> typeRef = new ParameterizedTypeReference<DTOPostas>() {};
            restTemplate.put("http://localhost:8080/postas", dtoPostasHttpEntity, DTOPostas.class);

        }
    }
}
