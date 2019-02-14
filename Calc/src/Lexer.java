import java.io.IOException;
import java.io.Reader;

public class Lexer {
    private int current;
    private Reader reader;

    public Lexer(Reader reader) throws IOException {
        this.reader = reader;
        this.current = reader.read();
    }

    public Lexeme getLexeme() throws IOException {
        Lexeme tmp;
        while (Character.isSpaceChar(current)) {
            current = reader.read();
        }
        switch (current) {
            case -1:
                tmp = new Lexeme(Lexeme.LexemeType.EOF, "");
                break;
            case '+':
                tmp = new Lexeme(Lexeme.LexemeType.PLUS, "+");
                current = reader.read();
                break;
            case '-':
                tmp = new Lexeme(Lexeme.LexemeType.MINUS, "-");
                current = reader.read();
                break;
            case '*':
                tmp = new Lexeme(Lexeme.LexemeType.MULT, "*");
                current = reader.read();
                break;
            case '/':
                tmp = new Lexeme(Lexeme.LexemeType.DEL,"/");
                current = reader.read();
                break;
            case '^':
                tmp = new Lexeme(Lexeme.LexemeType.POWER,"^");
                current = reader.read();
                break;
            case '(':
                tmp = new Lexeme(Lexeme.LexemeType.OPEN_BRACE,"(");
                current = reader.read();
                break;
            case ')':
                tmp = new Lexeme(Lexeme.LexemeType.CLOSE_BRACE, ")");
                current = reader.read();
                break;
            case '0': case'1':case'2':case'3':case'4':case'5':case'6':case'7':case'8': case'9':
                StringBuilder number = new StringBuilder();
                while (Character.isDigit(current)) {
                    number.append((char)current);
                    current = reader.read();
                }
                tmp = new Lexeme(Lexeme.LexemeType.NUMBER,number.toString());
                break;
                default:
                throw new IOException("Wrong character");
        }
        return tmp;
    }
}
