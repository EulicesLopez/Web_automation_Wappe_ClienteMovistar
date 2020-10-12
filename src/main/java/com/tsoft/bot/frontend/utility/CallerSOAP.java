/*@autor : Abraham Hernandez*/
package com.tsoft.bot.frontend.utility;

import com.tsoft.bot.frontend.exceptions.FrontEndException;
import com.tsoft.bot.frontend.exceptions.ServiceException;
import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.soap.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class CallerSOAP {

    public static void main(String[] args){

    }

    public static Element getNodeListXMLProductoActivo(String getResponse, String tipoProducto, int numeroDeDatos, String titularidad) throws FrontEndException {
        Element nodoTC = null;
        try {
            InputStream inputStream = new ByteArrayInputStream(getResponse.getBytes());
            SOAPMessage message = MessageFactory.newInstance().createMessage(null, inputStream);
            SOAPPart soapPart = message.getSOAPPart();
            SOAPEnvelope envelope = soapPart.getEnvelope();
            SOAPBody body = envelope.getBody();
            NodeList fatherTags = body.getElementsByTagNameNS("*", tipoProducto);
            for (int temp = 0; temp < fatherTags.getLength(); temp++) {
                Node node = (Node) fatherTags.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    if(eElement.getChildNodes().getLength()>numeroDeDatos) {
                        if(StringUtils.equalsIgnoreCase(tipoProducto, "lineaCredito") ||
                                (StringUtils.equalsIgnoreCase(tipoProducto, "tarjetaCredito") &&
                                        StringUtils.equalsIgnoreCase(eElement.getElementsByTagName("titularidad").item(0).getTextContent(), titularidad))) {
                            nodoTC = eElement;
                            break;
                        }
                    }
                }
            }

        } catch(SOAPException | IOException e ){
            e.printStackTrace();
        }
        if(nodoTC == null) {
            throw new FrontEndException("DP-001","Ocurrio un error inesperado al consultar los servicios");
        }
        return nodoTC;
    }

    public static String getValueTagXML(Element nodoProducto, String fatherTag, String childTag) throws Throwable {

        String getVar1=null;

        try {

            if(!Objects.isNull(nodoProducto)) {
//                getVar1 = nodoProducto.getElementsByTagName(fatherTag).item(0).getTextContent();
                NodeList sonTags = nodoProducto.getElementsByTagName(fatherTag);
                for (int temp = 0; temp < sonTags.getLength(); temp++) {
                    org.w3c.dom.Node node = sonTags.item(temp);
                    if (node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                        Element eElement = (Element) node;
                        getVar1 = eElement.getElementsByTagName(childTag).item(0).getTextContent();

                        break;
                    }
                }
            }

        }catch (Throwable e){
            e.getMessage();
        }

        if(getVar1 == null){
            throw new  FrontEndException("DP-001","Ocurrio un error inesperado al consultar los servicios");
        }
        return getVar1;
    }

    public static String getValueTagXML(String getResponse, String getFatherTag, String getSpecificTag) {

        String getVar1 = null;

        try {

            InputStream inputStream = new ByteArrayInputStream(getResponse.getBytes());

            SOAPMessage message = MessageFactory.newInstance().createMessage(null, inputStream);

            SOAPPart soapPart = message.getSOAPPart();

            SOAPEnvelope envelope = soapPart.getEnvelope();

            SOAPBody body = envelope.getBody();

            NodeList var1 = body.getElementsByTagNameNS("*", getFatherTag);

            for (int temp = 0; temp < var1.getLength(); temp++) {

                Node node = (Node) var1.item(temp);

                 if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) node;

                     getVar1  = eElement.getElementsByTagName(getSpecificTag).item(temp).getTextContent();

                     break;

                 }
            }
        } catch(SOAPException | IOException e){
                e.printStackTrace();
        }
        assert getVar1 != null;
        return getVar1;

    }

    public static String getValueTagXML(String getResponse, String getSpecificTag, int pos){

        String getVar1=null;

        try {

            InputStream inputStream = new ByteArrayInputStream(getResponse.getBytes());

            SOAPMessage message = MessageFactory.newInstance().createMessage(null, inputStream);

            SOAPPart soapPart = message.getSOAPPart();

            SOAPEnvelope envelope = soapPart.getEnvelope();

            SOAPBody body = envelope.getBody();

            NodeList var1 = body.getElementsByTagNameNS("*", getSpecificTag);

            getVar1 = var1.item(pos).getChildNodes().item(0).getNodeValue();

        }catch (Throwable e){
            e.getMessage();
        }

        assert getVar1 != null;
        return getVar1;
    }

    public static List<HashMap<String, String>> getValueTagsXML(String getResponse, String getSpecificTag, List<String> tags){
        List<HashMap<String, String>> mydata = null;
        try {

            InputStream inputStream = new ByteArrayInputStream(getResponse.getBytes());

            SOAPMessage message = MessageFactory.newInstance().createMessage(null, inputStream);

            SOAPPart soapPart = message.getSOAPPart();

            SOAPEnvelope envelope = soapPart.getEnvelope();

            SOAPBody body = envelope.getBody();
            
            mydata = new ArrayList<>();
            
            NodeList nList = body.getElementsByTagNameNS("*", getSpecificTag);
            for(int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = (Node) nList.item(temp);
                if(nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    HashMap<String, String> currentHash = new HashMap<>();
                    for ( String tag : tags) {
                        currentHash.put(tag, eElement.getElementsByTagName(tag).item(0).getTextContent());
                    }
                    mydata.add(currentHash);
                }
            }
        }catch (Throwable e){
            e.getMessage();
        }

        assert mydata != null;
        return mydata;
    }

    public static String detailResponse(String urlExcel, String requestExcel) throws Throwable {

        StringBuffer response = null;

        try {

            URL obj = new URL(urlExcel);

            long inicioDate = System.currentTimeMillis();

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("POST");

            con.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");

            con.setRequestProperty("Accept-Charset", "application/xml");

            con.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());

            wr.writeBytes(requestExcel);

            wr.flush();

            wr.close();

            int responseStatus = con.getResponseCode();

            if (responseStatus == 200) {

                System.out.println("[LOG] Servicio culminó con éxito"  + " ==> " + "Estado : " +responseStatus);

            } else {

                if (responseStatus == 500) {
                    System.out.println("[LOG] Error en el servicio"  + " ==> " + "Estado : " +responseStatus);
                }

                throw new FrontEndException("CR-001", "El servicio retornó estado: " + responseStatus);
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String inputLine;

            response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) { response.append(inputLine); }

            in.close();

            long finDate = System.currentTimeMillis();

            System.out.println("[LOG] Tiempo de respuesta: " + ((finDate - inicioDate) / 1000.0) + " segs");

        } catch (Throwable throwable) {
            if (response == null)  throw new ServiceException("Servicio No Disponible:  " + urlExcel , throwable);
            else throw new ServiceException( throwable);
        }
        return response.toString();
    }

}
