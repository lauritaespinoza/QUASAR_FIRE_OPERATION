# QUASAR FIRE OPERATION - API REST

This program allows determining the spacecraft position since its satellite's distances and decodes the secret messages received from them
To communicate with this program, was create a Rest API used as an interface to receive the message in JSON format and return the expected results in this same content-type.

For this program, the integration of a database was not necessary.

You can access the swagger documentation at this link:

    http://localhost:7881/quasar-fire-operation/swagger-ui/index.html

Next, you can see the principal endpoints and contracts established for doing requests over this API. If you want to know more details, please visit this link:
*Swagger Documentation

*Deployed in GCP

    https://exalted-slate-297517.ue.r.appspot.com/quasar-fire-operation/

This program was deployed in a free layer of GCP and you can access at the following URL:




If you wish to run this application in a local environment, you have to follow these steps:
1. Clone this repository in your favorite path and enter the folder created

2. If you have java and maven installed and running on your computer, runs this command:
   

    mvn clean install

   
    mvn clean spring-boot:run