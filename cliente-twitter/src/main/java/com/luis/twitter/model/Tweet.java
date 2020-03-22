package com.luis.twitter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "TWEET")
@DynamicUpdate
public class Tweet {
	
    private Long id;
    private String usuario;
    private String texto;
    private String localizacion;
    private Boolean validado;

    public Tweet() {

    }

    public Tweet(Long id, String usuario, String texto, String localizacion, Boolean validado) {
        super();
        this.id = id;
        this.usuario = usuario;
        this.texto = texto;
        this.localizacion = localizacion;
        this.validado = validado;
    }
     
    
    @Id
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    @Column
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /*
    Se establece a VARCHAR(1000) el tamaño de la columna porque, en las pruebas,
    el texto de algún tweet superaba el VARCHAR(255)
    */ 
    @Column(length = 1000)
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Column
    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    @Column
    public Boolean getValidado() {
		return validado;
	}

	public void setValidado(Boolean validado) {
		this.validado = validado;
	}

	@Override
    public String toString() {
        return "Tweet [id=" + id + ", usuario=" + usuario + ", texto=" + texto + ", localizacion=" + localizacion + ", validado=" + validado + "]";
    }
}
