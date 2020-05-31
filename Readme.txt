Projet réalisé dans le cadre de notre cours Applications web orientées services. Afin de pouvoir utiliser et exécuter le code
veuillez suivre les intsructions suivantes.

Le projet est composé de différentes partie à savoir :

- Implémentation des microservices
- dockerisation des microservices
- deployement des microservices

1 - Implémentation
    Le code a été implémenté en Java spring boot. A minima il faudra avoir un IDE de préference IntelliJ.
    Après l'installation d'IntelliJ faire:

    - CLoné le projet depuis le dépôt git
        > git clone https://github.com/Dikela/Projet-MicroService.git
    - Après avoir cloné le projet, il faut l'importer dans git. Attention à l'import!
        - Lancer IntelliJ
        - >file >new > Project from Existing Source
        - Selectionner le reppertoire > puis Ok
        - Cliquez sur "Unmark All"
        - Importer les différents modules ( les microservices )
            - En haut à droite dans intelliJ cliquez sur l'icone "Project Structure"
            - Dans Project settings
                cliquez sur :
                > Project : spécifier votre JDK ( jdk 14 pour ce projet )
                - Sous "Modules", à gauche, sélectionnez le symbole "+" afin d'indiquer à IntelliJ où
                    se trouvent les modules. Chaque module correspond à un Microservice.
                - Importer chaque module comme projet maven (Sélectionnez "Maven")
                - > finish
                - configurer les modules : pour chaque module specifier les sources et les resources
                    - selectionner le repertoire 'Java' dans main et associer le à sources
                    - puis le repertoire 'resources' dans le main et associez le à resources
                    il faudra faire cette manipulation pour tout les modules importés

  Après avoir importer le projet, on peut maintenant l'executer

  lancer les microservices puis aller sur la 'http://localhost:8005/' afin d'acceder à la page d'acceuil de l'application
  gérer par le service client. Et amuser vous!

   Quelques manipulations de recherche seront exécutées dans postman. Seulement les fonction d'interconnexion des microservices
   sont mises en evidence dans le client.


2 - Dockerisation
    il faudra d'abord installer docker sur votre machine.
    Si vous êtes sur windows home, installer docker toolbox et les manipulation se feront dans Docker Quickstart Terminal.

    Acceder aux microservices faites
    - mvn install ( soit en ligne de commande pour chaque microservice afin de génerer les .jar)
    - Dans le ocker Quickstart Terminal
        - palcer vous dans chaque microservice et taper la commande suivante :
            - docker build -t fichier.jar .  > entrer( noublier pas le point '.' à la fin. Le fichier jar correspond par exemple pour le microservice
            client à 'client.jar')
            - puis : > docker run -p 9090:8002 client.jar  ( 9090 est le port du conteneur à préciser et a chosir. 8002 est le port de config dans le Dockerfile)
            -> lancer : http://IP:9090/[lien] ( IP correspond à l'adresse IP de votre docker. vous pouvez la récupérée dès le lancement de Docker Quickstart Terminal
            et puis amusez vous !!!

3 - Deployement

    a) Installer minikube sur votre machine. Prenez la version la plus récente et compatible pour votre os
    b) installer kubectl ( En fonction de votre os)
    c) Déployer chaque images docker générés en tapant les commandes
        Placer vous dans le repertoire du microservice à déployer: puis taper:
        * > kubectl create -f fichier.yaml (fichier.yaml correspond au fichier de configuraton du microservice)
        *verifier qu'il à bien été déployé en tapant : > kubectl get deployments
        * Verifier que le service est crée : > kubectl get services
            s'il n'est pas crée, tapez : $> kubectl expose deployment service-deployé --type=NodePort ( service-deployé correspond à l'application déployé)
        * lancer le service :
            $> minikube service client
        Après l'ececution de chaque service il faudra les supprivés (le deployement et le service)
            $> kubectl delete service client
            $> kubectl delete deployment client

