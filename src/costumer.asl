// Agent costumer in project Bufe.mas2j

/* Initial beliefs and rules */

/* Initial goals */
!select(Type,Product).

+!select(Type,Product) : true
	<- .send(bufe,achieve,order(Type,Product)).

+order(Type,Product) : true
	<- !drink_or_eat(Product).
-order(Type,Product) : true
	<- !select(Type,Product).

// while I have product, consume
+!drink_or_eat(Product) : order(Type,Product)
	<- consume(Product);
	!drink_or_eat(Product).
+!drink_or_eat(Product) : not order(Type,Product)
	<- true.

+!pay(Amount)[source(bufe)] : true
	<-paying(Amount);
	.send(bufe, tell, payed(Amount)).

-!pay(_)
   :  true
   <- .current_intention(I);
      .print("Failed to achieve goal '!pay(_)'. Current intention is: ",I).

+gave_prod(Product)[source(bufe)]
	: true
	<- !drink_or_eat(Product).
