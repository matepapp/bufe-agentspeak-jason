// Agent bufe in project Bufe.mas2j
/* Initial beliefs and rules */
limit(product,10).

under_the_limit(P) :- limit(P,Limit)& Qty > Limit.
/* Initial goals */
/* Plans */
+!order(Type, Product)
	: not under_the_limit(Product)
	<- !at(bufe,storage);
	get(Product);
	!at(bufe,costumer);
	.send(costumer, achive, pay(amount)).
	
+!order(Type, Product)
	: under_the_limit(Product)
	<- .send(suppliers, achieve, supplement(Product,50));
	!has(order(Type,Product)).
	
-!order(_,_)
 :  true
   <- .current_intention(I); 
      .print("Failed to achieve goal '!has(_,_)'. Current intention is: ",I).
+!at(bufe,P) : at(bufe,P) <- true.
+!at(bufe,P) : not at(bufe, P)
	<-move(P);
	!at(bufe,P).
