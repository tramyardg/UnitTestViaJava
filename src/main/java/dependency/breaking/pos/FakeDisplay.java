package dependency.breaking.pos;

public class FakeDisplay implements IDisplay {

    private String lastLine = "Milk $3.99";

    @Override
    public void showLine(String line) {
        this.lastLine = line;
    }

    public String getLastLine() {
        return lastLine;
    }

}
