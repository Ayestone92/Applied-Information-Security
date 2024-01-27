import java.math.BigInteger;

public class Main {
    
    public static void main(String[] args) {
        
        EncryptedMessage interceptedMessage;
        SecretMessage Alice = new SecretMessage(666, 6661, 2227);

        interceptedMessage = Alice.encrypt();

        Attacker eve = new Attacker();

        BigInteger result = eve.decrypt(666, 6661, 2227, interceptedMessage);

        System.out.println("Eve's decrypted message: " + result);

        Weave weave = new Weave();

        EncryptedMessage modifiedMessage = weave.modifyInterceptedEncryption(interceptedMessage);

        BigInteger modifiedResult = eve.decrypt(666, 6661, 2227, modifiedMessage);

        System.out.println("Bob's decrypted message after Weaves modification: " + modifiedResult);


    }
}
