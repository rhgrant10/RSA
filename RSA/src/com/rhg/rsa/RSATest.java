package com.rhg.rsa;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class RSATest {
	private static int TESTS_PASS = 0;
	private static int TEST_ERROR = 1;
	private static int INCORRECT_INVOCATION = 127;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// File paths.  Only the file specified in plaintext must exist.
		String plaintext = null;
		String cryptotext = null;
		String decryptedText1 = null;
		String decryptedText2 = null;
		
		// Make sure we have the necessary arguments.
		if (args.length != 2) {
			System.out.println("You must provide a readable source file and a writable desination.");
			System.exit(RSATest.INCORRECT_INVOCATION);
		} else {
			plaintext = args[0];
			cryptotext = args[1];
			decryptedText1 = plaintext + "-private";
			decryptedText2 = plaintext + "-complete";
		}
		
		// Create the keys.
		RSAKeyGenerator keygen = new RSAKeyGenerator();
		RSACompleteKey completeKey = (RSACompleteKey)keygen.makeKey(RSAKey.COMPLETE_KEY);
		RSAPublicKey publicKey = (RSAPublicKey)keygen.makeKey(RSAKey.PUBLIC_KEY);
		RSAPrivateKey privateKey = (RSAPrivateKey)keygen.makeKey(RSAKey.PRIVATE_KEY);
		
		// Test encryption using the public key.  Other tests rely on this part working,
		// so just quit if it fails.
		if (!publicKey.use(plaintext, cryptotext)) {
			System.out.println("Public key encryption failed.");
			File f = new File(plaintext);
			System.out.println(String.format("plaintext from: %s", f.getAbsolutePath()));
			System.exit(RSATest.TEST_ERROR);
		}
		
		// Make the assumption that tests will pass.
		int status = RSATest.TESTS_PASS;
		
		// Test decryption using the private key.  Check accuracy if successful.
		if (!privateKey.use(cryptotext, decryptedText1)) {
			System.out.println("Private key decryption failed.");
			status = RSATest.TEST_ERROR;
		} else if (isDiff(plaintext, decryptedText1)) {
			System.out.println("Private key decryption produced wrong plaintext!");
			status = RSATest.TEST_ERROR;
		}
				
		// Test decryption using the complete key.  Check accuracy if successful.
		if (!completeKey.use(cryptotext, decryptedText2)) {
			System.out.println("Complete key decryption failed.");
			status = RSATest.TEST_ERROR;
		} else if (isDiff(plaintext, decryptedText2)) {
			System.out.println("Complete key decryption produced the wrong plaintext!");
			status = RSATest.TEST_ERROR;
		}
		
		// Report success, if that is the case.
		if (status == RSATest.TESTS_PASS) { 
			System.out.println("All tests passed!");
		}
		System.exit(status);
	}
	
	/**
	 * Returns true if there is a difference between the files specified by textA and textB
	 * @param textA path to file A
	 * @param textB path to file B
	 * @return true if the contents of file A are exactly the contents of file B; otherwise false.
	 */
	private static boolean isDiff(String textA, String textB) {
		byte[] aBytes = getBytes(textA);
		byte[] bBytes = getBytes(textB);
		
		if (aBytes.length != bBytes.length) {
			return true;
		}
		
		for (int i = 0; i < aBytes.length; i++) {
			if (aBytes[i] != bBytes[i]) return true;
		}
		
		return false;
	}
	
	/**
	 * Returns the contents of the file specified by fileName as an array of bytes. 
	 * @param fileName path of the file whose bytes should be returned
	 * @return the contents of fileName as a byte array
	 */
	private static byte[] getBytes(String fileName) {
        File fIn = new File(fileName);
        FileInputStream in = null;
        byte[] bytes = null;
        
        try {
            in = new FileInputStream(fIn);
            
            long fileSize = fIn.length();
            if (fileSize > Integer.MAX_VALUE) {
                System.out.println("Sorry, file was too large!");
            }
    
            bytes = new byte[(int) fileSize];
    
            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length
                && (numRead = in.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }
        } catch (IOException e) {
            return null;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                return null;
            }
        }

        return bytes;
    }
}
