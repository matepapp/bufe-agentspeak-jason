// Agent costumer in project Bufe.mas2j

/* Initial beliefs and rules */

/* Initial goals */
!select(Product).

+!select(Product) : true
	<- .send(bufe,achieve,order(Type, Product)).

+order(Type,Product) : true
	<- !drink_or_eat(Product).
-order(Type, Product) : true
	<- !select(Type,Product).

// while I have product, consume
+!drink_or_eat(Product) : order(Type,Product)
	<- consume(Product);
	!drink_or_eat(Product).
+!drink_or_eat(Product) : not order(Type, Product)
	<- true.

+!pay(Amount)[source(Ag)] : true
	<-paying(Amount);
	.send(Ag, tell, payed(Amount)).

-!pay(_)
   :  true
   <- .current_intention(I);
      .print("Failed to achieve goal '!pay(_)'. Current intention is: ",I).


+gave_prod(Product)[source(Ag)]
	: true
	<- !drink_or_eat(Product).
