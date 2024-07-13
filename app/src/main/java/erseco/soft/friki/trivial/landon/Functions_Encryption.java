package erseco.soft.friki.trivial.landon;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

class Functions_Encryption {

	private static final byte[] key = { 101, -9, 121, 27, 1, -35, 22, 89, 101, -9, 121, 27, 1, -35, 22, 89 };

	protected static String decrypt(String str) {
		try {

			Cipher localCipher = Cipher.getInstance("AES");

			localCipher.init(2, new SecretKeySpec(key, "AES"));

			str = new String(localCipher.doFinal(str.getBytes()));
			
		} catch (Exception e) {

			//System.out.println(e.getMessage());


		}
		return str;
	}

}