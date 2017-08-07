rm(list = ls()) # Remove all the objects we created so far.
library(igraph) # Load the igraph package

nodes <- read.csv("second_nodes_table.csv", header=T, as.is=T) # Load nodes table
links <- read.csv("second_edges_table.csv", header=T, as.is=T) # Load edges table

head(nodes) # Checking the first nodes (on the console)
head(links) # Checking the first edges (on the console)

nrow(nodes) # Total number of nodes
length(unique(nodes$id)) # Total number of UNIQUE nodes (checked by repeated id)
nrow(links) # Total number of edges
nrow(unique(links[,c("from", "to")])) # Total number of UNIQUE edges

links <- aggregate(links[,3], links[,-3], sum) # Aggregating all the repeated edges, summing weights also
links <- links[order(links$from, links$to),] # Ordering edge table
colnames(links)[3] <- "weight"
rownames(links) <- NULL

links # Visualization

net <- graph_from_data_frame(d=links, vertices=nodes, directed=T)
class(net) # Create a graph with all the clean data

search_word <- function() # Find the number id behing a node name
{ 
  word <- readline(prompt="Write the first word: ")
  n<-1
  while(V(net)[n]$name!=word && n<nrow(nodes))
    n=n+1
  
  return(as.integer(n))
}

i<-nrow(nodes)
while(i==nrow(nodes))
  i<-search_word()

nwords <- readline(prompt="Write the number of words ")

for(j in 1:nwords){ # Create a phrase with (nwords) words
  neigh.nodes <- neighbors(net, V(net)[i], mode="out") # all the neighbor nodes
  higher_weight <- 0
  higher_index <- 1
  
  for(k in 1:length(neigh.nodes)){ # Compare lenght of each edge formed by a node and its neighbors
    anode <- V(net)[i]$name # Actual node
    nnode <- neigh.nodes[k]$name # Neighbor node
    if(higher_weight<E(net, P=c(anode,nnode))$weight){
      higher_weight=E(net, P=c(anode,nnode))$weight
      higher_index=k
    }
  }
  message(" ", neigh.nodes[higher_index]$name)
  i <-1
  while(V(net)[i]$name!=neigh.nodes[higher_index]$name)
    i=i+1
}


