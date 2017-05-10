// Agent costumer in project Bufe.mas2j

/* Initial beliefs and rules */

/* Initial goals */
!select(product,N).

+order(costumer,Product) : true
	<- !drink_or_eat(Product).
-order(costumer, Product) : true
	<- !select(Product).
	
//while I have product, consume
+!drink_or_eat(Prduct) : order(constumer,Product)
	<- consume(Product);
	!drink_or_eat(Product).
+!drink_or_eat(Product) : not order(constumer, Product)
	<- true.

+!pay(amount)[source(Ag)] : true
	<-pay(amount);
	.send(Ag, tell, payed(amount)).


