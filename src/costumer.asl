// Agent costumer in project Bufe.mas2j

/* Initial beliefs and rules */

/* Initial goals */
!select(type,product).

+!select(type,Product) : true
	<- .send(bufe,achieve,order(type, Product)).

+order(type,Product) : true
	<- !drink_or_eat(Product).
-order(type, Product) : true
	<- !select(Product).
	
//while I have product, consume
+!drink_or_eat(Product) : order(type,Product)
	<- consume(Product);
	!drink_or_eat(Product).
+!drink_or_eat(Product) : not order(type, Product)
	<- true.

+!pay(amount)[source(Ag)] : true
	<-pay(amount);
	.send(Ag, tell, payed(amount)).

+gave_prod(Product)[source(bufe)]
	: true
	<- !drink_or_eat(Product).
