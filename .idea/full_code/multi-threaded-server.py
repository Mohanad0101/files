"""Multi-threaded DH server; same session loop as server.py. JSON one line per message."""
from __future__ import annotations

import json
import secrets
import socket
import threading

from dh_utils import derive_key_material, public_component, shared_secret, xor_bytes

HOST = "127.0.0.1"
PORT = 5000
_log_lock = threading.Lock()


def log(msg: str) -> None:
    with _log_lock:
        print(msg, flush=True)


def json_write(w, obj: dict) -> None:
    w.write(json.dumps(obj, ensure_ascii=False) + "\n")
    w.flush()


def json_read(r) -> dict:
    line = r.readline()
    if not line:
        raise EOFError("connection closed")
    return json.loads(line.strip())


def extract_field(plaintext: str, key: str) -> str:
    marker = f"{key}="
    if marker not in plaintext:
        return ""
    return plaintext.split(marker, 1)[1].split(";", 1)[0].strip()


def handle_client(conn: socket.socket, addr: tuple) -> None:
    try:
        r = conn.makefile("r", encoding="utf-8")
        w = conn.makefile("w", encoding="utf-8")
        hello = json_read(r)
        p = int(hello["p"])
        g = int(hello["g"])
        A = int(hello["A"])
        b = secrets.randbelow(p - 2) + 2
        B = public_component(g, b, p)
        json_write(w, {"B": B})
        K_server = shared_secret(A, b, p)
        key = derive_key_material(K_server, length=32)
        log(f"[server] [{addr}] shared K = {K_server}")
        first_done = False
        while True:
            try:
                payload = json_read(r)
            except EOFError:
                log(f"[server] [{addr}] EOF")
                break
            if payload.get("action") == "bye":
                log(f"[server] [{addr}] bye")
                break
            if "ciphertext_hex" not in payload:
                break
            ct = bytes.fromhex(payload["ciphertext_hex"])
            plaintext = xor_bytes(ct, key).decode("utf-8")
            log(f"[server] [{addr}] decrypted: {plaintext}")
            if not first_done:
                first_done = True
                if "student_name=" in plaintext:
                    sn = extract_field(plaintext, "student_name")
                    sg = extract_field(plaintext, "student_group")
                    snum = extract_field(plaintext, "student_number")
                    log(f"[server] [{addr}] student: {sn}, {sg}, {snum}")
                    response = (
                        f"Hello, {sn} (group {sg}, number {snum}). More messages or quit."
                    ).encode("utf-8")
                else:
                    response = b"ERROR: invalid first message"
            else:
                response = f"ECHO: {plaintext}".encode("utf-8")
            json_write(w, {"ciphertext_hex": xor_bytes(response, key).hex()})
    finally:
        conn.close()
        log(f"[server] [{addr}] closed")


def main() -> None:
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        s.bind((HOST, PORT))
        s.listen(128)
        log(f"[server] multi-threaded on {HOST}:{PORT}")
        while True:
            conn, addr = s.accept()
            threading.Thread(
                target=handle_client, args=(conn, addr), daemon=True
            ).start()


if __name__ == "__main__":
    main()
