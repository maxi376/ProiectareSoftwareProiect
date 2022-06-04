package controllers;

import dataAcces.DTOColet;
import dataAcces.DTOPostas;
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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class CoordonatorActivitateViewController {
    public Button butonCautareColet;
    public Button butonVizualizareTraseu;
    public Button butonVizualizareLista;
    public Button butonStergeColet;
    public Button butonAfisareColete;
    public Button butonUpdateColet;
    public Button butonAdaugareColet;
    public Button salvareRapoarte;
    public Button butonAlocareColet;
    public TextField textDestColet;
    public TextField textGreutateColet;
    public TextField textCoordonataX;
    public TextField textCoordonataY;
    public TextField textNumePostas;
    public ListView listViewPostasi;
    public ListView listViewListaColete;
    public ListView listViewTraseuPostas;
    public TextField textFieldSearchColet;
    public TextField textFieldSearchColet2;

    private List<DTOPostas> listPostasi=new ArrayList<DTOPostas>();
    private List<DTOColet> listColete=new ArrayList<DTOColet>();
    private List<DTOColet> listColeteTraseu=new ArrayList<DTOColet>();

    @FXML
    public void initialize() {
        listPostasi = getPostasi();
        listViewPostasi.setItems(FXCollections.observableArrayList(listPostasi));

        listColete = getColete();
        listViewListaColete.setItems(FXCollections.observableArrayList(listColete));

        listColeteTraseu = getColete();
        listViewTraseuPostas.setItems(FXCollections.observableArrayList(listColeteTraseu));

        textFieldSearchColet.setPromptText("search");
        textFieldSearchColet.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.equals("")){
                    listViewListaColete.setItems(FXCollections.observableArrayList(listColete));
                }else {
                    RestTemplate restTemplate = new RestTemplate();
                    List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
                    converters.add(new MappingJackson2HttpMessageConverter());
                    restTemplate.setMessageConverters(converters);
                    ParameterizedTypeReference<List<DTOColet>> typeRef = new ParameterizedTypeReference<List<DTOColet>>() {};
                    ResponseEntity<List<DTOColet>> colete = restTemplate.exchange("http://localhost:8080/api/colete/" + newValue, HttpMethod.GET, null, typeRef);
                    listViewListaColete.setItems(FXCollections.observableArrayList(colete.getBody()));
                }
            }
        });
        textFieldSearchColet2.setPromptText("search traseu");
        textFieldSearchColet2.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.equals("")){
                    listViewTraseuPostas.setItems(FXCollections.observableArrayList(listColeteTraseu));
                }else {
                    RestTemplate restTemplate = new RestTemplate();
                    List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
                    converters.add(new MappingJackson2HttpMessageConverter());
                    restTemplate.setMessageConverters(converters);
                    ParameterizedTypeReference<List<DTOColet>> typeRef = new ParameterizedTypeReference<List<DTOColet>>() {};
                    ResponseEntity<List<DTOColet>> colete = restTemplate.exchange("http://localhost:8080/api/colete/" + newValue, HttpMethod.GET, null, typeRef);
                    listViewTraseuPostas.setItems(FXCollections.observableArrayList(colete.getBody()));
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
    private List<DTOColet> getColete(){
        RestTemplate restTemplate=new RestTemplate();
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(converters);
        ParameterizedTypeReference<List<DTOColet>> typeRef = new ParameterizedTypeReference<List<DTOColet>>() {};
        ResponseEntity<List<DTOColet>> colete=restTemplate.exchange("http://localhost:8080/api/colete", HttpMethod.GET ,null, typeRef);
        return colete.getBody();
    }


    public void onClickButonAlocareColet(ActionEvent actionEvent) {
        String destColetText=textDestColet.getText();
        String greutateColetText=textGreutateColet.getText();
        String coordonataXText=textCoordonataX.getText();
        String coordonataYText=textCoordonataY.getText();
        String numePostas=textNumePostas.getText();

        DTOColet dtoColet = new DTOColet(destColetText,Integer.valueOf(greutateColetText),
                Double.valueOf(coordonataXText),Double.valueOf(coordonataYText),numePostas);

        RestTemplate restTemplate=new RestTemplate();
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(converters);
        HttpEntity<DTOColet> dtoColetHttpEntity=new HttpEntity<DTOColet>(dtoColet);
        DTOColet dtoColet1 = restTemplate.postForObject("http://localhost:8080/api/colete",dtoColetHttpEntity,DTOColet.class);
        listColete.add(dtoColet1);
        listViewListaColete.setItems(FXCollections.observableArrayList(listPostasi));
    }

    public void onClickSalvareRapoarte(ActionEvent actionEvent) {

    }

    public void onClickButonAdaugareColet(ActionEvent actionEvent) {
        String destColetText=textDestColet.getText();
        String greutateColetText=textGreutateColet.getText();
        String coordonataXText=textCoordonataX.getText();
        String coordonataYText=textCoordonataY.getText();
        String numePostas=textNumePostas.getText();

        DTOColet dtoColet = new DTOColet(destColetText,Integer.valueOf(greutateColetText),
                Double.valueOf(coordonataXText),Double.valueOf(coordonataYText),numePostas);

        RestTemplate restTemplate=new RestTemplate();
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(converters);
        HttpEntity<DTOColet> dtoColetHttpEntity=new HttpEntity<DTOColet>(dtoColet);
        DTOColet dtoColet1 = restTemplate.postForObject("http://localhost:8080/api/colete",dtoColetHttpEntity,DTOColet.class);
        listColete.add(dtoColet1);
        listViewListaColete.setItems(FXCollections.observableArrayList(listColete));
    }

    public void onClickButonUpdateColet(ActionEvent actionEvent) {
        String destColetText=textDestColet.getText();
        String greutateColetText=textGreutateColet.getText();
        String coordonataXText=textCoordonataX.getText();
        String coordonataYText=textCoordonataY.getText();
        String numePostas=textNumePostas.getText();

        DTOColet dtoColet = (DTOColet) listViewListaColete.getSelectionModel().getSelectedItem();
        int position=listViewListaColete.getSelectionModel().getSelectedIndex();
        Integer id=dtoColet.getId();
        dtoColet.setDest(destColetText);
        dtoColet.setGreutate(Integer.valueOf(greutateColetText));
        dtoColet.setX(Double.valueOf(coordonataXText));
        dtoColet.setY(Double.valueOf(coordonataYText));
        dtoColet.setNumePostas(numePostas);

        RestTemplate restTemplate=new RestTemplate();
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(converters);

        HttpEntity<DTOColet> dtoColetHttpEntity=new HttpEntity<DTOColet>(dtoColet);
        ParameterizedTypeReference<DTOColet> typeRef = new ParameterizedTypeReference<DTOColet>() {};
        ResponseEntity<DTOColet> dtoColet1 = restTemplate.exchange("http://localhost:8080/api/colete/"+id,HttpMethod.PUT,dtoColetHttpEntity,typeRef);
        listColete.add(dtoColet1.getBody());
        listViewListaColete.setItems(FXCollections.observableArrayList(listColete));
    }

    public void onClickButonStergeColet(ActionEvent actionEvent) {
        DTOColet dtoColet = (DTOColet) listViewListaColete.getSelectionModel().getSelectedItem();
        //List<DTOPostas> postasi = (List<DTOPostas>) listViewPostasi.getSelectionModel().getSelectedItems();

        RestTemplate restTemplate=new RestTemplate();
        List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
        converters.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(converters);
        restTemplate.delete("http://localhost:8080/api/colete/"+dtoColet.getId());
        listColete.remove(dtoColet);
        listViewListaColete.setItems(FXCollections.observableArrayList(listColete));
    }

    public void onClickButonVizualizareTraseu(ActionEvent actionEvent) {
        //de facut algoritm care sa faca traseu sa zicem dupa x distantele minime sau dupa x


        listColete = getColete();
        listViewListaColete.setItems(FXCollections.observableArrayList(listColete));
    }
}
