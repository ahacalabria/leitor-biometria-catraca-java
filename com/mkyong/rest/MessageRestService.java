package com.mkyong.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.topdata.EasyInner;
import com.topdata.easyInner.controller.OnlineController;
import com.topdata.easyInner.entity.Inner;
import com.topdata.easyInner.enumeradores.Enumeradores;
import com.topdata.easyInner.ui.JIFEasyInnerOnLine;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.lucene.queryParser.ParseException;

//http://localhost:8080/MERCURYFORMS/rest/
@Path("/")
public class MessageRestService {
    
    private Catraca catraca = Catraca.getInstance();
    private Biometria biometria = Biometria.getInstance();

    @Path("/config")
    @GET
    public Response config() throws IOException, InterruptedException{
//        this.catraca = new Catraca();
        this.catraca = Catraca.getInstance();
//        this.catraca.adicionarInner(1);
//        this.catraca.iniciar();
        return Response.ok("<h1>Servidor da Biometria: Configurado</h1>", MediaType.TEXT_HTML).build();
    }
    
    @Path("/addinner/{numinner}")
    @GET
    public Response addinner(@PathParam("numinner") int numinner) throws IOException, InterruptedException{
//        this.catraca = new Catraca();
//        this.catraca = new Catraca();
        this.catraca.adicionarInner(numinner);
//        this.catraca.iniciar();
        return Response.ok("<h1>Inner "+numinner+": Adicionado</h1>", MediaType.TEXT_HTML).build();
    }
    
    @Path("/iniciar")
    @GET
    public Response iniciar(){
        this.catraca.iniciar();
        return Response.ok("<h1>Comunicação com as catracas: Online</h1>", MediaType.TEXT_HTML).build();
    }
    
    @Path("/savebiometria")
    @GET
    public Response saveBiometria() throws IOException{
        Biometria b = new Biometria();
        String msg = b.getBiometriaDedoToString();
        return Response.ok(msg, MediaType.TEXT_PLAIN).build();
    }
    

    @Path("/entrar/{numinner}")
    @GET
    public Response entrar(@PathParam("numinner") int numinner) throws IOException {
        this.catraca.liberarCatraca(numinner);
        return Response.ok("<h1>Catraca liberada!</h1>", MediaType.TEXT_HTML).build();
    }
    
    @Path("/capturarbiometria")
    @GET
    public Response capturarbiometria() throws IOException {
        String biometria = "0";
        try {
             biometria=this.catraca.colherBiometria(1);
             Thread.sleep(5000);
             System.out.println("Biometria colhida: "+biometria);
             boolean res = this.buscarBiometria(biometria);
             System.out.println("Buscou: "+res);
             if(res != false) {
                 System.out.println("true");    
                 Thread.sleep(5000);
                 System.out.println("Liberar catraca: "+"1");
                 this.catraca.liberarCatraca(1);
             }else{
                System.out.println("false");    
             }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return Response.ok(biometria, MediaType.TEXT_HTML).build();
    }
    
    @Path("/stop")
    @GET
    public Response stop() throws IOException {
        this.catraca.parar();
        return Response.ok("<h1>Catraca desligada!</h1>", MediaType.TEXT_HTML).build();
    }
    @Path("/remove")
    @GET
    public Response remove() throws IOException {
        this.catraca.removerInner(1);
        return Response.ok("<h1>Catraca 001 removida!</h1>", MediaType.TEXT_HTML).build();
    }

    private boolean buscarBiometria(String biometria) {
        return this.biometria.searchBiometria(biometria);
    }
    
}
