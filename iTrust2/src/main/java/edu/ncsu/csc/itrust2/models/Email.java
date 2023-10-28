package edu.ncsu.csc.itrust2.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "email")
public class Email extends DomainObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull @Setter private String sender;

    @NotNull @Setter
    @ManyToOne
    @JoinColumn(name = "receiver", columnDefinition = "varchar(100)")
    private User receiver;

    @NotNull @Setter private String subject;

    @NotNull @Setter private String messageBody;

    public Email(String sender, User receiver, String subject, String messageBody) {
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.messageBody = messageBody;
    }
}
