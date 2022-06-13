package collections.list;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class LinkedSort {
	
	public static void main(String[] args) {
		List<Video> listVideo = new LinkedList<Video>();
			listVideo.add(new Video("liveShow", 511000));
			listVideo.add(new Video("GameShow", 2000));
			listVideo.add(new Video("Phim dai tap", 1034500));
			listVideo.add(new Video("Phim ngan tap", 100000));
			listVideo.add(new Video("Ban tin", 4235235));
		for(Video video : listVideo) {
			System.out.println(video.getTitle()+ " || " + video.getNumView());
		}
			
		listVideo.sort(new Comparator<Video>() {
			
			@Override
			public int compare(Video o1, Video o2) {
				
				return o1.getNumView() - o2.getNumView();
			}
			
		});
		System.out.println("#############");
		for(Video video : listVideo) {
			System.out.println(video.getTitle()+ " || " + video.getNumView());
		}
	}

}
