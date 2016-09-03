/* rules necessary for answering questions */

/* X = person Y=location T,T2= time 
This rule finds last location of a person */
isAt(X,Y) :- go(X, Y, T), \+ (go(X,_,T2), T2 > T).

/* This rule finds the last location of an object */
whereIs(Q,R) :- findall(R,(get(P,Q,I),go(P,R,_)),L), last(L,R),!.


