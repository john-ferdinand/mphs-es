/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility.puzzler;

import Puzzlerlib.PuzzlerDecrypt;
import Puzzlerlib.PuzzlerEncrypt;

/**
 *
 * @author Jordan
 */
public class Puzzler {

    private String str;

    public Puzzler(String str) {
        this.str = str;
    }

    public String getDecrypted() {
        String decryptedString;
        PuzzlerDecrypt pd = new PuzzlerDecrypt(str);
        decryptedString = pd.getCrypted();
        return decryptedString;
    }

    public String getEncrypted() {
        String encryptedString;
        PuzzlerEncrypt pe = new PuzzlerEncrypt(str, 27);
        encryptedString = pe.getEncrypted();
        return encryptedString;
    }
}
