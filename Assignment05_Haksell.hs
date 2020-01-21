prods    ::  Int  -> [Int]              -- gen inf list of products
prods n = [n * x | x <- [n..]]

-- factorial n = if n >0 then n * factorial (n-1) else 1
-- [ 2*x | x <- [1, 3 ..]]


mix      :: [Int] -> [Int] -> [Int]        -- mix two inf lists

mix lst1 lst2 = 
    if head lst1 == head lst2 then
        head lst1 : mix (tail lst1) (tail lst2)
    else
        if head lst1 < head lst2 then
            head lst1 : mix (tail lst1) lst2
        else
            head lst2 : mix (tail lst2) lst1

    
sieve    :: [Int] -> [Int] -> [Int]     -- sieve of eratosthenes

sieve lst1 lst2 =
    if lst2 == [] then
        head lst1 : (sieve (tail lst1) (prods (head lst1)))

    else
        if lst1 == [] then
            []
        else
            if ((head lst1) /= (head lst2)) then
                head lst1 : (sieve (tail lst1) (mix (lst2) (prods (head lst1))))
            else
                sieve (tail lst1) (tail(lst2))
    

firstn   ::  Int  -> [Int]              -- returns first n primes

firstn n = 
    take n (sieve [2..] [])

primesto ::  Int  -> [Int]              -- returns primes up to p

primesto p = 
    sieve [2..p][]

mergeSort ::  [Int] -> [Int]            -- returns a list that has been sorted in a merging way

mergeSort lst = 
    if (length lst) > 1 then
        slightlyDifferentMixFunction 
            (mergeSort (take (div (length lst) 2) lst))
            (mergeSort ([ lst!!x | x <- [(div (length lst) 2) .. ((length lst) - 1)]]))
    else
        lst

slightlyDifferentMixFunction :: [Int] -> [Int] -> [Int]

slightlyDifferentMixFunction lst1 lst2 = 
    if lst1 == [] then
        lst2
        else
            if lst2 == [] then
                lst1
                else
                    if head lst1 < head lst2 then
                        head lst1 : slightlyDifferentMixFunction (tail lst1) lst2
                    else
                        head lst2 : slightlyDifferentMixFunction (tail lst2) lst1