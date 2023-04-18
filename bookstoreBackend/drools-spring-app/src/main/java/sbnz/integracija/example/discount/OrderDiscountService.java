package sbnz.integracija.example.discount;

import demo.facts.OrderItem;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class OrderDiscountService {

    private static Logger log = LoggerFactory.getLogger(OrderDiscountService.class);

    @Autowired
    private KieContainer kieContainer;

    @Autowired
    public OrderDiscountService(KieContainer kieContainer) {
        log.info("Initialising a new example session.");
        this.kieContainer = kieContainer;
    }
    public OrderItem getDiscount(OrderItem orderItem) {
//        OrderDiscount orderDiscount = new OrderDiscount();
        KieSession kieSession = kieContainer.newKieSession();
//        kieSession.setGlobal("orderDiscount",  new OrderDiscount());
        kieSession.insert(orderItem);
        kieSession.fireAllRules();
        kieSession.dispose();
        return orderItem;
    }

//    public OrderItem getDiscount(OrderItem orderItem) {
//        KieSession kieSession = kieContainer.newKieSession();
//        kieSession.insert(orderItem);
//        kieSession.fireAllRules();
//        kieSession.dispose();
//        return orderItem;
//    }
}
