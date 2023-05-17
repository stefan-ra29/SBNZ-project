package sbnz.integracija.example.rate;

import demo.facts.Rate;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path="api/rate")
public class RateController {

    private final RateService service;

    public RateController(RateService service) {
        this.service = service;
    }

    @PostMapping("/rateBook")
    public void rateBook(@RequestBody Rate rate) {

        service.rateBook(rate);

        System.out.println(rate);
    }
}
