package dependency.breaking;

public class FakeDisplay implements IDisplay {

    private String lastLine = "";
    
    @Override
    public void showLine(String line) {
	this.lastLine = line;
    }
    
    public String getLastLine() {
	return lastLine;
    }

}
