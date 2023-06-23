package com.goit.hibernate.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.MapsId;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
@Getter
@Setter
@Data
@Entity
@Table(name = "ticket")
public class Ticket {

        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "created_at")
        private Timestamp createdAt;

        @ManyToOne
        @JoinColumn(name = "client_id", nullable = false)
        private Client client;

        @OneToOne
        @MapsId
        @JoinColumn(name = "planet_id")
        private Planet planetFrom;

        @OneToOne
        @MapsId
        @JoinColumn(name = "planet_id")
        private Planet planetTo;
}
