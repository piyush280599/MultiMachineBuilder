/**
 * 
 */
package mmb.engine.files;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.jar.JarInputStream;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.IOUtils;

import mmb.NN;

/**
 * @author oskar
 *
 */
public interface AdvancedFile {
	/**
	 * Open an input stream to given advanced file
	 * @return input stream
	 * @throws IOException if file can't be retrieved
	 */
	@NN InputStream getInputStream() throws IOException;
	
	/**
	 * Open an output stream to given advanced file
	 * @return output stream
	 * @throws IOException if file can't be retrieved or is read-only
	 */
	@NN OutputStream getOutputStream() throws IOException;
	
	/**
	 * @return file equivalent {@code File} object
	 * Converts the advanced file to local file (in case of online file downloads it)
	 * @throws IOException if the file is not local
	 */
	File asFile() throws IOException;
	
	/**
	 * Gets string path of file
	 * @return path
	 */
	@NN String name();
	
	/**
	 * Gets URL of the file
	 * @return URL
	 * @throws MalformedURLException if path is incorrect
	 */
	URL url() throws MalformedURLException;
		
	/**
	 * Gets parent path of the file
	 * @return parent
	 */
	AdvancedFile parent();
	
	/**
	 * Gets children of this advanced file. Not supported for online files.
	 * @return children files
	 * @throws Exception if retrieval fails
	 */
	AdvancedFile[] children() throws Exception;
	
	/**
	 * @return newly created JAR stream
	 * @throws IOException if file fails to open
	 */
	default JarInputStream asJAR() throws IOException {
		return new JarInputStream(getInputStream());
	}
	/**
	 * @return newly created ZIP stream
	 * @throws IOException if file fails to open
	 */
	default ZipInputStream asZIP() throws IOException {
		return new ZipInputStream(getInputStream());
	}
	
	/**
	 * Get base name and extension of given filename
	 * @param data path
	 * @return base, extension
	 */
	static String[] baseExtension(String data) {
		int i = data.lastIndexOf('.');
		if(i < 1) return new String[] {data, ""};
		String ext = data.substring(i+1);
		String base = data.substring(0, i);
		return new String[] {base, ext};
	}
	
	/**
	 * 
	 * @param data path
	 * @return parent directory,base,extension
	 */
	static String[] dirBaseExtension(String data) {
		int i = data.lastIndexOf('.'); // '.'
		int j = data.lastIndexOf('/'); // '/'
		int k = data.lastIndexOf('\\'); // '\'
		if(j == -1) j = k;
		if(k<j && k != -1) j=k;
		if(j == -1) { //no slashes
			String ext = data.substring(i+1);
			String base = data.substring(0, i);
			return new String[] {"", base, ext}; 
		}else if(i == -1) {
			return new String[] {"", data, ""};
		}else {
			String dir = data.substring(0, j-1);
			String ext = data.substring(i+1);
			String base = data.substring(j+1, i);
			return new String[] {dir, base, ext};
		}
	}
	
	/**
	 * Get directory and name of given file
	 * @param data path
	 * @return base, extension
	 */
	static String[] dirName(String data) {
		int j = data.lastIndexOf('/'); // '/'
		int k = data.lastIndexOf('\\'); // '\'
		if(j == -1) j = k;
		if(k<j && k != -1) j=k;
		if(j == -1) return new String[] {data, ""};
		String ext = data.substring(j+1);
		String dir = data.substring(0, j);
		return new String[] {dir, ext};
	}

	/**
	 * @return does the file exist?
	 * Always returns true for web files
	 */
	boolean exists();
	
	default void copyTo(AdvancedFile file) throws IOException {
		try(OutputStream os = file.getOutputStream()){
			copyTo(os);
		}
	}
	default void copyTo(OutputStream stream) throws IOException {
		try(InputStream is = getInputStream()){
			IOUtils.copy(is, stream);
			stream.flush();
			stream.close();
			is.close();
		}
	}
	
	public void create() throws IOException;

	/**
	 * @return is given file object a directory?
	 */
	boolean isDirectory();
	
}
