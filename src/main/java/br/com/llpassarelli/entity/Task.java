package br.com.llpassarelli.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Model class for Tasks
 */
@Entity
@Table(name="tasks")
public class Task implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Task() {
		 
	};
	
	public Task( String prioridade, String status, String data) {
		
		this.status = status;
		this.prioridade = prioridade;
		this.data = data;
 
	};
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
 
	private String prioridade, data, status;
 
	public Long getId() {
		return id;
	}
 
	public void setId(Long id) {
		this.id = id;
	}
 
	public String getPrioridade() {
		return prioridade;
	}
 
	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}
	
	public String getStatus() {
		return status;
	}
 
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getData() {
		return data;
	}
 
	public void setData(String data) {
		this.data = data;
	}
    //Define Getter and Setter for variables here.
}