import java.math.BigInteger;

public class EncryptedMessage {
    private BigInteger first;
    private BigInteger second;

    public EncryptedMessage(BigInteger first, BigInteger second){
        this.first=first;
        this.second=second;
    }

    public BigInteger getFirst(){
        return first;
    }

    public BigInteger getSecond(){
        return second;
    }


    


}
