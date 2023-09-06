package guru.springframework.msscbeerservice.repositories;

import guru.sfg.brewery.model.BeerStyleEnum;
import guru.springframework.msscbeerservice.domain.Beer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created by jt on 2019-05-17.
 */
// LEZIONE 59
    // Possiamo usare diverse implementazione per creare un repository Joa
    // A noi interessa l'ordinamento e l'impaginazione degli oggetti
    // PEr cui viene usata un Interfaccia che estende l'interfaccia JpaRepository
    // Se guardiamo la dichiarazione vediamo che a sua volta
    // estende PagingAndSortingRepository usata inizialmente da Thompson
    // Se osserviamo la dichiarazione di PagingAndSortingRepository
    // vediamo che estende a sua volta l'interfaccia CrudRepository
    // In alternativa si può usare direttamente l'interfaccia
    // CrudRepository senza nessun tipo di ordinamento o impaginazione
    // impostato dalle specifiche Jpa
    // Non abbiamo bisogno di aggiungere l'annotazione @Repository
    // perchè il tutto è già implementato nell'interfaccia
public interface BeerRepository extends JpaRepository<Beer, UUID> {
    // Permette di avere in ritorno l'istanza
    // di un oggetto Beer impaginabile . Il Paging deve essere passato
    // in input nei metodi esposti e questo è possibile perchè
    // abbiamo esteso la funzionalità dell'interfaccia CrudRepository
    //
    Page<Beer> findAllByBeerName(String beerName, Pageable pageable);

    Page<Beer> findAllByBeerStyle(BeerStyleEnum beerStyle, Pageable pageable);

    Page<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyleEnum beerStyle, Pageable pageable);

    Beer findByUpc(String upc);
}
