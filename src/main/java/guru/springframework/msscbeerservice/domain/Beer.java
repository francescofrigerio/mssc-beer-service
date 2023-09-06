package guru.springframework.msscbeerservice.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by jt on 2019-05-17.
 */
// LEZIONE 58
    // CREAZIONE PKG DOMAIN E NUOVE CLASSI
    // UTIIZZANDO LA LIB JPA E LOMBOK E IL PATTERN BUILDER
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// ANNOTO COME ENTITY HIBERNATE
// PERCHE' HIBERNATE E' UN IMPLEMENTAZIONE DELLE SPECIFICHE JPA api
@Entity
public class Beer {
    // ANNOTO COME IDENTIFICATORE ENTITY IPA
    // ANNOTAZIONE JPA COME VALORE UUID GENERATO
    // AUTOMATICAMENTE TRAMITE HIBERNATE
    // UTILIZZO QUI UN LOCK DI TIPO OTTIMISTICO
    @Id
    @GeneratedValue(generator = "UUID")
    // SETTO IL GENERATORE STESSO CON NOME E STRATEGIA SPECIFICANDO
    // CHE VOGLIO USARE HIBERNATE E COME TIPO UN UUID DI HIBERNATE
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="org.hibernate.type.UUIDCharType")
    // SPECIFICO COME DEVE ESSERE IL CAMPO DEL DB
    // CHE DEVE STORICIZZARE IL DATO
    // QUESTI ATTRIBUTI SPECIFICANO QUALE DATO
    // IN AUTOMATICO DEVE ESSERE GENERATO DA HIBERNATE
    // QUINDI VARCHAR LUNGO 36 NON MODIFICABIE E NON PUO' ESSERE NULL
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID id;

    // HIBERNATE PROPERTIES
    @Version
    private Long version;

    // USO UN TIMESTAMP PERCHE'
    // UTILIZZEREMO UN DB SQL
    // ATTUALMENTE E' UN ESTENSIONE HIBERNATE DI JPA
    // PERCHE' BISOGNA RICORDARE CHE HIBERNATE
    // E' UN IMPLEMENTAZIONE DELLE SPECIFICHE JPA api
    // Specifico quindi che non puo' essere modificato
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    // USO UN TIMESTAMP PERCHE'
    // UTILIZZEREMO UN DB SQL
    @UpdateTimestamp
    private Timestamp lastModifiedDate;

    private String beerName;
    private String beerStyle;

    // Non puo' essere duplicato nel mio DB
    // Hibernate nel creare il db aggiungera'
    // al campo una chiave univoca
    @Column(unique = true)
    private String upc;

    private BigDecimal price;

    // Attributi per tracciare gli oggetti della classe
    // traccia la qta a portata di mano
    private Integer minOnHand;
    // traccia la qta in preparazione
    private Integer quantityToBrew;


}
