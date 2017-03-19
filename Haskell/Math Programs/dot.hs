dot::[Float]->[Float]->Float
dot (x:[]) (y:[]) = x*y
dot (x:xs) (y:ys) = x*y + dot xs ys

main = do
       print(dot [1,2,3] [4,5,6])
