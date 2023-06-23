package com.goit.hibernate.app.mapper;

import com.goit.hibernate.app.dto.TicketDto;
import com.goit.hibernate.app.entity.Ticket;
import lombok.NoArgsConstructor;

import static java.util.Objects.isNull;

@NoArgsConstructor(staticName = "instance")
public final class TicketDtoMapper implements UniversalMapper<Ticket, TicketDto> {
    @Override
    public TicketDto mapEntityToDto(Ticket source) throws RuntimeException {
        if (isNull(source)) {
            return null;
        }
        TicketDto ticketDto = new TicketDto();
        ticketDto.setId(source.getId());
        ticketDto.setCreatedAt(source.getCreatedAt());
        ticketDto.setClient(source.getClient());
        ticketDto.setPlanetFrom(source.getPlanetFrom());
        ticketDto.setPlanetTo(source.getPlanetTo());
        return ticketDto;
    }

    @Override
    public Ticket mapDtoToEntity(TicketDto source) throws RuntimeException {
        if (isNull(source)) {
            return null;
        }
        Ticket ticket = new Ticket();
        ticket.setId(source.getId());
        ticket.setCreatedAt(source.getCreatedAt());
        ticket.setClient(source.getClient());
        ticket.setPlanetFrom(source.getPlanetFrom());
        ticket.setPlanetTo(source.getPlanetTo());

        return ticket;
    }


/*
    @Override
    public List<Client> map(List<ClientDto> source) throws RuntimeException {
        return source.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
*/

}
