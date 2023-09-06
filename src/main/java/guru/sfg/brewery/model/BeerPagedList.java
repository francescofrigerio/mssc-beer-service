package guru.sfg.brewery.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jt on 2019-05-12.
 */
// LEZIONE 114 Aggiungo Bind Paged Jobect
// Oggetto lista impaginabile di Spring
    // L'oggetto costruito dovrebbe
    // essere riferito ad un altro servizio
public class BeerPagedList extends PageImpl<BeerDto> implements Serializable {

    static final long serialVersionUID = 1114715135625836949L;

    //@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    @JsonCreator
    public BeerPagedList(@JsonProperty("content") List<BeerDto> content,
                         @JsonProperty("number") int number,
                         @JsonProperty("size") int size,
                         @JsonProperty("totalElements") Long totalElements,
                         @JsonProperty("pageable") JsonNode pageable,
                         @JsonProperty("last") boolean last,
                         @JsonProperty("totalPages") int totalPages,
                         @JsonProperty("sort") JsonNode sort,
                         @JsonProperty("first") boolean first,
                         @JsonProperty("numberOfElements") int numberOfElements) {

        // Sta creando un complesso json Object
        // tramite l'annotazione JsonCreator
        // Nel caso dobbiamo convertire un Json Object -> Pojo
        // dobbiamo utilizzare questa tecnica
        // Quando si cerca di costruire un oggetto Json
        // da uno Spring framework page Object come questa classe
        //
        super(content, PageRequest.of(number, size), totalElements);
    }

    public BeerPagedList(List<BeerDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public BeerPagedList(List<BeerDto> content) {
        super(content);
    }
}


