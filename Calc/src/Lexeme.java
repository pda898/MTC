public class Lexeme {
    enum LexemeType {
        PLUS,MINUS,MULT,DEL,POWER,NUMBER,OPEN_BRACE,CLOSE_BRACE,EOF
    }
    private LexemeType type;
    private String text;

    public Lexeme(LexemeType type, String text) {
        this.type = type;
        this.text = text;
    }

    public LexemeType getType() {
        return type;
    }

    public void setType(LexemeType type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
