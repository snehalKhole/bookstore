package com.bookstore.bookstore.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.io.Serializable;
import java.util.Date;
/**
 * @author SnehalKhole
 * @version $Id: BaseEntity.java $$
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {
	public interface Columns {
		String ID = "id";
		String CREATED = "created_at";
		String UPDATED = "updated_at";
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = Columns.ID, nullable=false)
	private Long id;
	@Column(name = Columns.CREATED, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW()", updatable=false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Column(name = Columns.UPDATED, columnDefinition = "TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW()")
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

}