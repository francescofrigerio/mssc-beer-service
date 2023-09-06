package guru.springframework.msscbeerservice.bootstrap;

import guru.sfg.brewery.model.BeerStyleEnum;
import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by jt on 2019-05-17.
 */
// LEZIONE 60
    // Ci sono diversi modi di metterlo
    // in esecuzione ma uno dei  modi più facili
    // e' quello di implementare l'interfaccia CommandLineRunner
    // e di annotarlo come component
    // Allo starup perchè è un component si spring
    // viene caricato e raccolto dal contesto di Spring
    // Inoltre Spring inietterà l'istanza del repository
    // dichiarato final nel costruttore che si potrebbe
    // anche scrivere in alternativa all'annotazione RequiredArgsConstructor
    // Poi ci verrà messo
    // in esecuzione il metodo loader tramite il metodo run
    // dell'interfaccia CommandLineRunner
@RequiredArgsConstructor
@Component
public class BeerLoader implements CommandLineRunner {

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";

    // Istanza del repository
    private final BeerRepository beerRepository;

    // Implemento il metodo run dell'interfaccia CommandLineRunner
    @Override
    public void run(String... args) throws Exception {

        // Se non ci sono dati li carico dal db
          if(beerRepository.count() == 0 ) {
              loadBeerObjects();
          }
    }

    private void loadBeerObjects() {

        // Specifico tutti i campi gestiti
        // da hibernate tramite il pattern builder
        Beer b1 = Beer.builder()
                .beerName("Mango Bobs")
                .beerStyle(BeerStyleEnum.IPA.name())
                .minOnHand(12)
                .quantityToBrew(200)
                .price(new BigDecimal("12.95"))
                .upc(BEER_1_UPC)
                .build();

        Beer b2 = Beer.builder()
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyleEnum.PALE_ALE.name())
                .minOnHand(12)
                .quantityToBrew(200)
                .price(new BigDecimal("12.95"))
                .upc(BEER_2_UPC)
                .build();

        Beer b3 = Beer.builder()
                .beerName("Pinball Porter")
                .beerStyle(BeerStyleEnum.PALE_ALE.name())
                .minOnHand(12)
                .quantityToBrew(200)
                .price(new BigDecimal("12.95"))
                .upc(BEER_3_UPC)
                .build();

        beerRepository.save(b1);
        beerRepository.save(b2);
        beerRepository.save(b3);

        System.out.println("[loadBeerObjects] count obj " + beerRepository.count());
    }
}
