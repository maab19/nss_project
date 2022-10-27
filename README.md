The application.yml file needs some configuration for the application to work.
To expose the server to the internet:
    change server.address to the LAN address of the computer
    change the redirect url to use the internet address of the computer
    create a github OAuth application and copy its client id and secret
        https://docs.github.com/en/developers/apps/building-oauth-apps/creating-an-oauth-app

Now run the server and the client application should be available at *internet address*:8080

The client needs to authenticate with github and click register to show up in the user list, after which any other user can send them messages