package controller.util;

import java.util.ArrayList;
import java.util.List;

import controller.upload.FileInfo;

public class FileUtil {

	public static FileInfo getFile(String extension, List<FileInfo> fileList) {
		FileInfo info = null;
		for (int i = 0; i < fileList.size(); i++) {
			String extn = fileList
					.get(i)
					.getFileName()
					.substring(
							fileList.get(i).getFileName().lastIndexOf(".") + 1,
							fileList.get(i).getFileName().length());
			if (extn.equals(extension)) {
				info = fileList.get(i);
			}
		}
		return info;
	}
}
