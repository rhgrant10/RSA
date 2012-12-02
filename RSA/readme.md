RSA: My Java Implementation
===========================

Introduction
------------
This program aims to provide the means to encrypt and decrypt files 
using the RSA algorithm.  
	
How to Use
---------- 
First, make an instance of the key generator, RSAKeyGenerator. Next, call 
it's RSAKey makeKey(byte) method, and specify which kind of key desired 
using the public constants PUBLIC_KEY, PRIVATE_KEY, and COMPLETE_KEY. 
Public keys are for encrypting, private keys are for decrypting, and 
complete keys are for efficient decrypting via the Chinese Remainder 
Theorem.  Lastly, call the key's void use(String, String) method, 
specifying a source file and a destination file in the arguments. Presto!


Version History
---------------
### 2010/05/31
  * Stoped working on the project.
  
### 2012/12/01 
  * Commited the project to GitHub unchanged.
  * I don't know if it works. It may not be the last version I had, as I'm
  not even sure when I gave this copy to my friend, as it was almost 2 years
  ago now.
  
Author
------
  * Robert Grant