import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.StringReader;

public class LexerTests {
    @Test
    void testOperators() {
        try (StringReader reader = new StringReader("+-*/^ ( )")) {
            Lexer test = new Lexer(reader);
            assertEquals(Lexeme.LexemeType.PLUS,test.getLexeme().getType());
            assertEquals(Lexeme.LexemeType.MINUS,test.getLexeme().getType());
            assertEquals(Lexeme.LexemeType.MULT,test.getLexeme().getType());
            assertEquals(Lexeme.LexemeType.DEL,test.getLexeme().getType());
            assertEquals(Lexeme.LexemeType.POWER,test.getLexeme().getType());
            assertEquals(Lexeme.LexemeType.OPEN_BRACE,test.getLexeme().getType());
            assertEquals(Lexeme.LexemeType.CLOSE_BRACE,test.getLexeme().getType());
            assertEquals(Lexeme.LexemeType.EOF,test.getLexeme().getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testNumbers() {
        try (StringReader reader = new StringReader("3 5 9 ")) {
            Lexer test = new Lexer(reader);
            Lexeme result = test.getLexeme();
            assertEquals(Lexeme.LexemeType.NUMBER,result.getType());
            assertEquals("3",result.getText());
            result = test.getLexeme();
            assertEquals(Lexeme.LexemeType.NUMBER,result.getType());
            assertEquals("5",result.getText());
            result = test.getLexeme();
            assertEquals(Lexeme.LexemeType.NUMBER,result.getType());
            assertEquals("9",result.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
