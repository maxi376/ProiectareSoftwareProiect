package controllers;

import dataAcces.DTOColet;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class PostasViewController {

    public Button butonVizualizareTraseu;
    public Button butonCautareColet;
    public Button butonVizualizareLista;
    public TextField textDestinatie;
    public TextField textGreutate;
    public TextField textCoordonataX;
    public TextField textCoordonataY;
    public TextField textNumePostas;
    public ListView listViewColete;
    public ListView listViewTraseu;
    public TextField textFieldSearchColet;
    public TextField textFieldSearchColet2;
    public TextField textFieldSearchColet3;

    private List<DTOColet> listColete=new ArrayList<DTOColet>();
    private List<DTOColet> listColeteTraseu=new ArrayList<DTOColet>();

    @FXML
    public void initialize() {
        listColete = getColete();
        listViewColete.setItems(FXCollections.observableArrayList(listColete));

        listColeteTraseu = getColete();
        listViewTraseu.setItems(FXCollections.observableArrayList(listColeteTraseu));

        textFieldSearchColet.setPromptText("search");
        textFieldSearchColet.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.equals("")){
                    listViewColete.setItems(FXCollections.observableArrayList(listColete));
                }else {
                    RestTemplate restTemplate = new RestTemplate();
                    List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
                    converters.add(new MappingJackson2HttpMessageConverter());
                    restTemplate.setMessageConverters(converters);
                    ParameterizedTypeReference<List<DTOColet>> typeRef = new ParameterizedTypeReference<List<DTOColet>>() {};
                    ResponseEntity<List<DTOColet>> colete = restTemplate.exchange("http://localhost:8080/api/colete/" + newValue, HttpMethod.GET, null, typeRef);
                    listViewColete.setItems(FXCollections.observableArrayList(colete.getBody()));
                }
            }
        });
        textFieldSearchColet2.setPromptText("search traseu");
        textFieldSearchColet2.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.equals("")){
                    listViewTraseu.setItems(FXCollections.observableArrayList(listColeteTraseu));
                }else {
                    RestTemplate restTemplate = new RestTemplate();
                    List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
                    converters.add(new MappingJackson2HttpMessageConverter());
                    restTemplate.setMessageConverters(converters);
                    ParameterizedTypeReference<List<DTOColet>> typeRef = new ParameterizedTypeReference<List<DTOColet>>() {};
                    ResponseEntity<List<DTOColet>> colete = restTemplate.exchange("http://localhost:8080/api/colete/" + newValue, HttpMethod.GET, null, typeRef);
                    listViewTraseu.setItems(FXCollections.observableArrayList(colete.getBody()));
                }
            }
        });
    }

    private List<DTOColet> getColete(){
        RestTemplate restTemplate=new RestTemplate();
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(converters);
        ParameterizedTypeReference<List<DTOColet>> typeRef = new ParameterizedTypeReference<List<DTOColet>>() {};
        ResponseEntity<List<DTOColet>> colete=restTemplate.exchange("http://localhost:8080/api/colete", HttpMethod.GET ,null, typeRef);
        return colete.getBody();
    }

    public void onClickButonVizualizareLista(ActionEvent actionEvent) {
        //

    }
    public void onClickButonVizualizareTraseu(ActionEvent actionEvent) {
        //
    }
    public void onClickButonCautareColet(ActionEvent actionEvent) {
        //cautare colete dupa numePostas

        int selected=-1;
    }

}
