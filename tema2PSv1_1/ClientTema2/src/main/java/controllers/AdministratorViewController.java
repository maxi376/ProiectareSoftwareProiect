package controllers;

import dataAcces.DTOCoordonatorActivitate;
import dataAcces.DTOPostas;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class AdministratorViewController {
    public Button butonAdaugarePostas;
    public Button butonAfisarePostas;
    public Button butonUpdatePostas;
    public Button butonStergerePostas;
    public Button butonAdaugareCoordonator;
    public Button butonAfisareCoordonator;
    public Button butonUpdateCoordonator;
    public Button butonStergereCoordonator;

    public TextField textUsernameP;
    public TextField textParolaC;
    public TextField textUsernameC;
    public TextField textParolaP;
    public ListView listViewPostasi;
    public ListView listViewCoordonatori;
    public TextField textSearchPostas=new TextField();
    public TextField textSearchCoordonatorActivitate=new TextField();

    private List<DTOPostas> listPostasi=new ArrayList<DTOPostas>();
    private List<DTOCoordonatorActivitate> listCoordonatori=new ArrayList<DTOCoordonatorActivitate>();


    @FXML
    public void initialize(){
        listPostasi=getPostasi();
        listViewPostasi.setItems(FXCollections.observableArrayList(listPostasi));

        listCoordonatori=getCoordonatori();
        listViewCoordonatori.setItems(FXCollections.observableArrayList(listCoordonatori));

        textSearchPostas.setPromptText("search");
        textSearchPostas.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.equals("")){
                    listViewPostasi.setItems(FXCollections.observableArrayList(listPostasi));
                }else {
                    RestTemplate restTemplate = new RestTemplate();
                    List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
                    converters.add(new MappingJackson2HttpMessageConverter());
                    restTemplate.setMessageConverters(converters);
                    ParameterizedTypeReference<List<DTOPostas>> typeRef = new ParameterizedTypeReference<List<DTOPostas>>() {};
                    ResponseEntity<List<DTOPostas>> postasi = restTemplate.exchange("http://localhost:8080/api/postas/" + newValue, HttpMethod.GET, null, typeRef);
                    listViewPostasi.setItems(FXCollections.observableArrayList(postasi.getBody()));
                }
            }
        });

        textSearchCoordonatorActivitate.setPromptText("search");
        textSearchCoordonatorActivitate.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.equals("")){
                    listViewCoordonatori.setItems(FXCollections.observableArrayList(listCoordonatori));
                }else {
                    RestTemplate restTemplate = new RestTemplate();
                    List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
                    converters.add(new MappingJackson2HttpMessageConverter());
                    restTemplate.setMessageConverters(converters);
                    ParameterizedTypeReference<List<DTOCoordonatorActivitate>> typeRef = new ParameterizedTypeReference<List<DTOCoordonatorActivitate>>() {};
                    ResponseEntity<List<DTOCoordonatorActivitate>> coordonatori = restTemplate.exchange("http://localhost:8080/api/coordonatoractivitate/" + newValue, HttpMethod.GET, null, typeRef);
                    listViewCoordonatori.setItems(FXCollections.observableArrayList(coordonatori.getBody()));
                }
            }
        });
    }

    private List<DTOPostas> getPostasi(){
        RestTemplate restTemplate=new RestTemplate();
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(converters);
        ParameterizedTypeReference<List<DTOPostas>> typeRef = new ParameterizedTypeReference<List<DTOPostas>>() {};
        ResponseEntity<List<DTOPostas>> postasi=restTemplate.exchange("http://localhost:8080/api/postas", HttpMethod.GET ,null, typeRef);
        return postasi.getBody();
    }
    private List<DTOCoordonatorActivitate> getCoordonatori(){
        RestTemplate restTemplate=new RestTemplate();
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(converters);
        ParameterizedTypeReference<List<DTOCoordonatorActivitate>> typeRef = new ParameterizedTypeReference<List<DTOCoordonatorActivitate>>() {};
        ResponseEntity<List<DTOCoordonatorActivitate>> coordonatori=restTemplate.exchange("http://localhost:8080/api/coordonatoractivitate", HttpMethod.GET ,null, typeRef);
        return coordonatori.getBody();
    }

    public void onClickButonAdaugarePostas(ActionEvent actionEvent) {
        String parola=textParolaP.getText();
        String numeUtilizator=textUsernameP.getText();
        DTOPostas dtoPostas = new DTOPostas(numeUtilizator,parola);

        RestTemplate restTemplate=new RestTemplate();
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(converters);
        HttpEntity<DTOPostas> dtoPostasHttpEntity=new HttpEntity<DTOPostas>(dtoPostas);
        DTOPostas dtoPostas1 = restTemplate.postForObject("http://localhost:8080/api/postas",dtoPostasHttpEntity,DTOPostas.class);
        listPostasi.add(dtoPostas1);
        listViewPostasi.setItems(FXCollections.observableArrayList(listPostasi));
    }
    public void onClickButonUpdatePostas(ActionEvent actionEvent) {
        String parola=textParolaP.getText();
        String numeUtilizator=textUsernameP.getText();

        DTOPostas dtoPostas = (DTOPostas) listViewPostasi.getSelectionModel().getSelectedItem();
        int position=listViewPostasi.getSelectionModel().getSelectedIndex();
        Integer id=dtoPostas.getId();
        dtoPostas.setNumeUtilizator(numeUtilizator);
        dtoPostas.setParola(parola);

        RestTemplate restTemplate=new RestTemplate();
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(converters);
        HttpEntity<DTOPostas> dtoPostasHttpEntity=new HttpEntity<DTOPostas>(dtoPostas);
        ParameterizedTypeReference<DTOPostas> typeRef = new ParameterizedTypeReference<DTOPostas>() {};
        ResponseEntity<DTOPostas> dtoPostas1 = restTemplate.exchange("http://localhost:8080/api/postas/"+id,HttpMethod.PUT,dtoPostasHttpEntity,typeRef);

        listPostasi.set(position,dtoPostas1.getBody());
        listViewPostasi.setItems(FXCollections.observableArrayList(listPostasi));
    }

    public void onClickButonStergerePostas(ActionEvent actionEvent) {
        DTOPostas dtoPostas = (DTOPostas) listViewPostasi.getSelectionModel().getSelectedItem();
        //List<DTOPostas> postasi = (List<DTOPostas>) listViewPostasi.getSelectionModel().getSelectedItems();

        RestTemplate restTemplate=new RestTemplate();
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(converters);
        restTemplate.delete("http://localhost:8080/api/postas/"+dtoPostas.getId());
        listPostasi.remove(dtoPostas);
        listViewPostasi.setItems(FXCollections.observableArrayList(listPostasi));
    }

    public void onClickButonAdaugareCoordonator(ActionEvent actionEvent) {
        String parola=textParolaC.getText();
        String numeUtilizator=textUsernameC.getText();
        DTOCoordonatorActivitate dtoCoordonatorActivitate = new DTOCoordonatorActivitate(numeUtilizator,parola);

        RestTemplate restTemplate=new RestTemplate();
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(converters);
        HttpEntity<DTOCoordonatorActivitate> dtoCoordonatorActivitateHttpEntity=new HttpEntity<DTOCoordonatorActivitate>(dtoCoordonatorActivitate);
        DTOCoordonatorActivitate dtoCoordonatorActivitate1 = restTemplate.postForObject("http://localhost:8080/api/coordonatoractivitate",dtoCoordonatorActivitateHttpEntity,DTOCoordonatorActivitate.class);
        listCoordonatori.add(dtoCoordonatorActivitate1);
        listViewCoordonatori.setItems(FXCollections.observableArrayList(listCoordonatori));
    }
    public void onClickButonUpdateCoordonator(ActionEvent actionEvent) {
        String parola=textParolaC.getText();
        String numeUtilizator=textUsernameC.getText();

        DTOCoordonatorActivitate dtoCoordonatorActivitate = (DTOCoordonatorActivitate) listViewCoordonatori.getSelectionModel().getSelectedItem();
        int position=listViewCoordonatori.getSelectionModel().getSelectedIndex();
        Integer id=dtoCoordonatorActivitate.getId();
        dtoCoordonatorActivitate.setNumeUtilizator(numeUtilizator);
        dtoCoordonatorActivitate.setParola(parola);

        RestTemplate restTemplate=new RestTemplate();
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(converters);
        HttpEntity<DTOCoordonatorActivitate> dtoCoordonatorActivitateHttpEntity=new HttpEntity<DTOCoordonatorActivitate>(dtoCoordonatorActivitate);
        ParameterizedTypeReference<DTOCoordonatorActivitate> typeRef = new ParameterizedTypeReference<DTOCoordonatorActivitate>() {};
        ResponseEntity<DTOCoordonatorActivitate> dtoCoordonatorActivitate1 = restTemplate.exchange("http://localhost:8080/api/coordonatoractivitate/"+id,HttpMethod.PUT,dtoCoordonatorActivitateHttpEntity,typeRef);

        listCoordonatori.set(position,dtoCoordonatorActivitate1.getBody());
        listViewCoordonatori.setItems(FXCollections.observableArrayList(listCoordonatori));
    }

    public void onClickButonStergereCoordonator(ActionEvent actionEvent) {
        DTOCoordonatorActivitate dtoCoordonatorActivitate = (DTOCoordonatorActivitate) listViewCoordonatori.getSelectionModel().getSelectedItem();
        //List<DTOPostas> postasi = (List<DTOPostas>) listViewPostasi.getSelectionModel().getSelectedItems();

        System.out.println(dtoCoordonatorActivitate.getId());
        RestTemplate restTemplate=new RestTemplate();

        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(converters);
        restTemplate.delete("http://localhost:8080/api/coordonatoractivitate/"+dtoCoordonatorActivitate.getId());
        listCoordonatori.remove(dtoCoordonatorActivitate);
        listViewCoordonatori.setItems(FXCollections.observableArrayList(listCoordonatori));
    }

    //public String toString
}
