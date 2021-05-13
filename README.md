# Apple ID Boot
Spring Boot application that uses Apple ID for login.

## Obtaining client ID and client secret
In the application.properties file I have put the parameters I obtained from
https://github.com/spring-projects/spring-security/issues/9047 , referencing two
environment variables APPLE_CLIENT_ID and APPLE_CLIENT_SECRET .

To find those out, I'll use the steps on https://auth0.com/docs/connections/apple-siwa/set-up-apple 

Ok, that needs to be deployed somewhere. Let the Yak shaving begin. First step: Build the
app with Azure Devops https://medium.com/@TimvanBaarsen/build-your-spring-boot-project-using-azure-pipelines-in-azure-devops-3305977991d

I created a new project on dev.azure.com, pointing to the github code.

I started off with a JDK version 11 standard Maven build, but the goal is to have
it in a container afterwards. I also ensure continuous integration is enabled
in the project.

The docker image can get built directly using mvn spring-boot:build-image ,
and I also added the task to the azure-pipelines.yaml

I won't be using Azure container registry like in the tutorial but instead I'll push
it directly to DockerHub. If I recall correctly I already did that in a project, so
I'll inspire myself there ( https://github.com/sebug/message-log/blob/master/azure-pipelines.yml ).

First I'll add a service connection to DockerHub. I'll also prepare the repository on
DockerHub.

I also adapted the pom to point to the repository I created. Now we can add the push
image task.

With the image pushed, I can close the circle and run the devops built image locally:

    docker pull sebug/apple-id-boot:628
    docker run -p 8080:8080 -d sebug/apple-id-boot:628

Now that that works, let's host it in Azure!

## Azure App Service
Initially I wanted to write this as a Bicep template, but I couldn't
get all the way there, so I configured it manually.

The main thing I had to do was set the WEBSITES_PORT to 8080 so that it knows where
the website is hosted. It is then accessible on https://appleidbootwa.azurewebsites.net/
which currently points us to the username/password login page. Now we can set up apple
sign in.

The big mystery was the redirect URI, so I chose https://appleidbootwa.azurewebsites.net/login/oauth2/code/apple to test. The domain is appleidbootwa.azurewebsites.net

I then went through the steps to create a key .p8 file. Now I have to know how to use it.

## Generate a client_secret
This one looks complicated. I have created a repository for the secret generation.

https://github.com/sebug/apple-secret-generator

