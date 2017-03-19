lrotate::[a]->Int->[a]
lrotate a 0      = a 
lrotate (x:xs) n =let list = xs++[x] in lrotate list (n-1)

main = do
	print(lrotate [1,2,3,4] 2)

