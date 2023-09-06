package guru.sfg.brewery.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

/**
 * Created by jt on 2019-05-12.
 */
    // vedere lo schema nella documentazione online
    // al path https://sfg-beer-works.github.io/brewery-api/#tag/Beer-Service
    // sotto il metodo getBeerById e lo schemma nella Response
    // tramite questo schema online della response
    // e' possibile creare l'oggetto Pojo
@Data // getters setters equals hashcode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto implements Serializable  {

    static final long serialVersionUID = -5815566940065181210L;

    // LEZIONE 70 Aggiungo dei constraints
    // che sono un codice difensivo rispetto
    // a qualche malintenzionato che volesse
    // disturbare la logica di business
    // Ci saranno proprieta di sola lettura sul client
    // e alcune proprieta inizializzate a null
    // quando facciamo la bind(costruzione)
    // dell'istanza della classe dentro java
    // e' richiesto che alcuni valori saranno null
    @Null
    private UUID id;

    @Null
    private Integer version;

    // The class include UTC Offeset
    @Null
    // LEZIONE 114 Aggiungo specifiche per json
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ", shape=JsonFormat.Shape.STRING)
    private OffsetDateTime createdDate;

    @Null
    // LEZIONE 114 Aggiungo specifiche per json
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ssZ", shape=JsonFormat.Shape.STRING)
    private OffsetDateTime lastModifiedDate;

    @NotBlank
    private String beerName;

    @NotNull
    private BeerStyleEnum beerStyle;

    // LEZIONE 70
    @NotNull
    //@Positive
    private String upc;

    // LEZIONE 114 Aggiungo specifiche per json
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Positive
    @NotNull
    private BigDecimal price;

    private Integer quantityOnHand;

}
