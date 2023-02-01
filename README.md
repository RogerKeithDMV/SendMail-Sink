[![N|Solid](https://cloudgensys.com/cg-demo/wp-content/uploads/2019/05/CG-Logo-01.png)](https://www.cloudgensys.com/)

# SendMail-Sink 
## 1.- Introduction

Sink application that send emails taking the body of the message as the payload. This will return only the response of email sended or the error in case of a problem.

Application can be builded with Maven or following the next steps with Docker.
 
- Maven: **`clean -DskipTests package`**
- Docker: **`mvnw clean install spring-boot:build-image -DskipTests=true`** .
## 2.- Library Objects and Methods
This application helps for one external library **org.JSON**, one package Java SE Library **java. net** and the library **Jakarta Mail**.
- ## Methods
    **sendMail:** Method that will receive the payload and will build the email.

    >  **NOTE:** Please be sure that the structure of the data that you pass to this methods is correct or else the API call will not be done.

- ## 2.2 Objects and properties
    The following are the objects and properties required by the application to work properly.
    - ## 2.2.1 
        - **recipient:** String that contains the email address of the sender of the mail.
        - **host:** String that contains the host of the email server that contains the account, an example of a host could be like smtp.gmail.com for G-Mail accounts.
        - **password:** String that contains the password of the email account. Depending of the email account it could require additional configurations to be applied directly in the configurations of the account, for example in the case of the G-Mail accounts is required to configure a different password to be used in external applications. If these actions are not performed a warning message for this library related to the password that may appear and prevents to perform the mail send.
        - **username:** String that contains the email account to have access to the mail server.
        - **subject:** String in a single line that contains a description that the sender set as a title and will be seen by the receiver on its mailbox. 
        > NOTE: The only values accepted by this properties are listed above.

## 3. Examples
Data for Spring Cloud Dataflow interaction method will be obtained by the incoming payload from Source or processor.
> This application does not have any default properties value so it is important to declare at least the authentication values and URL for the correct work of the application.

- **Args**
    - 1.- The payload will be obtained from the incoming message received by the Message Broker configurated.
    - 2.- Properties will be obtained at the moment of the definition of the Spring cloud Data Flow stream, below is the example for the stream:
    ```
    http | transform | send-mail-sink --username="yourmail@yourhost.com" --subject='Mail subject' --host="HostOfTheEmailAccount" --password="youpassword" --recipient="somemail@host.com"
    ```
    
- ## 3.1 sendMail
    - **Description:** This method will take and transform the incoming message and cast it to String and also will instantiate the main transform method using the required properties to make the call for the mail sender.
    - **Object and Properties:** To use this method, the user need to pass a Spring Message with a correct String or JSON as payload.
    - Samples: For this method it is required to get Spring Cloud Dataflow(SCDF) server running one Skipper server and one message broker, for a complete guide plase refer to the offical SCDF documentation: https://dataflow.spring.io/docs/

        - Step 1: Build .jar or upload Docker image and upload the application to SCDF server.
        - Step 2: Build following stream:
        ```
            http | transform | send-mail-sink --username="yourmail@yourhost.com" --subject='Mail subject' --host="HostOfTheEmailAccount" --password="youpassword" --recipient="somemail@host.com"
        ```
        - When the stream is deployed we can send sample data as below:
        ```
       	String exampleJSON = "\r\n"
    			+ "{\r\n"
    			+ "  \"Payload\": \" { This is the message of the mail } \"\r\n"
    			+ "}";
        ```
        - Then we can see the final response on the Log Sink application:
        ```
            Final output: Email Message Sent Successfully.
        ``