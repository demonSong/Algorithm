package com.daimens.algorithm.may;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.AbstractDocument.Content;

import org.omg.DynamicAny.NameDynAnyPairSeqHelper;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

/**
 * 
 * @author DemonSong
 * 
 *         588.Design an in-memory file system to simulate the following
 *         functions:
 * 
 *         ls: Given a path in string format. If it is a file path, return a
 *         list that only contains this file's name. If it is a directory path,
 *         return the list of file and directory names in this directory. Your
 *         output (file and directory names together) should in lexicographic
 *         order.
 * 
 *         mkdir: Given a directory path that does not exist, you should make a
 *         new directory according to the path. If the middle directories in the
 *         path don't exist either, you should create them as well. This
 *         function has void return type.
 * 
 *         addContentToFile: Given a file path and file content in string
 *         format. If the file doesn't exist, you need to create that file
 *         containing given content. If the file already exists, you need to
 *         append given content to original content. This function has void
 *         return type.
 * 
 *         readContentFromFile: Given a file path, return its content in string
 *         format.
 * 
 *         Example: Input:
 *         ["FileSystem","ls","mkdir","addContentToFile","ls","readContentFromFile"]
 *         [[],["/"],["/a/b/c"],["/a/b/c/d","hello"],["/"],["/a/b/c/d"]] Output:
 *         [null,[],null,null,["a"],"hello"] Explanation: filesystem Note: You
 *         can assume all file or directory paths are absolute paths which begin
 *         with / and do not end with / except that the path is just "/". You
 *         can assume that all operations will be passed valid parameters and
 *         users will not attempt to retrieve file content or list a directory
 *         or file that does not exist. You can assume that all directory names
 *         and file names only contain lower-case letters, and same names won't
 *         exist in the same directory.
 *
 */
public class FileSystem {

	private class File {
		char type;
		Map<String, File> files;
		StringBuilder content;
		// 初始化文件 d表示文件夹，f表示文件
		public File(char type) {
			this.type = type;
			if (type == 'd') {
				files = new HashMap<>();
			}
			if (type == 'f') {
				content = new StringBuilder();
			}
		}
	}

	File root;

	public FileSystem() {
		root = new File('d');
	}
	
	public List<String> ls(String path) {
		String[] names = path.split("/");
		File cur = root;
		for (int i = 0; i < names.length; i++){
			String name = names[i];
			if(name.isEmpty()) continue;
			cur = cur.files.get(name);
		}
		
		List<String> dirs = new ArrayList<>();
//		if (cur.files == null){
//			if (cur.content != null){
//				return Collections.singletonList(cur.content.toString());
//			}
//			return dirs;
//		}
		
		if (cur.type == 'f'){
			return Collections.singletonList(names[names.length-1]);
		}
		
		for (String files : cur.files.keySet()){
			dirs.add(files);
		}
		Collections.sort(dirs);
        return dirs;
    }
	
	public void mkdir(String path) {
		String[] names = path.split("/");
		File cur = root;
		for (int i = 0; i < names.length; i++){
			String name = names[i];
			if (name.isEmpty()) continue;
			if (!cur.files.containsKey(name)){ //当前文件夹不存在
				cur.files.put(name, new File('d'));
			}
			cur = cur.files.get(name);
		}
	}
	
	public void addContentToFile(String filePath, String content) {
        String[] names = filePath.split("/");
        File cur = root;
        for (int i = 0; i < names.length; i++){
        	String name = names[i];
        	if (name.isEmpty()) continue;
        	if (i == names.length - 1 && !cur.files.containsKey(name)){ //这些文件已经存在了
        		cur.files.put(name, new File('f'));
        	}
        	cur = cur.files.get(name);
        }
        cur.content.append(content);
    }
	
	public String readContentFromFile(String filePath) {
		String[] names = filePath.split("/");
        File cur = root;
        for (int i = 0; i < names.length; i++){
        	String name = names[i];
        	if(name.isEmpty()) continue;
        	if (i == names.length - 1 && !cur.files.containsKey(name)){ //这些文件已经存在了
        		cur.files.put(name, new File('f'));
        	}
        	cur = cur.files.get(name);
        }
        return cur.content.toString();
    }

	public static void main(String[] args) {
		FileSystem fileSystem = new FileSystem();
        fileSystem.mkdir("/goowmfn");
        System.out.println(fileSystem.ls("/"));
        System.out.println(fileSystem.ls("/goowmfn"));
        fileSystem.mkdir("/z");
        System.out.println(fileSystem.ls("/"));
        System.out.println(fileSystem.ls("/"));
        fileSystem.addContentToFile("/goowmfn/c", "shetopcy");
        System.out.println(fileSystem.ls("/goowmfn/c"));
	}
}
