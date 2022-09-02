package de.ems.tools;

import java.io.CharConversionException;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Checksum;

/**
 * This class implements a BASE64 Character stream encoder as specified
 * in RFC1521, part of the MIME specification as published by the Internet
 * Engineering Task Force (IETF).
 * <p>
 * Unlike other encoding schemes there is nothing in this encoding that
 * indicates where a buffer starts or ends. In other words, the encoded
 * text simply starts at the first line and ends with the last one.
 * <p>
 * <b>Copyright</b> &copy; 1995-1997
 * <a href="http://www.systemics.com/">Systemics Ltd</a> on behalf of the
 * <a href="http://www.systemics.com/docs/cryptix/">Cryptix Development Team</a>.
 * <br>All rights reserved.
 * <p>
 * <b>$Revision: 1.1 $</b>
 * @author  FH
 * @author  Raif S. Naffah
 * @since   Cryptix 2.2.2
 *
 * <code>
 * see cryptix.mime.Base64OutputStream
 * see cryptix.mime.Base64
 * </code>
 */
public class Base64InputStream
extends FilterInputStream
{
// Constants and variables
//...........................................................................

    /** Flag for having read all the encoded data. */
    private boolean finished;

    /** Accumulated checksum, if requested, null if no checksum is used. */
    private Checksum crc;

    /** Number of bytes of checksum to be read. */
    private int crcLength;

    /** Input data buffer. */
    private byte[] inBuf = new byte[4];

    /** Output data buffer. */
    private byte[] outBuf = new byte[3];

    /** Count of bytes in input data buffer. */
    private int inOff;

    /** Byte index into output data buffer. */
    private int outOff;

    /** Total bytes in output data buffer, always 3 except for last chunk. */
    private int outBufMax;
        
    static final int                      // lex tokens for local consumption
        CONV_WHITE = -1,
        CONV_PAD   = -2,
        CONV_OTHER = -3;


// Constructors
//...........................................................................

    /**
     * If check is true then look for and check a PGP-style checksum
     * immediately after the base64 portion.
     *
     * see cryptix.util.checksum.PRZ24
     * @param is, InputStream
     * @param check, boolean
     */
    public Base64InputStream (InputStream is, boolean check) {
        super(is);
        init(check ? new PRZ24() : null, 3);
    }

    /** Create a Base64InputStream with no checksum. 
     * 
     * @param is, InputStream
     */
    public Base64InputStream (InputStream is) {
        super(is);
        init(null, 0);
    }

    /**
     * Creates a Base64InputStream that uses the given <i>checksum</i>.
     * The checksum will be <i>length</i> bytes long, where
     * 0 &lt;= <i>length</i> &lt;= 8.
     * 
     * @param is, InputStream
     * @param checksum, Checksum
     * @param length, int
     */
    public Base64InputStream (InputStream is, Checksum checksum, int length) {
        super(is);
        checksum.reset();
        init(checksum, length);
    }


// FilterInputStream overridden methods
//...........................................................................

    @Override
	public synchronized void close ()
    throws IOException {
        finished = true;
        outOff = 0;
        super.close();
    }

    /**
     * Parse input in fours, producing three bytes to outBuf. Should only
     * see whitespace or illegal characters on a quadruplet boundary.
     * <p>
     * When using a checksum, wait for a "=" as the first character of
     * a quadruplet; read sufficient quadruplets for <code>crcLength</code>
     * bytes, and compare with our crc. If the end of the stream is reached
     * at a bad spot, throw EOFException.
     * <p>
     * If we get the first illegal character on a four byte boundary, return
     * -1 (Java EOF convention). Iff the underlying input stream is an
     * instanceof <code>PushbackInputStream</code>, the illegal character
     * will be pushed back.
     *
     * @see java.io.PushbackInputStream
     */
    @Override
	public synchronized int read ()
    throws IOException {
        if (outOff == 0) {                     // read another set of 4 bytes
            if (finished) return -1;            // see first if we are at end
            int inByte = 0;
            int n = CONV_WHITE;
            while (n == CONV_WHITE) {                      // skip whitespace
                inByte = in.read();
                if (inByte < 0) return -1;
                n = toNumber(inByte);
            }
            if (n < 0) {                                 // pad or conv other
                if (crc == null || n == CONV_OTHER)
                    // this stream does not contain Base64 data after all
                    throw new CharConversionException();
                long computed = crc.getValue();
                long actual = 0;
                crc = null;
                for (int i = 0; i < crcLength; i++) {  // read checksum bytes
                    inByte = read();
                    if (inByte < 0) throw new EOFException();
                    actual = actual << 8 | inByte;
                }
                finished = true;
                outOff = 0;
                if (actual != computed) throw new ChecksumException();
                return -1;
            }
            for (int i = 0; i < 4; ++i) {
                if (n == CONV_PAD) {
                    if (i < 2) throw new CharConversionException();
                } else if (n < 0)       // conv char read within a group of 4
                    throw new CharConversionException();
                else
                    inBuf[inOff++] = (byte) n;
                if (i != 3) {
                    inByte = in.read();
                    if (inByte < 0) throw new EOFException();
                    n = toNumber(inByte);
                }
            }
            writeTriplet();
        }
        int b = outBuf[outOff++] & 0xFF;
        if (outOff == outBufMax) outOff = 0;
        if (crc != null) crc.update(b);
        return b;

/* OLD VERSION:
        if (outOff == 0) {                     // read another set of 4 bytes
            if (finished) return -1;            // see first if we are at end
            int inByte = 0;
            int n = CONV_WHITE;
            while (n == CONV_WHITE) {                      // skip whitespace
                inByte = in.read();
                if (inByte < 0) return -1;
                n = toNumber(inByte);
            }
            if (n < 0) {                       // checksum character or other
                if (crc == null || n == CONV_OTHER) {
                    if (in instanceof PushbackInputStream)
                        ((PushbackInputStream) in).unread(inByte);
                    finished = true;
                    return -1;
                }
                long cks = crc.getValue();
                crc = null;                        // read the checksum bytes
                long cksfile = 0;
                for (int i = 0; i < crcLength; i++) {
                    inByte = this.read();
                    if (inByte < 0) throw new ChecksumException();
                    cksfile = (cksfile << 8) | inByte;
                }
                finished = true;
                outOff = 0;
                if (cksfile != cks) throw new ChecksumException();
                return -1;
            }
            while (true) {
                if (n == CONV_PAD) {
                    if (inOff < 2) throw new CharConversionException();
                } else if (n < 0)    // a conv_ char read within a group of 4
                    throw new CharConversionException();
                else
                    inBuf[inOff++] = (byte) n;
                if (inOff == 4) break;
                inByte = super.read();
                if (inByte < 0) throw new EOFException();
                n = toNumber(inByte);
            }
            writeTriplet();
        }
        int b = outBuf[outOff++] & 0xFF;
        if (outOff == outBufMax) outOff = 0;
        if (crc != null) crc.update(b);
        return b;
*/
    }

    @Override
	public synchronized int read (byte[] buffer, int offset, int length)
    throws IOException {
        for (int i = 0; i < length; ++i) {
            int c = read();
            if (c < 0) return i == 0 ? -1 : i;
            buffer[offset++] = (byte) c;
        }
        return length;
    }

    /**
     * Skips over and discards <i>n</i> bytes of data from the
     * input stream. The <code>skip</code> method may, for a variety of
     * reasons, end up skipping over some smaller number of bytes,
     * possibly 0. The actual number of bytes skipped is returned.
     *
     * @param  n    the number of bytes to be skipped.
     * @return the actual number of bytes skipped.
     * @exception IOException if an I/O error occurs.
     */
    @Override
	public synchronized long skip(long n) throws IOException {
        // There isn't any faster way of doing this, since we need to read
        // every byte to determine which are whitespace, etc.
        // Note that this may throw a ChecksumException.

        for (long i = 0; i < n; i++)
            if (this.read() < 0) return i;
        return n;
    }

    /**
     * Returns the number of bytes that can be guaranteed to be read from this
     * input stream without blocking. For a Base64InputStream, this is sometimes
     * 0 even when data is available, because we do not read ahead far enough to
     * determine whether the next character is the start of the checksum.
     *
     * @exception IOException if an I/O error occurs.
     */
    @Override
	public synchronized int available()
    throws IOException {
        return (outOff == 0) ? 0 : (outBufMax - outOff);
    }

    /**
     * Does nothing, since this class does not support mark/reset.
     */
    @Override
	public void mark(int readlimit) {}

    /**
     * Always throws an IOException, since this class does not support mark/reset.
     */
    @Override
	public void reset() throws IOException {
        throw new IOException("Base64InputStream does not support mark/reset");
    }

    /**
     * Tests if this input stream supports the <code>mark</code> and
     * <code>reset</code> methods of InputStream, which it does not.
     *
     * @return <code>false</code>, since this class does not support the
     *         <code>mark</code> and <code>reset</code> methods.
     */
    @Override
	public boolean markSupported() { return false; }


// Own methods
//...........................................................................

    private void init (Checksum checksum, int length) {
        if (length < 0 || length > 8)
            throw new IllegalArgumentException("length < 0 || length > 8");
        outBufMax = 3;
        inOff = outOff = 0;
        finished = false;
        crc = checksum;
        crcLength = length;
    }

    private void writeTriplet () {
        outBufMax = 0;
        outBuf[outBufMax++] = (byte) (inBuf[0] << 2 | inBuf[1] >>> 4);
        if (inOff > 2)
            outBuf[outBufMax++] = (byte) (inBuf[1] << 4 | inBuf[2] >>> 2);
        if (inOff > 3)
            outBuf[outBufMax++] = (byte) (inBuf[2] << 6 | inBuf[3]);
        inOff = 0;
    }

    private int toNumber (int inByte) {
        if (inByte >= 'a' & inByte <= 'z')
            return inByte - 'a' + 26;
        else if (inByte >= 'A' & inByte <= 'Z')
            return inByte - 'A';
        else if (inByte >= '0' & inByte <= '9')
            return inByte - '0' + 52;
        else if (inByte == '+')
            return 62;
        else if (inByte == '/')
            return 63;
        else if (inByte == '=')
            return CONV_PAD;
        else if (inByte == '\n' || inByte == '\r' || inByte == ' ' || inByte == '\t')
            return CONV_WHITE;
        else
            return CONV_OTHER;
    }
}
