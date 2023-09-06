package guru.springframework.msscbeerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.openfeign.EnableFeignClients;

// LEZIONE 86
// Fino alla lezione 85 SU MapStruct
// Vanno in errore i test andrebbe rifatto il fork
// creando un nuovo branch con il
// sorgente della lezione 85
// oppure guardare il progetto della sezione rest-docs
// che è molto simile e i test non vanno in errore 
//@EnableFeignClients
@SpringBootApplication
public class MsscBeerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsscBeerServiceApplication.class, args);
    }

}
