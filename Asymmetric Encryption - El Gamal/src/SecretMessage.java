import java.math.BigInteger;
import java.util.Random;

public class SecretMessage {

    private int message = 2000;
    private int base;
    private int prime;
    private int PK;
    private EncryptedMessage m;

    public SecretMessage (int base, int prime, int PK) {
        this.base = base;
        this.prime = prime;
        this.PK = PK;
    }  

    public int findGCD(int first, int second){
        int gcd = 0;
        int count = 1;
        while(count <= first && count <= second){
            if((first % count == 0) && (second % count == 0)){
                gcd = count;
            }
            count++;
        }

        return gcd;

    }

    public int findK(int prime){
        int temp = prime -1;

        int randomNumber = 0;

        Random random = new Random();

        while(findGCD(temp, randomNumber) != 1){
           randomNumber = random.nextInt(50);
        }

        return randomNumber;
    }

    public EncryptedMessage encrypt(){
        

        int k = findK(prime);
        BigInteger temp_message = BigInteger.valueOf(message);
        BigInteger temp_k = BigInteger.valueOf(k);
        BigInteger temp_base = BigInteger.valueOf(base);
        BigInteger temp_prime = BigInteger.valueOf(prime);
        BigInteger temp_PK = BigInteger.valueOf(PK);

        BigInteger c1 = temp_base.modPow(temp_k, temp_prime);
        BigInteger c2 = temp_message.multiply(temp_PK.pow(k)).mod(temp_prime);
        
        m = new EncryptedMessage(c1, c2);

        return m;
    }

    public int getBase() {
        return base;
    }

    public int getPrime() {
        return prime;
    }

    public int getPK() {
        return PK;
    }

    
   

    
}
