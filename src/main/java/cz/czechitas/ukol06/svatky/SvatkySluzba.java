package cz.czechitas.ukol06.svatky;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.file.Path;
import java.rmi.ServerError;
import java.time.MonthDay;
import java.util.List;
import java.util.stream.Stream;

public class SvatkySluzba {

    private final ObjectMapper objectMapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();
    private final Path cestaKDatum = Path.of("data/svatky.json");
    private final SeznamSvatku seznamSvatku;

    public SvatkySluzba() {
        // TODO načíst seznam svátků ze souboru svatky.json
        try {
            seznamSvatku = objectMapper.readValue(cestaKDatum.toFile(), SeznamSvatku.class);
        } catch (StreamReadException e) {
            System.err.println("StreamReadException in readValue - seznam svátků.");
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            System.err.println("DatabindException in readValue - seznam svátků.");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.err.println("IOException in readValue - seznam svátků.");
            throw new RuntimeException(e);
        }
    }

    public List<String> vyhledatSvatkyDnes() {
        return vyhledatSvatkyKeDni(MonthDay.now());
    }

    public List<String> vyhledatSvatkyKeDni(MonthDay day) {
        List<Svatek> svatky = seznamSvatku.getSvatky();
        return svatky
                .stream() // převedení na Stream<Svatek>
                .filter(svatek -> svatek.getDen().equals(day)) // vyfiltrovány jen svátky daného dne z parametru funkce
                .map(Svatek::getJmeno) // získání jmen
                .toList();
    }
}
