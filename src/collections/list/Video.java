package collections.list;

public class Video {

	private String title;
	private int numView;
	
	public Video (String title, int numView) {
		this.title = title;
		this.numView = numView;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getNumView() {
		return numView;
	}
	public void setNumView(int numView) {
		this.numView = numView;
	}
}
