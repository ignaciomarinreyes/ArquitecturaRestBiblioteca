# ArquitecturaRestBiblioteca

Para visualizar el cliente pinche en el siguiente enlace:
http://ws.docencia.ces.siani.es/a10/Cliente_biblioteca_servidor_7/

La aplicación se basa en la gestión de una biblioteca, que tiene una librería en su interior. La
biblioteca y la librería tienen web service distintos, pero comparten el mismo cliente. La
arquitectura de la aplicación consiste en un RESTFUL Service de una biblioteca (
Web_Service_biblioteca_rest_servidor_2) basado en una base de datos MySQL (base de datos
biblioteca), un cliente RESTFUL y SOAP de biblioteca y librería (Cliente biblioteca servidor_7), un
Web Service SOAP que funciona de conversor (Web_service_soap_json) y un Web Service
Restful de librería (Web_service_libreria_rest_servidor) basado también en una base de datos
MYSQL (base de datos librería). Los servicios externos los ofrece la Google Books API. En el
siguiente esquema podemos visualizar la arquitectura de la aplicación.

![Dibujo arquitectura](https://user-images.githubusercontent.com/46105514/86111803-1b6efd80-babf-11ea-920d-dfbde1498978.png)

El cliente RESTFUL y SOAP nos va a permitir buscar un libro según el ID de google, mostrar los
libros más importantes de la biblioteca, reservar un libro si eres un usuario, añadir un usuario o
eliminarlo, visualizar los libros reservados por el usuario, y mostrar un listado de los libros de la
librería.
A su vez, el cliente RESTFUL y SOAP utiliza de forma externa los servicios de Google Books, para
obtener información adicional de los libros que posee, esta comunicación la establece
indirectamente a través del Web service conversor SOAP.
El servicio conversor SOAP se encarga de obtener todas las consultas que se hacen a la API de
Google Books y traducirlas a un String entendible por el programa para poder crear los objetos
necesarios con ella.
El manejo de la base de datos se deja en gran parte a la misma BBDD con el uso de Triggers que
se encargan del “comportamiento” usual de la base de datos, logrando así crear una aplicación
más modular. Por otro lado, la aplicación cuenta con varios querys de forma abstracta para
poder reducir el volumen de información que se descarga desde la BBDD y utilizar solo la
información necesaria.
A su vez se aprovecha la Google Books API para poder obtener las imágenes de los libros
reduciendo la cantidad de información que se guarda en la BBDD así como el trafico a la misma,
y también se obtiene mediante dicha API las descripciones de los libros. Esto se logra mediante
una clase en Cliente SOAP y REST que se ha diseñado para manejar todas las peticiones que se
deseen hacer a la API de Google Books, y es en dicha clase donde se hace uso del servicio SOAP
para traducir los JSON que se obtienen en las diferentes consultas.
