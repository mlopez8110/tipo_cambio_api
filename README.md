Se utilizo la arquitectura hibrida formado por
•	Model
•	Controller
•	Service
•	Repository
•	Security

Los Enpoint Rest son los siguientes:

POST REGISTRO USUARIO
http://localhost:8077/api/auth/registrar
{
  "username": "usuario2",
  "password": "Collantes1234",
  "role": "USER"
}


POST LOGIN
http://localhost:8077/api/auth/login
{
  "username": "usuario2",
  "password": "Collantes1234",
  "role": "USER"
}


POST REGISTRO CAMBIO
http://localhost:8077/api/tipo-cambio/registrar
{
    "monedaOrigen": "USD",
    "monedaDestino": "EUR",
    "tipoCambio": 0.85
}

POST CONVERTIR MONEDA
http://localhost:8077/api/tipo-cambio/convertir?origen=USD&destino=EUR&monto=100

