
lrotate(P,0,Q) :-
  Q = P.
lrotate(P,N,Q):-
  N2 is N-1,
  [Head|Tail] = P,
  append(Tail,[Head],P2),
  lrotate(P2,N2,Q).

rrotate(P,N,Q):-
  length(P,L),
  N2 is L-mod(N,L),
  lrotate(P,N2,Q).
