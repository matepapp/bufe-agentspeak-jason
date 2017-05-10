// Agent bufe in project Bufe.mas2j
/* Initial beliefs and rules */
limit(product,10).

/* Initial goals */

!start.

/* Plans */

+!start : true <- .print("hello world.").
