median::[Int]->Int
median n |round(mod (length n) 2)==0         =let l=length(n) in (n!!(round(l/2)))+(n!!(round(l/2))+1)
	 |otherwise 		                        =let l=length(n) in (n!!(round(l/2)))
medianAux::[Int]->Int->Int
medianAux n l |mod l 2 == 0 = ((n!!round(l/2))+(n!!(round(l/2)+1)))/2
main=do
     print(median [1,3,5,7,9])
