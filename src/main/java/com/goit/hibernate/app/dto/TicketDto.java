package com.goit.hibernate.app.dto;

import com.goit.hibernate.app.entity.Client;
import com.goit.hibernate.app.entity.Planet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class TicketDto {

    private Long id;
    private Timestamp createdAt;
    private Client client;
    private Planet planetFrom;
    private Planet planetTo;
}
