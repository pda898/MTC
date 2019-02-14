import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTests {
    @Test
    void simpleTests(){
        try (Reader reader = new StringReader("2+2")) {
            Parser parser = new Parser(reader);
            assertEquals(4,parser.calc());
        } catch (IOException | ParserException e) {
            e.printStackTrace();
        }
        try (Reader reader = new StringReader("2-2")) {
            Parser parser = new Parser(reader);
            assertEquals(0,parser.calc());
        } catch (IOException | ParserException e) {
            e.printStackTrace();
        }
        try (Reader reader = new StringReader("2*2")) {
            Parser parser = new Parser(reader);
            assertEquals(4,parser.calc());
        } catch (IOException | ParserException e) {
            e.printStackTrace();
        }
        try (Reader reader = new StringReader("2/2")) {
            Parser parser = new Parser(reader);
            assertEquals(1,parser.calc());
        } catch (IOException | ParserException e) {
            e.printStackTrace();
        }
        try (Reader reader = new StringReader("2^2")) {
            Parser parser = new Parser(reader);
            assertEquals(4,parser.calc());
        } catch (IOException | ParserException e) {
            e.printStackTrace();
        }
        try (Reader reader = new StringReader("-2")) {
            Parser parser = new Parser(reader);
            assertEquals(-2,parser.calc());
        } catch (IOException | ParserException e) {
            e.printStackTrace();
        }
    }

    @Test
    void complexTests() {
        try (Reader reader = new StringReader("-2+2*2^2/2")) {
            Parser parser = new Parser(reader);
            assertEquals(2,parser.calc());
        } catch (IOException | ParserException e) {
            e.printStackTrace();
        }
    }
}
