package de.ems.tools;

import java.io.IOException;

/**
 * A Java class to handle checksum exceptions.
 * <p>
 * <b>Copyright</b> &copy; 1997
 * <a href="http://www.systemics.com/">Systemics Ltd</a> on behalf of the
 * <a href="http://www.systemics.com/docs/cryptix/">Cryptix Development Team</a>.
 * <br>All rights reserved.
 * <p>
 * <b>$Revision: 1.1 $</b>
 * @author  Raif S. Naffah
 */
public class ChecksumException
extends IOException
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3258408435031486520L;
	public ChecksumException () { super(); }
    public ChecksumException (String description) { super(description); }
}

