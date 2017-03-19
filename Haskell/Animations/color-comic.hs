import System.Console.ANSI
import System.IO
import Control.Concurrent
import Control.Monad

reset :: IO ()
reset = clearScreen >> setSGR[Reset] >> setCursorPosition 0 0

pause :: IO ()
pause = do
	hFlush stdout
	threadDelay 1000000

frame1 ::IO ()
frame1 = do
	setSGR [SetColor Foreground Dull Blue]
	setSGR [SetColor Background Dull White]
	putStrLn "xx                         xx"
	putStrLn " xx                       xx "
	putStrLn "   xx                   xx   "
	putStrLn "     xx               xx     "
	putStrLn "       xx           xx       "
	putStrLn "         xx       xx         "
	putStrLn "           xx   xx           "
	putStrLn "             xxx             "
	putStrLn "            xx xx            "
	putStrLn "          xx     xx          "
	putStrLn "        xx         xx        "
	putStrLn "      xx             xx      "
	putStrLn "    xx                 xx    "
	putStrLn "  xx                     xx  "
	putStrLn "xx                         xx"
	pause
	reset
	frame2

frame2 ::IO ()
frame2 = do 
	setSGR [SetColor Foreground Dull Red]
	setSGR [SetColor Background Dull White]
	putStrLn "     ooooooooooooooooooo     "
	putStrLn "    ooooooooooooooooooooo    "
	putStrLn "   oo                   oo   "
	putStrLn "  oo                     oo  "
	putStrLn " oo                       oo "
	putStrLn "oo                         oo"
	putStrLn "oo                         oo"
	putStrLn "oo                         oo"
	putStrLn "oo                         oo"
	putStrLn "oo                         oo"
	putStrLn " oo                       oo "
	putStrLn "  oo                     oo  "
	putStrLn "   oo                   oo   "
	putStrLn "    ooooooooooooooooooooo    "
	putStrLn "     ooooooooooooooooooo     "
	pause
	reset
	frame1

animation :: IO ()
animation = frame1

main = do
	animation

