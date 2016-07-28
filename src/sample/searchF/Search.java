package sample.searchF;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Search {

	public Search() {}

	private Pattern p = null;
	private Matcher m = null;
	private long totalLength = 0;
	private long filesNumber;

	public List getFind(String PATH, String mask) throws Exception {
		return find(PATH, mask);
	}

	public long getTotalLength() {
		return totalLength;
	}

	public long getFilesNumber() {
		return filesNumber;
	}

	public boolean getFileExist(String PATH) {
		return fileExist(PATH);
	}

	public boolean getPathExist(String PATH, boolean create) {
		return pathExist(PATH, create);
	}

	public long[] getFilesDate(List da0files) {
		return filesDate(da0files);
	}

	public boolean getNewMusic(String[] str, String[] str1, int i, int i1) {
		return newMusic(str, str1, i, i1);
	}

	private boolean accept(String name) {
		m = p.matcher(name);
		if (m.matches()) return true;
		else return false;
	}

	private List find(String startPATH, String mask) throws Exception {
		File directory = new File(startPATH);
		if (!directory.exists()) throw new Exception("PATH not exist!");
		p = Pattern.compile(mask, Pattern.CASE_INSENSITIVE);
		filesNumber = 0;
		totalLength = 0;
		ArrayList res = new ArrayList();
		searchFiles(directory, res);
		return res;
	}

	private void searchFiles(File directory, List res) {
		File[] list = directory.listFiles();
			for (int i = 0; i < list.length; i++) {
				if (list[i].isFile()) {
					if (accept(list[i].getName())) {
						filesNumber++;
						totalLength += list[i].length();
						res.add(list[i]);
					} else {
						searchFiles(list[i], res);
					}
				}
			}
	}

	private boolean fileExist(String PATH) {
		File path = new File(PATH);
		if (path.exists()) 
			if (path.isFile()) return true;
		return false;
	}

	private boolean pathExist(String PATH, boolean create) {
		File dir = new File(PATH);
		if (dir.exists()) if (dir.isDirectory()) return true;
		if (create) {
			dir.mkdirs();
			return false;
		}
		return false;
	}

	private long[] filesDate(List da0files) {
		long[] dates = new long[da0files.size()];
		for (int i = 0; i < da0files.size(); i++) 
			dates[i] = new File(da0files.get(i).toString()).lastModified();
		return dates;
	}

	private boolean newMusic(String[] str, String[] str1, int i, int i1) {
		return true;
	}
}
