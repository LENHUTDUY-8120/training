package JavaIO;

import java.io.File;
import java.io.FilenameFilter;

public class ExtFilter implements FilenameFilter{

	private String ext;
	
	public ExtFilter (String ext) {
		this.ext = ext;
	}
	
	@Override
	public boolean accept(File dir, String name) {
		return name.endsWith(ext);
	}

	
}
