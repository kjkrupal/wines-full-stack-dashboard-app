# Useful commands

### Build the search server container.
```
$ docker build -t kubeio-search-server .
```

### Run the search server container on common network.
```
$ docker run --network="fullstackapp_kubeio-network" -p 8080:8080 kubeio-search-server
```
