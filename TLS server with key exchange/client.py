import socket
import ssl

#the CA certificate
CA = "CA/ca-cert.pem"         
#the clients private key
CLIENTKEY = "CA/client-key.pem"
#the clients certificate
CLIENTCERT = "CA/client-cert.pem"

def client(message):

     #creating a SSL context to wrap around the cleint object
    context = ssl.SSLContext(ssl.PROTOCOL_TLS_CLIENT)

    # disabling hostname for flexibility, but may be a security risks
    context.check_hostname = False

    #CERT_NONE to disable server certificate validation, may also be a security risk though
    context.verify_mode = ssl.CERT_NONE

    #settings for the CAFILE
    context.load_verify_locations(cafile=CA)

    #loading the clients cert and key
    context.load_cert_chain(certfile=CLIENTCERT, keyfile=CLIENTKEY)

    #creating a client socket
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    # wrapping the client socket to enable TLS encryption
    client_socket = context.wrap_socket(client_socket)

    #specify server address
    client_socket.connect(("127.0.0.1", 7007))

    #send specified messaged
    client_socket.send(bytes(message, encoding='utf8'))

    #getting the server message
    server_message = str(client_socket.recv(1024), encoding='utf8')
    print(server_message)
    client_socket.close()

if __name__ == "__main__":
    client("Client here!")
