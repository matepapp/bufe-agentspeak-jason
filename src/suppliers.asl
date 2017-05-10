// Agent suppliers in project Bufe.mas2j
/* Initial beliefs and rules */
/* Initial goals */
/* Plans */
+!supplement(Product,Qty)[source(bufe)] : true
  <-  deliver(Product,Qty);
     .send(bufe, tell, delivered(Product,Qty)).
