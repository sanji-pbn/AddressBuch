
package de.ems.tools;


import java.io.IOException;
import java.io.OutputStream;



public class ModB64OutputStreamFilter extends OutputStream {

    private OutputStream os;
    private final char subst1;
    private final char subst2;
    private final boolean padding;

    public ModB64OutputStreamFilter(OutputStream os) {
        this(os, ',', '.', true);
    }

    public ModB64OutputStreamFilter(OutputStream os, char subst1, char subst2, boolean padding) {

        this.os      = os;
        this.subst1  = subst1;
        this.subst2  = subst2;
        this.padding = padding;
    }

    @Override
	public void write(int ch) throws IOException {

        if (ch == '+') {
            ch = subst1;
        } else if (ch == '/') {
            ch = subst2;
        } else if (ch == '=') {
            if (!padding) {
                return;
            }
        } else if ((ch == '\n') || (ch == '\r')) {
            return;
        }

        //System.out.print((char)ch);
        os.write((char) ch);
    }
}