// Agent costumer in project Bufe.mas2j

/* Initial beliefs and rules */

/* Initial goals */
!select(Product).

+!select(Product) : true
	<- .send(bufe,achieve,order(type, product)).

+order(Type,Product) : true
	<- !drink_or_eat(Product).
-order(Type, Product) : true
	<- !select(type,Product).

// while I have product, consume
+!drink_or_eat(Product) : order(Type,Product)
	<- consume(Product);
	!drink_or_eat(Product).
+!drink_or_eat(Product) : not order(Type, Product)
	<- true.

+!pay(Amount)[source(Ag)] : true
	<-pay(Amount);
	.send(Ag, tell, payed(Amount)).

+gave_prod(Product)[source(bufe)]
	: true
	<- !drink_or_eat(Product).
