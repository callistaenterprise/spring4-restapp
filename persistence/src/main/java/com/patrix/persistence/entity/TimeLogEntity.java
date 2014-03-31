package com.patrix.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

/**
 * Example time log entity.
 */
@Data
@Entity(name = "time_log")
public class TimeLogEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private String id;
	
	@Column(name = "title", nullable = false)
	private String title;
	
	@Column(name = "case_number", nullable = false)
	private String caseNumber;

	@Column(name = "work_code_id", nullable = false)
	private String workCodeId;
	
	@Column(name = "created_ts", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTimestamp;

    @PrePersist
    void onPrePerist() {
        setCreatedTimestamp(new Date());
    }
}