import System.Console.ANSI
import System.IO
import Control.Concurrent
import Control.Monad

reset :: IO ()
reset = clearScreen >> setSGR[Reset] >> setCursorPosition 0 0

pause :: IO ()
pause = do
	hFlush stdout
	threadDelay 33333

drawRow ::Int->Int->IO ()
drawRow 120 m = putStr " "
drawRow n m |n==m      = putStr "\\" >> drawRow (n+1) m
	    |otherwise = putStr " " >> drawRow (n+1) m

drawGrid ::Int->Int->IO ()
drawGrid n 0 = drawRow 0 n
drawGrid n m = drawRow 0 n >> putStrLn "" >> drawGrid (n+1) (m-1)

frame1 ::Int->Int->IO ()
frame1 n 300 = putStrLn ""
frame1 n m = do
	setSGR [SetColor Foreground Dull Green]
	setSGR [SetColor Background Dull Black]
	drawGrid n 80
	pause
	reset
	frame2 (mod (n+1) 120) (m+1)

frame2 ::Int->Int->IO ()
frame2 n 300 = putStrLn ""
frame2 n m = do 
	setSGR [SetColor Foreground Dull White]
	setSGR [SetColor Background Dull Black]
	drawGrid n 80
	pause
	reset
	frame3 (mod (n+1) 120) (m+1)

frame3 ::Int->Int->IO ()
frame3 n 300 = putStrLn ""
frame3 n m = do
	setSGR [SetColor Foreground Dull Cyan]
	setSGR [SetColor Background Dull Black]
	drawGrid n 80
	pause
	reset
	frame1 (mod (n+1) 120) (m+1)

animation :: IO ()
animation = frame1 0 0

main = do
	animation

