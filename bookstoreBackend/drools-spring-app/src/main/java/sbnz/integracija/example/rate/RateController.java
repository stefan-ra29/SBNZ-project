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
    public ResponseEntity<Object> rateBook(@RequestBody RateDto rateDto) throws Exception {

        for (Rate oneRate : service.getAll()) {
            if(oneRate.getBook().getId() == rateDto.getBookId() && oneRate.getUser().getId() == rateDto.getUserId()){
                String errorMessage = "You have already rated this book"; // Set your custom error message here
                return new ResponseEntity<>("You have already rated this book", HttpStatus.BAD_REQUEST);
            }
            else{
                if (rateDto.getRate() > 0 || rateDto.getRate() <= 5) {
                    service.rateBook(rateDto);
                } else{
                    String errorMessage = "The rate must be a number between 1 and 5"; // Set your custom error message here
                    return new ResponseEntity<>("The rate must be a number between 1 and 5", HttpStatus.BAD_REQUEST);
                }
            }
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
