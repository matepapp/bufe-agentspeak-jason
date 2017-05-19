// Agent suppliers in project Bufe.mas2j
/* Initial beliefs and rules */
/* Initial goals */
/* Plans */
+!supplement(Product,Qty)[source(Ag)] : true
  <-  deliver(Product,Qty);
     .send(Ag, tell, delivered(Product,Qty)).
