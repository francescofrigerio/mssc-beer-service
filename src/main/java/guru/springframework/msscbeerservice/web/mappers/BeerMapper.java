package guru.springframework.msscbeerservice.web.mappers;

import guru.sfg.brewery.model.BeerDto;
import guru.springframework.msscbeerservice.domain.Beer;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

/**
 * Created by jt on 2019-05-25.
 */
// LEZIONE 83
// LE GRAFE SONO OPZIONALI NEL CASO DELLA GRAFFA
    // SONO MAPPATI SIA I CAMPI CREATED E UPDATE DATE
    // SIA IL CAMPO BeerStyle da Stringa a Enum
@Mapper(uses = {DateMapper.class})
// @DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);

    BeerDto beerToBeerDtoWithInventory(Beer beer);

    Beer beerDtoToBeer(BeerDto dto);
}
