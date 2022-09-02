package de.ems.tools;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.rmi.server.UID;

import org.apache.log4j.Logger;

import de.ems.tools.Base64OutputStream;
import de.ems.tools.ModB64OutputStreamFilter;

public class AbstractPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2496621734089232857L;

	private static Logger log = Logger.getLogger(AbstractPK.class.getName());

	public String pk;

    public AbstractPK() {}

    public AbstractPK(UID uid) {
        this.pk = convert(uid);
    }

    public AbstractPK(String id) {
        this.pk = id;
    }

    @Override
	public int hashCode() {
        return this.pk.hashCode();
    }

    public String getID() {
        return pk;
    }

    @Override
	public boolean equals(Object pk) {

        boolean retValue = false;

        if (pk != null) {
            if (pk.getClass() == this.getClass()) {
                retValue = ((AbstractPK) pk).getID().equals(this.pk);
            }
        }

        return retValue;
    }

    public static String create() {
    	return convert(new UID());
    }

    public static String convert(UID uid) {

        try {
            ByteArrayOutputStream    bos       = new ByteArrayOutputStream();
            ModB64OutputStreamFilter b64filter = new ModB64OutputStreamFilter(bos, '-', '_', false);
            Base64OutputStream       b64os     = new Base64OutputStream(b64filter);
            DataOutputStream         dos       = new DataOutputStream(b64os);

            uid.write(dos);
            dos.close();
            b64os.close();
            b64filter.close();
            bos.close();

            return bos.toString();
        } catch (IOException ex) {

            // should not happen
            log.error("uid->string conversion failed!", ex);

            return uid.toString();
        }
    }
}
