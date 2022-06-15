# Certificates

All commands should happen in the `certificates/data-prepper` directory.

## Generate Root Certificate

Generate a private key:

```
openssl genrsa -out root-ca-key.pem 2048
```

Generate the CA certicate:

```
openssl req -x509 -sha256 -new -nodes -key root-ca-key.pem -days 3650 -out root-ca.pem -config server-csr.config
```

(n.b. I'm using the server-csr.config here which works. There might be a better configuration though.)

Validation:

```
openssl x509 -in root-ca.pem -text
```

## Server Certificate Generation

Generate a server certificate for Data Prepper.

```
openssl genrsa -out private-key.pem 2048
```

```
openssl req -new -key private-key.pem -sha256 -out server.csr -config server-csr.config
```


```
openssl req -in server.csr -noout -text
```

It should have:

```
Requested Extensions:
    ...
    X509v3 Subject Alternative Name:
        DNS:data-prepper
```

Use the Root CA to create a certificate:

```
openssl x509 -req -sha256 -in server.csr -CA root-ca.pem -CAkey root-ca-key.pem -CAcreateserial -out server-cert.pem -days 365 -extfile v3.ext
```

Validate:

```
openssl x509 -in server-cert.pem -text
```

It should have:

```
X509v3 extensions:
    X509v3 Subject Alternative Name:
        DNS:data-prepper
```

Note that `data-prepper` is important because it matches the hostname used by Docker.
