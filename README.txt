!!! Caution: This setup is only for testing purposes. For production deployment passwords and configurations need to be changed !!!

Database setup:
Install postgres version 15 on your system like described here: https://www.postgresql.org/download/
We also recommend to install pgadmin4 as GUI. On Windows it can be installed with the postgres installer for Linux it is probably available in the repos otherwise see here: https://www.pgadmin.org/download/

In pgadmin connect to the database server
Then right click on the postgres-database on the left sidebar and select SQL-
Open the databaseSetup/createDb.sql file in the SQL- and execute it.
Now open the databaseSetup/createUser.sql in the SQL- and execute it.
Then right click on the database server on the left sidebar and click Refresh
Then right click on the newly created ochat-database on the left sidebar and select SQL-
Now open the databaseSetup/createTables.sql in the SQL- and execute it.

Add the username 'ochat' and password 'passwd' of the db user in the OChat/src/main/resources/application.yml

Keycloak authorization server setup:
The authorization server is almost fully configured.
To support login with github some extra configuration is needed.
At first create a github OAuth application and copy its client id and secret Like described here:
          https://docs.github.com/en/developers/apps/building-oauth-apps/creating-an-oauth-app
Then start the keycloak server:
    -Open a terminal
    -Navigate to the directory keycloak-19.0.3
    -On Linux run the command: bin/kc.sh start-dev --http-port=8081
    -On Windows run the command: bin/kc.bat start-dev --http-port=8081
Now you can open a browser and enter in the url bar: localhost:8081
Click on 'Administration Console'
Login with the credentials:
    username: admin
    password: admin
Select the dropdown menu on the top left and select OChat
Click on Identity providers in the left sidebar

The application.yml file needs some configuration for the application to work.

To expose the server to the internet:

    change server.address to the LAN address of the computer

    change the redirect url to use the internet address of the computer

    create a github OAuth application and copy its client id and secret

        https://docs.github.com/en/developers/apps/building-oauth-apps/creating-an-oauth-app

    set up port forwarding on port 8080 if necessary

Now run the server and the client application should be available at *internet address*:8080

The client needs to authenticate with github and click register to show up in the user list, after which any other user can send them messages