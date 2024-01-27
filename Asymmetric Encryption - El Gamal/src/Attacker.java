import java.math.BigInteger;

public class Attacker {

    public BigInteger findPrivateKey(int base, int prime, int PK){

        BigInteger b = BigInteger.valueOf(base);
        BigInteger p = BigInteger.valueOf(prime);
        BigInteger pk = BigInteger.valueOf(PK);

        BigInteger count = BigInteger.ZERO;

        while (!b.modPow(count, p).equals(pk)){
  
            count = count.add(BigInteger.ONE);

        }

        return count;

    }

    public BigInteger decrypt(int base, int prime, int PK, EncryptedMessage m){

        BigInteger c1 =  m.getFirst();
        BigInteger c2 =  m.getSecond();

        BigInteger privateKey = findPrivateKey(666, 6661, 2227);

        BigInteger intermediateValue = c1.modPow(privateKey, BigInteger.valueOf(prime));

        BigInteger inverse = intermediateValue.modInverse(BigInteger.valueOf(prime));

        BigInteger message = c2.multiply(inverse).mod(BigInteger.valueOf(prime));

        return message;

    }


}
