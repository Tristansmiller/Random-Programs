parent(bill,bob).
male(bill).

mother(X,Y) :-
  parent(X,Y),
  female(X).

father(X,Y) :-
  parent(X,Y),
  male(X).

brother(X,Y) :-
  sibling(X,Y),
  male(X).

sister(X,Y) :-
  sibling(X,Y),
  female(X).

grandmother(X,Y) :-
  parent(Z,Y),
  parent(X,Z),
  female(X).

grandfather(X,Y) :-
  parent(Z,Y),
  parent(X,Z),
  male(X).

aunt(X,Y) :-
  parent(Z,Y),
  sibling(X,Z),
  female(X).

uncle(X,Y) :-
  parent(Z,Y),
  sibling(X,Z),
  male(X).

neice(X,Y) :-
  parent(Z,X),
  sibling(Y,Z),
  female(X).

nephew(X,Y) :-
  parent(Z,X),
  sibling(Y,Z),
  male(X).

cousin(X,Y) :-
  parent(Z,X),
  parent(W,Y),
  sibling(Z,W).
