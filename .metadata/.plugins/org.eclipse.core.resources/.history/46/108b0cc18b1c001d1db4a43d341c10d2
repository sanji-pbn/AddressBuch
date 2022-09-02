package de.ems.tools;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.Checksum;

/**
 * This class implements a BASE64 Character stream decoder as specified
 * in RFC1521, part of the MIME specification as published by the Internet
 * Engineering Task Force (IETF).
 * <p>
 * <b>Copyright</b> &copy; 1995-1997
 * <a href="http://www.systemics.com/">Systemics Ltd</a> on behalf of the
 * <a href="http://www.systemics.com/docs/cryptix/">Cryptix Development Team</a>.
 * <br>All rights reserved.
 * <p>
 * <b>$Revision: 1.1 $</b>
 * @author  Raif S. Naffah
 * @author  David Hopwood
 * @since   Cryptix 2.2.2
 */
public class Base64OutputStream
extends FilterOutputStream
{
// Variables and constants
//...........................................................................

    /** Accumulated checksum, if requested, null if no checksum is used. */
    private Checksum crc;

    /** Number of checksum bytes to append. */
    private int crcLength;

    /** Internal data buffer. */
    private byte[] inBuf;

    /** Count of bytes in internal data buffer. */
    private int inOff;

    /** Number of bytes output so far on line. */
    private int lineLength;

    private static final int MAX_LINE_LENGTH = 64;
    private static final char[] BASE64_CHARSET = {
    //   0   1   2   3   4   5   6   7
        'A','B','C','D','E','F','G','H', // 0
        'I','J','K','L','M','N','O','P', // 1
        'Q','R','S','T','U','V','W','X', // 2
        'Y','Z','a','b','c','d','e','f', // 3
        'g','h','i','j','k','l','m','n', // 4
        'o','p','q','r','s','t','u','v', // 5
        'w','x','y','z','0','1','2','3', // 6
        '4','5','6','7','8','9','+','/'  // 7
    };
    private static final char PADDING = '=';


// Constructors
//...........................................................................

    /**
     * If check is true then append a PGP-style checksum immediately
     * after the base64 portion.
     *
     * see cryptix.util.checksum.PRZ24
     
     * @param os, OutputStream
     * @param check, boolean
     */
    public Base64OutputStream (OutputStream os, boolean check) {
        super(os);
        init(check ? new PRZ24() : null, 3);
    }

    /** Creates a Base64OutputStream with no checksum. 
     * 
     * @param os, OutputStream
     */
    public Base64OutputStream (OutputStream os) {
        super(os);
        init(null, 0);
    }

    /**
     * Creates a Base64OutputStream that uses the given <i>checksum</i>.
     * The checksum will be <i>length</i> bytes long, where
     * 0 &lt;= <i>length</i> &lt;= 8.
     *
     * @param os, OutputStream
     * @param checksum, Checksum
     * @param length, int
     */
    public Base64OutputStream (OutputStream os, Checksum checksum, int length) {
        super(os);
        checksum.reset();
        init(checksum, length);
    }


// FilterOutputStream overridden methods
//............................................................................

    @Override
	public synchronized void write (int b)
    throws IOException {
        inBuf[inOff++] = (byte)b;
        if (crc != null) crc.update(b);
        if (inOff == 3) writeQuadruplet();
    }

    @Override
	public synchronized void write (byte[] b, int offset, int length)
    throws IOException {
        for (int i = 0; i < length; i++) this.write(b[offset++]);
    }

    @Override
	public synchronized void close ()
    throws IOException {
        writePadding();                 // process remaining chars in buffer
        // force line break unless current line is empty
        if (lineLength != 0) writeln();
        if (crc != null) {              // if should add a CRC then do it now
            long cks = crc.getValue();
            // write a padding byte, followed by the checksum bytes
            super.write(PADDING);
            crc = null;
//            for (int i = crcLength - 1; i >= 0; i++)
            for (int i = crcLength - 1; i >= 0; i--)
                this.write((int) (cks >>> (i * 8)) & 0xFF);
            writePadding();
            writeln();
        }
        super.flush();
        super.close();
    }


// Own methods
//...........................................................................

    private void init(Checksum checksum, int length) {
        if (length < 0 || length > 8)
            throw new IllegalArgumentException("length < 0 || length > 8");
        lineLength = inOff = 0;
        inBuf = new byte[3];
        crc = checksum;
        crcLength = length;
    }

    private void writePadding() throws IOException {
        if (inOff != 0) {
            for (int i = inOff; i < 3; i++) inBuf[i] = 0;
            writeQuadruplet();
        }
    }

    private void writeQuadruplet() throws IOException {
        char c = BASE64_CHARSET[(inBuf[0] & 0xFF) >> 2];
        super.write(c);
        c = BASE64_CHARSET[(inBuf[0] & 0x03) << 4 | (inBuf[1] & 0xFF) >> 4];
        super.write(c);
        c = inOff > 1 ? 
            BASE64_CHARSET[(inBuf[1] & 0x0F) << 2 | (inBuf[2] & 0xCF) >> 6] :
            PADDING;
        super.write(c);
        c = inOff > 2 ?
            BASE64_CHARSET[inBuf[2] & 0x3F] :
            PADDING;
        super.write(c);
        inOff = 0;
        lineLength += 4;
        if (lineLength >= MAX_LINE_LENGTH) writeln();
    }

    private void writeln ()
    throws IOException {
        super.write('\r');
        super.write('\n');
        lineLength = 0;
    }
}
