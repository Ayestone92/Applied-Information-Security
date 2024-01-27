import socket
import ssl

#static host
HOST = "127.0.0.1"  
#static port number 
PORT = 7007 

#the CA certificate (used by the server to verify client certificate)
CA = "CA/ca-cert.pem"
#the servers private key
SERVERKEY = "CA/server-key.pem"
#the servers certificate
SERVERCERT = "CA/server-cert.pem"


def start_server(message):
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as server:

        #binding a created tuple for the server to bind as address
        server.bind((HOST, PORT))

       #maximum number of conns = 5
        server.listen(5)

        #creating a SSL context to wrap around the server object
        context = ssl.SSLContext(ssl.PROTOCOL_TLS_SERVER)

        #making sure that the context uses the CA's and servers certification files
        context.verify_mode = ssl.CERT_REQUIRED

        #setting up the CA' certfile
        context.load_verify_locations(cafile=CA)

        #setting up the server's cert and key file
        context.load_cert_chain(certfile=SERVERCERT, keyfile=SERVERKEY)

        #finally we wrap the socket with the context to enable TLS encryption
        server = context.wrap_socket(server)

        # accepting and continueing connecttions
        while True:
            conn, addr = server.accept()
            clientMessage = ''
            print(f"Connected with {conn} to {addr}")

            # revieved data gets converted into string
            data = str(conn.recv(4096), encoding='utf8')

            #if no data is received, break
            if not data:
                break

            #pring the clientmessage
            clientMessage += data
            print(clientMessage)

            #sending a message back to client specified in the constructor
            conn.send(bytes(message, encoding='utf8'))

            conn.close()

if __name__ == "__main__":
    start_server("Connected to server")
