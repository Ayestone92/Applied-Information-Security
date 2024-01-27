import java.math.BigInteger;

public class Weave {

    public EncryptedMessage modifyInterceptedEncryption(EncryptedMessage m){
        
        BigInteger c1 = m.getFirst();
        BigInteger c2 = m.getSecond();
        BigInteger newC2;
        BigInteger multiplyBy2 = BigInteger.valueOf(2);

        newC2 = c2.multiply(multiplyBy2);

        EncryptedMessage modified = new EncryptedMessage(c1, newC2);

        return modified;

    }

}
