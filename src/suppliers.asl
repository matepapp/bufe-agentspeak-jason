// Agent suppliers in project Bufe.mas2j
/* Initial beliefs and rules */
/* Initial goals */
/* Plans */
+!supplement(Product,qty)[source(Ag)] : true
  <-  deliver(Product,qty);
     .send(Ag, tell, delivered(Product,qty)).
