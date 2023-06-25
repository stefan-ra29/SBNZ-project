package ruletests;


import demo.facts.User;
import demo.facts.transactions.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class FraudDetectionsTests {


    private KieSession kieSession;

    @Before
    public void start() {
        kieSession = kieContainer().newKieSession();
    }

    @Test
    public void furtherThan150kmAnd30MinutesFromLastOne() {
        Transaction transaction = Transaction.builder()
                .amount(15)
                .locationLongitude(getMyLongitude())
                .locationLatitude(getMyLatitude())
                .sender(userWithLastTransactionMoreThan150kms())
                .dateTime(LocalDateTime.now())
                .build();
        kieSession.insert(transaction);
        kieSession.getAgenda().getAgendaGroup("fraudDetection").setFocus();
        kieSession.fireAllRules();
        assertTrue(transaction.isFraudulentWarning());
        assertSame("Transaction could be fraudulent because it is within 30 minutes from the last one and" +
                " further than 150km from it", transaction.getFraudulentWarningMessage());
    }

    @Test
    public void userHasAllTransactionsMoreThan200kmsAway() {
        Transaction transaction = Transaction.builder()
                .amount(15)
                .locationLongitude(getMyLongitude())
                .locationLatitude(getMyLatitude())
                .sender(userWithTransactionsMoreThan200kms())
                .dateTime(LocalDateTime.now())
                .build();
        kieSession.insert(transaction);
        kieSession.getAgenda().getAgendaGroup("fraudDetection").setFocus();
        kieSession.fireAllRules();
        assertTrue(transaction.isFraudulentWarning());
        assertSame("Transaction could be fraudulent because it is more than 200km away from all" +
                " previous transactions", transaction.getFraudulentWarningMessage());
    }

    @Test
    public void userHasThirdTransactionIn10Minutes() {
        Transaction transaction = Transaction.builder()
                .amount(15)
                .locationLongitude(getMyLongitude())
                .locationLatitude(getMyLatitude())
                .sender(userWithThirdTransactionIn10Minutes())
                .dateTime(LocalDateTime.now())
                .build();
        kieSession.insert(transaction);
        kieSession.getAgenda().getAgendaGroup("fraudDetection").setFocus();
        kieSession.fireAllRules();
        assertTrue(transaction.isFraudulentWarning());
        assertSame("Transaction could be fraudulent because it is the " +
                "third in the last 10 minutes", transaction.getFraudulentWarningMessage());
    }

    @Test
    public void transactionIs4TimesLargerThanUsers4LastAverage() {
        Transaction transaction = Transaction.builder()
                .amount(15000)
                .locationLongitude(getMyLongitude())
                .locationLatitude(getMyLatitude())
                .sender(userWithAmount4TimesLarger())
                .dateTime(LocalDateTime.now())
                .build();
        kieSession.insert(transaction);
        kieSession.getAgenda().getAgendaGroup("fraudDetection").setFocus();
        kieSession.fireAllRules();
        assertTrue(transaction.isFraudulentWarning());
        assertSame("Transaction could be fraudulent because the amount is " +
                "much higher than average", transaction.getFraudulentWarningMessage());
    }

    @Test
    public void transactionIsOver1000AndBetween1AmAnd5Am() {
        Transaction transaction = Transaction.builder()
                .amount(1500)
                .locationLongitude(getMyLongitude())
                .locationLatitude(getMyLatitude())
                .sender(regularUser())
                .dateTime(LocalDateTime.of(LocalDate.now(), LocalTime.parse("03:00:00")))
                .build();
        kieSession.insert(transaction);
        kieSession.getAgenda().getAgendaGroup("fraudDetection").setFocus();
        kieSession.fireAllRules();
        assertTrue(transaction.isFraudulentWarning());
        assertSame("Transaction could be fraudulent because it is" +
                " between 1AM and 5AM with the amount over 1000", transaction.getFraudulentWarningMessage());
    }

    @Test
    public void regularTransaction() {
        Transaction transaction = Transaction.builder()
                .amount(1500)
                .locationLongitude(getMyLongitude())
                .locationLatitude(getMyLatitude())
                .sender(regularUser())
                .dateTime(LocalDateTime.now())
                .build();
        kieSession.insert(transaction);
        kieSession.getAgenda().getAgendaGroup("fraudDetection").setFocus();
        kieSession.fireAllRules();
        assertFalse(transaction.isFraudulentWarning());
        assertNull(transaction.getFraudulentWarningMessage());
    }

    private User userWithLastTransactionMoreThan150kms() {
        return User.builder()
                .outboundTransactions(List.of(Transaction.builder()
                        .dateTime(LocalDateTime.now().minusMinutes(10))
                        .locationLatitude(getLatitude150kms())
                        .locationLongitude(getLongitude150kms())
                        .build()))
                .build();
    }

    private User userWithTransactionsMoreThan200kms() {
        return User.builder()
                .outboundTransactions(List.of(Transaction.builder()
                                .dateTime(LocalDateTime.now().minusDays(10))
                                .locationLatitude(getLatitude200kms())
                                .locationLongitude(getLongitude200kms())
                                .build(),
                        Transaction.builder()
                                .dateTime(LocalDateTime.now().minusDays(11))
                                .locationLatitude(getLatitude200kms())
                                .locationLongitude(getLongitude200kms())
                                .build()
                ))
                .build();
    }

    private User userWithThirdTransactionIn10Minutes() {
        return User.builder()
                .outboundTransactions(List.of(Transaction.builder()
                                .dateTime(LocalDateTime.now().minusMinutes(2))
                                .locationLatitude(getMyLatitude())
                                .locationLongitude(getMyLongitude())
                                .build(),
                        Transaction.builder()
                                .dateTime(LocalDateTime.now().minusMinutes(4))
                                .locationLatitude(getMyLatitude())
                                .locationLongitude(getMyLongitude())
                                .build()
                ))
                .build();
    }

    public User userWithAmount4TimesLarger() {
        return User.builder()
                .outboundTransactions(List.of(Transaction.builder()
                                .amount(15)
                                .dateTime(LocalDateTime.now().minusDays(2))
                                .locationLatitude(getMyLatitude())
                                .locationLongitude(getMyLongitude())
                                .build(),
                        Transaction.builder()
                                .dateTime(LocalDateTime.now().minusDays(4))
                                .amount(20)
                                .locationLatitude(getMyLatitude())
                                .locationLongitude(getMyLongitude())
                                .build(), Transaction.builder()
                                        .dateTime(LocalDateTime.now().minusDays(5))
                                        .amount(50)
                                        .locationLatitude(getMyLatitude())
                                        .locationLongitude(getMyLongitude())
                                        .build(),
                                Transaction.builder()
                                        .dateTime(LocalDateTime.now().minusDays(6))
                                        .amount(100)
                                        .locationLatitude(getMyLatitude())
                                        .locationLongitude(getMyLongitude())
                                        .build()
                        )).build();
    }

    private User regularUser() {
        return User.builder()
                .outboundTransactions(List.of(Transaction.builder()
                                .amount(500)
                                .dateTime(LocalDateTime.now().minusDays(2))
                                .locationLatitude(getMyLatitude())
                                .locationLongitude(getMyLongitude())
                                .build(),
                        Transaction.builder()
                                .dateTime(LocalDateTime.now().minusDays(6))
                                .amount(700)
                                .locationLatitude(getMyLatitude())
                                .locationLongitude(getMyLongitude())
                                .build()
                )).build();
    }

    public KieContainer kieContainer() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks
                .newKieContainer(ks.newReleaseId("sbnz.integracija", "drools-spring-kjar", "0.0.1-SNAPSHOT"));
        KieScanner kScanner = ks.newKieScanner(kContainer);
        kScanner.start(10_000);
        return kContainer;
    }

    private double getMyLongitude() {
        return 44.8721627;
    }

    private double getMyLatitude() {
        return 17.6649336;
    }

    private double getLongitude150kms() {
        return 45.0084415;
    }

    private double getLatitude150kms() {
        return 19.8113035;
    }

    private double getLongitude200kms() {
        return 47.8478953;
    }

    private double getLatitude200kms() {
        return 35.1318742;
    }
}
