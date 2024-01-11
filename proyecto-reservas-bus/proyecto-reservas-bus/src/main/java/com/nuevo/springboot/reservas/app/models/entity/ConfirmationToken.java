package com.nuevo.springboot.reservas.app.models.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "confirmation_token")
public class ConfirmationToken {
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name="token_id")
	    private long tokenid;

	    @Column(name="confirmation_token")
	    private String confirmationToken;

	    @Temporal(TemporalType.TIMESTAMP)
	    private Date createdDate;

	    @OneToOne(targetEntity = Usuario.class, fetch = FetchType.EAGER)
	    @JoinColumn(nullable = false, name = "user_id")
	    private Usuario user;

	    public ConfirmationToken(Usuario user) {
	        this.user = user;
	        createdDate = new Date();
	        confirmationToken = UUID.randomUUID().toString();
	    }

		public long getTokenid() {
			return tokenid;
		}

		public void setTokenid(long tokenid) {
			this.tokenid = tokenid;
		}

		public String getConfirmationToken() {
			return confirmationToken;
		}

		public void setConfirmationToken(String confirmationToken) {
			this.confirmationToken = confirmationToken;
		}

		public Date getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}

		public Usuario getUser() {
			return user;
		}

		public void setUser(Usuario user) {
			this.user = user;
		}

		public ConfirmationToken(long tokenid, String confirmationToken, Date createdDate, Usuario user) {
			super();
			this.tokenid = tokenid;
			this.confirmationToken = confirmationToken;
			this.createdDate = createdDate;
			this.user = user;
		}

		public ConfirmationToken(String confirmationToken, Date createdDate, Usuario user) {
			super();
			this.confirmationToken = confirmationToken;
			this.createdDate = createdDate;
			this.user = user;
		}

		public ConfirmationToken(long tokenid) {
			super();
			this.tokenid = tokenid;
		}

		public ConfirmationToken() {
			super();
		}
	    
	    
}
