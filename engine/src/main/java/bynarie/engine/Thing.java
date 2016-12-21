package bynarie.engine;

public class Thing {
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String text;

    public Thing(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Thing{" +
                "text='" + text + '\'' +
                '}';
    }
}
