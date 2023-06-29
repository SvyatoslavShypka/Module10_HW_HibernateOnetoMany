package com.goit.hibernate.app.entity;

import jakarta.persistence.*;
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
        @JoinColumn(name = "from_planet_id", nullable = false)
        private Planet planetFrom;

        @OneToOne
        @JoinColumn(name = "to_planet_id", nullable = false)
        private Planet planetTo;
}
