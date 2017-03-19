import System.Console.ANSI
import System.IO
import Control.Concurrent
import Control.Monad
import Map
reset::IO ()
reset = clearScreen>>setCursorPosition 0 0

takeInput::Map->IO ()
takeInput m = do
	      putStrLn "Input a command:"
	      input<-getLine
	      let newMap = Map.move input m
	      Map.drawMap newMap
	      takeInput newMap

main = do
	takeInput (Map 0 0)


