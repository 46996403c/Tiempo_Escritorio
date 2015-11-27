package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.*;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;

public class Controller {
    //--------------------------------
    public CheckMenuItem barcelonaCMI;
    public CheckMenuItem madridCMI;
    public CheckMenuItem zaragozaCMI;
    public TextField ciudadTF;
    public TextField paisTF;
    //--------------------------------
    public MenuButton semanaMB;
    public CheckMenuItem dia_1_CMI;
    public CheckMenuItem dia_2_CMI;
    public CheckMenuItem dia_3_CMI;
    public CheckMenuItem dia_4_CMI;
    public CheckMenuItem dia_5_CMI;
    public CheckMenuItem dia_6_CMI;
    public CheckMenuItem dia_7_CMI;
    public CheckMenuItem dia_8_CMI;
    public CheckMenuItem dia_9_CMI;
    public CheckMenuItem dia_10_CMI;
    public CheckMenuItem dia_11_CMI;
    public CheckMenuItem dia_12_CMI;
    public CheckMenuItem dia_13_CMI;
    public CheckMenuItem dia_14_CMI;
    public CheckMenuItem dia_15_CMI;
    public TextField semanaTF;
    //--------------------------------
    public MenuButton diasSemanaMB;
    public CheckMenuItem lunesCMI;
    public CheckMenuItem martesCMI;
    public CheckMenuItem miercolesCMI;
    public CheckMenuItem juevesCMI;
    public CheckMenuItem viernesCMI;
    public CheckMenuItem sabadoCMI;
    public CheckMenuItem domingoCMI;
    public TextField fechaSemanaTF;
    //--------------------------------
    public RadioButton diasRB;
    public RadioButton horasRB;
    public RadioButton fechasRB;
    //--------------------------------
    public ListView<String> tiempoLV;
    public ObservableList observableList = FXCollections.observableArrayList();
    //--------------------------------
    public ImageView fotoIV;
    //--------------------------------
    public Tab graficaTAB;
    public TextField ciudadGrapTF;
    public TextField paisGrapTF;
    public TextField MaxMediaSem1;
    public TextField MinMediaSem1;
    public TextField MaxMediaSem2;
    public TextField MinMediaSem2;
    public TextField MaxMediaSem3;
    public TextField MinMediaSem3;
    public ListView<String> semana1LV;
    public ListView<String> semana2LV;
    public ListView<String> semana3LV;
    public ObservableList observableListSemana1 = FXCollections.observableArrayList();
    public ObservableList observableListSemana2 = FXCollections.observableArrayList();
    public ObservableList observableListSemana3 = FXCollections.observableArrayList();
    public Stage stage;
    //--------------------------------
    public String daily = ""; //sin la opcion del daily te hace la prevision por horas
    public String cnt = "";
    public int dia;
    public boolean horas = true;
    public boolean dias = false;
    public boolean fechas = false;
    //--------------------------------
    public TextArea descripcionTA;
    public Hyperlink link;
    public TextArea notasTA;
    //--------------------------------
    //http://api.openweathermap.org/data/2.5/forecast/daily?q=Barcelona&mode=xml&lang=es&units=metric&cnt=15&appid=cbe4f297cf8d498e5be683b08a61314e
    //urlBase()daily?q=ciudad()&mode=xml&lang=es&units=metric&cnt=15apiKey
    //--------------------------------
    public String UrlBase() {
        return "http://api.openweathermap.org/data/2.5/forecast/";
    }
    public String ApiKey() {
        return "&appid=cbe4f297cf8d498e5be683b08a61314e";
    }
    public String OpcionesApi(){
        return "&mode=xml&lang=es&units=metric";
    }
    public void Ciudad(javafx.event.ActionEvent actionEventCiudad){
        ciudadTF.setText("");
        barcelonaCMI.setSelected(false);
        madridCMI.setSelected(false);
        zaragozaCMI.setSelected(false);
        if (actionEventCiudad.getSource().equals(barcelonaCMI)){
            barcelonaCMI.setSelected(true);
            ciudadTF.setText("Barcelona,es");
        }
        else if (actionEventCiudad.getSource().equals(madridCMI)){
            madridCMI.setSelected(true);
            ciudadTF.setText("Madrid");
        }
        else if (actionEventCiudad.getSource().equals(zaragozaCMI)){
            zaragozaCMI.setSelected(true);
            ciudadTF.setText("Zaragoza,es");
        }else {
            ciudadTF.setText(ciudadTF.getText());
        }
    }
    public void Daily(javafx.event.ActionEvent actionEventDaily){
        horasRB.setSelected(false);
        diasRB.setSelected(false);
        fechasRB.setSelected(false);
        semanaTF.setDisable(true);
        semanaMB.setDisable(true);
        diasSemanaMB.setDisable(true);
        fechaSemanaTF.setDisable(true);
        horas = false;
        dias = false;
        fechas = false;
        if (actionEventDaily.getSource().equals(horasRB)){
            fechaSemanaTF.clear();
            horasRB.setSelected(true);
            horasRB.requestFocus();
            semanaTF.setDisable(true);
            semanaMB.setDisable(true);
            fechaSemanaTF.setDisable(true);
            diasSemanaMB.setDisable(true);
            fechaSemanaTF.clear();
            semanaTF.clear();
            Deshabilitador();
            daily = "";
            horas = true;
        }
        if (actionEventDaily.getSource().equals(diasRB)){
            diasRB.setSelected(true);
            diasRB.requestFocus();
            semanaTF.setDisable(false);
            semanaMB.setDisable(false);
            fechaSemanaTF.setDisable(true);
            diasSemanaMB.setDisable(true);
            fechaSemanaTF.clear();
            Deshabilitador();
            daily = "daily";
            dias = true;
        }
        if (actionEventDaily.getSource().equals(fechasRB)) {
            fechasRB.setSelected(true);
            fechasRB.requestFocus();
            semanaTF.setDisable(true);
            semanaMB.setDisable(true);
            diasSemanaMB.setDisable(false);
            fechaSemanaTF.setDisable(false);
            semanaTF.clear();
            Deshabilitador();
            daily = "daily";
            fechas = true;
        }
    }
    public void SiguientesDias(javafx.event.ActionEvent actionEventSiguientesDias){
        cnt = "&cnt=1";
        Deshabilitador();
        if (actionEventSiguientesDias.getSource().equals(dia_1_CMI)){
            dia_1_CMI.setSelected(true);
            semanaTF.setText("1 Dia");
            cnt = "&cnt=1";
        }
        if (actionEventSiguientesDias.getSource().equals(dia_2_CMI)){
            dia_2_CMI.setSelected(true);
            semanaTF.setText("2 Dias");
            cnt = "&cnt=2";
        }
        if (actionEventSiguientesDias.getSource().equals(dia_3_CMI)){
            dia_3_CMI.setSelected(true);
            semanaTF.setText("3 Dias");
            cnt = "&cnt=3";
        }
        if (actionEventSiguientesDias.getSource().equals(dia_4_CMI)){
            dia_4_CMI.setSelected(true);
            semanaTF.setText("4 Dias");
            cnt = "&cnt=4";
        }
        if (actionEventSiguientesDias.getSource().equals(dia_5_CMI)){
            dia_5_CMI.setSelected(true);
            semanaTF.setText("5 Dias");
            cnt = "&cnt=5";
        }
        if (actionEventSiguientesDias.getSource().equals(dia_6_CMI)){
            dia_6_CMI.setSelected(true);
            semanaTF.setText("6 Dias");
            cnt = new String("&cnt=6");
        }
        if (actionEventSiguientesDias.getSource().equals(dia_7_CMI)){
            dia_7_CMI.setSelected(true);
            semanaTF.setText("7 Dias");
            cnt = "&cnt=7";
        }
        if (actionEventSiguientesDias.getSource().equals(dia_8_CMI)){
            dia_8_CMI.setSelected(true);
            semanaTF.setText("8 Dias");
            cnt = "&cnt=8";
        }
        if (actionEventSiguientesDias.getSource().equals(dia_9_CMI)){
            dia_9_CMI.setSelected(true);
            semanaTF.setText("9 Dias");
            cnt = "&cnt=9";
        }
        if (actionEventSiguientesDias.getSource().equals(dia_10_CMI)){
            dia_10_CMI.setSelected(true);
            semanaTF.setText("10 Dias");
            cnt = "&cnt=10";
        }
        if (actionEventSiguientesDias.getSource().equals(dia_11_CMI)){
            dia_11_CMI.setSelected(true);
            semanaTF.setText("11 Dias");
            cnt = "&cnt=11";
        }
        if (actionEventSiguientesDias.getSource().equals(dia_12_CMI)){
            dia_12_CMI.setSelected(true);
            semanaTF.setText("12 Dias");
            cnt = "&cnt=12";
        }
        if (actionEventSiguientesDias.getSource().equals(dia_13_CMI)){
            dia_13_CMI.setSelected(true);
            semanaTF.setText("13 Dias");
            cnt = "&cnt=13";
        }
        if (actionEventSiguientesDias.getSource().equals(dia_14_CMI)){
            dia_14_CMI.setSelected(true);
            semanaTF.setText("14 Dias");
            cnt = "&cnt=14";
        }
        if (actionEventSiguientesDias.getSource().equals(dia_15_CMI)){
            dia_15_CMI.setSelected(true);
            semanaTF.setText("15 Dias");
            cnt = "&cnt=15";
        }
    }
    public void DiaSemana(javafx.event.ActionEvent actionEventDiaSemana){
        fechaSemanaTF.clear();
        Deshabilitador();
        // DayOfWeek 1
        if (actionEventDiaSemana.getSource().equals(domingoCMI)){
            dia=1;
            obtenerFecha(dia);
            domingoCMI.setSelected(true);
            fechaSemanaTF.setText("Domingo");
        }
        // DayOfWeek 2
        if (actionEventDiaSemana.getSource().equals(lunesCMI)){
            dia=2;
            obtenerFecha(dia);
            lunesCMI.setSelected(true);
            fechaSemanaTF.setText("Lunes");
        }
        // DayOfWeek 3
        if (actionEventDiaSemana.getSource().equals(martesCMI)){
            dia=3;
            obtenerFecha(dia);
            martesCMI.setSelected(true);
            fechaSemanaTF.setText("Martes");
        }
        // DayOfWeek 4
        if (actionEventDiaSemana.getSource().equals(miercolesCMI)){
            dia=4;
            obtenerFecha(dia);
            miercolesCMI.setSelected(true);
            fechaSemanaTF.setText("Miercoles");
        }
        // DayOfWeek 5
        if (actionEventDiaSemana.getSource().equals(juevesCMI)){
            dia=5;
            obtenerFecha(dia);
            juevesCMI.setSelected(true);
            fechaSemanaTF.setText("Jueves");
        }
        // DayOfWeek 6
        if (actionEventDiaSemana.getSource().equals(viernesCMI)){
            dia=6;
            obtenerFecha(dia);
            viernesCMI.setSelected(true);
            fechaSemanaTF.setText("Viernes");
        }
        // DayOfWeek 7
        if (actionEventDiaSemana.getSource().equals(sabadoCMI)){
            dia=7;
            obtenerFecha(dia);
            sabadoCMI.setSelected(true);
            fechaSemanaTF.setText("Sabado");
        }
    }
    public void obtenerFecha(int dia) {
        Calendar fecha = new GregorianCalendar();
        int DayOfWeek = fecha.get(Calendar.DAY_OF_WEEK);
        //System.out.println(DayOfWeek);
        int semanaCompleta = 7;
        int inicioSemana = 0;

        if (dia==DayOfWeek){
            //System.out.println("dia==DayOfWeek "+dia+" = "+DayOfWeek);
            cnt ="&cnt=1";
        }
        if (dia>DayOfWeek){
            //System.out.println("dia>DayOfWeek "+dia+" > "+DayOfWeek);
            int diaFinal = dia - DayOfWeek + 1;
            //System.out.println("diaFinal: " + diaFinal);
            cnt ="&cnt="+diaFinal;
        }
        if (dia<DayOfWeek){
            //System.out.println("dia>DayOfWeek "+dia+" < "+DayOfWeek);
            int primerPaso = semanaCompleta - DayOfWeek;
            //System.out.println("primerPaso: " + primerPaso);
            int segundoPaso = inicioSemana + dia;
            //System.out.println("segundoPaso: " + segundoPaso);
            int resultado = (primerPaso + segundoPaso) + 1;
            //System.out.println("resultado " + resultado);
            cnt = "&cnt="+Integer.toString(resultado);
        }
        /*
        hoy posicion 5
        hasta domin = pos 1

        Si el dia es de la siguiente semana
        posicion de hoy menos la semana completa
        dia seleccionado + el resultado anterior

        Si el dia es de la misma semana
        pisicion de hoy mas el resultado de la resta de la semanana entera y el dia deseado.
        */
    }
    public void Deshabilitador(){
        lunesCMI.setSelected(false);
        martesCMI.setSelected(false);
        miercolesCMI.setSelected(false);
        juevesCMI.setSelected(false);
        viernesCMI.setSelected(false);
        sabadoCMI.setSelected(false);
        domingoCMI.setSelected(false);

        dia_1_CMI.setSelected(false);
        dia_2_CMI.setSelected(false);
        dia_3_CMI.setSelected(false);
        dia_4_CMI.setSelected(false);
        dia_5_CMI.setSelected(false);
        dia_6_CMI.setSelected(false);
        dia_7_CMI.setSelected(false);
        dia_8_CMI.setSelected(false);
        dia_9_CMI.setSelected(false);
        dia_10_CMI.setSelected(false);
        dia_11_CMI.setSelected(false);
        dia_12_CMI.setSelected(false);
        dia_13_CMI.setSelected(false);
        dia_14_CMI.setSelected(false);
        dia_15_CMI.setSelected(false);
    }
    public void Grafica() throws ParserConfigurationException, SAXException, IOException {
        Stage stage = new Stage();
        stage.setTitle("Evolucion de la temperatura: " + ciudadTF.getText());
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Dias");
        yAxis.setLabel("Temperatura");
        //creating the chart
        final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
        lineChart.setTitle("Temperatura - Dias");

        URL urlApi = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=" + ciudadTF.getText() + "&mode=xml&lang=es&units=metric&cnt=16&appid=cbe4f297cf8d498e5be683b08a61314e");
        System.out.println(urlApi);


        //Creamos el protocolo de entrada
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(String.valueOf(urlApi));

        NodeList listaNodoLugar = doc.getElementsByTagName("location");
        Element lugar = (Element) listaNodoLugar.item(0);
        //System.out.println("Lugar: " + lugar.getElementsByTagName("name").item(0).getTextContent());
        ciudadGrapTF.setText(lugar.getElementsByTagName("name").item(0).getTextContent());
        paisGrapTF.setText(lugar.getElementsByTagName("country").item(0).getTextContent());

        //Normalizamos los procesos
        doc.getDocumentElement().normalize();
        NodeList listaNodoTiempo = doc.getElementsByTagName("time");


        //Creamos un for para que vaya leyendo de cada nodo hasta el final del documento
        XYChart.Series semana1Max = new XYChart.Series();
        XYChart.Series semana2Max = new XYChart.Series();
        XYChart.Series semana3Max = new XYChart.Series();
        XYChart.Series semana1Min = new XYChart.Series();
        XYChart.Series semana2Min = new XYChart.Series();
        XYChart.Series semana3Min = new XYChart.Series();
        semana1Max.setName("Maxima a 07 dias");
        semana2Max.setName("Maxima a 14 dias");
        semana3Max.setName("Maxima a 16 dias");
        semana1Min.setName("Minima a 07 dias");
        semana2Min.setName("Minima a 14 dias");
        semana3Min.setName("Minima a 16 dias");
        observableListSemana1.setAll();
        observableListSemana2.setAll();
        observableListSemana3.setAll();
        double sem1MaxTotal = 0;
        double sem1MinTotal = 0;
        for (int i = 0; i <= 6; i++) {
            Element tiempo = (Element) listaNodoTiempo.item(i);
            semana1Max.getData().add(new XYChart.Data(i, Double.parseDouble(tiempo.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("max").getNodeValue())));
            semana1Min.getData().add(new XYChart.Data(i, Double.parseDouble(tiempo.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("min").getNodeValue())));
            String day = tiempo.getAttribute("day");
            //DIA - MES - AÑO
            String diaPanalla = day.substring(8,10)+"/"+day.substring(5,7)+"/"+day.substring(0,4);
            observableListSemana1.add("DIA: "+i+" --> "+diaPanalla);
            observableListSemana1.add("Tempratura maxima: " + tiempo.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("max").getNodeValue() + " Celsius");
            double sem1Max = Double.parseDouble(tiempo.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("max").getNodeValue());
            sem1MaxTotal = sem1MaxTotal +sem1Max;
            observableListSemana1.add("Tempratura minima: " + tiempo.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("min").getNodeValue() + " Celsius\n");
            double sem1Min = Double.parseDouble(tiempo.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("min").getNodeValue());
            sem1MinTotal = sem1MinTotal +sem1Min;
            semana1LV.setItems(observableListSemana1);
        }
        double sem1MaxMedia = sem1MaxTotal/7;
        MaxMediaSem1.setText(""+sem1MaxMedia);
        double sem1MinMedia = sem1MinTotal/7;
        MinMediaSem1.setText(""+sem1MinMedia);
        for (int i = 6; i <= 13; i++) {
            Element tiempo = (Element) listaNodoTiempo.item(i);
            semana2Max.getData().add(new XYChart.Data(i, Double.parseDouble(tiempo.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("max").getNodeValue())));
            semana2Min.getData().add(new XYChart.Data(i, Double.parseDouble(tiempo.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("min").getNodeValue())));
        }
        double sem2MaxTotal = 0;
        double sem2MinTotal = 0;
        for (int i = 7; i <= 13; i++) {
            Element tiempo = (Element) listaNodoTiempo.item(i);
            String day = tiempo.getAttribute("day");
            //DIA - MES - AÑO
            String diaPanalla = day.substring(8,10)+"/"+day.substring(5,7)+"/"+day.substring(0,4);
            observableListSemana2.add("DIA: "+i+" --> "+diaPanalla);
            observableListSemana2.add("Tempratura minima: " + tiempo.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("min").getNodeValue() + " Celsius");
            double sem2Max = Double.parseDouble(tiempo.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("max").getNodeValue());
            sem2MaxTotal = sem2MaxTotal +sem2Max;
            observableListSemana2.add("Tempratura maxima: " + tiempo.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("max").getNodeValue() + " Celsius\n");
            double sem2Min = Double.parseDouble(tiempo.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("min").getNodeValue());
            sem2MinTotal = sem2MinTotal +sem2Min;
            semana2LV.setItems(observableListSemana2);
        }
        double sem2MaxMedia = sem2MaxTotal/7;
        MaxMediaSem2.setText(""+sem2MaxMedia);
        double sem2MinMedia = sem2MinTotal/7;
        MinMediaSem2.setText(""+sem2MinMedia);
        double sem3MaxTotal = 0;
        double sem3MinTotal = 0;
        for (int i = 13; i < listaNodoTiempo.getLength(); i++) {
            Element tiempo = (Element) listaNodoTiempo.item(i);
            semana3Max.getData().add(new XYChart.Data(i, Double.parseDouble(tiempo.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("max").getNodeValue())));
            semana3Min.getData().add(new XYChart.Data(i, Double.parseDouble(tiempo.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("min").getNodeValue())));
            String day = tiempo.getAttribute("day");
            //DIA - MES - AÑO
            String diaPanalla = day.substring(8,10)+"/"+day.substring(5,7)+"/"+day.substring(0,4);
            observableListSemana3.add("DIA: "+i+" --> "+diaPanalla);
            observableListSemana3.add("Tempratura minima: " + tiempo.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("min").getNodeValue() + " Celsius");
            observableListSemana3.add("Tempratura maxima: " + tiempo.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("max").getNodeValue() + " Celsius\n");
            double sem3Max = Double.parseDouble(tiempo.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("max").getNodeValue());
            double sem3Min = Double.parseDouble(tiempo.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("min").getNodeValue());
            sem3MaxTotal = sem2MaxTotal +sem3Max;
            sem3MinTotal = sem2MinTotal +sem3Min;
            semana3LV.setItems(observableListSemana3);
        }
        double sem3MaxMedia = sem3MaxTotal/7;
        MaxMediaSem3.setText(""+sem3MaxMedia);
        double sem3MinMedia = sem3MinTotal/7;
        MinMediaSem3.setText(""+sem3MinMedia);
        //defining a series
        Scene scene  = new Scene(lineChart,500,400);
        lineChart.getData().addAll(semana1Max, semana2Max, semana3Max,semana1Min, semana2Min, semana3Min);

        stage.setScene(scene);
        stage.show();
    }
    public void VerTiempo() throws IOException, ParserConfigurationException, SAXException {
        graficaTAB.setDisable(false);
        URL api = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=Barcelona&mode=xml&lang=es&units=metric&cnt=3&appid=cbe4f297cf8d498e5be683b08a61314e");
        URL urlApi = new URL(UrlBase()+daily+"?q="+ciudadTF.getText()+OpcionesApi()+cnt+ApiKey());
        System.out.println(urlApi);
        //Creamos el protocolo de entrada
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(String.valueOf(urlApi));

        DecimalFormat df = new DecimalFormat("#0.00");

        fotoIV.setImage(null);
        //Normalizamos los procesos
        NodeList listaNodoLugar = doc.getElementsByTagName("location");
        Element lugar = (Element) listaNodoLugar.item(0);
        //System.out.println("Lugar: " + lugar.getElementsByTagName("name").item(0).getTextContent());
        ciudadTF.setText(lugar.getElementsByTagName("name").item(0).getTextContent());
        paisTF.setText(lugar.getElementsByTagName("country").item(0).getTextContent());
        doc.getDocumentElement().normalize();
        NodeList listaNodoTiempo = doc.getElementsByTagName("time");
        //Creamos un for para que vaya leyendo de cada nodo hasta el final del documento
        if (horas==true){
            observableList.setAll();
            for (int i = 0; i < listaNodoTiempo.getLength(); i++) {
                Element tiempo = (Element) listaNodoTiempo.item(i);

                String from = tiempo.getAttribute("from");
                String to = tiempo.getAttribute("to");
                //DIA - MES - AÑO - Hora
                String fromPanalla = from.substring(8,10)+"/"+from.substring(5,7)+"/"+from.substring(0,4)+" - "+from.substring(12,19);
                String toPantalla = to.substring(8,10)+"/"+to.substring(5,7)+"/"+to.substring(0,4)+" - "+to.substring(12,19);
                observableList.add(fromPanalla+" --> "+toPantalla);
                observableList.add("Tiempo: " + tiempo.getElementsByTagName("symbol").item(0).getAttributes().getNamedItem("name").getNodeValue());
                observableList.add("Tempratura media: " + tiempo.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("value").getNodeValue() + " Celsius");
                observableList.add("Tempratura minima: " + tiempo.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("min").getNodeValue() + " Celsius");
                observableList.add("Tempratura maxima: " + tiempo.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("max").getNodeValue() + " Celsius");
                Double velViento = Double.parseDouble(tiempo.getElementsByTagName("windSpeed").item(0).getAttributes().getNamedItem("mps").getNodeValue());
                observableList.add("Viento: " + df.format(velViento * 3.6) + " Kph");
                observableList.add("\n");
                tiempoLV.setItems(observableList);
            }
        }
        else if (dias==true){
            observableList.setAll();
            for (int i = dia; i < listaNodoTiempo.getLength(); i++) {
                Element tiempo = (Element) listaNodoTiempo.item(i);
                String day = tiempo.getAttribute("day");
                //DIA - MES - AÑO
                String diaPanalla = day.substring(8,10)+"/"+day.substring(5,7)+"/"+day.substring(0,4);
                observableList.add(diaPanalla);
                observableList.add("Tiempo: " + tiempo.getElementsByTagName("symbol").item(0).getAttributes().getNamedItem("name").getNodeValue());
                observableList.add("Tempratura dia: " + tiempo.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("day").getNodeValue() + " Celsius");
                observableList.add("Tempratura noche: " + tiempo.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("night").getNodeValue() + " Celsius");
                observableList.add("Tempratura minima: " + tiempo.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("min").getNodeValue() + " Celsius");
                observableList.add("Tempratura maxima: " + tiempo.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("max").getNodeValue() + " Celsius");
                Double velViento = Double.parseDouble(tiempo.getElementsByTagName("windSpeed").item(0).getAttributes().getNamedItem("mps").getNodeValue());
                observableList.add("Viento: " + df.format(velViento * 3.6) + " Kph");
                observableList.add("\n");
                tiempoLV.setItems(observableList);
            }
        }
        else if (fechas==true){
            observableList.setAll();
            String icono = "01d";
            for (int i = (listaNodoTiempo.getLength()-1); i < listaNodoTiempo.getLength(); i++) {
                Element tiempo = (Element) listaNodoTiempo.item(i);
                String day = tiempo.getAttribute("day");
                //DIA - MES - AÑO
                String diaPanalla = day.substring(8,10)+"/"+day.substring(5,7)+"/"+day.substring(0,4);
                observableList.add(diaPanalla);
                observableList.add("Tiempo: " + tiempo.getElementsByTagName("symbol").item(0).getAttributes().getNamedItem("name").getNodeValue());
                observableList.add("Tempratura dia: " + tiempo.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("day").getNodeValue() + " Celsius");
                observableList.add("Tempratura noche: " + tiempo.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("night").getNodeValue() + " Celsius");
                observableList.add("Tempratura minima: " + tiempo.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("min").getNodeValue() + " Celsius");
                observableList.add("Tempratura maxima: " + tiempo.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("max").getNodeValue() + " Celsius");
                Double velViento = Double.parseDouble(tiempo.getElementsByTagName("windSpeed").item(0).getAttributes().getNamedItem("mps").getNodeValue());
                observableList.add("Viento: " + df.format(velViento * 3.6) + " Kph");
                observableList.add("\n");
                tiempoLV.setItems(observableList);
                icono = tiempo.getElementsByTagName("symbol").item(0).getAttributes().getNamedItem("var").getNodeValue();
            }
            fotoIV.setImage(new Image("file:src\\iconos\\" + icono + ".png"));
            //fotoIV.setImage(new Image("http://openweathermap.org/img/w/"+icono+".png"));
        }
        else {
            observableList.setAll();
            observableList.add("ERROR DE CONEXION, COMPRUEBA TU CONEXION A INTERNET");
            tiempoLV.setItems(observableList);
        }
    }
    public void Exit(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Tiempo, cerrar programa");
        alert.setHeaderText("Seguro que quires salir?");
        alert.setContentText("Pulsa Acptar para salir.\nPulsa Cancelar para volver al programa");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Platform.exit();
        }
    }
    public void Informacion() throws MalformedURLException {
        descripcionTA.setText("Programa creado por oscarXIII\n" +
                "Programa que muestra la prediccion de tiempo a 15 dias largos a partir del dia actual.\n" +
                "Si se quuiere introducir una ciudad de un pais concreto se recomienda poner en el campo de ciudad:\n\t\t{ciudad},{codigo del pais}\n" +
                "Por ejemplo para Barcelona de España seria: bacelona,es Si se pone solo Barcelona sale la ciudad de Colombia.\n" +
                "Pata ver la lista de codigo de ciudades visita el enlace de abajo o haz clic en \"Codigos Pais\".");
        link.setText("http://openweathermap.org/help/city_list.txt");
        link.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                URI u = null;
                try {
                    u = new URI("http://openweathermap.org/help/city_list.txt");
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();
                }
                try {
                    java.awt.Desktop.getDesktop().browse(u);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
    public void EnlaceBoton(){
        URI u = null;
        try {
            u = new URI("http://openweathermap.org/help/city_list.txt");
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
        try {
            java.awt.Desktop.getDesktop().browse(u);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
