// Agent bufe in project Bufe.mas2j
/* Initial beliefs and rules */
limit(Product,10).

under_the_limit(P) :- limit(P,Limit)& Qty < Limit.
/* Initial goals */
/* Plans */
+!order(Type,Product)
	: not under_the_limit(Product)
	<- 	!at(bufe,storage);
		get(Product);
		!at(bufe,costumer);
		.send(costumer, achieve, pay(Amount)).

+!order(Type,Product)
	: under_the_limit(Product)
	<- .send(suppliers, achieve, supplement(Product,50));
	!order(Type,Product).

-!order(_,_)
 :  true
   <- .current_intention(I);
      .print("Failed to achieve goal '!order(_,_)'. Current intention is: ",I).

+!at(bufe,P) : at(bufe,P) <- true.
+!at(bufe,P) : not at(bufe, P)
	<- move(P);
	!at(bufe,P).

+delivered(Product, _Qty)[source(suppliers)]
	: true
	<- !order(Type,Product).

+payed(Amount)[source(costumer)]
	:true
	<- give_prod(Product);
	.send(costumer,tell, gave_prod(Product)).
