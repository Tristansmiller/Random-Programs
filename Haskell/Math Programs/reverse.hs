reverse::Int->Int
reverse n = let list=Prelude.reverse(toList(n)) in foldl combine 0 list
	  	               where combine num x =10*num+x

toList::Int->[Int]
toList 0 = []
toList n = toList (div n 10) ++ [mod n 10]

main=do
     print(Main.reverse 1873)

