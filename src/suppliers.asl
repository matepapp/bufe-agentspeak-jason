// Agent suppliers in project Bufe.mas2j
/* Initial beliefs and rules */
/* Initial goals */
/* Plans */
+!supplement(Product,qty)[source(bufe)] : true
  <-  deliver(Product,qty);
     .send(bufe, tell, delivered(Product,qty)).
