package sample;

import javafx.event.ActionEvent;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import sun.rmi.runtime.Log;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.IOException;
import java.net.*;
import java.text.DecimalFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Controller {
    //--------------------------------
    public CheckMenuItem barcelonaCMI;
    public CheckMenuItem madridCMI;
    public CheckMenuItem zaragozaCMI;
    public TextField ciudadTF;
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
    public ListView tiempoLV;
    //--------------------------------
    public AreaChart graficaAC;
    //--------------------------------
    public Button test;
    public String daily = "daily"; //sin la opcion del daily te hace la prevision por horas
    public String cnt = "&cnt=3";
    //http://api.openweathermap.org/data/2.5/forecast/daily?q=Barcelona&mode=xml&lang=es&units=metric&cnt=15&appid=cbe4f297cf8d498e5be683b08a61314e
    //urlBase()daily?q=ciudad()&mode=xml&lang=es&units=metric&cnt=15apiKey
    //--------------------------------
    public String urlBase() {
        return "http://api.openweathermap.org/data/2.5/forecast/";
    }
    public String apiKey() {
        return "&appid=cbe4f297cf8d498e5be683b08a61314e";
    }
    public String opcionesApi(){
        return "&mode=xml&lang=es&units=metric";
    }
    public void ciudad(javafx.event.ActionEvent actionEventCiudad){
        ciudadTF.setText("");
        barcelonaCMI.setSelected(false);
        madridCMI.setSelected(false);
        zaragozaCMI.setSelected(false);
        if (actionEventCiudad.getSource().equals(barcelonaCMI)){
            barcelonaCMI.setSelected(true);
            ciudadTF.setText("Barcelona");
        }
        else if (actionEventCiudad.getSource().equals(madridCMI)){
            madridCMI.setSelected(true);
            ciudadTF.setText("Madrid");
        }
        else if (actionEventCiudad.getSource().equals(zaragozaCMI)){
            zaragozaCMI.setSelected(true);
            ciudadTF.setText("Zaragoza");
        }else {
            ciudadTF.setText(ciudadTF.getText());
        }
    }
    public void daily(javafx.event.ActionEvent actionEventDaily){
        horasRB.setSelected(false);
        diasRB.setSelected(false);
        fechasRB.setSelected(false);
        semanaTF.setDisable(true);
        semanaMB.setDisable(true);
        diasSemanaMB.setDisable(true);
        fechaSemanaTF.setDisable(true);
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
            deshabilitador();
            daily = "";
        }
        if (actionEventDaily.getSource().equals(diasRB)){
            diasRB.setSelected(true);
            diasRB.requestFocus();
            semanaTF.setDisable(false);
            semanaMB.setDisable(false);
            fechaSemanaTF.setDisable(true);
            diasSemanaMB.setDisable(true);
            fechaSemanaTF.clear();
            deshabilitador();
            daily = "daily";
        }
        if (actionEventDaily.getSource().equals(fechasRB)) {
            fechasRB.setSelected(true);
            fechasRB.requestFocus();
            semanaTF.setDisable(true);
            semanaMB.setDisable(true);
            diasSemanaMB.setDisable(false);
            fechaSemanaTF.setDisable(false);
            semanaTF.clear();
            deshabilitador();
            daily = "daily";
        }
    }
    public void SiguientesDias(javafx.event.ActionEvent actionEventSiguientesDias){
        deshabilitador();
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
    public void diaSemana(javafx.event.ActionEvent actionEventDiaSemana){
        fechaSemanaTF.clear();
        deshabilitador();
        int dia;
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
        //falta acabar de centrar los dias para que concuerden bien
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
            //System.out.println("dia<DayOfWeek "+dia+" > "+DayOfWeek);
            int diaFinal = dia - 1;
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
    public void deshabilitador(){
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
    public void llamadaApi(){
        //llama da a la api para que muestre tres dias de informacion
        //http://api.openweathermap.org/data/2.5/forecast/daily?q=Barcelona&mode=xml&lang=es&units=metric&cnt=3&appid=cbe4f297cf8d498e5be683b08a61314e
        //String api = urlBase()+daily(AEdaily)+ciudad(AEciudad)+opcionesApi()+SiguientesDias(AESiguientesDias)+apiKey();
        String api = urlBase()+daily+"?q="+ciudadTF.getText()+opcionesApi()+cnt+apiKey();
        //diaSemana(AEDiaSemana);
        System.out.println(api);
    }
    public void verTiempo() throws IOException, ParserConfigurationException, SAXException {
        URL api = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=Barcelona&mode=xml&lang=es&units=metric&cnt=3&appid=cbe4f297cf8d498e5be683b08a61314e");
        URL urlApi = new URL(urlBase()+daily+"?q="+ciudadTF.getText()+opcionesApi()+cnt+apiKey());
        //Creamos el protocolo de entrada
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(String.valueOf(urlApi));

        DecimalFormat df = new DecimalFormat("#0.00");

        //Normalizamos los procesos
        NodeList listaNodoLugar = doc.getElementsByTagName("location");
        Element lugar = (Element) listaNodoLugar.item(0);
        System.out.println("Lugar: " + lugar.getElementsByTagName("name").item(0).getTextContent());
        ciudadTF.setText(lugar.getElementsByTagName("name").item(0).getTextContent());
        doc.getDocumentElement().normalize();
        NodeList listaNodoForecast = doc.getElementsByTagName("forecast");
        NodeList listaNodoTiempo = doc.getElementsByTagName("time");
        //System.out.println(nl.getLength());

        //Creamos un for para que vaya leyendo de cada nodo hasta el final del documento




        for (int e=0 ; e < listaNodoForecast.getLength(); e++) {
            Element forecast = (Element) listaNodoForecast.item(e);
            System.out.println("Fecha: " + forecast.getElementsByTagName("time").item(0).getAttributes().getNamedItem("day").getNodeValue());
            //tiempoLV.setItems(forecast.getElementsByTagName("time").item(0).getAttributes().getNamedItem("day").getNodeValue());
        }
        for (int i = 0; i < listaNodoTiempo.getLength(); i++) {
            Element tiempo = (Element) listaNodoTiempo.item(i);
            /*
            tiempo = tiempo.getElementsByTagName("symbol").item(0).getAttributes().getNamedItem("name").getNodeValue();
            tiempoLV.setItems(tiempo.getElementsByTagName("symbol").item(0).getAttributes().getNamedItem("name").getNodeValue());
            tiempoLV.setCellFactory(hol);
            */
            //System.out.println("Fechas:\tDesde: " + tiempo.getAttribute("from") + "\tHasta: " + tiempo.getAttribute("to"));
            System.out.println("Tiempo: " + tiempo.getElementsByTagName("symbol").item(0).getAttributes().getNamedItem("name").getNodeValue());
            System.out.println("Tempratura por el dia: " + tiempo.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("day").getNodeValue() + " Celsius");
            System.out.println("Tempratura por la noche: " + tiempo.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("night").getNodeValue() + " Celsius");
            System.out.println("Tempratura minima: " + tiempo.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("min").getNodeValue() + " Celsius");
            System.out.println("Tempratura minima: " + tiempo.getElementsByTagName("temperature").item(0).getAttributes().getNamedItem("max").getNodeValue() + " Celsius");
            Double velViento = Double.parseDouble(tiempo.getElementsByTagName("windSpeed").item(0).getAttributes().getNamedItem("mps").getNodeValue());
            System.out.println("Viento: " + df.format(velViento * 3.6) + " Kph");
            System.out.println("\n");
            Element VIENTO = (Element) tiempo.getElementsByTagName("windSpeed").item(0);
            VIENTO.setAttribute("kph", String.valueOf(df.format(velViento * 3.6)));
        }
    }
}
