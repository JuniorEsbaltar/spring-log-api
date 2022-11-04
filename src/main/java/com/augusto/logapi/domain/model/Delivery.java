package com.augusto.logapi.domain.model;

import com.augusto.logapi.domain.exception.DomainException;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<Occurrence> occurrenceList = new ArrayList<>();

    @Embedded
    private Recipient recipient;

    private BigDecimal fee;

    @Enumerated(EnumType.STRING)
    private StatusDelivery status;

    private OffsetDateTime dateOrder;

    private OffsetDateTime dateFinished;

    public Occurrence addOccurrence(String description) {
        Occurrence occurrence = new Occurrence();
        occurrence.setDescription(description);
        occurrence.setDateRegister(OffsetDateTime.now());
        occurrence.setDelivery(this);

        this.getOccurrenceList().add(occurrence);

        return occurrence;
    }

    public void finish() {
        if (!canBeFinished()) {
            throw new DomainException("Delivery can't be finish");
        }

        setStatus(StatusDelivery.FINISHED);
        setDateFinished(OffsetDateTime.now());
    }

    public boolean canBeFinished() {
        return StatusDelivery.PENDING.equals(getStatus());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Delivery delivery = (Delivery) o;
        return id != null && Objects.equals(id, delivery.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
