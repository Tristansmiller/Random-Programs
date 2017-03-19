euler :: Float
euler = eulerAux 100

eulerAux :: Int -> Float
eulerAux 1 = 1
eulerAux n = 1/(factorial n) + eulerAux n-1 

factorial :: Int -> Float
factorial 1 = 1
factorial n = fromIntegral (n) * factorial (n-1)


main = do
       print(euler)
