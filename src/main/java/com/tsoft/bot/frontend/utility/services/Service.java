package com.tsoft.bot.frontend.utility.services;

import com.tsoft.bot.frontend.utility.services.https_petitions.HttpPetition;
import com.tsoft.bot.frontend.utility.services.https_petitions.HttpPetitionFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class Service {
    private final String typePetition;
    private HttpPetition httpPetition;
    private final DataService dataService;
    private HttpResponse response;


    public Service(String typePetition, DataService dataService) {
        this.typePetition = typePetition;
        this.dataService = dataService;
    }

    public Service create() {
        httpPetition =  HttpPetitionFactory.getPetition(typePetition);
        httpPetition.configurePetition(dataService);
        return this;
    }

    public HttpResponse build() throws Throwable {
        try{
            long inicioDate = System.currentTimeMillis();
            HttpClient client = HttpClientBuilder.create().build();
            response = httpPetition.execute(client);
            long finDate = System.currentTimeMillis();
            System.out.println("[LOG] Tiempo de respuesta: "+((finDate -inicioDate)/1000.0)+" segs");
            return response;
        }catch (Throwable throwable){
            System.out.println("[ERROR] : Servicio No disponible "+throwable.getCause());
            System.out.println("[ERROR] : Servicio No disponible "+throwable.getMessage());
            throw throwable;
        }
    }
}
