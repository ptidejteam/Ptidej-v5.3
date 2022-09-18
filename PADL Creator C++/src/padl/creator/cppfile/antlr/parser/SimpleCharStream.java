/*******************************************************************************
 * Copyright (c) 2001-2014 Yann-Ga�l Gu�h�neuc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Yann-Ga�l Gu�h�neuc and others, see in file; API and its implementation
 ******************************************************************************/
/* Generated By:JavaCC: Do not edit this line. SimpleCharStream.java Version 4.0 */
package padl.creator.cppfile.antlr.parser;

/**
 * An implementation of interface CharStream, where the stream is assumed to
 * contain only ASCII characters (without unicode processing).
 */

public class SimpleCharStream {
	public static final boolean staticFlag = true;
	static int bufsize;
	static int available;
	static int tokenBegin;
	static public int bufpos = -1;
	static protected int bufline[];
	static protected int bufcolumn[];

	static protected int column = 0;
	static protected int line = 1;

	static protected boolean prevCharIsCR = false;
	static protected boolean prevCharIsLF = false;

	static protected java.io.Reader inputStream;

	static protected char[] buffer;
	static protected int maxNextCharInd = 0;
	static protected int inBuf = 0;
	static protected int tabSize = 8;

	/**
	 * Method to adjust line and column numbers for the start of a token.
	 */
	static public void adjustBeginLineColumn(int newLine, final int newCol) {
		int start = SimpleCharStream.tokenBegin;
		int len;

		if (SimpleCharStream.bufpos >= SimpleCharStream.tokenBegin) {
			len =
				SimpleCharStream.bufpos - SimpleCharStream.tokenBegin
						+ SimpleCharStream.inBuf + 1;
		}
		else {
			len =
				SimpleCharStream.bufsize - SimpleCharStream.tokenBegin
						+ SimpleCharStream.bufpos + 1 + SimpleCharStream.inBuf;
		}

		int i = 0, j = 0, k = 0;
		int nextColDiff = 0, columnDiff = 0;

		while (i < len
				&& SimpleCharStream.bufline[j =
					start % SimpleCharStream.bufsize] == SimpleCharStream.bufline[k =
					++start % SimpleCharStream.bufsize]) {
			SimpleCharStream.bufline[j] = newLine;
			nextColDiff =
				columnDiff + SimpleCharStream.bufcolumn[k]
						- SimpleCharStream.bufcolumn[j];
			SimpleCharStream.bufcolumn[j] = newCol + columnDiff;
			columnDiff = nextColDiff;
			i++;
		}

		if (i < len) {
			SimpleCharStream.bufline[j] = newLine++;
			SimpleCharStream.bufcolumn[j] = newCol + columnDiff;

			while (i++ < len) {
				if (SimpleCharStream.bufline[j =
					start % SimpleCharStream.bufsize] != SimpleCharStream.bufline[++start
						% SimpleCharStream.bufsize]) {
					SimpleCharStream.bufline[j] = newLine++;
				}
				else {
					SimpleCharStream.bufline[j] = newLine;
				}
			}
		}

		SimpleCharStream.line = SimpleCharStream.bufline[j];
		SimpleCharStream.column = SimpleCharStream.bufcolumn[j];
	}
	static public void backup(final int amount) {

		SimpleCharStream.inBuf += amount;
		if ((SimpleCharStream.bufpos -= amount) < 0) {
			SimpleCharStream.bufpos += SimpleCharStream.bufsize;
		}
	}

	static public char BeginToken() throws java.io.IOException {
		SimpleCharStream.tokenBegin = -1;
		final char c = SimpleCharStream.readChar();
		SimpleCharStream.tokenBegin = SimpleCharStream.bufpos;

		return c;
	}

	static public void Done() {
		SimpleCharStream.buffer = null;
		SimpleCharStream.bufline = null;
		SimpleCharStream.bufcolumn = null;
	}

	static protected void ExpandBuff(final boolean wrapAround) {
		final char[] newbuffer = new char[SimpleCharStream.bufsize + 2048];
		final int newbufline[] = new int[SimpleCharStream.bufsize + 2048];
		final int newbufcolumn[] = new int[SimpleCharStream.bufsize + 2048];

		try {
			if (wrapAround) {
				System.arraycopy(
					SimpleCharStream.buffer,
					SimpleCharStream.tokenBegin,
					newbuffer,
					0,
					SimpleCharStream.bufsize - SimpleCharStream.tokenBegin);
				System.arraycopy(
					SimpleCharStream.buffer,
					0,
					newbuffer,
					SimpleCharStream.bufsize - SimpleCharStream.tokenBegin,
					SimpleCharStream.bufpos);
				SimpleCharStream.buffer = newbuffer;

				System.arraycopy(
					SimpleCharStream.bufline,
					SimpleCharStream.tokenBegin,
					newbufline,
					0,
					SimpleCharStream.bufsize - SimpleCharStream.tokenBegin);
				System.arraycopy(
					SimpleCharStream.bufline,
					0,
					newbufline,
					SimpleCharStream.bufsize - SimpleCharStream.tokenBegin,
					SimpleCharStream.bufpos);
				SimpleCharStream.bufline = newbufline;

				System.arraycopy(
					SimpleCharStream.bufcolumn,
					SimpleCharStream.tokenBegin,
					newbufcolumn,
					0,
					SimpleCharStream.bufsize - SimpleCharStream.tokenBegin);
				System.arraycopy(
					SimpleCharStream.bufcolumn,
					0,
					newbufcolumn,
					SimpleCharStream.bufsize - SimpleCharStream.tokenBegin,
					SimpleCharStream.bufpos);
				SimpleCharStream.bufcolumn = newbufcolumn;

				SimpleCharStream.maxNextCharInd =
					SimpleCharStream.bufpos +=
						SimpleCharStream.bufsize - SimpleCharStream.tokenBegin;
			}
			else {
				System.arraycopy(
					SimpleCharStream.buffer,
					SimpleCharStream.tokenBegin,
					newbuffer,
					0,
					SimpleCharStream.bufsize - SimpleCharStream.tokenBegin);
				SimpleCharStream.buffer = newbuffer;

				System.arraycopy(
					SimpleCharStream.bufline,
					SimpleCharStream.tokenBegin,
					newbufline,
					0,
					SimpleCharStream.bufsize - SimpleCharStream.tokenBegin);
				SimpleCharStream.bufline = newbufline;

				System.arraycopy(
					SimpleCharStream.bufcolumn,
					SimpleCharStream.tokenBegin,
					newbufcolumn,
					0,
					SimpleCharStream.bufsize - SimpleCharStream.tokenBegin);
				SimpleCharStream.bufcolumn = newbufcolumn;

				SimpleCharStream.maxNextCharInd =
					SimpleCharStream.bufpos -= SimpleCharStream.tokenBegin;
			}
		}
		catch (final Throwable t) {
			throw new Error(t.getMessage());
		}

		SimpleCharStream.bufsize += 2048;
		SimpleCharStream.available = SimpleCharStream.bufsize;
		SimpleCharStream.tokenBegin = 0;
	}

	static protected void FillBuff() throws java.io.IOException {
		if (SimpleCharStream.maxNextCharInd == SimpleCharStream.available) {
			if (SimpleCharStream.available == SimpleCharStream.bufsize) {
				if (SimpleCharStream.tokenBegin > 2048) {
					SimpleCharStream.bufpos =
						SimpleCharStream.maxNextCharInd = 0;
					SimpleCharStream.available = SimpleCharStream.tokenBegin;
				}
				else if (SimpleCharStream.tokenBegin < 0) {
					SimpleCharStream.bufpos =
						SimpleCharStream.maxNextCharInd = 0;
				}
				else {
					SimpleCharStream.ExpandBuff(false);
				}
			}
			else if (SimpleCharStream.available > SimpleCharStream.tokenBegin) {
				SimpleCharStream.available = SimpleCharStream.bufsize;
			}
			else if (SimpleCharStream.tokenBegin - SimpleCharStream.available < 2048) {
				SimpleCharStream.ExpandBuff(true);
			}
			else {
				SimpleCharStream.available = SimpleCharStream.tokenBegin;
			}
		}

		int i;
		try {
			if ((i =
				SimpleCharStream.inputStream.read(
					SimpleCharStream.buffer,
					SimpleCharStream.maxNextCharInd,
					SimpleCharStream.available
							- SimpleCharStream.maxNextCharInd)) == -1) {
				SimpleCharStream.inputStream.close();
				throw new java.io.IOException();
			}
			else {
				SimpleCharStream.maxNextCharInd += i;
			}
			return;
		}
		catch (final java.io.IOException e) {
			--SimpleCharStream.bufpos;
			SimpleCharStream.backup(0);
			if (SimpleCharStream.tokenBegin == -1) {
				SimpleCharStream.tokenBegin = SimpleCharStream.bufpos;
			}
			throw e;
		}
	}

	static public int getBeginColumn() {
		return SimpleCharStream.bufcolumn[SimpleCharStream.tokenBegin];
	}

	static public int getBeginLine() {
		return SimpleCharStream.bufline[SimpleCharStream.tokenBegin];
	}

	/**
	 * @deprecated 
	 * @see #getEndColumn
	 */

	static public int getColumn() {
		return SimpleCharStream.bufcolumn[SimpleCharStream.bufpos];
	}

	static public int getEndColumn() {
		return SimpleCharStream.bufcolumn[SimpleCharStream.bufpos];
	}

	static public int getEndLine() {
		return SimpleCharStream.bufline[SimpleCharStream.bufpos];
	}

	static public String GetImage() {
		if (SimpleCharStream.bufpos >= SimpleCharStream.tokenBegin) {
			return new String(
				SimpleCharStream.buffer,
				SimpleCharStream.tokenBegin,
				SimpleCharStream.bufpos - SimpleCharStream.tokenBegin + 1);
		}
		else {
			return new String(
				SimpleCharStream.buffer,
				SimpleCharStream.tokenBegin,
				SimpleCharStream.bufsize - SimpleCharStream.tokenBegin)
					+ new String(
						SimpleCharStream.buffer,
						0,
						SimpleCharStream.bufpos + 1);
		}
	}

	/**
	 * @deprecated 
	 * @see #getEndLine
	 */

	static public int getLine() {
		return SimpleCharStream.bufline[SimpleCharStream.bufpos];
	}

	static public char[] GetSuffix(final int len) {
		final char[] ret = new char[len];

		if (SimpleCharStream.bufpos + 1 >= len) {
			System.arraycopy(SimpleCharStream.buffer, SimpleCharStream.bufpos
					- len + 1, ret, 0, len);
		}
		else {
			System.arraycopy(SimpleCharStream.buffer, SimpleCharStream.bufsize
					- (len - SimpleCharStream.bufpos - 1), ret, 0, len
					- SimpleCharStream.bufpos - 1);
			System.arraycopy(SimpleCharStream.buffer, 0, ret, len
					- SimpleCharStream.bufpos - 1, SimpleCharStream.bufpos + 1);
		}

		return ret;
	}

	static protected int getTabSize(final int i) {
		return SimpleCharStream.tabSize;
	}

	static public char readChar() throws java.io.IOException {
		if (SimpleCharStream.inBuf > 0) {
			--SimpleCharStream.inBuf;

			if (++SimpleCharStream.bufpos == SimpleCharStream.bufsize) {
				SimpleCharStream.bufpos = 0;
			}

			return SimpleCharStream.buffer[SimpleCharStream.bufpos];
		}

		if (++SimpleCharStream.bufpos >= SimpleCharStream.maxNextCharInd) {
			SimpleCharStream.FillBuff();
		}

		final char c = SimpleCharStream.buffer[SimpleCharStream.bufpos];

		SimpleCharStream.UpdateLineColumn(c);
		return c;
	}

	static protected void setTabSize(final int i) {
		SimpleCharStream.tabSize = i;
	}
	static protected void UpdateLineColumn(final char c) {
		SimpleCharStream.column++;

		if (SimpleCharStream.prevCharIsLF) {
			SimpleCharStream.prevCharIsLF = false;
			SimpleCharStream.line += SimpleCharStream.column = 1;
		}
		else if (SimpleCharStream.prevCharIsCR) {
			SimpleCharStream.prevCharIsCR = false;
			if (c == '\n') {
				SimpleCharStream.prevCharIsLF = true;
			}
			else {
				SimpleCharStream.line += SimpleCharStream.column = 1;
			}
		}

		switch (c) {
			case '\r' :
				SimpleCharStream.prevCharIsCR = true;
				break;
			case '\n' :
				SimpleCharStream.prevCharIsLF = true;
				break;
			case '\t' :
				SimpleCharStream.column--;
				SimpleCharStream.column +=
					SimpleCharStream.tabSize - SimpleCharStream.column
							% SimpleCharStream.tabSize;
				break;
			default :
				break;
		}

		SimpleCharStream.bufline[SimpleCharStream.bufpos] =
			SimpleCharStream.line;
		SimpleCharStream.bufcolumn[SimpleCharStream.bufpos] =
			SimpleCharStream.column;
	}

	public SimpleCharStream(final java.io.InputStream dstream) {
		this(dstream, 1, 1, 4096);
	}

	public SimpleCharStream(
		final java.io.InputStream dstream,
		final int startline,
		final int startcolumn) {
		this(dstream, startline, startcolumn, 4096);
	}
	public SimpleCharStream(
		final java.io.InputStream dstream,
		final int startline,
		final int startcolumn,
		final int buffersize) {
		this(
			new java.io.InputStreamReader(dstream),
			startline,
			startcolumn,
			buffersize);
	}

	public SimpleCharStream(
		final java.io.InputStream dstream,
		final String encoding) throws java.io.UnsupportedEncodingException {
		this(dstream, encoding, 1, 1, 4096);
	}

	public SimpleCharStream(
		final java.io.InputStream dstream,
		final String encoding,
		final int startline,
		final int startcolumn) throws java.io.UnsupportedEncodingException {
		this(dstream, encoding, startline, startcolumn, 4096);
	}

	public SimpleCharStream(
		final java.io.InputStream dstream,
		final String encoding,
		final int startline,
		final int startcolumn,
		final int buffersize) throws java.io.UnsupportedEncodingException {
		this(
			encoding == null ? new java.io.InputStreamReader(dstream)
					: new java.io.InputStreamReader(dstream, encoding),
			startline,
			startcolumn,
			buffersize);
	}

	public SimpleCharStream(final java.io.Reader dstream) {
		this(dstream, 1, 1, 4096);
	}

	public SimpleCharStream(
		final java.io.Reader dstream,
		final int startline,
		final int startcolumn) {
		this(dstream, startline, startcolumn, 4096);
	}

	public SimpleCharStream(
		final java.io.Reader dstream,
		final int startline,
		final int startcolumn,
		final int buffersize) {
		if (SimpleCharStream.inputStream != null) {
			throw new Error(
				"\n   ERROR: Second call to the constructor of a static SimpleCharStream.  You must\n"
						+ "       either use ReInit() or set the JavaCC option STATIC to false\n"
						+ "       during the generation of this class.");
		}
		SimpleCharStream.inputStream = dstream;
		SimpleCharStream.line = startline;
		SimpleCharStream.column = startcolumn - 1;

		SimpleCharStream.available = SimpleCharStream.bufsize = buffersize;
		SimpleCharStream.buffer = new char[buffersize];
		SimpleCharStream.bufline = new int[buffersize];
		SimpleCharStream.bufcolumn = new int[buffersize];
	}

	public void ReInit(final java.io.InputStream dstream) {
		this.ReInit(dstream, 1, 1, 4096);
	}

	public void ReInit(
		final java.io.InputStream dstream,
		final int startline,
		final int startcolumn) {
		this.ReInit(dstream, startline, startcolumn, 4096);
	}

	public void ReInit(
		final java.io.InputStream dstream,
		final int startline,
		final int startcolumn,
		final int buffersize) {
		this.ReInit(
			new java.io.InputStreamReader(dstream),
			startline,
			startcolumn,
			buffersize);
	}
	public void ReInit(final java.io.InputStream dstream, final String encoding)
			throws java.io.UnsupportedEncodingException {
		this.ReInit(dstream, encoding, 1, 1, 4096);
	}
	public void ReInit(
		final java.io.InputStream dstream,
		final String encoding,
		final int startline,
		final int startcolumn) throws java.io.UnsupportedEncodingException {
		this.ReInit(dstream, encoding, startline, startcolumn, 4096);
	}
	public void ReInit(
		final java.io.InputStream dstream,
		final String encoding,
		final int startline,
		final int startcolumn,
		final int buffersize) throws java.io.UnsupportedEncodingException {
		this.ReInit(
			encoding == null ? new java.io.InputStreamReader(dstream)
					: new java.io.InputStreamReader(dstream, encoding),
			startline,
			startcolumn,
			buffersize);
	}

	public void ReInit(final java.io.Reader dstream) {
		this.ReInit(dstream, 1, 1, 4096);
	}

	public void ReInit(
		final java.io.Reader dstream,
		final int startline,
		final int startcolumn) {
		this.ReInit(dstream, startline, startcolumn, 4096);
	}

	public void ReInit(
		final java.io.Reader dstream,
		final int startline,
		final int startcolumn,
		final int buffersize) {
		SimpleCharStream.inputStream = dstream;
		SimpleCharStream.line = startline;
		SimpleCharStream.column = startcolumn - 1;

		if (SimpleCharStream.buffer == null
				|| buffersize != SimpleCharStream.buffer.length) {
			SimpleCharStream.available = SimpleCharStream.bufsize = buffersize;
			SimpleCharStream.buffer = new char[buffersize];
			SimpleCharStream.bufline = new int[buffersize];
			SimpleCharStream.bufcolumn = new int[buffersize];
		}
		SimpleCharStream.prevCharIsLF = SimpleCharStream.prevCharIsCR = false;
		SimpleCharStream.tokenBegin =
			SimpleCharStream.inBuf = SimpleCharStream.maxNextCharInd = 0;
		SimpleCharStream.bufpos = -1;
	}

}