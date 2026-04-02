# Clean Explanations (Scientifically Correct)

## Symmetric encryption (private/shared key)

Symmetric encryption uses one shared secret key for both encryption and decryption.

- Sender and receiver must both know the same key in advance.
- Main challenge: **secure key distribution**.
- If the key is leaked, all data encrypted with that key is compromised.

Typical families:
- Stream ciphers (encrypt stream units; modern example: ChaCha20).
- Block ciphers (fixed-size blocks; modern standard: AES).

## Asymmetric encryption (public/private key)

Asymmetric cryptography uses a mathematically related key pair:
- Public key: shareable.
- Private key: secret, kept by owner.

Common use cases:
- Encrypting data to the private-key holder.
- Digital signatures for authenticity/non-repudiation.
- Key exchange/authentication in protocols like TLS.

## Diffie-Hellman key agreement

Diffie-Hellman (DH) is not direct "message encryption."  
DH is a **key agreement** method:

1. Public parameters: `p`, `g`.
2. Client picks secret `a`, sends `A = g^a mod p`.
3. Server picks secret `b`, sends `B = g^b mod p`.
4. Client computes `K = B^a mod p`.
5. Server computes `K = A^b mod p`.

Both derive the same `K = g^(ab) mod p` without sending `K` over network.

## Important security limitation

Basic DH does **not authenticate identities**.

If an active attacker intercepts and replaces public values, it can create:
- one DH secret with client;
- another DH secret with server;
- then decrypt/modify/re-encrypt messages (MITM).

Therefore, real systems combine DH/ECDH with authentication (certificates/signatures), as done in TLS.

## SSL/TLS handshake: practical meaning

TLS handshake establishes:
1. Algorithm negotiation.
2. Server authentication (certificate verification).
3. Shared session key establishment.
4. Protected channel for application data.

Modern guidance:
- Prefer TLS 1.2+ (ideally TLS 1.3).
- Disable deprecated protocols and weak ciphers.
- Use certificate validation and hostname verification.

## Why this lab still matters

This lab isolates fundamentals:
- modular arithmetic behind key agreement;
- protocol message flow;
- conversion from shared secret to key bytes.

It builds the conceptual bridge to production protocols (TLS) while making limitations explicit.
