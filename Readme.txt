Projet réalisé dans le cadre de notre cours Applications web orientées services. Afin de pouvoir utiliser et exécuter le code
veuillez suivre les intsructions suivantes.

Le projet est composé de différentes partie à savoir :

- Implémentation des microservices
- dockerisation des microservices
- deployement des microservices

1 - Implémentation
    Le code a été implémenté en Java spring boot. A minima il faudra avoir un IDE de préference IntelliJ.
    Après l'installation d'IntelliJ faire:

    a - CLoné le projet depuis le dépôt git
        > git clone https://github.com/Dikela/Projet-MicroService.git
    b - Après avoir cloné le projet, il faut l'importer dans git. Attention à l'import!
        1 - Lancer IntelliJ
        2 - >file >new > Project from Existing Source
        3 - Selectionner le reppertoire > puis Ok
        4 - Cliquez sur "Unmark All"
        5 - Importer les différents modules ( les microservices )
            a - En haut à droite dans intelliJ cliquez sur l'icone "Project Structure"
            b - Dans Project settings
                cliquez sur :
                > Project : spécifier votre JDK ( jdk 14 pour ce projet )
                1 - Sous "Modules", à gauche, sélectionnez le symbole "+" afin d'indiquer à IntelliJ où
                    se trouvent les modules. Chaque module correspond à un Microservice.
                2 - Importer chaque module comme projet maven (Sélectionnez "Maven")
                3 - > finish
                4 - configurer les modules : pour chaque module specifier les sources et les resources
                    a - selectionner le repertoire 'Java' dans main et associer le à sources
                    b - puis le repertoire 'resources' dans le main et associez le à resources
                    il faudra faire cette manipulation pour tous les modules importés
2 - Exécution du projet
  Après avoir importer le projet, on peut maintenant l'executer

  L'accès aux autres services de notre se fait via notre service client qui represente notre frontend. Pour cela :
   - Exécuter le service en lançant la classe main du client. (On peut faire clique droit sur la classe main puis > run "ClientApplication")
   - ouvrer votre navigateur et copier coller 'http://localhost:8005/' afin d'acceder à la page d'acceuil de l'application.
   Afin de consommer les différents services, il va falloir les lancés également dans intelliJ en faisant clique droit droit
   sur leur classe main puis > run "...".

   Quelques manipulations de recherche seront exécutées dans postman. Seulement les fonction d'interconnexion des microservices
   sont mises en evidence dans le client.


2 - Dockerisation
    il faudra d'abord installer docker sur votre machine.
    Si vous êtes sur windows home, installer docker toolbox et les manipulation se feront dans Docker Quickstart Terminal.

    Acceder aux microservices, puis faites:
    - mvn install ( soit en ligne de commande pour chaque microservice afin de génerer les .jar) pour chaque microservice
    - Lancer e Docker Quickstart Terminal. Après le demarrage copier l'adresse Ip quelques part.
    - Dans le Docker Quickstart Terminal
        - palcer vous dans chaque microservice et taper la commande suivante :
            - docker build -t fichier.jar .  > entrer( noublier pas le point '.' à la fin. Le fichier jar correspond par exemple pour le microservice
            client à 'client.jar'). Cela permet de créer l'image du service dans docker
            - puis : > docker run -p 9090:8002 client.jar  ( 9090 est le port du conteneur à préciser et a chosir. 8002 est le port de config dans le Dockerfile),
            pour lancer le microservice
            -> Ouvrez votre navigateur et lancer : http://IP:9090/[lien REST] ( IP correspond à l'adresse IP de votre docker. vous pouvez la récupérée dès le lancement de Docker Quickstart Terminal
            et puis amusez vous !!!

3 - Deployement
    Tout d'abord técharger la version de minikube correspondant à votre os, puis:

    a) Installer minikube sur votre machine. Prenez la version la plus récente et compatible pour votre os. Avant cela assurer vous que vous avez une machine virtuelle installée
        sur votre ordinateur (dans notre cas on a utilisé virtualbox).
    b) installer kubectl ( En fonction de votre os)
    c) Déployer chaque images docker générés en tapant les commandes suivantes:

        Placer vous dans le repertoire du microservice à déployer: puis taper:

        1 * > kubectl create -f fichier.yaml

            (fichier.yaml correspond au fichier de configuraton du microservice)
        2 *verifier qu'il à bien été déployé en tapant :

            > kubectl get deployments

            et verifié aussi le pod en tapant

            > kubectl get pods

        3 * Verifier que le service est crée :

        > kubectl get services

            si le service n'est pas crée, tapez :

            $> kubectl expose deployment service-deployé --type=NodePort

            ( service-deployé correspond à l'application déployé)

        4 * lancer le service : prenons par exemple notre service client

            $> minikube service client

        Après l'ececution de chaque service il faudra les supprivés (le deployement et le service)

            $> kubectl delete service client
            $> kubectl delete deployment client

