package com.payAm.core.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Payam Mostafaei
 * Creation Time: 2017/Jan/12 - 09:30 PM
 */
public class FileUtil {

	public static byte[] readBytes(InputStream inputStream) throws IOException {
		return IOUtils.toByteArray(inputStream);
	}

	public static byte[] readBytes(String filePath) throws IOException {
		InputStream inputStream = null;
		try {
			checkFileExistsAndCreateIfNecessary(filePath);
			File file = new File(filePath);
			inputStream = new FileInputStream(file);

			long length = file.length();

			if (length > Integer.MAX_VALUE) {
				closeQuietly(inputStream);
				throw new IOException("File Is Very Large To Load");
			}

			byte[] bytes = new byte[(int) length];

			int offset = 0;
			int numRead = inputStream.read(bytes, offset, bytes.length - offset);
			while (offset < bytes.length && numRead >= 0) {
				offset += numRead;
				numRead = inputStream.read(bytes, offset, bytes.length - offset);
			}

			if (offset < bytes.length) {
				closeQuietly(inputStream);
				throw new IOException("Could not completely read file " + file.getName());
			}
			return bytes;
		} finally {
			closeQuietly(inputStream);
		}
	}

	public static String readText(String filePath) throws IOException {
		return new String(readBytes(filePath), "utf-8");
	}

	public static void writeBytes(String filePath, byte[] content) throws IOException {
		FileOutputStream fileOutputStream = null;
		try {
			checkFileExistsAndCreateIfNecessary(filePath);
			fileOutputStream = new FileOutputStream(new File(filePath));
			fileOutputStream.write(content);
		} finally {
			closeQuietly(fileOutputStream);
		}
	}

	public static void writeText(String filePath, String content) throws IOException {
		writeBytes(filePath, content.getBytes("utf-8"));
	}

	public static void writeBytes(String filePath, InputStream inputStream) throws IOException {
		writeBytes(filePath, readBytes(inputStream));
	}

	public static boolean checkFileExistsAndCreateIfNecessary(String filePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			try {
				File configsDir = file.getParentFile();
				if (!configsDir.exists()) {
					configsDir.mkdirs();
				}
				file.createNewFile();
			} catch (IOException e) {
			}
			return false;
		} else {
			return true;
		}
	}

	public static void writeObject(Object inputObject, String outputFilePath) {
		FileOutputStream fileOutputStream = null;
		ObjectOutputStream objectOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(new File(outputFilePath));
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(inputObject);
		} catch (Exception e) {
		} finally {
			closeQuietly(objectOutputStream);
			closeQuietly(fileOutputStream);
		}
	}

	public static Object readObject(String inputFilePath) {
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		try {
			fileInputStream = new FileInputStream(new File(inputFilePath));
			objectInputStream = new ObjectInputStream(fileInputStream);
			return objectInputStream.readObject();
		} catch (Exception e) {
			return null;
		} finally {
			closeQuietly(objectInputStream);
			closeQuietly(fileInputStream);
		}
	}

	public static List<String> readLines(String filePath) {
		if (!StringUtil.hasText(filePath)) {
			return new ArrayList<String>();
		}
		return readLines(new File(filePath));
	}

	public static List<String> readLines(File file) {
		List<String> allLines = new ArrayList<String>();
		if (file != null) {
			FileInputStream fileInputStream = null;
			InputStreamReader inputStreamReader = null;
			BufferedReader bufferedReader = null;
			try {
				if (file.exists()) {
					fileInputStream = new FileInputStream(file);
					inputStreamReader = new InputStreamReader(fileInputStream);
					bufferedReader = new BufferedReader(inputStreamReader);
					String line = bufferedReader.readLine();
					while (line != null) {
						allLines.add(convertIfUnicode(line));
						line = bufferedReader.readLine();
					}
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			} finally {
				closeQuietly(bufferedReader);
				closeQuietly(inputStreamReader);
				closeQuietly(fileInputStream);
			}
		}
		return allLines;
	}

	public static String convertIfUnicode(String inputString) {
		char[] in = inputString.toCharArray();
		int off = 0;
		int len = in.length;
		int newLen = len * 2;
		if (newLen < 0) {
			newLen = Integer.MAX_VALUE;
		}
		char[] convtBuf = new char[newLen];
		char aChar;
		char[] out = convtBuf;
		int outLen = 0;
		int end = off + len;

		while (off < end) {
			aChar = in[off++];
			if (aChar == '\\') {
				aChar = in[off++];
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = in[off++];
						switch (aChar) {
							case '0':
							case '1':
							case '2':
							case '3':
							case '4':
							case '5':
							case '6':
							case '7':
							case '8':
							case '9':
								value = (value << 4) + aChar - '0';
								break;
							case 'a':
							case 'b':
							case 'c':
							case 'd':
							case 'e':
							case 'f':
								value = (value << 4) + 10 + aChar - 'a';
								break;
							case 'A':
							case 'B':
							case 'C':
							case 'D':
							case 'E':
							case 'F':
								value = (value << 4) + 10 + aChar - 'A';
								break;
							default:
								throw new IllegalArgumentException("Malformed \\uxxxx encoding.");
						}
					}
					out[outLen++] = (char) value;
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f')
						aChar = '\f';
					out[outLen++] = aChar;
				}
			} else {
				out[outLen++] = aChar;
			}
		}
		return new String(out, 0, outLen);
	}

	public static void writeLinesToFile(String filePath, List<String> lines, boolean append) {
		if (!StringUtil.hasText(filePath)) {
			return;
		}
		writeLinesToFile(new File(filePath), lines, append);
	}

	public static void writeLinesToFile(File file, List<String> lines, boolean append) {
		if (file == null || lines == null || lines.size() == 0) {
			return;
		}
		FileOutputStream fileOutputStream = null;
		OutputStreamWriter outputStreamWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			if (file.exists() && !append) {
				deleteFiles(file.getAbsolutePath());
			}
			FileUtils.forceMkdir(file.getParentFile());
			if (!file.exists() && !file.createNewFile()) {
				throw new Exception("Can not create file " + file.getAbsolutePath());
			}
			fileOutputStream = new FileOutputStream(file, append);
			outputStreamWriter = new OutputStreamWriter(fileOutputStream);
			bufferedWriter = new BufferedWriter(outputStreamWriter);
			boolean addNewLine = append && file.length() > 0;
			for (String line : lines) {
				if (addNewLine) {
					bufferedWriter.newLine();
				} else {
					addNewLine = true;
				}
				bufferedWriter.write(line);
			}
			bufferedWriter.flush();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			closeQuietly(bufferedWriter);
			closeQuietly(outputStreamWriter);
			closeQuietly(fileOutputStream);
		}
	}

	public static void deleteFiles(String... filePaths) {
		if (filePaths != null) {
			for (String filePath : filePaths) {
				File file = new File(filePath);
				FileUtils.deleteQuietly(file);
			}
		}
	}

	public static void closeQuietly(Closeable closeable) {
		if (closeable != null) {
			if (closeable instanceof Flushable) {
				try {
					((Flushable) closeable).flush();
				} catch (IOException e) {
					// Do nothing.
				}
			}

			try {
				closeable.close();
			} catch (IOException e) {
				// Do nothing.
			}
		}
	}

}
