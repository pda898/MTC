import java.io.*;

import static java.lang.Math.pow;

public class Parser {
    private Reader reader;
    private Lexer lexer;
    private Lexeme lexeme;

    public Parser(Reader reader) throws IOException {
        this.reader = reader;
        this.lexer = new Lexer(reader);
        this.lexeme = lexer.getLexeme();
    }

    public static void main(String args[]) {
        if (args.length == 0) {
           try (Reader reader = new InputStreamReader(System.in)) {
               System.out.println(new Parser(reader).calc());
           } catch (IOException e) {
               e.printStackTrace();
           } catch (ParserException e) {
               System.err.println(e.getMessage());
           }
        } else {
            try (Reader reader = new FileReader(args[0])) {
                System.out.println(new Parser(reader).calc());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParserException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public int calc() throws IOException, ParserException {
        if (lexeme.getType() == Lexeme.LexemeType.EOF) {
            throw new ParserException("Empty statement");
        }
        int tmp = parseExpr();
        if (lexeme.getType() != Lexeme.LexemeType.EOF) {
            throw new ParserException("Malformed formula");
        }
        return tmp;
    }

    private int parseExpr() throws IOException, ParserException {
        int tmp = parseTerm();
        while ((lexeme.getType() == Lexeme.LexemeType.PLUS) || (lexeme.getType() == Lexeme.LexemeType.MINUS)) {
            if (lexeme.getType() == Lexeme.LexemeType.PLUS) {
                lexeme = lexer.getLexeme();
                tmp += parseTerm();
            } else {
                lexeme = lexer.getLexeme();
                tmp -= parseTerm();
            }
        }
        return tmp;
    }

    private int parseTerm() throws IOException, ParserException {
        int tmp = parseFactor();
        while ((lexeme.getType() == Lexeme.LexemeType.MULT) || (lexeme.getType() == Lexeme.LexemeType.DEL)) {
            if (lexeme.getType() == Lexeme.LexemeType.MULT) {
                lexeme = lexer.getLexeme();
                tmp *= parseFactor();
            } else {
                lexeme = lexer.getLexeme();
                tmp /= parseFactor();
            }
        }
        return tmp;
    }

    private int parseFactor() throws IOException, ParserException {
        int tmp = parsePower();
        if (lexeme.getType() == Lexeme.LexemeType.POWER) {
            lexeme = lexer.getLexeme();
            tmp = (int) pow(tmp,parseFactor());
        }
        return tmp;
    }

    private int parsePower() throws IOException, ParserException {
        if (lexeme.getType() == Lexeme.LexemeType.MINUS) {
            lexeme = lexer.getLexeme();
            return (-1)*parseAtom();
        }
        return parseAtom();
    }

    private int parseAtom() throws IOException, ParserException {
        if (lexeme.getType() == Lexeme.LexemeType.NUMBER) {
            String number = lexeme.getText();
            lexeme = lexer.getLexeme();
            return Integer.parseInt(number);
        }
        if (lexeme.getType() == Lexeme.LexemeType.OPEN_BRACE) {
            lexeme = lexer.getLexeme();
            int tmp = parseExpr();
            if (lexeme.getType() != Lexeme.LexemeType.CLOSE_BRACE) {
                throw new ParserException("Close brace lost");
            }
        }
        throw new ParserException("Found wrong symbol");
    }
}
