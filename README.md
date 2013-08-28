RSA
===

My Java implementation of RSA encryption.

Introduction
------------
This program aims to provide the means to encrypt and decrypt files 
using the RSA algorithm.  
	
How to Use
---------- 
1. Create an instance of `RSAKeyGenerator`.
2. Call it's `makeKey()` method, passing in a constant to specify which kind of key should be returned.
   * `RSAKey.PUBLIC_KEY` for encrypting
   * `RSAKey.PRIVATE_KEY` for decrypting
   * `RSAKey.COMPLETE_KEY` for efficient decryption using the Chinese Remainder Theorum
3. Cast the `RSAKey` returned to the appropriate type of `RSAKey`.
   * `RSAPublicKey`
   * `RSAPrivateKey`
   * `RSACompleteKey`
4. Call the key's `use()` method, passing in the source and destination file paths.
   * the key will perform it's operation on the file specified by `source`
   * the key will write the result of it's operation to the file specified by `destination`

Additionally, `RSATest` now contains a main method for testing.  Useage is as follows:

   java com.rhg.rsa.RSATest plaintext destination
   
where `plaintext` is the path to a readable file, and `destination` is the prefix to use for storage of files created during the tests.

Version History
---------------
### 2013/8/27
   * Fixed and tested the code
   * Now contains test class `RSATest`

### 2012/12/01 
   * Commited the project to GitHub unchanged.
   * I don't know if it works. It may not be the last version I had, as I'm
  not even sure when I gave this copy to my friend, as it was almost 2 years
  ago now.
  
### 2010/05/31
   * Stoped working on the project.
  
Author
------
  * Robert Grant
