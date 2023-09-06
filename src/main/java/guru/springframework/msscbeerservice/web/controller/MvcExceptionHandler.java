package guru.springframework.msscbeerservice.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jt on 2019-05-25.
 */
// LEZIONE 71 AGGIUNGO Validatazione dei BEan e Gestione errori
@ControllerAdvice
public class MvcExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> validationErrorHandler(ConstraintViolationException ex){
        // INizializzo con la dimensione della lista di eccezzioni
        List<String> errorsList = new ArrayList<>(ex.getConstraintViolations().size());
        // Itero sulla lista di eccezzioni e aggiungo sulla lista
        // di ritornare in output
        ex.getConstraintViolations().forEach(error -> errorsList.add(error.toString()));
        // NOn esiste al momento un oggetto errore per cui
        // ritorno un generico ResponseEntity in uno stato errato 400
        // Quindi non c'è un qualcosa di formale attualmente
        // mentre nelle prossi versioni probabilmente ci sarà
        // qualcosa di formale per la gestione degli errori
        // durante la validazione degli oggetti
        return new ResponseEntity<>(errorsList, HttpStatus.BAD_REQUEST);
    }

}
