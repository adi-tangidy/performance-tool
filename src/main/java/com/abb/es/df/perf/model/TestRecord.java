package com.abb.es.df.perf.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(schema = "datafabric_perf", name = "records")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Timestamp createdTimestamp;
    private Timestamp lastModifiedTimestamp;

    @PreUpdate
    public void updatedTimestamp(){
        lastModifiedTimestamp = new Timestamp(System.currentTimeMillis());
    }

    @PrePersist
    public void createdTimestamp(){
        createdTimestamp = new Timestamp(System.currentTimeMillis());
        lastModifiedTimestamp = createdTimestamp;
    }
}
