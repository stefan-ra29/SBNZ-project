package sbnz.integracija.example.rate;

import demo.facts.Rate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path="api/rate")
public class RateController {

    private final RateService service;

    public RateController(RateService service) {
        this.service = service;
    }

    @PostMapping("/rateBook")
    public ResponseEntity<Object> rateBook(@RequestBody Rate rate) throws Exception {

        for (Rate oneRate : service.getAll()) {
            if(oneRate.getBookId() == rate.getBookId() && oneRate.getUserId() == rate.getUserId()){
//                throw new Exception("You have already rated this book");
                String errorMessage = "You have already rated this book"; // Set your custom error message here
                return new ResponseEntity<>("You have already rated this book", HttpStatus.BAD_REQUEST);
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
            }
            else{
                if (rate.getRate() > 0 || rate.getRate() <= 5) {
                    service.rateBook(rate);
//                    return ResponseEntity.status(HttpStatus.OK).body("Book rated");
                } else{
//                    throw new Exception("The rate must be a number between 1 and 5");
                    String errorMessage = "The rate must be a number between 1 and 5"; // Set your custom error message here
                    return new ResponseEntity<>("The rate must be a number between 1 and 5", HttpStatus.BAD_REQUEST);
//                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
                }
            }
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
//        return ResponseEntity.status(HttpStatus.OK).body("Book rated");

//        System.out.println(rate);
    }
}
