!!! Caution: This setup is only for testing purposes. For production deployment passwords, secrets and some configurations need to be changed !!!
1. Use the provided Virtual Box image
We provide with ochat.ova a virtual box image in which the application is already set up.

To use it install VirtualBox:
And import the image.
Important credentials:
    Linux user:
        username: ochat
        password: ochat
        sudo password: ochat
    pgadmin4 master password: passwd
    DB super user:
        username: postgres
        password: passwd
    keycloak admin:
        username: admin
        password: admin
To run the application at first start the keycloak server:
    1. Open a terminal
    2. Use the command: cd ~/OChat/keycloak-19.0.3
    3. followed by the command: bin/kc.sh start-dev --http-port=8081
Afterwards open IntelliJ, which is already installed
The correct project should already be opened.
If not open the folder ~/OChat/OChat as a project in IntelliJ
Then press the run button or Shift+F10 to start the application
You can now reach the application by opening a browser and entering localhost:8080 in the URL bar and pressing enter

3rd party login with Github is not setup out of the box.
To set it up follow the steps described below under: Keycloak authorization server setup:


2. Set up the application on your own device

Database setup:
Install postgres on your system like described here: https://www.postgresql.org/download/
We also recommend to install pgadmin4 as GUI. On Windows it can be installed with the postgres installer for Linux it is probably available in the repos otherwise see here: https://www.pgadmin.org/download/

In pgadmin connect to the database server
Then right click on the postgres-database on the left sidebar and select Query-Tool
Open the databaseSetup/createDb.sql file in the Query-Tool and execute it.
Now open the databaseSetup/createUser.sql in the Query-Tool and execute it.
Then right click on the database server on the left sidebar and click Refresh
Then right click on the newly created ochat-database on the left sidebar and select Query-Tool
Now open the databaseSetup/createTables.sql in the Query-Tool and execute it.

Add the username 'ochat' and password 'passwd' of the db user in the OChat/src/main/resources/application.yml

Keycloak authorization server setup:
The authorization server is almost fully configured.
To support login with github some extra configuration is needed.
At first create a github OAuth application and copy its client id and secret Like described here:
          https://docs.github.com/en/developers/apps/building-oauth-apps/creating-an-oauth-app
          -Use "http://localhost:8081" as Homepage URL and Authorization Callback URL
Then start the keycloak server:
    -Open a terminal
    -Navigate to the directory keycloak-19.0.3
    -On Linux run the command: bin/kc.sh start-dev --http-port=8081
    -On Windows run the command: bin/kc.bat start-dev --http-port=8081
Now you can open a browser and enter in the url bar: localhost:8081
and press enter
Click on 'Administration Console'
Login with the credentials:
    username: admin
    password: admin
Select the dropdown menu on the top left and select OChat
Click on Identity providers in the left sidebar
Select GitHub from the listed providers
Paste the copied client id and client secret in the client id and client secret field
Press Add

Running the application:
You have to load all dependencies and build the project before you can run it.
For that we recommend to use an IDE like IntelliJ IDEA.
Open the OChat folder as a project in IntelliJ. IntelliJ should now load all dependencies for you.
After that you can try to build the project.
Before you run the project ensure that the database and the keycloak server are running
Then you can run the application from IntelliJ using Shift+F10