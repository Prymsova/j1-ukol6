package cz.czechitas.ukol06.svatky;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SvatkySluzbaTest {

    @Test
    void vyhledatSvatkyKeDni() {
        SvatkySluzba svatkySluzba = new SvatkySluzba();
        assertNotNull(svatkySluzba);

        // test - správně vyhodnocený svátek jednoho jména daný den
        List<String> svatekIvona = new ArrayList<>();
        svatekIvona.add("Ivona");
        assertEquals(svatekIvona, svatkySluzba.vyhledatSvatkyKeDni(MonthDay.of(3, 23)));
        assertNotEquals(svatekIvona, svatkySluzba.vyhledatSvatkyKeDni(MonthDay.of(12, 24)));

        // test - správně vyhodnocený svátek dvou jmen daný den
        List<String> svatekPetrPavel = new ArrayList<>();
        svatekPetrPavel.add("Petr");
        svatekPetrPavel.add("Pavel");
        assertEquals(svatekPetrPavel, svatkySluzba.vyhledatSvatkyKeDni(MonthDay.of(6, 29)));
        assertNotEquals(svatekPetrPavel, svatkySluzba.vyhledatSvatkyKeDni(MonthDay.of(12, 24)));

        // test - správně vyhodnoceno, že nemá svátek žádné jméno v daný den
        List<String> svatekNikdo = new ArrayList<>();
        assertEquals(svatekNikdo, svatkySluzba.vyhledatSvatkyKeDni(MonthDay.of(1, 1)));
    }
}
