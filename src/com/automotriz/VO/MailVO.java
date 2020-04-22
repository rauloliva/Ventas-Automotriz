package com.automotriz.VO;

public class MailVO {
    private String destinatario;
    private String ausnto;
    private String mensaje;
    private Object attachFile;
    
    public void setDestinatario(String destinatario){
        this.destinatario = destinatario;
    }
    
    public String getDestinatario(){
        return this.destinatario;
    }
    
    public void setAsunto(String asunto){
        this.ausnto = asunto;
    }
    
    public String getAsunto(){
        return this.ausnto;
    }
    
    public void setMensaje(String mensaje){
        this.mensaje = mensaje;
    }
    
    public String getMensaje(){
        return this.mensaje;
    }
    
    public void setAttachFile(Object attacObject){
        this.attachFile = attacObject;
    }
    
    public Object getAttachFile(){
        return this.attachFile;
    }
}
